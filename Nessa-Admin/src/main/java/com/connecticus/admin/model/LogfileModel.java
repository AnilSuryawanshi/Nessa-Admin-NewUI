package com.connecticus.admin.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection="filelog")
public class LogfileModel {
	
	
	private String ipadd;
	private String applicationname;
	private String failQue;
	private String utterence;
	
	
	public String getIpadd() {
		return ipadd;
	}
	public void setIpadd(String ipadd) {
		this.ipadd = ipadd;
	}
	public String getApplicationname() {
		return applicationname;
	}
	public void setApplicationname(String applicationname) {
		this.applicationname = applicationname;
	}
	public String getFailQue() {
		return failQue;
	}
	public void setFailQue(String failQue) {
		this.failQue = failQue;
	}
	public String getUtterence() {
		return utterence;
	}
	public void setUtterence(String utterence) {
		this.utterence = utterence;
	}
	
	
}
