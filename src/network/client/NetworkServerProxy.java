package network.client;

import network.Message;

public class NetworkServerProxy extends ServerProxy {
    public NetworkServerProxy(ClientApplicationInterface clientApplication) {
        super(clientApplication);

        // start listener w�u gibb het ke ahnig wo dasdm� das darf (hint es isch nid im konstruktor)
        // immer we listener �ppis empfaht -> nachricht deserialisi�r� und a clientApplication witer g� (handleMessage)
    }

    @Override
    public void send(Message message) {
        // send nachricht �ber netzw�rk (per socket) zum server
    }
}
