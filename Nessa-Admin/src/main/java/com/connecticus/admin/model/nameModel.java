package com.connecticus.admin.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection="name")
public class nameModel {
	private String list;
	private String type;
	
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
