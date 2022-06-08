package com.example.constants;

public enum ApplicationConstants {

	PING_RESPONSE("Working fine");
	
	private String message;
	
	private ApplicationConstants(String msg) {
		this.message = msg;
	}
	
	public String getMessage() {
		return this.message;
	}
}
