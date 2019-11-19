package application.server;

public class Player {
    private int x, y;
    private String name;
    private String connectionId;

    public Player(String name, String connectionId) {
        this.name = name;
        this.connectionId = connectionId;
    }

    public Player(String name, String connectionId, int x, int y) {
        this.name = name;
        this.connectionId = connectionId;
        this.x = x;
        this.y = y;
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(String connectionId) {
        this.connectionId = connectionId;
    }
}
