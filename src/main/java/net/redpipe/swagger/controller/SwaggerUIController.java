package net.redpipe.swagger.controller;


import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/swagger")
public class SwaggerUIController {

    @GET
    public String displaySwaggerUI() {
        return "Should be swagger UI";
    }



}
