package network.server;

import network.Message;

public class NetworkServer extends Server {
    // spichärä aui clients wo afragä
    // wahrschiinläch wird das mitärä spezieuä message gmacht oder so (no abklärä)


    public NetworkServer(ServerApplicationInterface serverApplication) {
        super(serverApplication);

        // start listener wöu gibb het ke ahnig wo dasdmä das darf (hint es isch nid im konstruktor)
        // immer we listener öppis empfaht -> nachricht deserialisiärä
        //  faus no nid registiert oder spezieui message (no abklärä), client spichärä
        //  när eifach nachricht a server witerleitä
    }

    @Override
    public void send(Message message, String connectionId) {
        // send nachricht über netzwärk (per socket) zum client mit dr richtigä connectionId
        // vo wo isch diä connectionId? client messages hei aui ä client name, wird dä vrwändet
    }

    @Override
    public void broadcast(Message message) {
        // send nachricht über netzwärk (per socket) zu aunä gspichertä clients
    }
}
