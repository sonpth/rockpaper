package code.resmed.rockpaper.model;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@NamedQueries({
	@NamedQuery(name = "User.getAllUsername", query = "select u.username from User u")
})
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    @Type(type = "uuid-char")
    private UUID id;
    
    @Column(nullable = false)
    private String username;
    
	@JsonProperty
    @Column(nullable = false)
    private String password;
    
	@OneToOne(cascade = CascadeType.ALL)
	private GameStats gameStats;
    
    public User() {
    	this.gameStats = new GameStats();
    }

    public User(String username, String password) {
    	this();
    	this.username = username;
    	this.password = password;
    }

	/* Auto-generated */
    public UUID getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public GameStats getGameStats() {
		return gameStats;
	}
}
