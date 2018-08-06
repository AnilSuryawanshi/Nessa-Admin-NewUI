package com.connecticus.admin.dto;

public class FailureLogActionStatusDto {
private String actionStatus;

public String getActionStatus() {
	return actionStatus;
}
public void setActionStatus(String actionStatus) {
	this.actionStatus = actionStatus;
}


public String getCompletedCount() {
	return completedCount;
}
public void setCompletedCount(String completedCount) {
	this.completedCount = completedCount;
}
private String completedCount;

}

