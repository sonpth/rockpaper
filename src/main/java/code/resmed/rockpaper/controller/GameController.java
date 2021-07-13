package code.resmed.rockpaper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import code.resmed.rockpaper.model.Game;
import code.resmed.rockpaper.model.Game.Shape;
import code.resmed.rockpaper.model.GameStats;
import code.resmed.rockpaper.model.ServiceResponse;
import code.resmed.rockpaper.model.UserRequest;
import code.resmed.rockpaper.service.GameService;
import code.resmed.rockpaper.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/rest")
@Api
public class GameController {
	private @Autowired GameService gameService;
	private @Autowired UserService userService;
		
	@PostMapping("/user/register")
	@ApiOperation(value = "Create user",  notes = "This method creates a new user")
	public ServiceResponse<String> register(@RequestBody UserRequest userRequest){
		ServiceResponse<String> response = new ServiceResponse<>();
		
		if (!userService.createUser(userRequest.getUsername(), userRequest.getPassword())) {
			response.setStatus(ServiceResponse.Status.FAILED);
			response.setMessage("Failed to register, please check your information");
		}
		
		return response;
	}
	
	@GetMapping("/user/list")
	@ApiOperation(value = "List users",  notes = "This method returns the list of users")
	public ServiceResponse<List<String>> userList(){
		ServiceResponse<List<String>> response = new ServiceResponse<>();
		
		response.setPayload(userService.getAllUsername());
		
		return response;
	}
	
	@PostMapping("/game/play")
	@ApiOperation(value = "Play a game",  notes = "This method is used when user decide what shape they are going to use")
	public ServiceResponse<Game> playGame(@RequestBody Shape userShape){
		ServiceResponse<Game> response = new ServiceResponse<>();
		
		response.setPayload(gameService.process(userShape));
		
		return response;
	}
	
	@PostMapping("/game/feelLucky")
	@ApiOperation(value = "I am feel lucky",  notes = "This method is used when user decide to randomize their choice")
	public ServiceResponse<Game> feelLucky(){
		ServiceResponse<Game> response = new ServiceResponse<>();

		response.setPayload(gameService.process(null));
		
		return response;
	}

	
	@GetMapping("/game/stats")
	@ApiOperation(value = "Get stats",  notes = "This method is responsible for returning the stats for the user who called this method")
	public ServiceResponse<GameStats> getStats(){
		ServiceResponse<GameStats> response = new ServiceResponse<>();
		
		response.setPayload(gameService.getGameStats());
		
		return response;
	}
}
