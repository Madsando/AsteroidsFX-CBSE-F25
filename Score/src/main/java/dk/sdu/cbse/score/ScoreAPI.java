package dk.sdu.cbse.score;

import dk.sdu.cbse.common.score.ScoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

public class ScoreAPI implements ScoreService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String url = "http://localhost:8080/score?point=";

    @Override
    public boolean updateScore(int scoreIncrement) {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url + scoreIncrement, String.class);
            return (response.getStatusCode() == HttpStatus.OK & response.getBody() != null);
        } catch (ResourceAccessException e) {
            return false;
        }
    }

    @Override
    public int getScore() {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url + 0, String.class);
            return (response.getBody() == null ? -1 : Integer.parseInt(response.getBody()));
        } catch (ResourceAccessException e) {
            return -1;
        }

    }
}
