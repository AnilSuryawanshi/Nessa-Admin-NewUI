package com.connecticus.admin.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.connecticus.admin.controller.AdminController;
import com.connecticus.admin.dto.LogfileDTO;
import com.connecticus.admin.service.AdminService;
import com.google.gson.Gson;

class ScheduleAtFixedCron extends TimerTask {
	@Autowired
	AdminService adminService;

	private static final Logger slf4jLogger = LoggerFactory.getLogger(AdminController.class);
	Gson gson = new Gson();

	@Override
	public void run() {

		System.out.println("Hi see you after 10 seconds");
		Boolean flag = false;
		// String quection="";
		Properties Proper = new Properties();
		InputStream inputStream = null;
		inputStream = getClass().getClassLoader().getResourceAsStream("mongodbconnection.properties");
		try {
			Proper.load(inputStream);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String filenamepath = Proper.getProperty("filenamepath");
		// log file path
		// String FILENAME =
		// "C://Users//1341//Desktop//FAQ_Bot//log//Jul_training.txt";
		String FILENAME = filenamepath;// log May_activity.log
		List<LogfileDTO> logfileDTOsList = new ArrayList<LogfileDTO>();
		try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {

				String[] uname = sCurrentLine.split(" ", 3);
				String quection = uname[2];
				System.out.println("quection>>>" + quection);
				LogfileDTO logfileDTO = new LogfileDTO();
				logfileDTO.setIpadd("192.168.1.104");
				logfileDTO.setFailQue("How may i help you?");
				logfileDTO.setApplicationname("TechM");
				logfileDTO.setUtterence(quection);
				logfileDTOsList.add(logfileDTO);
				//System.out.println("JSON OUTput==>>" + gson.toJson(logfileDTO));
			}

			// flag = adminService.AddfilereadData(logfileDTOsList);
			System.out.println("save controler test1");

		} catch (Exception e) {
			e.printStackTrace();
			slf4jLogger.error("Error in failurelogcontroller view dashboard", e);
		}
		// return gson.toJson(logfileDTOsList);
		// return flag;

	}

}
