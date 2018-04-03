package com.example.server;

import com.example.common.REST.PotatOsApi;
import com.example.common.REST.UserREST;
import com.example.common.User;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(PotatOsApi.API_DIR)
public class RESTApi {

    //just a function for testing that the server is up.  Url to test will be printed on startup.
    @GET
    @Path(PotatOsApi.HEARTBEAT)
    @Produces(MediaType.TEXT_PLAIN)
    public Response heartbeat() {
        System.out.println("Heartbeat requested, sending ok.");
        return Response.status(Response.Status.OK)
                .entity("server is up")
                .build();
    }
}
