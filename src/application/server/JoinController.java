package application.server;

import network.server.Server;
import protocol.client2server.ClientMessage;
import protocol.server2client.ErrorMessage;
import protocol.server2client.PlayerJoined;
import protocol.server2client.StartGame;

public class JoinController extends Controller {

    public JoinController(Server server, Game game) {
        super(server, game);
    }

    @Override
    public void handleMessage(ClientMessage msg, String connectionId) {
        if (game.isRunning()) {
            server.send(new ErrorMessage("Das spiel läuft bereits."), connectionId);
        } else if (game.playerExists(msg.getPlayerName())) {
            server.send(new ErrorMessage("Der Name wird bereits verwendet."), connectionId);
        } else {
            Player player = game.createPlayer(msg.getPlayerName());
            server.broadcast(new PlayerJoined(player.getName(), player.getX(), player.getY()));
            if (game.isFull()) {
                game.setRunning(true);
                server.broadcast(new StartGame(game.getMap()));
            }
        }
    }
}
