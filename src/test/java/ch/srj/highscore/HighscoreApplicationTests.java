package ch.srj.highscore;

import ch.srj.highscore.service.ScoreService;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HighscoreApplicationTests {

    @Autowired
    ScoreService scoreService;

    @Value("${spring.datasource.url}")
    private String url;

    @BeforeEach
    void beforeMethod() {
        Assume.assumeTrue(this.url.startsWith("jdbc:h2:mem"));
    }

    @Test
    void integrationTest() {
        String playername = "player1";
        scoreService.addScore(playername, 20.0);
        Assert.assertEquals(1, scoreService.getScore(playername).size());
    }
}
