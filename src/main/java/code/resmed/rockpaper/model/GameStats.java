package code.resmed.rockpaper.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GameStats {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column
	private int gamePlayed;
	
	@Column
	private int numberOfWin;
	
	@Column
	private int numberOfLose;
	
	@Column
	private int numberOfDraw;

	/* Auto-generated */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getGamePlayed() {
		return gamePlayed;
	}

	public void setGamePlayed(int gamePlayed) {
		this.gamePlayed = gamePlayed;
	}

	public int getNumberOfWin() {
		return numberOfWin;
	}

	public void setNumberOfWin(int numberOfWin) {
		this.numberOfWin = numberOfWin;
	}

	public int getNumberOfLose() {
		return numberOfLose;
	}

	public void setNumberOfLose(int numberOfLose) {
		this.numberOfLose = numberOfLose;
	}

	public int getNumberOfDraw() {
		return numberOfDraw;
	}

	public void setNumberOfDraw(int numberOfDraw) {
		this.numberOfDraw = numberOfDraw;
	}
}
