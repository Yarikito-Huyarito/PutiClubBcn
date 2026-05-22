package util;

import server.ApiServer;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) throws Exception {

        ApiServer apiServer = new ApiServer();

        System.out.println("Iniciando Servidor de putas");
        apiServer.start();

        Thread.sleep(1000);

        System.out.println("Probando API");


        request("GET", "http://localhost:8080/resenas", null);


        String jsonCreate = """
        {
            "nombre":"Juan",
            "puntuacion":5,
            "comentario":"Muy bien",
            "prostibuloId":1
        }
        """;

        request("POST", "http://localhost:8080/resenas", jsonCreate);


        request("GET", "http://localhost:8080/resenas", null);

        System.out.println("Test completado");
    }

    private static void request(String method, String urlStr, String body) throws Exception {

        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod(method);
        conn.setRequestProperty("Content-Type", "application/json");

        if (body != null) {
            conn.setDoOutput(true);
            try (OutputStream os = conn.getOutputStream()) {
                os.write(body.getBytes(StandardCharsets.UTF_8));
            }
        }

        int responseCode = conn.getResponseCode();

        System.out.println("================================");
        System.out.println(method + " " + urlStr);
        System.out.println("Status: " + responseCode);

        InputStream is = (responseCode >= 200 && responseCode < 300)
                ? conn.getInputStream()
                : conn.getErrorStream();

        if (is != null) {
            String response = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            System.out.println("Response: " + response);
        }

        System.out.println("================================\n");
    }
}