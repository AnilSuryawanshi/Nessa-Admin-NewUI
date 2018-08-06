package com.connecticus.admin.model;
import javax.persistence.Column;
import javax.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.connecticus.admin.dto.ActivityLog;

@Document (collection="activityLog")
public class ActivityLogModel {
	@Id
	private String id;
	@Column(name="domain")
	private String domain;
	
	@Column(name="user")
	private String user;
	
	private String sessionId;
	
	@Column(name="clientIP")
	private String clientIP;
	
	@Column(name="task")
	private String task;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getClientIP() {
		return clientIP;
	}

	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public boolean insertActivityLogData(ActivityLog activityLog) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
