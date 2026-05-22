package fentafloyd;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import controlador.ResenyasControlador;
import dao.ImplementacionMySQL.ResenyasMySQL;
import model.Resenyas;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ResenyaFloyder implements HttpHandler {

    private final ResenyasControlador controlador =
            new ResenyasControlador(new ResenyasMySQL());

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String method = exchange.getRequestMethod();
        String query = exchange.getRequestURI().getQuery();

        try {
            switch (method) {

                case "GET":
                    if (query != null && query.contains("id=")) {
                        int id = Integer.parseInt(query.split("=")[1]);
                        Resenyas r = controlador.obtenirPerId(id);
                        sendResponse(exchange, 200, toJson(r));
                    } else {
                        List<Resenyas> list = controlador.obtenirTots();
                        sendResponse(exchange, 200, toJsonList(list));
                    }
                    break;

                case "POST":
                    String bodyPost = readBody(exchange);
                    Resenyas nueva = fromJson(bodyPost);
                    controlador.crear(nueva);
                    sendResponse(exchange, 201, "{mensajee : creado }");
                    break;

                case "PUT":
                    int idPut = Integer.parseInt(query.split("=")[1]);
                    String bodyPut = readBody(exchange);

                    Resenyas update = fromJson(bodyPut);
                    update.setIdResenya(idPut);

                    controlador.actualitzar(update);
                    sendResponse(exchange, 200, "{mensaje : modificado}");
                    break;

                case "DELETE":
                    int idDel = Integer.parseInt(query.split("=")[1]);
                    controlador.eliminar(idDel);
                    sendResponse(exchange, 200, "{mensaje : eliminado}");
                    break;

                default:
                    sendResponse(exchange, 405, "{ error : metodo no permetido MAMAHUEVO}");
            }

        } catch (Exception e) {
            sendResponse(exchange, 500, "{error : " + e.getMessage() + " }");
        }
    }


    private String readBody(HttpExchange exchange) throws IOException {
        InputStream is = exchange.getRequestBody();
        return new String(is.readAllBytes(), StandardCharsets.UTF_8);
    }

    private void sendResponse(HttpExchange exchange, int status, String response) throws IOException {
        exchange.getResponseHeaders().add("Content-Type", "application/json");
        exchange.sendResponseHeaders(status, response.getBytes().length);

        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private String toJson(Resenyas r) {
        if (r == null) return "{}";

        return "{"
                + "id : " + r.getIdResenya() + ","
                + "prostibuloId : " + r.getIdProstibulo() + ","
                + "nombre : " + r.getNomClientResenya() + ","
                + "puntuacion : " + r.getPuntuacionResenya() + ","
                + "comentario : " + r.getComentarioResenya()
                + "}";
    }

    private String toJsonList(List<Resenyas> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < list.size(); i++) {
            sb.append(toJson(list.get(i)));
            if (i < list.size() - 1) sb.append(",");
        }

        sb.append("]");
        return sb.toString();
    }

    private Resenyas fromJson(String json) {
        Resenyas r = new Resenyas();

        r.setNomClientResenya(extract(json, "nombre"));
        r.setComentarioResenya(extract(json, "comentario"));
        r.setPuntuacionResenya(Integer.parseInt(extract(json, "puntuacion")));
        r.setIdProstibulo(Integer.parseInt(extract(json, "prostibuloId")));

        return r;
    }

    private String extract(String json, String key) {
        String pattern = " " + key + " :";
        int start = json.indexOf(pattern) + pattern.length();

        if (json.charAt(start) == '"') {
            start++;
            int end = json.indexOf(" ", start);
            return json.substring(start, end);
        } else {
            int end = json.indexOf(",", start);
            if (end == -1) end = json.indexOf("}", start);
            return json.substring(start, end).trim();
        }
    }
}