package code.resmed.rockpaper.service;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import code.resmed.rockpaper.model.Game;
import code.resmed.rockpaper.model.Game.GameResult;
import code.resmed.rockpaper.model.Game.Shape;
import code.resmed.rockpaper.model.GameStats;
import code.resmed.rockpaper.model.User;
import code.resmed.rockpaper.repository.GameStatsRepository;
import code.resmed.rockpaper.repository.UserRepository;

@Service
public class GameService {
	private @Autowired UserRepository userRepo;
	private @Autowired GameStatsRepository gameRepo;

	public Game process(Shape userShape) {
		Game game = new Game();
		if (userShape == null) {
			userShape = generateShape();
		}
		game.setUserShape(userShape);
		
		Shape serverShape = generateShape();
		game.setServerShape(serverShape);
		
		GameResult gameResult = decide(userShape, serverShape);
		game.setGameResult(gameResult);
		
		GameStats gameStats = getGameStats();
		gameStats.setGamePlayed(gameStats.getGamePlayed() + 1);
		
		switch (gameResult) {
		case WIN:
			gameStats.setNumberOfWin(gameStats.getNumberOfWin() + 1);
			break;
		case LOSE:
			gameStats.setNumberOfLose(gameStats.getNumberOfLose() + 1);
			break;
		case DRAW:
			gameStats.setNumberOfDraw(gameStats.getNumberOfDraw() + 1);
		}
		gameRepo.save(gameStats);
		
		return game;
	}
	
	public GameStats getGameStats() {
		final String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		User user = userRepo.findByUsername(username);
		GameStats gameStats = user.getGameStats();
		return gameStats;
	}
	
	protected GameResult decide(Shape userShape, Shape generatedShape) {
		if (userShape == generatedShape) {
			return GameResult.DRAW;
		}
		
		switch (userShape) {
		case ROCK:
			return generatedShape == Shape.PAPER ? GameResult.LOSE : GameResult.WIN;
		case PAPER:
			return generatedShape == Shape.SCISSORS ? GameResult.LOSE : GameResult.WIN;
		default:
			return generatedShape == Shape.ROCK ? GameResult.LOSE : GameResult.WIN;
		}
	}
	
	protected Shape generateShape() {
		int r = ThreadLocalRandom.current().nextInt(0, 3);
		return Shape.values()[r];
	}
}
