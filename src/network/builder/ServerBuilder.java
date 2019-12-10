package network.builder;

import network.server.Server;
import network.server.ServerApplicationInterface;

import java.io.IOException;

public interface ServerBuilder {
    ServerBuilder WithPort(int port);
    ServerBuilder WithMaxPlayers(int maxPlayerCount);
    ServerBuilder WithServerApplication(ServerApplicationInterface serverApplication);
    Server Build();
    Server BuildAndStartListening() throws IOException;
}
