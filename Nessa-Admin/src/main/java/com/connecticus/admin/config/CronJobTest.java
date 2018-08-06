package com.connecticus.admin.config;

import java.util.Timer;

public class CronJobTest {
	 public static void main(String[] args){

	     Timer t = new Timer();
	     ScheduleAtFixedCron mTask = new ScheduleAtFixedCron();
	     // This task is scheduled to run every 10 seconds

	   //  t.scheduleAtFixedRate(mTask, 0, 50000);
	     
	   }
}
