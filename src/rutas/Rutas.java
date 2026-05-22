package rutas;

import fentafloyd.*;
import com.sun.net.httpserver.HttpServer;

public class Rutas {
    public static void register(HttpServer server) {

        server.createContext(
                "/prostibulos",
                new ProstibuloFloyder()
        );

        server.createContext(
                "/resenas",
                new ResenyaFloyder()
        );
    }
}
