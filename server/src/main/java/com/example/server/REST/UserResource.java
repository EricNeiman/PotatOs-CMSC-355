package com.example.server.REST;

import com.example.common.REST.ClassUserPassRequest;
import com.example.common.REST.UserREST;
import com.example.common.User;
import com.example.server.DatabaseHelper.UserTable;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class UserResource {
    @POST
    @Path(UserREST.CREATE_USER)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(String message) {
        System.out.println("Creating a user..." + message);
        Gson gson = new Gson();
        Response rs;
        try {
            UserTable.createUser(gson.fromJson(message, User.class));
            rs = Response.status(Response.Status.OK).build();
        } catch (JsonSyntaxException ex) {
            System.out.println("Invalid json received.");
            rs = Response.status(Response.Status.BAD_REQUEST).build();
        } catch (SQLException ex) {
            System.out.println("SQL Error.");
            rs = Response.status(Response.Status.BAD_REQUEST).build();
        }
        return rs;  //todo should return the created user?
    }

    @POST
    @Path(UserREST.GET_USER_BY_EMAIL_PASS)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getUserByEmailPass(String message) {
        Gson gson = new Gson();
        ClassUserPassRequest form = gson.fromJson(message, ClassUserPassRequest.class);

        try {
            User user = UserTable.getUserByEmailPass(form.email, form.passwordHash);
            return Response.status(Response.Status.OK)
                    .entity(
                            gson.toJson(user)
                    ).build();
        } catch (SQLException ex) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path(UserREST.GET_USER_BY_ID + "/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public static Response getById(@PathParam("id") int userID) {
        try {
            Gson gson = new Gson();
            User user = UserTable.getUserById(userID);
            return Response.status(Response.Status.OK)
                    .entity(
                            gson.toJson(user)
                    ).build();
        } catch (SQLException ex){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path(UserREST.UPDATE_USER)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id") int id, String message) {
        Gson gson = new Gson();
        try {
            UserTable.updateUser(gson.fromJson(message, User.class));
            return Response.status(Response.Status.OK).build();
        } catch (SQLException ex) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
