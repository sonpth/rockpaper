package code.resmed.rockpaper.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import code.resmed.rockpaper.model.User;

@SpringBootTest
public class UserRepositoryTest {
	@Autowired
	private UserRepository harness;
	
	@Test
	public void testGetUserByName() {
		User user = harness.findByUsername("admin");
		assertNotNull(user);
	}
}
