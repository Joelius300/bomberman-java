package network.client;

import network.Message;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class NetworkServerProxy extends ServerProxy {
    private final Socket socket;
    private final SocketAddress address;

    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    public NetworkServerProxy(ClientApplicationInterface clientApplication, SocketAddress address) {
        super(clientApplication);

        socket = new Socket();
        this.address = address;
    }

    /*
    This method is not defined by the contract so probably no one will call
    it even if it's public. Therefore: TODO make private and move to constructor (although that's bad)
     */
    public void connect() throws IOException {
        socket.connect(address);
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        new Thread(this::listenToSocket).start();

        System.out.println("Connected to " + socket.getInetAddress());
    }

    @Override
    public void send(Message message) {
        try {
            // send it through (gets serialized automatically)
            objectOutputStream.writeObject(message);
        } catch (IOException e) {
            System.out.println("The message " + message + "could not be sent: " + e.getMessage());
        }
    }

    private void listenToSocket() {
        try {
            while(true) {
                Message message = (Message) objectInputStream.readObject();
                clientApplication.handleMessage(message);
            }
        } catch (IOException e) {
            System.out.println("Message could not be received: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Could not cast message: " + e.getMessage());
        }
    }
}
