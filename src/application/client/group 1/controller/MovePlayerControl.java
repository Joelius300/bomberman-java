package application.client.gruppe1.controller;

import application.client.gruppe1.model.Game;
import application.client.gruppe1.view.BombermanPanel;
import network.client.ServerProxy;
import protocol.Direction;
import protocol.client2server.MovePlayer;

public class MovePlayerControl extends Control{

	public MovePlayerControl(ServerProxy serverProxy, Game game, BombermanPanel view) {
		super(serverProxy, game, view);
	}
	
	public void movePlayer(String playerName, Direction direction) {
		MovePlayer message = new MovePlayer(playerName, direction);
		serverProxy.send(message);
	}
}
