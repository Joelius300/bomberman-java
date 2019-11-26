package ch.srj.highscore;

import ch.srj.highscore.service.ScoreService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
@SpringBootApplication
public class HighscoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(HighscoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(ScoreService service) {
        return (args) -> {
            service.addScore("anix", 123.0);
            service.getScore("anix").forEach(System.out::println);
        };
    }
}
