package com.connecticus.admin.model;

import javax.persistence.Column;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection="agentStatus")
public class AgentStatusModel {
	
	@Id
	private String id;
	
	@Column(name="status")
	private String status;
	
	@Column(name="agentId")
	private String agentId;
	
	@Column(name="agentName")
	private String agentName;
	
	@Column(name="sessionId")
	private String sessionId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}


}
