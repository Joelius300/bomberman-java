package network.builder;

import network.client.ClientApplicationInterface;
import network.client.NetworkServerProxy;
import network.client.ServerProxy;
import network.server.NetworkServer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;

public class NetworkClientBuilder implements ClientBuilder {
    private ClientApplicationInterface clientApplication;
    private SocketAddress address;

    public NetworkClientBuilder() {
        try {
            address = new InetSocketAddress(InetAddress.getLocalHost(), NetworkServer.DEFAULT_SERVER_PORT);
        } catch (UnknownHostException e) {
            address = null;
        }
    }

    public ClientBuilder WithSocketAddress(SocketAddress address){
        this.address = address;
        return this;
    }

    public ClientBuilder WithLocalHost(int port) throws UnknownHostException {
        this.address = new InetSocketAddress(InetAddress.getLocalHost(), port);
        return this;
    }

    public ClientBuilder WithClientApplication(ClientApplicationInterface clientApplication){
        this.clientApplication = clientApplication;
        return this;
    }

    public ServerProxy Build(){
        if (clientApplication == null) {
            throw new IllegalStateException("The Client cannot be built because no ClientApplicationInterface was registered for this client. Call WithClientApplication before building.");
        }

        if (address == null){
            throw new IllegalStateException("The Client cannot be built because no SocketAddress was registered for this client. Call WithSocketAddress or WithLocalHost before building.");
        }

        return new NetworkServerProxy(clientApplication, address);
    }

    public ServerProxy BuildAndConnect() throws IOException {
        ServerProxy client = Build();
        client.connect();
        return client;
    }
}
