package com.example.common.REST;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.ServerErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class PotatOsApi {

    public static final String API_DIR = "api";

    //droplet ip
    public static final String API_IP = "104.131.117.72";
    public static final String API_PORT = "8080";

    public static final String HEARTBEAT = "heartbeat";
    public static final String API_PATH = "http://" + API_IP + ":" + API_PORT + "/" + API_DIR + "/";

    //posts to the url subPath (what appears after "api/" with parameterJson in the body, and
    //returns the body of what the server returns
    public static String postJson(String subPath, String parameterJson) {
        Client client = ClientBuilder.newClient();

        URI url = null;
        try {
            url = new URI(API_PATH + subPath);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }

        WebTarget target = client.target(url);

        Response rs = target.request(MediaType.APPLICATION_JSON_TYPE).post(
                Entity.entity(
                        parameterJson,
                        MediaType.APPLICATION_JSON
                )
        );

        if (rs.getStatus() == Response.Status.OK.getStatusCode()) {
            return rs.readEntity(String.class);
        } else {
            System.out.println("Response was not ok from the server on REST call to " + subPath);
            return null;
        }
    }

    public static boolean isServerUp() {
        String message = postJson(HEARTBEAT);
        if (message != null) {
            return true;
        } else {
            return false;
        }
    }

    public static String postJson(String subPath) {
        return postJson(subPath, "");
    }
}
