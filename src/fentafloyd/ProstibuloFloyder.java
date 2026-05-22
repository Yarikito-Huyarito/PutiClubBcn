package fentafloyd;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import controlador.ProstibulosControlador;
import dao.ImplementacionMySQL.ProstibuloMySQL;
import model.Prostibulos;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ProstibuloFloyder implements HttpHandler {
    private final ProstibulosControlador controlador =
            new ProstibulosControlador(new ProstibuloMySQL());

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String method = exchange.getRequestMethod();
        String query = exchange.getRequestURI().getQuery();

        try {

            switch (method) {

                case "GET":
                    if (query != null && query.contains("id=")) {
                        int id = Integer.parseInt(query.split("=")[1]);
                        Prostibulos p = controlador.obtenirPerId(id);
                        send(exchange, 200, toJson(p));
                    } else {
                        List<Prostibulos> list = controlador.obtenirTots();
                        send(exchange, 200, toJsonList(list));
                    }
                    break;

                case "POST":
                    Prostibulos nuevo = fromJson(readBody(exchange));
                    controlador.crear(nuevo);
                    send(exchange, 201, "{mensaje : creado}");
                    break;

                case "PUT":
                    int idPut = Integer.parseInt(query.split("=")[1]);
                    Prostibulos update = fromJson(readBody(exchange));
                    update.setId(idPut);

                    controlador.actualitzar(update);
                    send(exchange, 200, "{mensaje : modificado}");
                    break;

                case "DELETE":
                    int idDel = Integer.parseInt(query.split("=")[1]);
                    controlador.eliminar(idDel);
                    send(exchange, 200, "{mensaje : eliminado}");
                    break;

                default:
                    send(exchange, 405, "{error : metodo no permetido Asqueroso pendejo}");
            }

        } catch (Exception e) {
            send(exchange, 500, "{error: " + e.getMessage() + "}");
        }
    }

    private String readBody(HttpExchange exchange) throws IOException {
        InputStream is = exchange.getRequestBody();
        return new String(is.readAllBytes(), StandardCharsets.UTF_8);
    }

    private void send(HttpExchange ex, int code, String response) throws IOException {
        ex.getResponseHeaders().add("Content-Type", "application/json");
        ex.sendResponseHeaders(code, response.getBytes().length);

        OutputStream os = ex.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private String toJson(Prostibulos p) {
        if (p == null) return "{}";

        return "{"
                + "id :" + p.getIdProstibulo() + ","
                + "nombre : " + p.getNomProstibulo() + " ,"
                + "direccion : " + p.getDireccionProstibulo() + " ,"
                + "capacidad : " + p.getCapacidadChicas() + ","
                + "origen : " + p.getOrigen() + " ,"
                + "travestit : " + p.isTravestit() + ","
                + "drogas : " + p.isDrogas() + ","
                + "armas : " + p.isArmas() + ","
                + "precio : " + p.getPrecio()
                + "}";
    }

    private String toJsonList(List<Prostibulos> list) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < list.size(); i++) {
            sb.append(toJson(list.get(i)));
            if (i < list.size() - 1) sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }

    private Prostibulos fromJson(String json) {

        Prostibulos p = new Prostibulos();

        p.setNomProstibulo(extract(json, "nombre"));
        p.setDireccionProstibulo(extract(json, "direccion"));
        p.setCapacidadChicas(Integer.parseInt(extract(json, "capacidad")));
        p.setOrigen(Prostibulos.OrigenEnum.valueOf(extract(json, "origen")));
        p.setTravestit(Boolean.parseBoolean(extract(json, "travestit")));
        p.setDrogas(Boolean.parseBoolean(extract(json, "drogas")));
        p.setArmas(Boolean.parseBoolean(extract(json, "armas")));
        p.setPrecio(Integer.parseInt(extract(json, "precio")));

        return p;
    }

    private String extract(String json, String key) {
        String pattern = " " + key + " :";
        int start = json.indexOf(pattern) + pattern.length();

        if (json.charAt(start) == '"') {
            start++;
            int end = json.indexOf("", start);
            return json.substring(start, end);
        } else {
            int end = json.indexOf(",", start);
            if (end == -1) end = json.indexOf("}", start);
            return json.substring(start, end).trim();
        }
    }
}