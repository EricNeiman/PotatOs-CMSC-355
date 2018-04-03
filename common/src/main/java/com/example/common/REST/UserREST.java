package com.example.common.REST;

import com.example.common.User;
import com.google.gson.Gson;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by vita on 4/3/18.
 */

public class UserREST {

    //provided api functions
    public static final String CREATE_USER = "createUser";
    //full path that you request from
    public static final String CREATE_USER_FULL_PATH = PotatOsApi.API_PATH + CREATE_USER;

    //returns the copy of the user as interpreted by the server
    public User createUpdate(User input) {
        Client client = ClientBuilder.newClient();

        URI url = null;
        try {
            url = new URI(CREATE_USER_FULL_PATH);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        WebTarget target = client.target(url);
        Response rs = target.request(MediaType.APPLICATION_JSON_TYPE).post(
                Entity.entity(
                        new Gson().toJson(input),
                        MediaType.APPLICATION_JSON
                )
        );

        if (rs.getStatus() == Response.Status.OK.getStatusCode()) {
            String json = rs.readEntity(String.class);
            Gson gson = new Gson();


            return gson.fromJson(json, User.class);
        } else {
            System.out.println("Response was not ok from the server on User create.");
            return null;
        }
    }

    public User getById(int userID) {
        return null;
    }

    public boolean delete(User input) {
        return false;
    }
}
