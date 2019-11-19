package application.server;

import network.server.Server;
import network.server.ServerApplicationInterface;
import protocol.client2server.ClientMessage;

public abstract class Controller implements ServerApplicationInterface {
    protected Server server;
    protected Game game;

    public Controller(Server server, Game game) {
        this.server = server;
        this.game = game;
    }
}
