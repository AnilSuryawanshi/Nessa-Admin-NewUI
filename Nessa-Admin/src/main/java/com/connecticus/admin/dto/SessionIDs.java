package com.connecticus.admin.dto;

import java.util.Comparator;

public class SessionIDs 
{
    /**
	 * 
	 */
	
	private String id;
	private String failures;
	private String user;
	private String lastAcess;
	private Long time;
	private int instance;
	private boolean chat;
	private String dislikes;
	private String likes;
	public static Comparator<SessionIDs> StuNameComparator = new Comparator<SessionIDs>() {

		public int compare(SessionIDs s1, SessionIDs s2) {
		   Long StudentName1 = s1.getTime();
		   Long StudentName2 = s2.getTime();

		   //ascending order
		   return StudentName1.compareTo(StudentName2);

		   //descending order
		   //return StudentName2.compareTo(StudentName1);
	    }};
	
	public boolean isChat() {
		return chat;
	}
	public void setChat(boolean chat) {
		this.chat = chat;
	}
	public int getInstance() {
		return instance;
	}
	public void setInstance(int instance) {
		this.instance = instance;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFailures() {
		return failures;
	}
	public void setFailures(String failures) {
		this.failures = failures;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getLastAcess() {
		return lastAcess;
	}
	public void setLastAcess(String lastAcess) {
		this.lastAcess = lastAcess;
	}
	public String getDislikes() {
		return dislikes;
	}
	public void setDislikes(String dislikes) {
		this.dislikes = dislikes;
	}
	public String getLikes() {
		return likes;
	}
	public void setLikes(String likes) {
		this.likes = likes;
	}

   
}