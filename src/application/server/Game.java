package application.server;

import protocol.Direction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Game {
    private static final char TILE_FREE = ' '; //TODO: Merge mit Client Simon
    private static final char TILE_BREAKABLE = 'o'; //TODO: Merge mit Client Simon
    private static final char TILE_UNBREAKABLE = 'x'; //TODO: Merge mit Client Simon
    public static final int MAX_PLAYERS = 4; //TODO: Merge mit Server Joel

    private HashMap<String, Player> players;

    private char[][] map;


    private boolean running;

    public Game() {
        this.players = new HashMap<>();
        this.running = false;
    }

    public boolean playerExists(String name) {
        return players.containsKey(name);
    }

    public void addPlayer(Player player) {
        setPlayerPosition(player, players.size());
        players.put(player.getName(), player);
    }

    private void setPlayerPosition(Player player, int count) {
        switch (count) {
            case 0:
                player.setPos(1, 1);
                break;
            case 1:
                player.setPos(map[0].length - 2, 1);
                break;
            case 2:
                player.setPos(map[0].length - 2, map.length - 2);
                break;
            case 3:
                player.setPos(1, map[0].length);
                break;
        }
    }

    public boolean canPlayerMove(Player player, Direction direction) {
        try {
            switch (direction) {
                case UP:
                    return map[player.getY() - 1][player.getX()] == TILE_FREE;
                case DOWN:
                    return map[player.getY() + 1][player.getX()] == TILE_FREE;
                case LEFT:
                    return map[player.getY()][player.getX() - 1] == TILE_FREE;
                case RIGHT:
                    return map[player.getY()][player.getX() + 1] == TILE_FREE;
                default:
                    return false;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            return false;
        }
    }

    public List<Player> handleBombExplosion(int x, int y) {
        List<Player> killedPlayers = new ArrayList<>();
        return null;
    }

    public void setMap(char[][] map) {
        this.map = map;
    }

    public char[][] getMap() {
        return map;
    }

    public boolean isFull() {
        return players.size() >= MAX_PLAYERS;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
