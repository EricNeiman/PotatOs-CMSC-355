package com.example.common.REST;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
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
        URI url = null;
        try {
            url = new URI(API_PATH + subPath);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }

        HttpClient client = HttpClientBuilder.create().build();
        try {
            HttpRequestBase request = null;
            if (post) {
                request = new HttpPost(url);
            } else {
                request = new HttpGet(url);
            }

            HttpResponse rs = client.execute(request);

            System.out.println("Made a request to the server: " + url + " response: " + rs.getStatusLine().getStatusCode());
            if (rs.getStatusLine().getStatusCode() == 200) {
                return rs.getEntity().toString();
            } else {
                System.out.println("Response was not ok from the server on REST call to " + subPath);
                return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
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
