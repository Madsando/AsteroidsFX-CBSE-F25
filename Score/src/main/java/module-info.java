import dk.sdu.cbse.common.score.ScoreService;
import dk.sdu.cbse.score.ScoreAPI;

module Score {
    requires CommonScore;
    requires spring.web;

    provides ScoreService with ScoreAPI;
}


