package application.highscore.src;

public class Main {
    public static void main(String[] args) {
        ScoreService service = new ScoreService();
        service.add("player", 23.0);
        service.update(1,"player", 23.0);
        service.delete(1L);
    }
}
