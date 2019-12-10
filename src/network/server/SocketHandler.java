package network.server;

import network.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

class SocketHandler {
    private Socket clientSocket;
    private ObjectInputStream objInputStream;
    private ObjectOutputStream objOutputStream;

    public SocketHandler(Socket socket) throws IOException {
        clientSocket = socket;

        objOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        objOutputStream.flush();
        objInputStream = new ObjectInputStream(clientSocket.getInputStream());
    }

    public Message readMessage() throws IOException, ClassNotFoundException {
        return (Message)objInputStream.readObject();
    }

    public void send(Message message) throws IOException {
        objOutputStream.writeObject(message);
    }

    public boolean isConnected(){
        return clientSocket.isConnected();
    }

    public void close() throws IOException {
        clientSocket.close();
    }
}