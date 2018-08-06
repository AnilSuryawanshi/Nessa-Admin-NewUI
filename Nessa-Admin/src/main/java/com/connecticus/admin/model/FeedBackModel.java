package com.connecticus.admin.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "feedBackDialog")
public class FeedBackModel {
	
	@Id
	String id;
	String user;
	String sessionId;
	Date currentTime;
	List<FeedBackDialog> feedBAckDialogList;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public Date getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(Date currentTime) {
		this.currentTime = currentTime;
	}
	public List<FeedBackDialog> getFeedBAckDialogList() {
		return feedBAckDialogList;
	}
	public void setFeedBAckDialogList(List<FeedBackDialog> feedBAckDialogList) {
		this.feedBAckDialogList = feedBAckDialogList;
	}
	
	
	


}
