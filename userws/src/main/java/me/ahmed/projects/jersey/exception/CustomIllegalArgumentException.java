package me.ahmed.projects.jersey.exception;


public class CustomIllegalArgumentException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5438487718083894444L;

	private int statusCode;

	public CustomIllegalArgumentException(String message, int statusCode) {
		super(message);
		this.statusCode = statusCode;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}


}
