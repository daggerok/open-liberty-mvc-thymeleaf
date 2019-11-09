// package com.example.mvc;
//
// import lombok.extern.slf4j.Slf4j;
//
// import javax.annotation.Priority;
// import javax.enterprise.context.RequestScoped;
// import javax.inject.Inject;
// import javax.servlet.ServletContext;
// import javax.ws.rs.GET;
// import javax.ws.rs.Path;
// import javax.ws.rs.PathParam;
// import javax.ws.rs.core.Response;
// import java.util.Objects;
//
// import static java.lang.String.format;
// import static javax.ws.rs.core.Response.Status.NOT_FOUND;
//
// /**
//  * https://daggerok.github.io/thymeleaf-ee/
//  */
// @Slf4j
// @Path("")
// @Priority(1)
// @RequestScoped
// public class WebjarsResource {
//
//     @Inject
//     ServletContext context;
//
//     /**
//      * Serving webjar dependencies
//      *
//      * see: https://www.webjars.org
//      */
//
//     @GET
//     @Path("{path: ^webjars\\/.*}")
//     public Response webjars(@PathParam("path") final String path) {
//         log.debug("handling webjars: {}", path);
//         var absolutePath = format("/META-INF/resources/%s", path);
//         var resource = getClass().getClassLoader().getResourceAsStream(absolutePath);
//         return Objects.isNull(resource)
//                 ? Response.status(NOT_FOUND).build()
//                 : Response.ok().entity(resource).build();
//     }
// }
