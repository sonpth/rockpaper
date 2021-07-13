package code.resmed.rockpaper.model;

public class Game {
	public enum Shape {
		ROCK,
		PAPER,
		SCISSORS
	}
	
	public enum GameResult {
		WIN,
		LOSE,
		DRAW
	}

	private Shape userShape;
	private Shape serverShape;
	private GameResult gameResult;
	
	/* Auto-generated */
	public Shape getUserShape() {
		return userShape;
	}
	public void setUserShape(Shape userShape) {
		this.userShape = userShape;
	}
	public Shape getServerShape() {
		return serverShape;
	}
	public void setServerShape(Shape serverShape) {
		this.serverShape = serverShape;
	}
	public GameResult getGameResult() {
		return gameResult;
	}
	public void setGameResult(GameResult gameResult) {
		this.gameResult = gameResult;
	}
}
