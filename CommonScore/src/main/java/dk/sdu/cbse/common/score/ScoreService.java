package dk.sdu.cbse.common.score;

public interface ScoreService {
    boolean updateScore(int scoreIncrement);
    int getScore();
}
