package network.client;

import network.Message;

public class NetworkServerProxy extends ServerProxy {
    public NetworkServerProxy(ClientApplicationInterface clientApplication) {
        super(clientApplication);

        // start listener wöu gibb het ke ahnig wo dasdmä das darf (hint es isch nid im konstruktor)
        // immer we listener öppis empfaht -> nachricht deserialisiärä und a clientApplication witer gä (handleMessage)
    }

    @Override
    public void send(Message message) {
        // send nachricht über netzwärk (per socket) zum server
    }
}
