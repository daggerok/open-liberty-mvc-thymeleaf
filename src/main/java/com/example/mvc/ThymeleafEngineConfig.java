package com.example.mvc;

import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.krazo.engine.ViewEngineBase;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.mvc.engine.ViewEngineContext;
import javax.mvc.engine.ViewEngineException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Singleton
public class ThymeleafEngineConfig extends ViewEngineBase {

    @Inject
    private ServletContext servletContext;

    private TemplateEngine templateEngine;

    @PostConstruct
    public void configure() {
        var templateResolver = new ServletContextTemplateResolver(servletContext);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setCacheable(false);
        templateResolver.setSuffix(".html");
        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
    }

    @Override
    public boolean supports(String view) {
        return !view.contains(".");
    }

    @Override
    public void processView(ViewEngineContext context) throws ViewEngineException {
        var request = context.getRequest(HttpServletRequest.class);
        var response = context.getResponse(HttpServletResponse.class);
        var webContext = new WebContext(request, response, servletContext, request.getLocale());
        webContext.setVariables(context.getModels().asMap());
        request.setAttribute("view", context.getView());
        Try.run(() -> templateEngine.process(/*layout*/ context.getView(), webContext, response.getWriter()))
           .onFailure(e -> log.error("\n\n\n" + e.getLocalizedMessage() + "\n"));
    }
}
