package network.client;

import network.Message;

import java.io.*;
import java.net.Socket;

public class NetworkServerProxy extends ServerProxy implements Runnable {
    private final Socket socket;

    public NetworkServerProxy(Socket socket, ClientApplicationInterface clientApplication) {
        super(clientApplication);
        this.socket = socket;
    }

    @Override
    public void send(Message message) {
        try {
            // get the output stream from the socket.
            OutputStream outputStream = socket.getOutputStream();
            // create an object output stream from the output stream so we can send an object through it
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            // send it through (gets serialized automatically)
            objectOutputStream.writeObject(message);
        } catch (IOException e) {

        }
    }

    public void run() {
        try {
            // get the input stream from the connected socket
            InputStream inputStream = socket.getInputStream();
            // create a DataInputStream so we can read data from it.
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            while(true) {
                Message message = (Message) objectInputStream.readObject();
                clientApplication.handleMessage(message);
            }
        } catch (IOException e) {

        }
        catch (ClassNotFoundException e) {

        }
    }
}
