package application.client.group1.model;

import application.client.group1.controller.ClientMessage;

public class DropBomb extends ClientMessage {
  private int positionX;
  private int positionY;

  public DropBomb(String playerName, int positionX, int positionY) {
    super(playerName);
    this.positionX = positionX;
    this.positionY = positionY;
  }

  public int getPositionX() {
    return positionX;
  }

  public int getPositionY() {
    return positionY;
  }
}
