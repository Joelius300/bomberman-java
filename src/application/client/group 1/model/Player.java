package application.client.gruppe1.model;

import java.awt.Point;

public class Player {
	private int x, y;
	
	public Player(String playerName) {
		name = playerName;
	}

	public Player(String playerName, int initialX, int initialY) {
		this(playerName);
		x = initialX;
		y = initialY;
	}

	private String name;

	public boolean isYourName(String playerName) {
		return playerName == this.name;
	}

	public void setPosition(int initialX, int initialY) {
		x = initialX;
		y = initialY;
	}
}
