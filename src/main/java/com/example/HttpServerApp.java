package com.example;

import com.sun.net.httpserver.HttpServer;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class HttpServerApp {

    public static void main(String[] args) throws Exception {

        int port = Integer.parseInt(
                System.getenv().getOrDefault("PORT", "8080")
        );

        HttpServer server = HttpServer.create(
                new InetSocketAddress("0.0.0.0", port), 0
        );

        server.createContext("/health", exchange -> {
            String response = "OK";
            exchange.sendResponseHeaders(200, response.length());
            exchange.getResponseBody().write(response.getBytes());
            exchange.getResponseBody().close();
        });

        server.createContext("/scan", exchange -> {
            String response = "scan reached";

            try {
                System.out.println("SCAN endpoint hit");

                boolean result = App.runScan();
                response = result
                        ? "New update found. Email sent."
                        : "No new updates.";

            } catch (Exception e) {
                e.printStackTrace();
                response = "ERROR: " + e.toString();
            }

            byte[] bytes = response.getBytes(StandardCharsets.UTF_8);

            exchange.getResponseHeaders().set("Content-Type", "text/plain; charset=utf-8");
            exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
            exchange.sendResponseHeaders(200, bytes.length);

            try (OutputStream os = exchange.getResponseBody()) {
                os.write(bytes);
            }
        });

        server.start();
        System.out.println("Server started on port " + port);
    }
}
