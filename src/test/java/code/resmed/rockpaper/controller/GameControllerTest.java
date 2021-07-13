package code.resmed.rockpaper.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import code.resmed.rockpaper.RockPaperApp;
import code.resmed.rockpaper.model.ServiceResponse;
import code.resmed.rockpaper.model.UserRequest;

@SpringBootTest(classes = RockPaperApp.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@Tag("IntegrationTest")
public class GameControllerTest {
	@LocalServerPort
	private String port;
	
	private RestTemplate restTemplate = new RestTemplate();
	
	private String getBaseRestUrl() {
		return String.format("http://localhost:%s/rest", port);
	}
	
	private HttpHeaders headers;
	
	@BeforeEach
	private void init() {
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBasicAuth("admin", "123456");
	}
	
	@Test
	public void testInvalidRegister() {
		headers = new HttpHeaders();
		headers.clearContentHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		UserRequest user = new UserRequest();
		user.setUsername("validUsername");
		HttpEntity<UserRequest> request = new HttpEntity<UserRequest>(user, headers);
		
		ServiceResponse<?> response = restTemplate.postForObject(getBaseRestUrl() + "/user/register", request, ServiceResponse.class);
		assertEquals(response.getStatus(), ServiceResponse.Status.FAILED);
	}
	
	@Test
	public void testPlayInvalidShape() {
		HttpEntity<String> request = new HttpEntity<String>("", headers);

		try {
			restTemplate.postForObject(getBaseRestUrl() + "/game/play", request, String.class);
			fail("Expected 400");
		} catch (HttpStatusCodeException e) {
			assertEquals(400, e.getRawStatusCode());
		}
	}
}
