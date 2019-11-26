package application.server;

public class Server {

    private Controller joinController;

    public Server(network.server.Server server) {
        Game game = new Game();
        joinController = new JoinController(server, game);
    }

    public Controller getJoinController() {
        return joinController;
    }
}
