package network;

import network.client.ClientApplicationInterface;
import network.client.NetworkServerProxy;
import network.server.NetworkServer;
import protocol.client2server.JoinGame;
import protocol.server2client.PlayerJoined;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Scanner;

public class DemoMainClient {
    public static void main(String[] args) throws IOException {
        ArrayList<NetworkServerProxy> clients = new ArrayList<>();
        // send some demo-messages from different clients
        for (int i = 0; i < 4; i++){
            try {
                final String name = "Spieler" + i;
                ClientApplicationInterface fakeClient = (message) -> System.out.println("Client " + name +" received: " + ((PlayerJoined) message).getPlayerName() + " joined");
                NetworkServerProxy client = new NetworkServerProxy(fakeClient, new InetSocketAddress(InetAddress.getLocalHost(), 42069));
                clients.add(client);
                client.connect();
                client.send(new JoinGame(name));

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Press enter to disconnect all clients.");
        new Scanner(System.in).nextLine();

        for (NetworkServerProxy client:
             clients) {
            client.close();
        }
    }
}
