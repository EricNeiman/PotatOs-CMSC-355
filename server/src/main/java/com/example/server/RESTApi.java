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
    @POST
    @Path(UserREST.CREATE_USER)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(String message) {
        System.out.println("Creating a user..." + message);

        Gson gson = new Gson();
        Response rs;
        try {
            User user = gson.fromJson(message, User.class);

            //TODO: add to database
            System.out.println("User created ... " + user.getName());
            rs = Response.status(Response.Status.OK).build();
        } catch (JsonSyntaxException ex) {
            System.out.println("Invalid json received.");
            rs = Response.status(Response.Status.BAD_REQUEST).build();
        }
        return rs;
    }


    @POST
    @Path(PotatOsApi.ADD_USER_TO_CLASS)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addUserToClass(@PathParam("user") int userID, @PathParam("class") int classID) {
        Response rs;

        if (userID >=0 && classID >=0) {
            rs = Response.status(Response.Status.OK).entity("added").build();
        } else {
            rs = Response.status(Response.Status.BAD_REQUEST).entity("invalid params").build();
        }

        return rs;
    }


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
