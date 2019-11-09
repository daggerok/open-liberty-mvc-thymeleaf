package com.example.mvc;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.Priority;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import static java.lang.String.format;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

/**
 * https://daggerok.github.io/thymeleaf-ee/
 */
@Slf4j
@Path("")
@Priority(2)
@RequestScoped
public class StaticResourcesResource {

    @Inject
    ServletContext context;

    /**
     * Serving static files form folders:
     * <p>
     * /WEB-INF/resources
     * /WEB-INF/static
     * /WEB-INF/public
     * /WEB-INF/assets
     */

    @GET
    @Path("{path: ^(assets|public|static|resources).*}")
    public Response staticResources(@PathParam("path") final String path) {
        log.debug("handling assets: {}", path);
        var resource = context.getResourceAsStream(format("/WEB-INF/%s", path));
        return null == resource
                ? Response.status(NOT_FOUND).build()
                : Response.ok().entity(resource).build();
    }
}
