package ch.srj.highscore.service;

import ch.srj.highscore.entity.Score;
import ch.srj.highscore.repository.ScoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreService {

    private final ScoreRepository scoreRepository;

    private ScoreService(ScoreRepository scoreRepository){
        this.scoreRepository = scoreRepository;
    }

    public void addScore(String playername, Double points) {
        scoreRepository.save(new Score(playername, points));
    }

    public List<Score> getScore(String playername){
        return scoreRepository.findByPlayername(playername);
    }
}
