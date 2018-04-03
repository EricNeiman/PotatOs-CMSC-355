package com.example.server.DatabaseHelper;

import com.example.common.REST.UserREST;
import com.example.common.User;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by vita on 4/3/18.
 */

public class UserTable {
    public static final String CREATE_SQL = "CREATE TABLE Users (UserID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, passwordHash TEXT, email TEXT, name TEXT);"


    @POST
    @Path(UserREST.GET_USER_BY_ID)
    @Consumes(MediaType.APPLICATION_JSON)
    public User getUserByID(int id) {

    }

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


}
