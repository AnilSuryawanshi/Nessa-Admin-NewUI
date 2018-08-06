package com.connecticus.admin.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.swing.filechooser.FileFilter;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.connecticus.admin.dto.ActivityLog;
import com.connecticus.admin.dto.Data;
import com.connecticus.admin.dto.FailureLogDto;
import com.connecticus.admin.dto.FeedBackDto;
import com.connecticus.admin.dto.GonogoDto;
import com.connecticus.admin.dto.Info;
import com.connecticus.admin.dto.JsonResult;
import com.connecticus.admin.dto.LogfileDTO;
import com.connecticus.admin.dto.Messages;
import com.connecticus.admin.model.FailureLogModel;
import com.connecticus.admin.service.AdminService;
import com.connecticus.admin.util.AdminUtil;
import com.google.gson.Gson;

@RestController
public class FailureLogcontroller {
	@Autowired
	AdminService adminService;
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(AdminController.class);
	Gson gson = new Gson();


	@RequestMapping(value = { "/testingclass" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView Presentation() {
		ModelAndView view = new ModelAndView();
		view.setViewName("AddUser");
		System.out.println("Presentation page is working good ");
		slf4jLogger.error(" IN WELCOME Presentation");
		slf4jLogger.debug("IN WELCOME DEBUG");

		return view;
	}
	
	@RequestMapping(value = { "/weldone" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView hello123() {
		ModelAndView view = new ModelAndView();
		view.setViewName("success");
		slf4jLogger.error(" IN WELCOME ERROR");
		slf4jLogger.debug("IN WELCOME DEBUG");

		return view;
	}
	@RequestMapping(value = { "/insertFailure" }, method = RequestMethod.GET)
	public @ResponseBody JsonResult insertFailureIntoDatabase(){
		JsonResult currencyReportResult = new JsonResult();

		Messages message = new Messages();
		Info info = new Info();
		Data data = new Data();
		String[] shortMonths = new DateFormatSymbols().getShortMonths();
		if( !ObjectUtils.isEmpty(AdminUtil.getMonths() ) ){
	        for (String shortMonth : AdminUtil.getMonths()) {
	        	try{
	        		Properties prop = new Properties();
					InputStream inputStream = null;
					inputStream = getClass().getClassLoader().getResourceAsStream("urlconfig.properties");
					prop.load(inputStream);
					
					String url1 = prop.getProperty("getFailureLogFilePath");
			File folder =new File(url1+shortMonth);
			File[] listOfFiles=null;
			if( folder!=null && folder.exists() ){
				System.out.println("folder"+folder);
				 listOfFiles=folder.listFiles(new FilenameFilter() {
					public boolean accept(File dir, String name) {
						boolean result;
						if(name.contains("failures")){
							result=true;
						}
						else{
							result=false;
						}
						return result;
					}
				});
			}	
				
				
				if( listOfFiles!=null && !ArrayUtils.isEmpty(listOfFiles)) {
					for( File file:listOfFiles ){
						if(file.isFile()){
								
							if(this.failureLogFileRead(file)){
								System.out.println("FileSuccessed"+file.getName());
								
								}else{
								System.out.println("Activity File not dumped"+file.getName());
							}
						}
					}
			}
					
	        }catch(Exception e){
	        	e.printStackTrace();
	        }
	       	}
		}
		message.setChat("false");
		data.setInfo(info);
		data.setError("");
		currencyReportResult.setMessage(message);
		return currencyReportResult;
		
	}
	
	@RequestMapping(value = { "/insertCurrentDateFailure" }, method = RequestMethod.GET)
	public boolean insertCurrentDateFailure(){
		String[] shortMonths = new DateFormatSymbols().getShortMonths();
		
		if( !ObjectUtils.isEmpty(AdminUtil.getMonths() ) ){
	        for (String shortMonth : AdminUtil.getMonths()) {
	        	try{
	        		Properties prop = new Properties();
					InputStream inputStream = null;
					inputStream = getClass().getClassLoader().getResourceAsStream("urlconfig.properties");
					prop.load(inputStream);
					
					String url1 = prop.getProperty("getFailureLogFilePath");
			File folder =new File(url1+shortMonth);
			File[] listOfFiles=null;
			if( folder!=null && folder.exists() ){
				System.out.println("folder"+folder);
				 listOfFiles=folder.listFiles(new FilenameFilter() {
					public boolean accept(File dir, String name) {
						boolean result;
						if(name.contains("training")){
							result=true;
						}
						else{
							result=false;
						}
						return result;
					}
				});
			}	
				
				
				if( listOfFiles!=null && !ArrayUtils.isEmpty(listOfFiles)) {
					for( File file:listOfFiles ){
						if(file.isFile()){
								
							if(this.failureLogFileRead(file)){
								System.out.println("FileSuccessed"+file.getName());
								
								}else{
								System.out.println("Activity File not dumped"+file.getName());
							}
						}
					}
			}
					
	        }catch(Exception e){
	        	e.printStackTrace();
	        }
	       	}
		}
		return false;
		
	}
	
	/*@RequestMapping(value = { "/insert" }, method = RequestMethod.GET)
	public boolean insertFileIntoDatabase(){
		File[] listOfFiles=null;
		if( !ObjectUtils.isEmpty(AdminUtil.getMonths() ) ){
			        for (String shortMonth : AdminUtil.getMonths()) {
			        	
			        	//try{	
					File folder =new File("F://VOLVO/Nessa_V9.4/res/logs/training/"+shortMonth);
					System.out.println("hhhj");
					
					if( folder!=null && folder.exists() ){
						
						listOfFiles=folder.listFiles(new FilenameFilter() {
							public boolean accept(File dir, String name) {
								boolean result;
								if(name.contains("training")){
									result=true;
								}
								else{
									result=false;
								}
								return result;
							}
						});
					}
        	}	}	
	        	
			
				if( listOfFiles!=null && !ArrayUtils.isEmpty(listOfFiles)) {
					for( File file:listOfFiles ){
							if(file.isFile()){
								if(this.failureLogFileRead(file)){
									System.out.println("FileSuccessed"+file.getName());

									}else{
									System.out.println("File not dumped"+file.getName());
								}
							
							}
						}
				}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
		
	}*/
	
	@RequestMapping(value = { "/insertActivity" }, method = RequestMethod.GET)
	public boolean insertActivityIntoDatabase(){
		String[] shortMonths = new DateFormatSymbols().getShortMonths();
		if( !ObjectUtils.isEmpty(AdminUtil.getMonths() ) ){
	        for (String shortMonth : AdminUtil.getMonths()) {
	        	try{
			File folder =new File("D:/CCS/Nessa/Live_Chat/VolvoNessa_V10.3_web/res/logs/activity/"+shortMonth);
			File[] listOfFiles=null;
			if( folder!=null && folder.exists() ){
				System.out.println("folder"+folder);
				 listOfFiles=folder.listFiles(new FilenameFilter() {
					public boolean accept(File dir, String name) {
						boolean result;
						if(name.contains("activity")){
							result=true;
						}
						else{
							result=false;
						}
						return result;
					}
				});
			}	
				
				
				if( listOfFiles!=null && !ArrayUtils.isEmpty(listOfFiles)) {
					for( File file:listOfFiles ){
						if(file.isFile()){
								
							if(this.failureLogActivityFileRead(file)){
								System.out.println("FileSuccessed"+file.getName());
								
								}else{
								System.out.println("Activity File not dumped"+file.getName());
							}
						}
					}
			}
					
	        }catch(Exception e){
	        	e.printStackTrace();
	        }
	       	}
		}
		return false;
		
	}
	public Boolean failureLogActivityFileRead(File file) {
		System.out.println("kkkk"+file);
		
		Boolean flag = false;
		BufferedReader objReader = null;	
		List<ActivityLog> activeLogList = new ArrayList<>();
			try {
				 if(file.exists()&& file.isFile()){
					 
					 System.out.println("fileJava"+file);
					 objReader = new BufferedReader(new FileReader(file));
					 String sCurrentLine;
				while ((sCurrentLine = objReader.readLine()) != null) {
					StringBuilder sb = new StringBuilder();
					sb.append(sCurrentLine);
				       // or
				       //  sb.append(line).append(System.getProperty("line.separator"));
				     
				      sCurrentLine = sb.toString().replaceAll("\\<.*?>","");
					 ///System.out.println("sCurrentLine>>>"+sCurrentLine);
					String[] uname = sCurrentLine.split("domain:");// split for	domain															// domain name
					String domain = uname[1];
					String[] domainname = domain.split(" ");
					String Domain = domainname[0];
					
					
					String[] userData = sCurrentLine.split("user:");// split for clientIP																	// clintIP
					String userSplit = userData[1];
					String[] userSplitPart = userSplit.split(" ");
					String user = userSplitPart[0];
					
					String[] sessionIdData = sCurrentLine.split("sessionId:");// split for clientIP																	// clintIP
					String sessionIdSplit = sessionIdData[1];
					String[] sessionIdSplitPart = sessionIdSplit.split(" ");
					String sessionId = sessionIdSplitPart[0];
					
					String[] clientIPData = sCurrentLine.split("clientIP:");// split	for utterance																	 
					String clientIPDataSplit = clientIPData[1];
					String[] clientIPSplitData = clientIPDataSplit.split(" ");
					String clientIP = clientIPSplitData[0];
					
					
					String[] taskData = sCurrentLine.split("task:");// split	for utterance																	 
					String taskDataDataSplit = taskData[1];
					String[] taskDataSplitData = taskDataDataSplit.split(" ");
					String task = taskDataSplitData[0];
					System.out.println("domain"+domain);
					System.out.println("user"+user);
					System.out.println("sessionId"+sessionId);
					System.out.println("clientIP"+clientIP);
					System.out.println("task"+task);
					
					ActivityLog activeLog = new ActivityLog();
					
					activeLog.setDomain(Domain);
					activeLog.setUser(user);
					activeLog.setSessionId(sessionId);
					activeLog.setClientIP(clientIP);
					activeLog.setTask(task);
					
					Gson gson = new Gson();
					
					activeLogList.add(activeLog);
					System.out.println("JsonData"+gson.toJson(activeLogList));		
				
				}
				
				flag = adminService.insertActivityLogData(activeLogList);		
				
				//File processed =new File("F:\\VOLVO\\Nessa_V9.4\\logs\\training\\"+"Jan"+"\\"+fileNew+"_proccessed"+".log");
				
			/*	if(file.renameTo(file.getAbsolutePath().)){
					System.out.println("rename succes");
				}else{
					System.out.println("rename not succes"+entry.getKey().getPath());
				}  */
			System.out.println("FileDumped Status"+file.getName()+"Status"+flag);
			File newFile= new  File(file.getParentFile()+"/"+file.getName()+"_processed.log");
			System.out.println("fileNEWWWW"+newFile);
			if(file.renameTo(newFile)){
				System.out.println("rename succes");
			}else{
				System.out.println("rename nit succes");
			}  
			System.out.println("CanonicalPath"+file.getParent());
				 }
		}catch(Exception e){
			e.printStackTrace();
		}
			finally{
				if(objReader!=null){
					try {
						objReader.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			return flag;
	}
//	public Boolean failureLogActivityFileRead(File file) {}	
		public Boolean failureLogFileRead(File file) {
			Boolean flag = false;
			BufferedReader objReader = null;	
			List<FailureLogDto> FailureLogDtoList = new ArrayList<FailureLogDto>();
			List<FailureLogDto> logfileDTOsList = new ArrayList<FailureLogDto>();
				try {
					 if(file.exists()&& file.isFile()){
					String strCurrentLine;
				
					System.out.println("fileJava"+file);
					objReader = new BufferedReader(new FileReader(file));
					String sCurrentLine;
					while ((sCurrentLine = objReader.readLine()) != null) {
						StringBuilder sb = new StringBuilder();
						sb.append(sCurrentLine);
					       // or
					       //  sb.append(line).append(System.getProperty("line.separator"));
					     
					      sCurrentLine = sb.toString().replaceAll("\\<.*?>","");
						 ///System.out.println("sCurrentLine>>>"+sCurrentLine);
						String[] uname = sCurrentLine.split("domain:");// split for	domain															// domain name
						String domain = uname[1];
						String[] dt = uname[0].split(",");
						String[] domainname = domain.split(" ");
						String Domain = domainname[0];
						String[] clientIP = sCurrentLine.split("clientIP:");// split for clientIP																	// clintIP
						String IPAddr = clientIP[1];
						String[] IPAddress = IPAddr.split(" ");
						String Ipaddress = IPAddress[0];
						String[] sessionId=sCurrentLine.split("sessionId:");// split for sessionId
						String instanceId=sessionId[1];	
						String[] sessionId1=instanceId.split(" ");
						String instanceID=sessionId1[0];

						String[] utterance = sCurrentLine.split("utterance:");// split	for utterance																	 
						String userUtterance = utterance[1];
						/*String[] date = sCurrentLine.split("");// split	for date																	 
						String date1 = date[1];*/
						System.out.println("Utterance"+userUtterance);
						System.out.println("'"+dt[0]);
				      /*  SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");

			            Date date = formatter.parse(dt[0]);
			            */
			            
			            DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
			            Date date = (Date)formatter.parse(dt[0]);
			            System.out.println("datee"+date);

						FailureLogDto failureLogDto = new FailureLogDto();
						failureLogDto.setApplicationName(Domain);
						failureLogDto.setIpAddress(Ipaddress);
						failureLogDto.setNessaQuestion("How may i help you?"); //is a hardcode value after change dershankar sir 
						failureLogDto.setUserUtterence(	userUtterance);
						failureLogDto.setDate(date);
						failureLogDto.setSessionId(instanceID);
						Gson gson = new Gson();
						
						logfileDTOsList.add(failureLogDto);
						System.out.println("JsonData"+gson.toJson(logfileDTOsList));
						//System.out.println("JSON OUTput==>>" + gson.toJson(failureLogDto));		
					}
					flag = adminService.AddfilereadData(logfileDTOsList);			
					
				System.out.println("FileDumped Status"+file.getName()+"Status"+flag);
				File newFile= new  File(file.getParentFile()+"/"+file.getName()+"_processed.log");
				System.out.println("fileNEWWWW"+newFile);
				if(file.renameTo(newFile)){
					System.out.println("rename succes");
				}else{
					System.out.println("rename nit succes");
				}  
				System.out.println("CanonicalPath"+file.getParent());
					 }
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(objReader!=null){
					try {
						objReader.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
				return flag;
		}
		
		@RequestMapping(value = "/getFailureLogById", method = RequestMethod.POST)
		@ResponseBody FailureLogModel getFailureLogById(@RequestParam String sessionId) {
			String fullName = "";
			try {
				FailureLogModel failureLogModel = new FailureLogModel();

				failureLogModel = adminService.getFailureLogBySessionId(sessionId);

			
				return failureLogModel;
			} catch (Exception e) {
				e.printStackTrace();
				slf4jLogger.error("Error in AdmniController getDisikeList", e);
				return null;
			}

		}

}
