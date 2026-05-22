package server;

import rutas.*;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class ApiServer {
        private HttpServer server;
        private final int PORT = 8080;

        public void start() {
            try {
                server = HttpServer.create(
                        new InetSocketAddress(PORT),
                        0
                );
                Rutas.register(server);
                server.setExecutor(null);
                server.start();
                System.out.println("ApiRest coriendo en httplocalhost:" + PORT);
            } catch (IOException e) {
                throw new RuntimeException("Has hecho algo mal pendejo, o tenemos recursos tan malos que ni el servidor es eficiente");
            }
        }
        public void stop() {
            if (server != null) {
                server.stop(0);
            }
        }
}
