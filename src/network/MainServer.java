package network;

import network.server.NetworkServer;

import java.io.IOException;

public class MainServer {
    public static void main(String[] args) throws IOException {
        NetworkServer server = new NetworkServer(/* VON WO?! */);
        server.startListening();
    }
}
