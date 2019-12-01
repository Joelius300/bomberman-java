package network;

import network.server.NetworkServer;
import network.server.ServerApplicationInterface;
import protocol.client2server.JoinGame;
import protocol.server2client.PlayerJoined;

import javax.sound.midi.SysexMessage;
import java.io.IOException;
import java.util.Scanner;

public class DemoMainServer {
    public static void main(String[] args) throws IOException {
        ServerApplicationInterface fakeServer = (message, connId) -> System.out.println(((JoinGame) message).getPlayerName() + " mit id " + connId + " : sent message");
        NetworkServer server = new NetworkServer(fakeServer);
        server.startListening();

        System.out.println("Ready? Press enter to broadcast some messages");
        new Scanner(System.in).nextLine();

        // broadcast some demo-messages
        for (int i = 0; i < NetworkServer.MAX_PLAYER_COUNT; i++){
            try {
                server.broadcast(new PlayerJoined("Player"+i, 0,0));

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
