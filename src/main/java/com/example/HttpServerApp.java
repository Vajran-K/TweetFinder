package com.example;

import com.sun.net.httpserver.HttpServer;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class HttpServerApp {

    public static void main(String[] args) throws Exception {

        int port = Integer.parseInt(
            System.getenv().getOrDefault("PORT", "8080")
        );

        HttpServer server = HttpServer.create(
            new InetSocketAddress(port), 0
        );

        server.createContext("/scan", exchange -> {

            String response;

            try {
                boolean isNew = App.runScan();
                response = isNew
                    ? "New update found. Email sent."
                    : "No new updates.";
            } catch (Exception e) {
                response = "Error during scan.";
                e.printStackTrace();
            }

            exchange.getResponseHeaders()
                    .add("Access-Control-Allow-Origin", "*");
            exchange.sendResponseHeaders(200, response.length());

            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        });

        server.start();
        System.out.println("Server started on port " + port);
    }
}
