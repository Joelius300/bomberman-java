package ch.srj.highscore.repository;

import ch.srj.highscore.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findByPlayername(String playername);
}
