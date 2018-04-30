package com.example.server;

import com.example.common.REST.PotatOsApi;
import com.example.server.DatabaseHelper.PotatOsDatabase;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(PotatOsApi.API_DIR)
public class RESTApi {

    //just a function for testing that the server is up.  Url to test will be printed on startup.
    @GET
    @Path(PotatOsApi.HEARTBEAT)
    public Response heartbeat() {
        System.out.println("Heartbeat requested, sending ok.");
        return Response.status(Response.Status.OK)
                .entity("server is up")
                .build();
    }

    @GET
    @Path(PotatOsApi.RESET_DATABASE)
    public Response resetDatabase() {
        PotatOsDatabase.deleteTables();
        try {
            System.out.println("=============================");
            System.out.println("****Resetting database****");
            System.out.println("=============================");
            PotatOsDatabase.createTables();
            return Response.status(Response.Status.OK)
                    .entity("database reset")
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
