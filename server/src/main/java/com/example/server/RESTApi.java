package com.example.server;

import com.example.common.REST.PotatOsApi;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
