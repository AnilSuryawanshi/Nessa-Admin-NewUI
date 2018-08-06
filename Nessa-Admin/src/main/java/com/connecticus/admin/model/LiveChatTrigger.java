package com.connecticus.admin.model;
import javax.persistence.Column;
import javax.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection="liveChatTrigger")
public class LiveChatTrigger {
	@Id
	private String id;
	@Column(name="handoverDecision")
	private String handoverDecision;
	
	@Column(name="sessionId")
	private String sessionId;
	
	@Column(name="user")
	private String user;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHandoverDecision() {
		return handoverDecision;
	}

	public void setHandoverDecision(String handoverDecision) {
		this.handoverDecision = handoverDecision;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	
}
