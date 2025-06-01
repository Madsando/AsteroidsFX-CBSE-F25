package dk.sdu.cbse.scoresystem;

import dk.sdu.cbse.common.data.GameData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ScoreSystemApplication {
	private int score = 0;

	public static void main(String[] args) {
		SpringApplication.run(ScoreSystemApplication.class, args);
	}

	@GetMapping("/score")
	public int calculateScore(@RequestParam(value = "point") int score) {
		this.score += score;
		return this.score;
	}
}
