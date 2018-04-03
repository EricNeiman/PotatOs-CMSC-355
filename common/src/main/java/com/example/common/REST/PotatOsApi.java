package com.example.common.REST;

import com.example.common.Class;
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

public class PotatOsApi {

    public static final String API_DIR = "api";

    //droplet ip
    public static final String API_IP = "104.131.117.72";
    public static final String API_PORT = "8080";

    public static final String HEARTBEAT = "heartbeat";
    public static final String API_PATH = "http://" + API_IP + ":" + API_PORT + "/" + API_DIR + "/";


    public static String getJsonREST(String api_path) {




    }
}
