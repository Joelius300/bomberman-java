package network;

import network.builder.ClientBuilder;
import network.builder.NetworkClientBuilder;
import network.client.ClientApplicationInterface;
import network.client.NetworkServerProxy;
import network.client.ServerProxy;
import network.server.NetworkServer;
import protocol.client2server.JoinGame;
import protocol.server2client.PlayerJoined;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Scanner;

public class DemoMainClient {
    public static void main(String[] args) throws Exception {
        ArrayList<ServerProxy> clients = new ArrayList<>();
        ClientBuilder clientBuilder = new NetworkClientBuilder()
                .WithLocalHost(NetworkServer.DEFAULT_SERVER_PORT);

        // send some demo-messages from different clients
        for (int i = 0; i < 4; i++){
            try {
                final String name = "Spieler" + i;
                ServerProxy client = clientBuilder
                        .WithClientApplication((message) -> System.out.println("Client " + name +" received: " + ((PlayerJoined) message).getPlayerName() + " joined"))
                        .BuildAndConnect();
                clients.add(client);
                client.send(new JoinGame(name));

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Press enter to disconnect all clients.");
        new Scanner(System.in).nextLine();

        for (ServerProxy client:
             clients) {
            client.close();
        }
    }
}
