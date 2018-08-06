package com.connecticus.admin.util;

public enum AdminConstant {
 SKYPE("skype"),
WEB("web"),
LIKE(""),
DISLIKE("");
private String constValue;

AdminConstant(String adminConstValue){
	this.constValue=adminConstValue;
}

public String getConstValue(){
	return constValue;
}
	
}
