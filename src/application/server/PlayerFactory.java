package application.server;

public class PlayerFactory {
    public Player createPlayer(String name, String connectionId) {
        return new Player(name, connectionId);
    }
}
