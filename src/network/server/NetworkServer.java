package network.server;

import network.Message;

public class NetworkServer extends Server {
    // spich�r� aui clients wo afrag�
    // wahrschiinl�ch wird das mit�r� spezieu� message gmacht oder so (no abkl�r�)


    public NetworkServer(ServerApplicationInterface serverApplication) {
        super(serverApplication);

        // start listener w�u gibb het ke ahnig wo dasdm� das darf (hint es isch nid im konstruktor)
        // immer we listener �ppis empfaht -> nachricht deserialisi�r�
        //  faus no nid registiert oder spezieui message (no abkl�r�), client spich�r�
        //  n�r eifach nachricht a server witerleit�
    }

    @Override
    public void send(Message message, String connectionId) {
        // send nachricht �ber netzw�rk (per socket) zum client mit dr richtig� connectionId
        // vo wo isch di� connectionId? client messages hei aui � client name, wird d� vrw�ndet
    }

    @Override
    public void broadcast(Message message) {
        // send nachricht �ber netzw�rk (per socket) zu aun� gspichert� clients
    }
}
