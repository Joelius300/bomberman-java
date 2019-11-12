package application.client.gruppe1.controller;

import application.client.gruppe1.model.Game;
import application.client.gruppe1.view.BombermanPanel;
import network.client.ServerProxy;

public class Control {

	protected ServerProxy serverProxy;
	Game game;
	BombermanPanel view;
	
	public Control(ServerProxy serverProxy, Game game, BombermanPanel view) {
		this.serverProxy = serverProxy;
		this.game = game;
		this.view = view;
	}

	
}
