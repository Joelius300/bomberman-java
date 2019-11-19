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

        // start listener w�u gibb het ke ahnig wo dasdm� das darf (hint es isch nid im konstruktor)
        // immer we listener �ppis empfaht -> nachricht deserialisi�r� und a clientApplication witer g� (handleMessage)
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
        // send nachricht �ber netzw�rk (per socket) zum server
    }
}
