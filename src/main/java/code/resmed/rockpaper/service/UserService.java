package code.resmed.rockpaper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import code.resmed.rockpaper.model.User;
import code.resmed.rockpaper.repository.UserRepository;

@Service
public class UserService {
	private @Autowired UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public boolean createUser(String username, String password) {
		if (!StringUtils.hasText(password)) return false;
		
		User user = userRepo.findByUsername(username);
		if (user != null) return false;
		
		user = new User(username, passwordEncoder.encode(password));
		userRepo.save(user);
		return true;
	}
	
	public List<String> getAllUsername() {
		return userRepo.getAllUsername();
	}
}
