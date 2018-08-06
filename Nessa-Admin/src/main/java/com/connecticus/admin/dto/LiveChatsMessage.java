package com.connecticus.admin.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LiveChatsMessage {
	
	Integer chatId;
	String liveAgentMessage;
	String userMessage;
	String user;
	Date currentTime1;
	String currentTime;
	Date timeStamp1;
	String msgRead;
	public Integer getChatId() {
		return chatId;
	}
	public void setChatId(Integer chatId) {
		this.chatId = chatId;
	}
	public String getLiveAgentMessage() {
		return liveAgentMessage;
	}
	public void setLiveAgentMessage(String liveAgentMessage) {
		this.liveAgentMessage = liveAgentMessage;
	}
	public String getUserMessage() {
		return userMessage;
	}
	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	public Date getCurrentTime1() {
		return currentTime1;
	}
	public void setCurrentTime1(Date currentTime1) {
		this.currentTime1 = currentTime1;
	}
	public String getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
		
			setCurrentTime1(new Date(Long.parseLong(currentTime)*1000));
		
	}
	public Date getTimeStamp1() {
		return timeStamp1;
	}
	public void setTimeStamp1(Date timeStamp1) {
		this.timeStamp1 = timeStamp1;
	}
	public String getMsgRead() {
		return msgRead;
	}
	public void setMsgRead(String msgRead) {
		this.msgRead = msgRead;
	}
	
	

}
