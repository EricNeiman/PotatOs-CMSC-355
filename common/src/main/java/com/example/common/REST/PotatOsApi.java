package com.example.common.REST;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import java.net.URI;
import java.net.URISyntaxException;


import javax.ws.rs.core.Response;

public class PotatOsApi {

    public static final String API_DIR = "api";

    //droplet ip
    public static final String API_IP = "104.131.117.72";
    public static final String API_PORT = "8080";

    public static final String HEARTBEAT = "heartbeat";
    public static final String API_PATH = "http://" + API_IP + ":" + API_PORT + "/" + API_DIR + "/";
    public static final String RESET_DATABASE = "resetDatabase";

    //posts to the url subPath (what appears after "api/" with parameterJson in the body, and
    //returns the body of what the server returns
    public static String postJson(String subPath, String parameterJson, boolean post) {
        Client client = Client.create();

        URI url = null;
        try {
            url = new URI(API_PATH + subPath);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }

        WebResource target = client.resource(url);
        ClientResponse rs = null;
        if (post) {
            rs = target
                    .type("application/json")
                    .post(ClientResponse.class, parameterJson);
        } else {
            rs = target
                    .type("application/json")
                    .get(ClientResponse.class);
        }

        System.out.println("Made a request to the server: " + url + " response: " + rs.getStatus());
        if (rs.getStatus() == Response.Status.OK.getStatusCode()) {
            return rs.getEntity(String.class);
        } else {
            System.out.println("Response was not ok from the server on REST call to " + subPath);
            return null;
        }
    }

    public static boolean isServerUp() {
        String message = postJson(HEARTBEAT, false);
        if (message != null) {
            return true;
        } else {
            return false;
        }
    }

    public static void resetDatabase() {
        String message = postJson(RESET_DATABASE, false);
    }

    public static String postJson(String subPath, boolean post) {
        return postJson(subPath, "", post);
    }
}
