package network.builder;

import network.client.ClientApplicationInterface;
import network.client.ServerProxy;

import java.io.IOException;
import java.net.SocketAddress;
import java.net.UnknownHostException;

public interface ClientBuilder {
    ClientBuilder WithSocketAddress(SocketAddress address);
    ClientBuilder WithLocalHost(int port) throws UnknownHostException;
    ClientBuilder WithClientApplication(ClientApplicationInterface clientApplication);
    ServerProxy Build();
    ServerProxy BuildAndConnect() throws IOException;
}
