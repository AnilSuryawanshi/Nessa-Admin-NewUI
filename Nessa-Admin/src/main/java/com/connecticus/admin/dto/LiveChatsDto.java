package com.connecticus.admin.dto;

import java.util.Date;
import java.util.List;


public class LiveChatsDto {
	
	String id;
	Integer chatId;
	String liveAgent;
	String user;
	Date createdOn;
	String instanceID;
	Date timeStamp;
	String timeStamp1;
	Date currentTime;
	String status;
	String msgRead;
	List<LiveChatsMessage> livechatsMessage;
	public String getLiveAgent() {
		return liveAgent;
	}
	public void setLiveAgent(String liveAgent) {
		this.liveAgent = liveAgent;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public String getInstanceID() {
		return instanceID;
	}
	public void setInstanceID(String instanceID) {
		this.instanceID = instanceID;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getTimeStamp1() {
		return timeStamp1;
	}
	public void setTimeStamp1(String timeStamp1) {
		this.timeStamp1 = timeStamp1;
	}
	public Date getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(Date currentTime) {
		this.currentTime = currentTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMsgRead() {
		return msgRead;
	}
	public void setMsgRead(String msgRead) {
		this.msgRead = msgRead;
	}
	
	public List<LiveChatsMessage> getLivechatsMessage() {
		return livechatsMessage;
	}
	public void setLivechatsMessage(List<LiveChatsMessage> livechatsMessage) {
		this.livechatsMessage = livechatsMessage;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getChatId() {
		return chatId;
	}
	public void setChatId(Integer chatId) {
		this.chatId = chatId;
	}
	
	

}
