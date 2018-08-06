package com.connecticus.admin.dto;

public class UserSpecificChannelDto {

	private String userLogin;
	private String lastLogin;
	private String fullName;
    private String firstLogin;

    private String web;
    
    private String mobile;

    private String skype;
    private int totalNoLogin;
	public String getUserLogin() {
		return userLogin;
	}
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}
	public String getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getFirstLogin() {
		return firstLogin;
	}
	public void setFirstLogin(String firstLogin) {
		this.firstLogin = firstLogin;
	}
	public String getWeb() {
		return web;
	}
	public void setWeb(String web) {
		this.web = web;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getSkype() {
		return skype;
	}
	public void setSkype(String skype) {
		this.skype = skype;
	}
	public int getTotalNoLogin() {
		return totalNoLogin;
	}
	public void setTotalNoLogin(int totalNoLogin) {
		this.totalNoLogin = totalNoLogin;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
