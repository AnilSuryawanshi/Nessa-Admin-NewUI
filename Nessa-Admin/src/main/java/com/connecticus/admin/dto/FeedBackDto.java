package com.connecticus.admin.dto;

import java.util.Date;
import java.util.List;

import com.connecticus.admin.model.FeedBackDialog;


public class FeedBackDto {
	
	
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
