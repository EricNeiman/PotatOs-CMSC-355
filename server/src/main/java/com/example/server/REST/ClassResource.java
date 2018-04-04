package com.example.server.REST;

import com.example.common.REST.ClassREST;
import com.example.server.DatabaseHelper.ClassTable;
import com.google.gson.Gson;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.example.common.Class;

import java.sql.SQLException;


public class ClassResource {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path(ClassREST.CREATE_CLASS)
    public Response createClass(String message) {
        Response rs;
        try {
            Gson gson = new Gson();
            Class cls = gson.fromJson(message, Class.class);
            ClassTable.createClass(cls);

            rs = Response.status(Response.Status.OK)
                    .entity(
                            gson.toJson(cls)
                    )
                    .build();
        } catch (SQLException e) {
            rs = Response.status(Response.Status.BAD_REQUEST).build();
        }
        return rs;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(ClassREST.GET_CLASSES_FOR_USER_ID + "/{id}")
    public Response getClassesForUserId(@PathParam("id") int id) {
        Gson gson = new Gson();

        Response rs = Response.status(Response.Status.OK)
                .entity(
                        gson.toJson(ClassTable.getClassesForUserId(id))
                )
                .build();

        return rs;
    }
}
