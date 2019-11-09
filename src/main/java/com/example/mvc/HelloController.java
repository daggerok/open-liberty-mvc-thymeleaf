package com.example.mvc;

import javax.inject.Inject;
import javax.mvc.Controller;
import javax.mvc.Models;
import javax.mvc.View;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("")
@Controller
public class HelloController {

    @Inject
    private Models models;

    @GET
    @View("hello")
    @Produces(MediaType.TEXT_HTML)
    public void hello() {
        models.put("name", "World");
    }

    @GET
    @Path("world")
    @Produces(MediaType.TEXT_HTML)
    public String wold() {
        models.put("name", "Hello");
        return "hello";
    }

    @GET
    @Path("{name}")
    @Produces(MediaType.TEXT_HTML)
    public String wold(@PathParam("name") String name) {
        models.put("name", name);
        return "hello";
    }
}
