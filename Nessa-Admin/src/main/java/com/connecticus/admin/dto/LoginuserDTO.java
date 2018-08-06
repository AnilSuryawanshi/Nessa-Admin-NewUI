package com.connecticus.admin.dto;

import java.util.Comparator;



public class LoginuserDTO implements Comparator<LoginuserDTO>{

	private String name;
	private int y;
	private String loginUser;
	
	
	
	public String getLoginUser() {
		return loginUser;
	}
	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	@Override
	public int compare(LoginuserDTO o1, LoginuserDTO o2) {
		// TODO Auto-generated method stub
		return o1.getY()-o2.getY();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
