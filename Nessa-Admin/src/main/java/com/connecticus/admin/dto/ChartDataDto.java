package com.connecticus.admin.dto;

import java.util.Comparator;

public class ChartDataDto implements Comparator<ChartDataDto>{

	private String name;
	private int y;
	private String serviceName;
	
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	@Override
	public int compare(ChartDataDto o1, ChartDataDto o2) {
		// TODO Auto-generated method stub
		return o1.getY()-o2.getY();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
}
