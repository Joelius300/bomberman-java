package network;

import network.builder.NetworkServerBuilder;
import network.builder.ServerBuilder;
import network.server.NetworkServer;
import network.server.Server;
import network.server.ServerApplicationInterface;
import protocol.client2server.JoinGame;
import protocol.server2client.PlayerJoined;

import javax.sound.midi.SysexMessage;
import java.io.IOException;
import java.util.Scanner;

public class DemoMainServer {
    public static void main(String[] args) throws Exception {
        ServerApplicationInterface fakeServer = (message, connId) -> System.out.println(((JoinGame) message).getPlayerName() + " mit id " + connId + " : sent message");
        Server server = new NetworkServerBuilder()
                .WithPort(NetworkServer.DEFAULT_SERVER_PORT)
                .WithMaxPlayers(4)
                .WithServerApplication(fakeServer)
                .Build();

        try (server) {
            server.startListening();

            System.out.println("Ready? Press enter to broadcast some messages");
            new Scanner(System.in).nextLine();

            // broadcast some demo-messages
            for (int i = 0; i < 4; i++) {
                try {
                    server.broadcast(new PlayerJoined("Player" + i, 0, 0));

                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
