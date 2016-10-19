package me.ahmed.projects.jersey.exception;

public class GenericException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6484205388329892904L;
	
	private int statusCode ;
	
	public GenericException(String message , int statusCode){
		super(message);
		this.statusCode = statusCode ;
	}
	
	 public GenericException(String message, int statusCode ,Throwable cause) {
	        super(message, cause);
	        this.statusCode = statusCode ;
	    }

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

}
