package com.connecticus.admin.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection="nessabot")
public class versionModel {

	private String list;
	private String type;
	/*private String name;
	private String managername;
	private String city;
	private String empno;
	private String deparment;
	*/
	public String getList() {
		return list;
	}
	public void setList(String list) {
		this.list = list;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
	

}
