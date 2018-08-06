package com.connecticus.admin.dto;

import java.util.Date;

public class FailureLogDto {

	int id;
	
	private String applicationName;
	
	private String ipAddress;
	
	private String nessaQuestion;
	
	private String userUtterence;
	
	private Date date;
	
	private String sessionId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getNessaQuestion() {
		return nessaQuestion;
	}

	public void setNessaQuestion(String nessaQuestion) {
		this.nessaQuestion = nessaQuestion;
	}

	public String getUserUtterence() {
		return userUtterence;
	}

	public void setUserUtterence(String userUtterence) {
		this.userUtterence = userUtterence;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	
	
}
