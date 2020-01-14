package application.server;

public class PlayerFactory {
    public Player getPlayer(String name, String connectionId) {
        return new Player(name);
    }
}
