package network.client;

import network.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class NetworkServerProxy extends ServerProxy {
    private final int Server_Port = 4269;
    private final String Server_Host = "localhost";
    private Socket serverSocket;

    public NetworkServerProxy(ClientApplicationInterface clientApplication) throws IOException {
        super(clientApplication);
        try {
            serverSocket = new Socket(Server_Host, Server_Port);
            ClientHandler clientHandler = new ClientHandler(Server_Host);
            clientHandler.schreibeNachricht(serverSocket, "Nachricht");
        }
        catch (IOException exception){
            exception.printStackTrace();
        }

        // start listener wöu gibb het ke ahnig wo dasdmä das darf (hint es isch nid im konstruktor)
        // immer we listener öppis empfaht -> nachricht deserialisiärä und a clientApplication witer gä (handleMessage)
    }

    @Override
    public void send(Message message) {
        try {
            ObjectOutputStream serverOutputStream = new ObjectOutputStream(serverSocket.getOutputStream());
            serverOutputStream.writeObject(message);
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
        // send nachricht über netzwärk (per socket) zum server
    }
}
