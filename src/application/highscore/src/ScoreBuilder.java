package application.highscore.src;

public class ScoreBuilder {
    private String playername;
    private double points;

    public ScoreBuilder(){}

    public ScoreBuilder playername(String playername){
        this.playername = playername;
        return this;
    }

    public ScoreBuilder points(double points){
        this.points = points;
        return this;
    }

    public Score build(){
        return new Score(playername, points);
    }
}
