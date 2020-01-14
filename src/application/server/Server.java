package application.server;

import network.builder.NetworkServerBuilder;
import network.server.NetworkServer;

import java.io.IOException;

public class Server {

    private Controller joinController;

    public static void main(String[] args) {
        new Server();
    }

    public Server() {
        network.server.Server server = null;
        try {
            server = new NetworkServerBuilder()
                    .WithMaxPlayers(4)
                    .WithPort(NetworkServer.DEFAULT_SERVER_PORT)
                    .BuildAndStartListening();
            Game g = new Game();
            joinController = new JoinController(server, g);
            g.setRunning(true);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
