package com.connecticus.admin.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection="failurelog")
public class FailureLogModel {

	@Id
	private String id;
	
	@Column(name="applicationname")
	private String applicationName;
	
	@Column(name="ipaddress")
	private String ipAddress;
	
	@Column(name="nessaquestion")
	private String nessaQuestion;
	
	@Column(name="userutterence")
	private String userUtterence;
	
	@Column(name="actionStatus")
	private String actionStatus;
	
	@Column(name="remark")
	private String remark;
	
	@Column(name="date")
	private Date date;
	
	@Column(name="sessionId")
	private String sessionId;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getActionStatus() {
		return actionStatus;
	}

	public void setActionStatus(String actionStatus) {
		this.actionStatus = actionStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
