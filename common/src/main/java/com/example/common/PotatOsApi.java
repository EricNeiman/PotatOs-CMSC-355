package com.example.common;

import com.google.gson.Gson;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class PotatOsApi {

    public static final String API_DIR = "api";

    //droplet ip
    public static final String API_IP = "104.131.117.72";
    public static final String API_PORT = "8080";

    //provided api functions
    public static final String CREATE_USER = "createUser";
    public static final String HEARTBEAT = "heartbeat";
    public static final String API_PATH = "http://" + API_IP + ":" + API_PORT + "/" + API_DIR + "/";

    //full path that you request from
    public static final String CREATE_USER_FULL_PATH = API_PATH + CREATE_USER;


    //sends a request to create the user to the REST server.
    //TODO: return status?
    public void createUser(User user) {
        Client client = ClientBuilder.newClient();

        URI url = null;
        try {
            url = new URI(PotatOsApi.CREATE_USER_FULL_PATH);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        WebTarget target = client.target(url);
        target.request(MediaType.APPLICATION_JSON_TYPE).post(
                Entity.entity(
                    new Gson().toJson(user),
                    MediaType.APPLICATION_JSON
                )
        );
    }
}
