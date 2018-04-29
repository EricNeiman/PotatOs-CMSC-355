package com.example.server.REST;

import com.example.common.REST.ClassUserPassRequest;
import com.example.common.REST.PotatOsApi;
import com.example.common.REST.SmallUser;
import com.example.common.REST.UserREST;
import com.example.common.User;
import com.example.server.DatabaseHelper.EnrollmentsTable;
import com.example.server.DatabaseHelper.UserTable;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.sql.SQLException;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path(PotatOsApi.API_DIR)
public class UserResource {
    //returns the ID of the created user.  It is the only difference that needs to be returned.
    @POST
    @Path(UserREST.CREATE_USER)
    public Response createUser(String message) {
        System.out.println("Creating a user..." + message);
        Gson gson = new Gson();
        Response rs;
        try {
            User user = gson.fromJson(message, User.class);
            UserTable.createUser(user);

            rs = Response.status(Response.Status.OK).entity(user.getId()).build();
        } catch (JsonSyntaxException ex) {
            System.out.println("Invalid json received.");
            rs = Response.status(Response.Status.BAD_REQUEST).build();
        } catch (SQLException ex) {
            System.out.println("SQL Error.");
            rs = Response.status(Response.Status.BAD_REQUEST).build();
        }
        return rs;
    }

    @POST
    @Path(UserREST.GET_USER_BY_EMAIL_PASS)
    public Response getUserByEmailPass(String message) {
        System.out.println("Got a login request: " + message);
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
    @Path(UserREST.GET_USER_BY_ID)
    public static Response getById(String message) {
        try {
            Gson gson = new Gson();
            int id = gson.fromJson(message, int.class);
            User user = UserTable.getUserById(id);
            return Response.status(Response.Status.OK)
                    .entity(
                            gson.toJson(user)
                    ).build();
        } catch (SQLException ex){
            ex.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path(UserREST.UPDATE_USER)
    public Response updateUser(String message) {
        Gson gson = new Gson();
        try {
            SmallUser user = gson.fromJson(message, SmallUser.class);
            UserTable.updateUser(user);

            for (int i: user.getClassesIn()) {
                EnrollmentsTable.enrollUserInClass(user, i);
            }

            return Response.status(Response.Status.OK).build();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path(UserREST.DELETE_USER)
    public Response deleteUser(String message) {
        Gson gson = new Gson();
        int id = gson.fromJson(message, int.class);

        try {
            UserTable.deleteUserById(id);
            return Response.status(Response.Status.OK).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
