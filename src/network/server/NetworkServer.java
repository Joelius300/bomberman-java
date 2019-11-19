package network.server;

import network.Message;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NetworkServer extends Server {
    public static final int MAX_PLAYER_COUNT = 4; // TODO: Nimm vo Server Jannis
    public static final int SERVER_PORT = 42069;

    private final ExecutorService pool;
    private final ServerSocket serverSocket;
    private final ConcurrentHashMap<String, SocketHandler> clients;

    public NetworkServer(ServerApplicationInterface serverApplication) throws IOException {
        super(serverApplication);

        pool = Executors.newFixedThreadPool(MAX_PLAYER_COUNT);
        serverSocket = new ServerSocket(SERVER_PORT);
        clients = new ConcurrentHashMap<>();
    }

    private void acceptClients() {
        try {
            while (clients.size() < NetworkServer.MAX_PLAYER_COUNT) {
                Socket cs = serverSocket.accept();  //warten auf Client
                SocketHandler handler = new SocketHandler(cs);
                String connectionId = UUID.randomUUID().toString();
                clients.put(connectionId, handler);
                // Starte den Handler-Thread zur Weiterleitung der Client-Messages
                pool.execute(() -> listenToSocket(handler, connectionId));
            }
        } catch (IOException ex) {
            System.out.println("Was interrupted while accepting the clients.");
        }
    }

    private void listenToSocket(SocketHandler socket, String connectionId){
        try{
            while(socket.isConnected()){
                Message receivedMessage = socket.readMessage();
                serverApplication.handleMessage(receivedMessage, connectionId);
            }
        }catch(Exception ex){
            System.out.println("Connection to Client '" + connectionId + "' lost.");
        }

        // Remove disconnected Socket
        clients.remove(connectionId);
    }

    public void startListening(){
        new Thread(this::acceptClients).start();
    }

    @Override
    public void send(Message message, String connectionId) {
        if (!clients.containsKey(connectionId)){
            System.out.println("There's no client with the connectionId " + connectionId);
            return;
        }

        // send nachricht über netzwärk (per socket) zum client mit dr richtigä connectionId
        SocketHandler handler = clients.get(connectionId);
        try {
            handler.send(message);
        } catch (IOException e) {
            System.out.println("Couldn't send message to client " + connectionId);
        }
    }

    @Override
    public void broadcast(Message message) {
        // send nachricht über netzwärk (per socket) zu aunä gspichertä clients
        for (Map.Entry<String, SocketHandler> entry:
             clients.entrySet()) {
            try {
                entry.getValue().send(message);
            } catch (IOException e) {
                System.out.println("Couldn't send message to client " + entry.getKey());
            }
        }
    }
}
