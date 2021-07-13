package code.resmed.rockpaper.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import code.resmed.rockpaper.model.User;

public interface UserRepository extends JpaRepository<User, UUID> {
	public User findByUsername(String username);
	
	public List<String> getAllUsername();
}
