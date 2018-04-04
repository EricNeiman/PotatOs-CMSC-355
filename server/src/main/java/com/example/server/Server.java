package com.example.server;

import javax.ws.rs.ext.RuntimeDelegate;

import com.example.common.REST.PotatOsApi;
import com.example.server.DatabaseHelper.PotatOsDatabase;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {
    static HttpServer startServer() throws IOException {
        final HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                server.stop(0);
            }
        }));

        HttpHandler handler = RuntimeDelegate.getInstance().createEndpoint(new PotatOsServerApplication(), HttpHandler.class);
        server.createContext("/", handler);
        server.start();
        return server;
    }

    public static void main(String[] args) {
        try {
            if (!PotatOsDatabase.checkTables()) {
                return; //unable to create tables
            }

            System.out.println("Starting web server...");
            startServer();
            System.out.println("The server is now running.  You can get a heartbeat by visiting " + PotatOsApi.API_PATH + PotatOsApi.HEARTBEAT);
            System.out.println("If you are debugging, then check " + "http://localhost:8080/api/heartbeat");

            Thread.currentThread().join();
        } catch (Exception ex) {
            ex.printStackTrace();
    }
    }
}
