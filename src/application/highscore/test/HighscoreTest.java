package application.highscore.test;

import application.highscore.src.Score;
import application.highscore.src.ScoreService;
import org.junit.Assert;
import org.junit.Test;

public class HighscoreTest {
    @Test
    public void test(){
        String playername = "name";
        ScoreService service = new ScoreService();
        Assert.assertEquals(0, service.get(playername).size());

        service.add(playername, 19L);
        Assert.assertEquals(1, service.get(playername).size());

        service.update(((Score) service.get(playername).get(0)).getId(), "name2", 20.0);
        Assert.assertNotNull(service.get("name2"));

        service.delete(((Score) service.get("name2").get(0)).getId());
        Assert.assertEquals(0, service.get(playername).size());
    }
}
