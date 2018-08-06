package com.connecticus.admin.dto;

import java.util.List;



public class JsonResult {

	
	private Messages message;
	private List<Messages> messages;
	public Messages getMessage() {
		return message;
	}

	public void setMessage(Messages message) {
		this.message = message;
	}
	public List<Messages> getMessages() {
		return messages;
	}

	public void setMessages(List<Messages> messages) {
		this.messages = messages;
	}
	
	
}
