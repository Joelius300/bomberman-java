package application.client.gruppe1.controller;

import application.client.gruppe1.model.Game;
import application.client.gruppe1.view.BombermanPanel;
import network.client.ServerProxy;

public class ControlFactory {

	private ServerProxy serverProxy;
	private Game game;
	private static ControlFactory instance;
	private BombermanPanel view;
	
	public static void initiate(ServerProxy serverProxy, Game game, BombermanPanel view) {
		if(instance == null) {
			instance = new ControlFactory(serverProxy, game, view);
		}
	}
	
	public ControlFactory(ServerProxy serverProxy, Game game, BombermanPanel view) {
		this.serverProxy = serverProxy;
		this.game = game;
		this.view = view;
	}
	
	public static ControlFactory instance() {
		return instance;
	}
	
	public JoinGameControl createJoinGameControl() {
		return new JoinGameControl(serverProxy, game, view);
	}
	
	public DropBombControl createDropBombControl() {
		return new DropBombControl(serverProxy, game, view);
	}
	
	public MovePlayerControl createMovePlayerControl() {
		return new MovePlayerControl(serverProxy, game, view);
	}

	public PlayerJoinedControl createPlayerJoinedControl() {
		return new PlayerJoinedControl(serverProxy, game, view);	
	}

	public UpdateControl createUpdateControl() {
		return new UpdateControl(serverProxy, game, view);
	}

}
