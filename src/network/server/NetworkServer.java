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

    // spich�r� aui clients wo afrag�
    // wahrschiinl�ch wird das mit�r� spezieu� message gmacht oder so (no abkl�r�)


    public NetworkServer(ServerApplicationInterface serverApplication) throws IOException {
        super(serverApplication);

        pool = Executors.newFixedThreadPool(MAX_PLAYER_COUNT);
        serverSocket = new ServerSocket(SERVER_PORT);

        // start listener w�u gibb het ke ahnig wo dasdm� das darf (hint es isch nid im konstruktor)
        // immer we listener �ppis empfaht -> nachricht deserialisi�r�
        //  faus no nid registiert oder spezieui message (no abkl�r�), client spich�r�
        //  n�r eifach nachricht a server witerleit�
    }

    @Override
    public void send(Message message, String connectionId) {
        // send nachricht �ber netzw�rk (per socket) zum client mit dr richtig� connectionId
        // vo wo isch di� connectionId? client messages hei aui � client name, wird d� vrw�ndet
    }

    @Override
    public void broadcast(Message message) {
        // send nachricht �ber netzw�rk (per socket) zu aun� gspichert� clients
    }
}
