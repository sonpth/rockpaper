package code.resmed.rockpaper.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import code.resmed.rockpaper.model.Game.GameResult;
import code.resmed.rockpaper.model.Game.Shape;

@SpringBootTest
public class GameServiceTest {
	@Autowired
	private GameService harness;

	@Test
	public void testDecide() {
		assertTrue(GameResult.DRAW == harness.decide(Shape.PAPER, Shape.PAPER));
		assertTrue(GameResult.WIN == harness.decide(Shape.PAPER, Shape.ROCK));
		assertTrue(GameResult.LOSE == harness.decide(Shape.PAPER, Shape.SCISSORS));
	}
	
	@Test
	public void testGenerateShape() {
		boolean[] expected = new boolean[3];
		final int numberOfTries = 10;
		
		for (int i = 0; i < numberOfTries; i++) {
			expected[harness.generateShape().ordinal()] = true;
		}
		
		for (int i = 0; i < 3; i++) {
			assertTrue(expected[i], "Expected " + Shape.values()[i] + " has been generated");
		}
	}

}
