package application.client.gruppe1.model;

import java.util.ArrayList;
import java.util.List;

import protocol.server2client.PlayerJoined;
import protocol.server2client.Update;

public class Game {
	private Player myPlayer;
	private List <Player> oponents = new ArrayList<Player>();
	
	public void createMyPlayer(String playerName) {
		myPlayer = new Player(playerName);
		
	}

	public void playerJoined(PlayerJoined message) {
		String playerName = message.getPlayerName();
		int initialX = message.getInitialPositionX();
		int initialY = message.getInitialPositionY();
		if(myPlayer.isYourName(playerName)) {
			myPlayer.setPosition(initialX, initialY);
		} else {
			Player oponent = new Player(playerName, initialX, initialY);
			oponents.add(oponent);
		}
	}

	public void update(Update message) {
		// TODO Auto-generated method stub
	}
}
