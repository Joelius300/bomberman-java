package application.server;

import network.server.Server;
import protocol.client2server.ClientMessage;

public abstract class Controller {
    protected Server server;
    protected Game game;

    public Controller(Server server, Game game) {
        this.server = server;
        this.game = game;
    }

    public abstract void handleMessage(ClientMessage msg, String connectionId);
}
