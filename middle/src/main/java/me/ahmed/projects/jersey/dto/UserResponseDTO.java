package me.ahmed.projects.jersey.dto;

public class UserResponseDTO extends BaseResponseDTO {

	private String sessionKey;
	private String userId;

	public UserResponseDTO() {
	}

	public UserResponseDTO(int code, String text, String sessionKey, String userId) {
		super(code, text);
		this.sessionKey = sessionKey;
		this.userId = userId;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
