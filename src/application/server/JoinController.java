package application.server;

import network.Message;
import network.server.Server;
import protocol.client2server.JoinGame;
import protocol.server2client.ErrorMessage;
import protocol.server2client.PlayerJoined;
import protocol.server2client.StartGame;

public class JoinController extends Controller {

    public JoinController(Server server, Game game) {
        super(server, game);
    }


    @Override
    public void handleMessage(Message message, String connectionId) {
        if(message instanceof JoinGame) {
            JoinGame joinMessage = (JoinGame)message;
            PlayerFactory pf = new PlayerFactory();
            if (game.isRunning()) {
                server.send(new ErrorMessage("Das spiel läuft bereits."), connectionId);
            } else if (game.playerExists(joinMessage.getPlayerName())) {
                server.send(new ErrorMessage("Der Name wird bereits verwendet."), connectionId);
            } else {
                Player player = pf.getPlayer(joinMessage.getPlayerName(), connectionId);
                game.addPlayer(player);
                server.broadcast(new PlayerJoined(player.getName(), player.getX(), player.getY()));
                if (game.isFull()) {
                    game.setRunning(true);
                    server.broadcast(new StartGame(game.getMap()));
                }
            }
        }
    }
}
