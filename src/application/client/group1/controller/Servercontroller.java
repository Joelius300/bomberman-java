package application.client.group1.controller;

import network.Message;
import network.client.ClientApplicationInterface;
import network.client.ServerProxy;

public class Servercontroller extends ServerProxy implements ClientApplicationInterface {

    /**
     * Konstruktor. Initialisiert das neue ServerProxy-Objekt mit der Referenz auf das Empf�ngerobjekt.
     *
     * @param clientApplication Das Empf�ngerobjekt des Bomberman-Clients f�r Nachrichten.
     */
    public Servercontroller(ClientApplicationInterface clientApplication) {
        super(clientApplication);
    }

    @Override
    public void send(Message message) {

    }

    @Override
    public void handleMessage(Message message) {

    }
}
