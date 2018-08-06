package com.connecticus.admin.model;

import javax.persistence.Column;
import javax.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.connecticus.admin.dto.UserChannelCountDto;

@Document(collection="servicelog")
public class ServiceLogModel {


	
	    private String id;	
		private String applicationName;	
		private String ipAddress;
		private String dateTime;
		private String taskName;	
		private String nessaQuestion;	
		private String userUtterence;	
	    private String serverresponse;   
	    private String serviceName;  
	    private String loginUser;
	    private String channel;
	    private int count;
	    private String feedback;
	    private String sessionId;
		public String getFeedback() {
			return feedback;
		}

		public void setFeedback(String feedback) {
			this.feedback = feedback;
		}

		public String getChannel() {
			return channel;
		}

		public void setChannel(String channel) {
			this.channel = channel;
		}

		public String getDateTime() {
			return dateTime;
		}

		public void setDateTime(String dateTime) {
			this.dateTime = dateTime;
		}

		public String getLoginUser() {
			return loginUser;
		}

		public void setLoginUser(String loginUser) {
			this.loginUser = loginUser;
		}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

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

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
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

	public String getServerresponse() {
		return serverresponse;
	}

	public void setServerresponse(String serverresponse) {
		this.serverresponse = serverresponse;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
}
