package com.connecticus.admin.dto;

public class UserChannelCountDto {

	private String loginUser;
	private String channel;
	private String userCount;
	private String firstLoginDate;	
	
	public String getFirstLoginDate() {
		return firstLoginDate;
	}
	public void setFirstLoginDate(String firstLoginDate) {
		this.firstLoginDate = firstLoginDate;
	}
	public String getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	private String lastLoginDate;
	
	public String getLoginUser() {
		return loginUser;
	}
	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		channel = channel;
	}
	public String getUserCount() {
		return userCount;
	}
	public void setUserCount(String userCount) {
		this.userCount = userCount;
	}
	
}
