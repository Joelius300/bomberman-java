package network.builder;

import network.server.NetworkServer;
import network.server.Server;
import network.server.ServerApplicationInterface;

import java.io.IOException;

public class NetworkServerBuilder implements ServerBuilder{
    private static final int DEFAULT_MAX_PLAYER_COUNT = 4; // TODO: Nimm vo Server Jannis
    private ServerApplicationInterface serverApplication;
    private int maxPlayerCount, serverPort;

    public NetworkServerBuilder(){
        maxPlayerCount = DEFAULT_MAX_PLAYER_COUNT;
        serverPort = NetworkServer.DEFAULT_SERVER_PORT;
    }

    public ServerBuilder WithPort(int port){
        this.serverPort = port;
        return this;
    }

    public ServerBuilder WithMaxPlayers(int maxPlayerCount){
        this.maxPlayerCount = maxPlayerCount;
        return this;
    }

    public ServerBuilder WithServerApplication(ServerApplicationInterface serverApplication){
        this.serverApplication = serverApplication;
        return this;
    }

    public Server Build() {
        if (serverApplication == null) {
            throw new IllegalStateException("The Server cannot be built because no ServerApplicationInterface was registered for this server. Call WithServerApplication before building.");
        }

        if (serverPort < 0 || serverPort > 0xFFFF){
            throw new IllegalStateException("The Server cannot be built because the registered port for this server is " + serverPort + " but it has to be in the range 0 - 65535. Call WithPort with a valid value before building.");
        }

        if (maxPlayerCount < 1){
            throw new IllegalStateException("The Server cannot be built because the registered max-player-count for this server is below 1. Call WithMaxPlayers with a value above 0 before building.");
        }

        return new NetworkServer(serverApplication, maxPlayerCount, serverPort);
    }

    public Server BuildAndStartListening() throws IOException {
        Server server = Build();
        server.startListening();
        return server;
    }
}
