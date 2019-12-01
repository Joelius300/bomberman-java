package network;

import network.client.ClientApplicationInterface;
import network.client.NetworkServerProxy;
import network.server.NetworkServer;
import protocol.client2server.JoinGame;
import protocol.server2client.PlayerJoined;

import java.io.IOException;
import java.net.InetSocketAddress;

public class DemoMainClient {
    public static void main(String[] args) throws IOException {

        // send some demo-messages from different clients
        for (int i = 0; i < NetworkServer.MAX_PLAYER_COUNT; i++){
            try {
                final String name = "Spieler" + i;
                ClientApplicationInterface fakeClient = (message) -> System.out.println("Client " + name +" received: " + ((PlayerJoined) message).getPlayerName() + " joined");
                NetworkServerProxy client = new NetworkServerProxy(fakeClient, new InetSocketAddress(NetworkServer.SERVER_PORT));
                client.connect();
                client.send(new JoinGame(name));

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
