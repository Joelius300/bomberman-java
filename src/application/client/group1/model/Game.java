package application.client.group1.model;

import java.util.ArrayList;
import java.util.List;

import protocol.server2client.PlayerJoined;
import protocol.server2client.Update;
import application.client.group1.model.Player;

public class Game {
	private static final char TILE_FREE = ' ';
	private static final char TILE_BREAKABLE = 'o';
	private static final char TILE_UNBREAKABLE = 'x';
	private char[][] map;
	private Player myPlayer;
	private List <Player> oponents = new ArrayList<Player>();
	private boolean running;
	public void createMyPlayer(String playerName, int x, int y) {
		myPlayer = new Player(playerName, playerName, x, y);
		
	}

	public void playerJoined(PlayerJoined message) {
		String playerName = message.getPlayerName();
		int initialX = message.getInitialPositionX();
		int initialY = message.getInitialPositionY();
		if(myPlayer.getName().equals(playerName)) {
			myPlayer.setPos(initialX, initialY);
		} else {
			Player oponent = new Player(playerName,playerName, initialX, initialY);
			oponents.add(oponent);
		}
	}

	public void update(Update message) {
		// TODO Auto-generated method stub
	}
}
