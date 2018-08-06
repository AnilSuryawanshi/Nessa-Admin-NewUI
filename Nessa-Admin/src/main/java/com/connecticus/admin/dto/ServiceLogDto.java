package com.connecticus.admin.dto;


public class ServiceLogDto {

    private int id;	
	private String applicationName;	
	private String ipAddress;
	private String dateTime;
	private String taskName;	
	private String nessaQuestion;	
	private String userUtterence;	
    private String serverresponse;   
    private String serviceName;  
    private String loginUser;
    private int count;
    

    
    
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
