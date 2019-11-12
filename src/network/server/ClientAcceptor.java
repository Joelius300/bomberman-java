package network.server;

import network.Message;
import protocol.client2server.JoinGame;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class ClientAcceptor implements Runnable {
    private final ExecutorService pool;
    private final ServerSocket serverSocket;
    private final ConcurrentHashMap<String, Socket> clients;

    public ClientAcceptor(ExecutorService pool, ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.pool = pool;
        clients = new ConcurrentHashMap<String, Socket>();
    }

    public void run() {
        try {
            while (clients.size() < NetworkServer.MAX_PLAYER_COUNT) {
                /*
                 Zun�chst wird eine Client-Anforderung entgegengenommen(accept-Methode).
                 Der ExecutorService pool liefert einen Thread, auf welchem geh�rt wird
                 ob der Client dem Spiel beitritt. Falls ja, wird er zu den Clients hinzugef�gt.
                */
                Socket cs = serverSocket.accept();  //warten auf Client-Anforderung

                //starte den Handler-Thread zur Realisierung der Client-Anforderung
                pool.execute(new ClientAdder(clients, cs));
            }
        } catch (IOException ex) {
            System.out.println("ClientAcceptor was interrupted");
        }
    }

    private class ClientAdder implements Runnable {
        private final ConcurrentHashMap<String, Socket> clients;
        private final Socket client;

        public ClientAdder(ConcurrentHashMap<String, Socket> clients, Socket client){
            this.clients = clients;
            this.client = client;
        }

        @Override
        public void run() {
            try {
                // get the input stream from the connected socket
                InputStream inputStream = client.getInputStream();
                // create a DataInputStream so we can read data from it.
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                while(true) {
                    Message message = (Message) objectInputStream.readObject();
                    if (message instanceof JoinGame) {
                        String name = ((JoinGame)message).getPlayerName();
                        if(!clients.containsKey(name)) {
                            clients.put(name, client);
                            break;
                        }
                    }
                }
            } catch (IOException e) {

            }
            catch (ClassNotFoundException e) {

            }
        }
    }
}
