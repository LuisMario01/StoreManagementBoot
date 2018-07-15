package com.store.management.storemanagement.util;

public class MessageWrapper {
	private boolean error;
	private String message;
	
	public MessageWrapper() {}
	
	public MessageWrapper(boolean error, String message) {
		this.error = error;
		this.message = message;
	}
	
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
