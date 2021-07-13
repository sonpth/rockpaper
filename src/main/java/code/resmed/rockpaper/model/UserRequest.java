package code.resmed.rockpaper.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UserRequest {
	@ApiModelProperty(example = "admin", position =  1)
	private String username;

	@ApiModelProperty(example = "123456", position = 2)
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
