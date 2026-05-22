package rutas;

import com.sun.net.httpserver.HttpHandler;
import fentafloyd.*;
import com.sun.net.httpserver.HttpServer;

public class Rutas {
    public static void register(HttpServer server) {

        server.createContext(
                "/prostibulos",
                (HttpHandler) new ProstibuloFloyder()
        );

        server.createContext(
                "/resenas",
                new ResenyaFloyder()
        );
    }
}
