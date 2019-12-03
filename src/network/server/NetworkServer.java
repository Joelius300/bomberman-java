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
    // public static final int MAX_PLAYER_COUNT = 4; // TODO: Nimm vo Server Jannis
    // public static final int SERVER_PORT = 42069;

    private final ExecutorService pool;
    private final ConcurrentHashMap<String, SocketHandler> clients;
    private ServerSocket serverSocket;
    private final int maxPlayerCount, serverPort;

    public NetworkServer(ServerApplicationInterface serverApplication, int maxPlayerCount, int serverPort) {
        super(serverApplication);

        pool = Executors.newFixedThreadPool(maxPlayerCount);
        clients = new ConcurrentHashMap<>();
        this.maxPlayerCount = maxPlayerCount;
        this.serverPort = serverPort;
    }

    private void acceptClients() {
        try {
            while (clients.size() < maxPlayerCount) {
                Socket cs = serverSocket.accept();  // wait for client
                SocketHandler handler = new SocketHandler(cs);
                String connectionId = UUID.randomUUID().toString();
                clients.put(connectionId, handler);
                // start handler-thread to receive and forward the client-messages
                pool.execute(() -> listenToSocket(handler, connectionId));
                System.out.println("Client with id " + connectionId + " from " + cs.getInetAddress() + " connected to the server.");
            }
        } catch (IOException ex) {
            System.out.println("Was interrupted while accepting the clients. The Server was probably closed.");
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
        // Maybe TODO notify that a client disconnected?
        clients.remove(connectionId);
    }

    /*
    This method is not defined by the contract so probably no one will call
    it even if it's public. Therefore: TODO make private and move to constructor (although that's bad)
     */
    public void startListening() throws IOException {
        serverSocket = new ServerSocket(serverPort);
        new Thread(this::acceptClients).start();
    }

    @Override
    public void send(Message message, String connectionId) {
        if (!clients.containsKey(connectionId)){
            System.out.println("There's no client with the connectionId " + connectionId);
            return;
        }

        SocketHandler handler = clients.get(connectionId);
        try {
            handler.send(message);
        } catch (IOException e) {
            System.out.println("Couldn't send message to client " + connectionId);
        }
    }

    @Override
    public void broadcast(Message message) {
        for (Map.Entry<String, SocketHandler> entry:
             clients.entrySet()) {
            try {
                entry.getValue().send(message);
            } catch (IOException e) {
                System.out.println("Couldn't send message to client " + entry.getKey());
            }
        }
    }

    public void close() throws IOException {
        serverSocket.close();
        for (Map.Entry<String, SocketHandler> entry:
                clients.entrySet()) {
            entry.getValue().close();
        }
        pool.shutdown();
    }
}
