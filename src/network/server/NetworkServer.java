package network.server;

import network.Message;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NetworkServer extends Server {
    public static final int MAX_PLAYER_COUNT = 4;
    public static final int SERVER_PORT = 42069;

    private final ExecutorService pool;
    private final ServerSocket serverSocket;

    // spichärä aui clients wo afragä
    // wahrschiinläch wird das mitärä spezieuä message gmacht oder so (no abklärä)


    public NetworkServer(ServerApplicationInterface serverApplication) throws IOException {
        super(serverApplication);

        pool = Executors.newFixedThreadPool(MAX_PLAYER_COUNT);
        serverSocket = new ServerSocket(SERVER_PORT);

        // start listener wöu gibb het ke ahnig wo dasdmä das darf (hint es isch nid im konstruktor)
        // immer we listener öppis empfaht -> nachricht deserialisiärä
        //  faus no nid registiert oder spezieui message (no abklärä), client spichärä
        //  när eifach nachricht a server witerleitä
    }

    @Override
    public void send(Message message, String connectionId) {
        // send nachricht über netzwärk (per socket) zum client mit dr richtigä connectionId
        // vo wo isch diä connectionId? client messages hei aui ä client name, wird dä vrwändet
    }

    @Override
    public void broadcast(Message message) {
        // send nachricht über netzwärk (per socket) zu aunä gspichertä clients
    }
}
