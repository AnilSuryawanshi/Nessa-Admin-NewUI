
package com.connecticus.admin.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.ConnectException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
//ldap import
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.servlet.ServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.connecticus.admin.dto.ActiveSession;
import com.connecticus.admin.dto.AdduserDTO;
import com.connecticus.admin.dto.ChartDataDto;
import com.connecticus.admin.dto.Data;
import com.connecticus.admin.dto.FailureLogActionStatusDto;
import com.connecticus.admin.dto.FeedBackDto;
import com.connecticus.admin.dto.FeedBackDialog;
import com.connecticus.admin.dto.Info;
import com.connecticus.admin.dto.JsonResult;
import com.connecticus.admin.dto.Ldapuser;
import com.connecticus.admin.dto.LikeDislikeDto;
import com.connecticus.admin.dto.LiveAgentCountDTO;
import com.connecticus.admin.dto.LiveChatUser;
import com.connecticus.admin.dto.LiveChatsDto;
import com.connecticus.admin.dto.LiveChatsMessage;
import com.connecticus.admin.dto.Messages;
import com.connecticus.admin.dto.Result;
import com.connecticus.admin.dto.ServiceLogChannelDto;
import com.connecticus.admin.dto.ServiceLogDto;
import com.connecticus.admin.dto.SessionIDs;
import com.connecticus.admin.dto.UserSpecificChannelDto;
import com.connecticus.admin.model.Agent;
import com.connecticus.admin.model.AgentStatusModel;
import com.connecticus.admin.model.FailureLogModel;
import com.connecticus.admin.model.FeedBackModel;
import com.connecticus.admin.model.ServiceLogModel;
import com.connecticus.admin.service.AdminService;
import com.connecticus.admin.util.AdminConstant;
import com.connecticus.admin.util.AdminUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

/**
 * For Email Sending functionality ,logging of service call and failure logs
 * 
 * @author vaibhav
 * 
 */
@RestController
@PropertySource("classpath:urlconfig.properties")
public class AdminController {

	@Autowired
	AdminService adminService;
	
	@Autowired
	Environment env;
	
	Gson gson = new Gson();

	private static final Logger slf4jLogger = LoggerFactory.getLogger(AdminController.class);

	/**
	 * For Loading Welcome Page
	 * 
	 * @return view
	 */
	private HttpHeaders createHeaders() {
		HttpHeaders headers = new HttpHeaders() {
			private static final long serialVersionUID = 1L;

		};
		headers.add("Content-Type", "application/json");
		headers.add("Accept", "application/json");
		return headers;
	}
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public ModelAndView loadWelcomePage() {

		ModelAndView view = new ModelAndView();
		view.setViewName("success");
		slf4jLogger.info(" IN WELCOME");

		return view;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {

		ModelAndView view = new ModelAndView();
		view.setViewName("login");
		slf4jLogger.info(" IN WELCOME");

		return view;
	}
	
	
	@RequestMapping(value ="/getaddUser", method = RequestMethod.GET)
	public ModelAndView Adduser() {

		ModelAndView view = new ModelAndView();
		view.setViewName("AddUser");
		slf4jLogger.info(" IN WELCOME");

		return view;
	}
	
	
	
	/**
	 * For Sending Email if emailServiceActive is true in configuration file and
	 * record log to database if failureLogService is true in configuration
	 * file.
	 * 
	 * @return true
	 */
	@RequestMapping(value = "/sendMail", method = RequestMethod.POST)
	public @ResponseBody String recordLog(ServletRequest request, @RequestBody String emailBody) {

		slf4jLogger.info("IN Admin Controller: recordLog ");
		System.out.println("emailBody::"+emailBody);
		try {

			Properties prop = new Properties();
			prop.load(new FileInputStream(
					new File(request.getServletContext().getRealPath("/WEB-INF/emailConfiguration.properties"))));

			String emailServiceActive = prop.getProperty("emailServiceActive");
			String failureLogService = prop.getProperty("failureLogService");

			if (emailServiceActive.equals("true")) {
               System.out.println("in mail controller:");
				String configurationProperty = prop.getProperty("configuration");
				String emailId = prop.getProperty("userName");
				String password = prop.getProperty("password");
				adminService.EmailService(emailBody, configurationProperty, emailId, password);
			}
			if (failureLogService.equals("true")) {

				adminService.FailureLog(emailBody);
			}


			return "true"; 

		} catch (Exception e) {
			e.printStackTrace();
			slf4jLogger.error("Error in AdmniController", e);
			return "false";
		}
	}

	@RequestMapping(value = "/getFailureLog", method = RequestMethod.GET)
	ModelAndView getFailureLog() {
		ModelAndView mv = new ModelAndView("FailureLogList");
		try {

			List<FailureLogModel> failureLogList = adminService.getFailureLogList();
			
			 Collections.reverse(failureLogList);
			
			mv.addObject("failureLogList", failureLogList);
			return mv;
		} catch (Exception e) {

			slf4jLogger.error("Error in AdmniController fetch failureLogList", e);
			return mv;
		}

	}

	
	/// end vaibhav change
	
	@RequestMapping(value = "/updateRemark", method = RequestMethod.POST)
	boolean updateFailureLogRecord(@RequestBody String failureLogModel) {

		try {
			System.out.println("testdata"+failureLogModel);
			FailureLogModel logModel = new FailureLogModel();
			ObjectMapper failureLogObj = new ObjectMapper();
			logModel = failureLogObj.readValue(failureLogModel, FailureLogModel.class);

			FailureLogModel failureLogModelData = adminService.getFailureLogById(logModel.getId());

			failureLogModelData.setActionStatus(logModel.getActionStatus());
			failureLogModelData.setRemark(logModel.getRemark());

			boolean returndata = adminService.updateFailureLogRecord(failureLogModelData);

			return returndata;
		} catch (Exception e) {

			slf4jLogger.error("Error in AdmniController update failureLog", e);
			return false;
		}
	}

	@RequestMapping(value = "/getFailureLogComp", method = RequestMethod.GET)
	ModelAndView getFailureLogCompletedRejected() {
		ModelAndView mv = new ModelAndView("FailureComRej");
		try {

			List<FailureLogModel> failureLogList = adminService.getFailureLogCompletedRejectedList();
			mv.addObject("failureLogList", failureLogList);
			
		} catch (Exception e) {

			slf4jLogger.error("Error in AdmniController fetch failureLogList", e);
		}
			return mv;
	}
	@RequestMapping(value = { "/index"	 }, method = RequestMethod.GET)
	ModelAndView dashboard() {
		ModelAndView mv = new ModelAndView("index");
		try {
			 /*Timer t = new Timer();
		     ScheduleAtFixedCron mTask = new ScheduleAtFixedCron();
		     // This task is scheduled to run every 10 seconds

		     t.scheduleAtFixedRate(mTask, 0, 10000*60*60*12);
			*/
			System.out.println("hello welcome demo ##########################################");
			List<String> uniqueUserList = new ArrayList<String>();
			uniqueUserList = adminService.getUniqueListUser();
			List<ChartDataDto> serviceLogList = adminService.getServiceLogListByCount();
			List<ServiceLogChannelDto> serviceLogChannelData = adminService.getNoOfUserEachChannel();

			String realisticFailureCount = "";

			List<LikeDislikeDto> serviceLogUserSpecificList= new ArrayList<>();
			String like="";
			String disLike="";
			/* code for count user list name login start by VA */

			try {
				serviceLogUserSpecificList = adminService.getLikeDisLike();
				List<FailureLogActionStatusDto> failureLogList = adminService.getFailureLogCompletedRejectedCount();
				if(CollectionUtils.isNotEmpty(failureLogList)){
					realisticFailureCount=failureLogList.get(0).getCompletedCount();
				}
				System.out.println("userList"+uniqueUserList.size());

				if( CollectionUtils.isNotEmpty((serviceLogUserSpecificList) )) {
					for(LikeDislikeDto serviceLogUserSpecific:serviceLogUserSpecificList){
						if(!serviceLogUserSpecific.getFeedback().equals(null)){
							if(serviceLogUserSpecific.getFeedback().trim().equals("like")){
								like = serviceLogUserSpecific.getFeedbackCount();
						}else if(serviceLogUserSpecific.getFeedback().equals("dislike")){
							disLike =serviceLogUserSpecific.getFeedbackCount();
						}
					}
					}
				}
				if(CollectionUtils.isNotEmpty(failureLogList)){
					realisticFailureCount=failureLogList.get(0).getCompletedCount();
				}
				
				 				System.out.println("cgfg"+realisticFailureCount);
								mv.addObject("realisticCount", realisticFailureCount);

				System.out.println("userList"+uniqueUserList.size());
				mv.addObject("like", like);
				int retentionRate= (int)this.getCountUserRetension();
				mv.addObject("retentionRate",retentionRate);
				mv.addObject("disLike", disLike);
				mv.addObject("userCount", uniqueUserList.size());
			} catch (Exception e) {
				e.printStackTrace();
			}
			/* code for count user list name login send By VA */
			mv.addObject("serviceLogChannel", serviceLogChannelData);
			mv.addObject("serviceLogList", serviceLogList);
		} catch (Exception e) {
			slf4jLogger.error("Error in AdmniController view dashboard", e);
		}
		return mv;
	}
	
	@RequestMapping(value = { "/dashboard", "/" }, method = RequestMethod.GET)
	ModelAndView viewDashboard() {
		ModelAndView mv = new ModelAndView("DashBoard");
		try {
			 /*Timer t = new Timer();
		     ScheduleAtFixedCron mTask = new ScheduleAtFixedCron();
		     // This task is scheduled to run every 10 seconds

		     t.scheduleAtFixedRate(mTask, 0, 10000*60*60*12);
			*/
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers = this.createHeaders();

			System.out.println("hello welcome demo ##########################################");
			List<String> uniqueUserList = new ArrayList<String>();
			uniqueUserList = adminService.getUniqueListUser();
			List<ChartDataDto> serviceLogList = adminService.getServiceLogListByCount();
			List<ServiceLogChannelDto> serviceLogChannelData = adminService.getNoOfUserEachChannel();

			String realisticFailureCount = "";

			List<LikeDislikeDto> serviceLogUserSpecificList= new ArrayList<>();
			List<LiveAgentCountDTO> liveAgentCountDTO=new ArrayList<>();

			String like="";
			String disLike="";
			/* code for count user list name login start by VA */
			//	try{
				
			try {
				String url= env.getProperty("getActiveSessionNessaBot");
				System.out.println("url>>>"+url);
				/*
				System.setProperty("javax.net.ssl.keyStore", "/WEB-INF/myhmi.key");
				System.setProperty("javax.net.ssl.keyStorePassword", "naturaldialog");
				*/
				HttpEntity<ActiveSession> response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(headers),
						ActiveSession.class);

					String activeSession= response.getBody().getStatus().getCurrentSessions();
				
				serviceLogUserSpecificList = adminService.getLikeDisLike();
				long count=adminService.getLiveAgentCount();
				long count1=adminService.gethandOverUserCount();

				List<FailureLogActionStatusDto> failureLogList = adminService.getFailureLogCompletedRejectedCount();
				if(CollectionUtils.isNotEmpty(failureLogList)){
					realisticFailureCount=failureLogList.get(0).getCompletedCount();
				}
				System.out.println("userList"+uniqueUserList.size());

				if( CollectionUtils.isNotEmpty((serviceLogUserSpecificList) )) {
					for(LikeDislikeDto serviceLogUserSpecific:serviceLogUserSpecificList){
						if(!serviceLogUserSpecific.getFeedback().equals(null)){
							if(serviceLogUserSpecific.getFeedback().trim().equals("like")){
								like = serviceLogUserSpecific.getFeedbackCount();
						}else if(serviceLogUserSpecific.getFeedback().equals("dislike")){
							disLike =serviceLogUserSpecific.getFeedbackCount();
						}
					}
					}
				}
				if(CollectionUtils.isNotEmpty(failureLogList)){
					realisticFailureCount=failureLogList.get(0).getCompletedCount();
				}
				
				 				System.out.println("cgfg"+realisticFailureCount);
								mv.addObject("realisticCount", realisticFailureCount);

				System.out.println("userList"+uniqueUserList.size());
				mv.addObject("like", like);
				int retentionRate= (int)this.getCountUserRetension();
				mv.addObject("retentionRate",retentionRate);
				mv.addObject("disLike", disLike);
				mv.addObject("userCount", uniqueUserList.size());
				mv.addObject("activeSession", activeSession);
				mv.addObject("count",count);
				mv.addObject("count1",count1);


			} catch (Exception e) {
				e.printStackTrace();
			}
			/* code for count user list name login send By VA */
			mv.addObject("serviceLogChannel", serviceLogChannelData);
			mv.addObject("serviceLogList", serviceLogList);
		} catch (Exception e) {
			slf4jLogger.error("Error in AdmniController view dashboard", e);
		}
		return mv;
	}
	
	// save all record
	@RequestMapping(value = "/adduser", method = RequestMethod.POST)
	public ModelAndView saveQuestionAndAnswer(@ModelAttribute AdduserDTO adduserDTO) {
		ModelAndView mv = new ModelAndView();
		boolean flag = false;
		try {
			String fname = adduserDTO.getFullname();
			String uid = adduserDTO.getUid();
			String email = adduserDTO.getEmail();
			String mobile = adduserDTO.getMobile();
			String passwd = adduserDTO.getPassword();
			String cpasswd = adduserDTO.getCpassword();
			String role = adduserDTO.getRole();

			System.out.println("full name===>" + fname);
			System.out.println("uid name===>" + uid);
			System.out.println("email===>" + email);
			System.out.println("mobile===>" + mobile);
			System.out.println("passwd===> " + passwd);
			System.out.println("cpasswd==>" + cpasswd);
			System.out.println("role==>" + role);

			Properties LDAPconnection = ldapconnection();

			// for user
			if (role.equals("User")) {

				System.out.println("=====>>user role is working good");

				String entryDN = "uid=" + uid + ",ou=users,ou=system";
				// String entryDN = "uid="+uid+",uid=admin,ou=system";
				Attribute cn = new BasicAttribute("cn", fname);
				Attribute sn = new BasicAttribute("sn", uid);
				Attribute mail = new BasicAttribute("mail", email);
				Attribute phone = new BasicAttribute("mobile", mobile);
				Attribute UserPassword = new BasicAttribute("userPassword", passwd);
				Attribute displayName = new BasicAttribute("displayName", passwd);
				Attribute oc = new BasicAttribute("objectClass");
				oc.add("top");
				oc.add("person");
				oc.add("organizationalPerson");
				oc.add("inetOrgPerson");
				DirContext ctx = null;
				System.out.println("connection ok for testing11 ");
				try {
					// get a handle to an Initial DirContext
					ctx = new InitialDirContext(LDAPconnection);
					// build the entry
					BasicAttributes entry = new BasicAttributes();
					entry.put(cn);
					entry.put(sn);
					entry.put(mail);
					entry.put(phone);
					entry.put(UserPassword);
					entry.put(oc);
					entry.put(displayName);
					// Add the entry
					ctx.createSubcontext(entryDN, entry);
					System.out.println("AddUser: added entry " + entryDN + ".");
					flag = true;
					System.out.println("user add flag true");
					mv.setViewName("redirect:dashboard");
				} catch (NamingException e) {
					flag = false;
					System.out.println("user add flag false");
					mv.setViewName("redirect:errorPage");
					System.err.println("AddUser: error adding " + "entry." + e);
					System.out.println("connection fail  ");

				}

			} else {
				System.out.println("=====>>Admin role is working good");

				// String entryDN = "uid="+uid+",ou=users,ou=system";
				String entryDN = "uid=" + uid + ",uid=admin,ou=system";
				Attribute cn = new BasicAttribute("cn", fname);
				Attribute sn = new BasicAttribute("sn", uid);
				Attribute mail = new BasicAttribute("mail", email);
				Attribute phone = new BasicAttribute("mobile", mobile);
				Attribute UserPassword = new BasicAttribute("userPassword", passwd);
				Attribute displayName = new BasicAttribute("displayName", passwd);
				Attribute oc = new BasicAttribute("objectClass");
				oc.add("top");
				oc.add("person");
				oc.add("organizationalPerson");
				oc.add("inetOrgPerson");
				DirContext ctx = null;
				System.out.println("connection ok for testing11 ");
				try {
					// get a handle to an Initial DirContext
					ctx = new InitialDirContext(LDAPconnection);
					// build the entry
					BasicAttributes entry = new BasicAttributes();
					entry.put(cn);
					entry.put(sn);
					entry.put(mail);
					entry.put(phone);
					entry.put(UserPassword);
					entry.put(oc);
					entry.put(displayName);
					// Add the entry
					ctx.createSubcontext(entryDN, entry);
					mv.setViewName("redirect:dashboard");
					System.out.println("AddUser: added entry " + entryDN + ".");
					flag = true;
					System.out.println("admin add flag true");
				} catch (NamingException e) {
					flag = false;
					System.out.println("admin add flag false");
					mv.setViewName("redirect:errorPage");
					System.err.println("AddUser: error adding entry." + e);
					System.out.println("connection fail  ");
				}
			}
			System.out.println("testing flag by add user=====" + flag);
			if (flag) {
				/*--------------- start change by vaibhav mail send new code--------------*/
				Properties prop = new Properties();
				InputStream inputStream = null;
				inputStream = getClass().getClassLoader().getResourceAsStream("OtpOnEmails.properties");
				prop.load(inputStream);

				String emailId = prop.getProperty("userMailId");
				List<String> arr = new ArrayList<String>();

				arr = Arrays.asList(emailId.split(","));

				for (int i = 0; i < arr.size(); i++) {
					System.out.println(" -->" + arr.get(i));
				}
				try {
					// mail send code
					Properties prop1 = new Properties();
					InputStream inputStream1 = null;
					inputStream1 = getClass().getClassLoader().getResourceAsStream("ldapapplication.properties");
					prop1.load(inputStream1);
					String mailId = prop1.getProperty("vendormailid");
					String password = prop1.getProperty("password");
					String subject = "New Ldap user";
					String body = "Dear Admin " + "\n" + " \n"
							+ "This is to inform you that New LDAP user has been Created Please see below information "
							+ " \n" + "\nFullName : " + fname + " \n" + "User ID  : " + uid + " \n" + "Email ID : "
							+ email + " \n" + "Mobile   : " + mobile + " \n" + "ServiceNow Password : " + passwd + " \n"
							+ "Role : " + role + " \n" + "\n Please create ServiceNow user for " + fname
							+ "   User ID  : " + uid + "   ServiceNow Password : " + passwd + ". \n" +

							"https://dev23287.service-now.com/nav_to.do?uri=%2Fsys_user_list.do%3Fsysparm_userpref_module%3D56e8b9ce3718200044e0bfc8bcbe5d00%26sysparm_clear_stack%3Dtrue%26sysparm_clear_stack%3Dtrue%26sysparm_clear_stack%3Dtrue"
							+ " \n " + " \n Thanks \n" + "Support Team. ";
					Properties props = System.getProperties();
					String host = "smtp.gmail.com";
					props.put("mail.smtp.starttls.enable", "true");
					props.put("mail.smtp.host", host);
					props.put("mail.smtp.user", mailId);
					props.put("mail.smtp.password", password);
					props.put("mail.smtp.port", "587");
					props.put("mail.smtp.auth", "true");
					Session session = Session.getDefaultInstance(props);
					MimeMessage message = new MimeMessage(session);
					message.setFrom(new InternetAddress(mailId));

					InternetAddress[] addressTo = new InternetAddress[arr.size()];
					System.out.println(addressTo.length);
					int counter = 0;
					for (String recipient : arr) {
						addressTo[counter] = new InternetAddress(recipient);
						counter++;
					}
					message.addRecipients(Message.RecipientType.TO, addressTo);
					message.setSubject(subject);
					message.setText(body);
					Transport transport = session.getTransport("smtp");
					transport.connect(host, mailId, password);
					transport.sendMessage(message, message.getAllRecipients());
					transport.close();
					System.out.println("mail send Successfully ");

				} catch (Exception e) {
					e.printStackTrace();
					// close mail send new password
					System.out.println("mail send fails");
				}
				/*--------------- start change by vaibhav mail send new code--------------*/
			}
		} catch (Exception e) {
			System.out.println("testing login add user");
			// logger.error("Error in save record", e);
			e.printStackTrace();
			mv.setViewName("redirect:errorPage");
		}
		return mv;
	}

	@RequestMapping(value = "/errorPage", method = RequestMethod.GET)
	public ModelAndView errorPage() {

		ModelAndView view = new ModelAndView();
		view.setViewName("errorpage");
		slf4jLogger.info(" IN errorpage");

		return view;
	}

	@RequestMapping(value = "/checkHello", method = RequestMethod.POST)
	boolean checkHelloMethod(@RequestBody Object object) {

		System.out.println("Hello world");
		return false;
	}
    
	@RequestMapping(value = "/allgetldap", method = RequestMethod.GET)
	ModelAndView allgetldap() {
		ModelAndView mv = new ModelAndView("Allgetldap");
		try {
			List<Ldapuser> allLDapuserList = new ArrayList<Ldapuser>();
			try {
				Properties LDAPconnection = ldapconnection();
				DirContext context = new InitialDirContext(LDAPconnection);
				String searchFilter = "(objectClass=inetOrgPerson)";
				// String[] requiredAttributes = {"cn","mobile"};
				String[] requiredAttributes = { "sn", "cn", "mail", "mobile", "uid", "displayName" };

				SearchControls controls = new SearchControls();
				controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
				controls.setReturningAttributes(requiredAttributes);
				NamingEnumeration users = context.search("ou=users,ou=system", searchFilter, controls);

				SearchResult searchResult = null;
				String fullName = "";
				String mail = "";
				String mobileArray = "";
				String uid = "";
				String displayName = "";
				while (users.hasMore()) {

					searchResult = (SearchResult) users.next();
					javax.naming.directory.Attributes attr = searchResult.getAttributes();
					fullName = attr.get("cn").get(0).toString();

					uid = attr.get("uid").get(0).toString();
					mail = attr.get("mail").get(0).toString();
					mobileArray = attr.get("mobile").get(0).toString();
					displayName = attr.get("displayName").get(0).toString();

					System.out.println("servicenow password: ========================= " + displayName);
					System.out.println("Full Name: = " + fullName);
					System.out.println("Email ID= " + mail);
					System.out.println("UID Name= " + uid);
					System.out.println("Mobile No = " + mobileArray);		
					Ldapuser ldapuser = new Ldapuser();
					if (fullName != "") {
						ldapuser.setName(fullName);
						System.out.println("Full Name: = " + fullName);
					}
					if (uid != "") {
						ldapuser.setUid(uid);
						System.out.println("UID Name= " + uid);
					}
					if (mail != "") {
						ldapuser.setMail(mail);
						System.out.println("Email ID= " + mail);
					}
					if (mobileArray != "") {
						ldapuser.setMobile(mobileArray);
						System.out.println("mobileArray No = " + mobileArray);
					}

					allLDapuserList.add(ldapuser);
					
				}
				mv.addObject("allLDapuserList", allLDapuserList);
				return mv;
			} catch (NamingException e) {
				e.printStackTrace();
			}

			return mv;
		} catch (Exception e) {

			slf4jLogger.error("Error in AdmniController fetch failureLogList", e);
			return mv;
		}
	}

	// remove record by id
	@RequestMapping(value = "/deleteuserRecord/{uid}", method = RequestMethod.GET)
	public ModelAndView deleteuserRecord(@PathVariable("uid") String uid) {
		ModelAndView mv = new ModelAndView();
		try {
			Properties LDAPconnection = ldapconnection();
			DirContext ctx = new InitialDirContext(LDAPconnection);
			ctx.destroySubcontext("uid=" + uid + ",ou=users,ou=system");
			System.out.println("delete successfuly ");

			mv.setViewName("redirect:/allgetldap");
			return mv;

		} catch (Exception e) {
			// e.printStackTrace();
			// return ("redirect:/errorPage");
			// mv.setViewName("redirect:allgetldap");
		}
		return mv;
	}

	// edit record by id all 4 field
	@RequestMapping(value = "/editLdapRecord/{uid}", method = RequestMethod.GET)
	public ModelAndView editQuesAnsRecord(@PathVariable("uid") String uid) {
		ModelAndView mv = new ModelAndView("EditLdapRecord");
		try {

			try {
				Properties LDAPconnection = ldapconnection();
				DirContext context = new InitialDirContext(LDAPconnection);
				String searchFilter = "(objectClass=inetOrgPerson)";
				// String[] requiredAttributes = {"cn","mobile"};
				String[] requiredAttributes = { "sn", "cn", "mail", "mobile", "uid" };

				SearchControls controls = new SearchControls();
				controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
				controls.setReturningAttributes(requiredAttributes);
				NamingEnumeration users = context.search("uid=" + uid + ",ou=users,ou=system", searchFilter, controls);

				SearchResult searchResult = null;
				String commonName = "";
				String mail = "";
				String mobileArray = "";
				// String uid = null;
				while (users.hasMore()) {

					searchResult = (SearchResult) users.next();
					javax.naming.directory.Attributes attr = searchResult.getAttributes();
					commonName = attr.get("cn").get(0).toString();

					uid = attr.get("uid").get(0).toString();
					mail = attr.get("mail").get(0).toString();
					mobileArray = attr.get("mobile").get(0).toString();
					System.out.println("Full Name: = " + commonName);
					System.out.println("Email ID= " + mail);
					System.out.println("UID Name= " + uid);
					System.out.println("Mobile No = " + mobileArray);
					Ldapuser ldapuser = new Ldapuser();
					ldapuser.setName(commonName);
					ldapuser.setUid(uid);
					ldapuser.setMail(mail);
					ldapuser.setMobile(mobileArray);

					// * mv.setViewName("redirect:/"); return mv;

					mv.addObject("ldapuser", ldapuser);
				}
				return mv;
			} catch (NamingException e) {
				e.printStackTrace();
				return mv;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}

	// save all record
	@RequestMapping(value = "/addedituser", method = RequestMethod.POST)
	public ModelAndView addedituser(@ModelAttribute Ldapuser ldapuser) {
		ModelAndView mv = new ModelAndView();
		try {
			String fname = ldapuser.getName();
			String uid = ldapuser.getUid();
			String email = ldapuser.getMail();
			String mobile = ldapuser.getMobile();

			System.out.println("full name===>" + fname);
			System.out.println("uid name===>" + uid);
			System.out.println("email===>" + email);
			System.out.println("mobile===>" + mobile);

			Properties LDAPconnection = ldapconnection();

			DirContext ctx = new InitialDirContext(LDAPconnection);
			ModificationItem[] mods = new ModificationItem[4];
			Attribute mod0 = new BasicAttribute("mail", email);
			Attribute mod1 = new BasicAttribute("mobile", mobile);
			Attribute mod2 = new BasicAttribute("sn", uid);
			Attribute mod3 = new BasicAttribute("cn", fname);
			System.out.println("add successfully");

			mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, mod0);
			mods[1] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, mod1);
			// mods[1] = new ModificationItem(DirContext.ADD_ATTRIBUTE, mod1);
			// mods[2] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
			// mod2);
			mods[2] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, mod2);
			mods[3] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, mod3);
			System.out.println("1234 edit successfully");
			ctx.modifyAttributes("uid=" + uid + ",ou=users,ou=system", mods);

			System.out.println("edit work  successfully");

			mv.setViewName("redirect:/allgetldap");
			return mv;

		} catch (Exception e) {
			// logger.error("Error in save record", e);
			e.printStackTrace();
			mv.setViewName("redirect:errorPage");
		}
		return mv;
	}

	public Properties ldapconnection() throws Exception {
		try {
			Properties prop = new Properties();
			InputStream inputStream = null;
			inputStream = getClass().getClassLoader().getResourceAsStream("ldapapplication.properties");
			prop.load(inputStream);
			Properties initilaProperties = new Properties();
			initilaProperties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			initilaProperties.put(Context.PROVIDER_URL, prop.getProperty("ldapURL"));
			initilaProperties.put(Context.SECURITY_AUTHENTICATION, "simple");
			initilaProperties.put(Context.SECURITY_PRINCIPAL, prop.getProperty("adminname"));
			initilaProperties.put(Context.SECURITY_CREDENTIALS, prop.getProperty("adminpassword"));
			// TODO code application logic here
			System.out.println("connection ok for testing11 ");
			return initilaProperties;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// ################################# new service for loginuser details by VA
	// ##################
	
	@RequestMapping(value = "/numberofuserLog", method = RequestMethod.GET)
	ModelAndView numberofuserLog() {
		List<ServiceLogDto> ServiceLogList = adminService.getLoginuserList();
		ModelAndView mv = new ModelAndView("LoginUserPage");
		try {
			System.out.println("list of ServiceLogList in controller:::" + ServiceLogList.size());
			List<String> list = new ArrayList<String>();
			for (int j = 0; j < ServiceLogList.size(); j++) {
				if (ServiceLogList.get(j).getLoginUser() != null) {
					list.add(ServiceLogList.get(j).getLoginUser());
				}
			}
			Set<String> unique = new HashSet<String>(list);
			System.out.println("unique>>>>>>>>>>>" + unique);
			System.out.println("unique>>" + unique.size());
			// mv.setViewName(unique.size());
			mv.addObject(unique.size());
			return mv;
		} catch (Exception e) {
			slf4jLogger.error("Error in AdmniController fetch failureLogList", e);
			return null;
		}

	}
	
	// @author Richa for user log detail
	
	@RequestMapping(value = "/getLoginUserLog", method = RequestMethod.GET)
	ModelAndView getLoginUserLog() {
		ModelAndView mv = new ModelAndView("LoginUserPage");
		String fullName = "";
		try {
			
			List<UserSpecificChannelDto> serviceLogUserSpecificFinalList = new ArrayList<UserSpecificChannelDto>();
			List<UserSpecificChannelDto> serviceLogUserSpecificList = adminService.getServiceLogDataGroupByChannel();
		
			Gson gson = new Gson();
			gson.toJson("Data"+serviceLogUserSpecificList);
			for( UserSpecificChannelDto obj:serviceLogUserSpecificList ){
				
				Properties prop= new Properties();
				InputStream inputStreamUrlConfig = getClass().getClassLoader().getResourceAsStream("urlconfig.properties");
				prop.load(inputStreamUrlConfig);
				int sum =0;
					
					if(obj.getSkype()!=null&&obj.getSkype()!=""){
						sum+= Integer.parseInt(obj.getSkype().trim());
					}else if(obj.getSkype()==null||obj.getSkype()==""){
						obj.setSkype("0");
					}
					if(obj.getMobile()!=null&&obj.getMobile()!=""){
						sum+= Integer.parseInt(obj.getMobile().trim());
					}else if(obj.getMobile()==null||obj.getMobile()==""){
						obj.setMobile("0");
					}
					if(obj.getWeb()!=null&&obj.getWeb()!=""){
						System.out.println("webcount::"+obj.getWeb());
						sum+= Integer.parseInt(obj.getWeb().trim());
					}else if(obj.getWeb()==null||obj.getWeb()==""){
						obj.setWeb("0");
					}
					System.out.println("final sum::"+sum);
					obj.setTotalNoLogin(sum);
				//System.out.println("DATAAAAAAA"+obj.setFullName(fullName));
				String url = prop.getProperty("urlLdapUserById");
				String newUrl = url+"?userUID="+obj.getUserLogin();
				
				RestTemplate restTemplate = new RestTemplate();
				fullName = restTemplate.getForObject( newUrl, String.class );
				System.out.println("FullName"+fullName);
				obj.setFullName(fullName);
				System.out.println("web="+obj.getWeb());
				
				if(obj.getFullName()==null){
					obj.setFullName(obj.getUserLogin());
				}
				//obj.setTotalNoLogin(sum);
				serviceLogUserSpecificFinalList.add(obj);
				
			}
			
			mv.addObject("serviceLogUserSpecificList", serviceLogUserSpecificFinalList);
			System.out.println("fina llist::"+gson.toJson(serviceLogUserSpecificFinalList));
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			slf4jLogger.error("Error in AdmniController fetch failureLogList", e);
			return null;
		}

	}
	
	@RequestMapping(value = "/feedback", method = RequestMethod.POST)
	public @ResponseBody List feedback(ServletRequest request ) {
		List<LikeDislikeDto> serviceLogUserSpecificList= new ArrayList<>();
		System.out.println("Feedback in controller");
		String like="";
		String disLike="";
		try {
			serviceLogUserSpecificList = adminService.getLikeDisLike();
			
			if( CollectionUtils.isNotEmpty((serviceLogUserSpecificList) )) {
				for(LikeDislikeDto serviceLogUserSpecific:serviceLogUserSpecificList){
					System.out.println("Likedata>>>>"+serviceLogUserSpecific.getFeedback());
					if(!serviceLogUserSpecific.getFeedback().equals(null)){
						if(serviceLogUserSpecific.getFeedback().trim().equals(AdminConstant.LIKE.getConstValue())){
							System.out.println("LikeGGGG"+serviceLogUserSpecific.getFeedbackCount());
							like = serviceLogUserSpecific.getFeedbackCount();
					}else{
						disLike =serviceLogUserSpecific.getFeedbackCount();
					}
				}
				}
			}
			return serviceLogUserSpecificList; 

		} catch (Exception e) {
			e.printStackTrace();
			slf4jLogger.error("Error in AdmniController", e);
			return serviceLogUserSpecificList;
		}
	}
	
	@RequestMapping(value = "/getCountUserRetension", method = RequestMethod.POST)
	public @ResponseBody float getCountUserRetension(){
		List<UserSpecificChannelDto> serviceLogUserSpecificList = adminService.getServiceLogDataGroupByChannel();
		int countUser=0;
			try{
				for( UserSpecificChannelDto obj:serviceLogUserSpecificList ){
					int sum =0;
									
								if(obj.getSkype()!=null&&obj.getSkype()!=""){
									sum+= Integer.parseInt(obj.getSkype().trim());
								}
								
								if(obj.getMobile()!=null&&obj.getMobile()!=""){
									sum+= Integer.parseInt(obj.getMobile().trim());
								}
								if(obj.getWeb()!=null&&obj.getWeb()!=""){
									System.out.println("webcount::"+obj.getWeb());
									sum+= Integer.parseInt(obj.getWeb().trim());
								}
								System.out.println("final sum::"+sum);
								if(sum>1){
									countUser=countUser+1;	
								}
							
				}
				List<String> uniqueUserList = new ArrayList<String>();
				uniqueUserList = adminService.getUniqueListUser();
				if(CollectionUtils.isNotEmpty(uniqueUserList)){
				System.out.println("coutUser"+countUser);
				System.out.println("uniqueUserList"+uniqueUserList.size());
				float proportion = (float) countUser/uniqueUserList.size();
				return proportion*100;
				}else{
					return 0;
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		return countUser;
	}
	
	@RequestMapping(value = "/getLiveUsers", method = RequestMethod.GET)
	public @ResponseBody void getLiveUsers(){
		
	RestTemplate restTemplate = new RestTemplate();
	restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
	HttpHeaders headers = AdminUtil.createHeaders();
	try{
	String url= env.getProperty("getActiveSessionNessaBot");
	System.out.println("HHHHHH"+url);
	HttpEntity<ActiveSession> response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(headers),
			ActiveSession.class);
	ObjectMapper mapper1 = new ObjectMapper();
	
	Gson gson = new Gson();
	for(SessionIDs session:response.getBody().getStatus().getSessionIDs()){
		System.out.println("DataSession"+session.getId());
	}
	
	
	System.out.println("DataDDD"+gson.toJson(response.getBody().getStatus().getSessionIDs()));
		//http://192.168.1.219:9001/nessa/status
	}catch(Exception e){
		e.printStackTrace();
	}
	}
	
	
	@RequestMapping(value = "/getLiveUser", method = RequestMethod.GET)
	public ModelAndView getLiveUser() throws ConnectException{
		
		ModelAndView mv = new ModelAndView("livechat");	
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		HttpHeaders headers = AdminUtil.createHeaders();
		try{
		String url= env.getProperty("getActiveSessionNessaBot");
	//	try{
		HttpEntity<ActiveSession> response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(headers),
				ActiveSession.class);
		
		ObjectMapper mapper1 = new ObjectMapper();
		
		Gson gson = new Gson();
		System.out.println("Userrrrrr");
		Map<String,SessionIDs> map = new HashMap<>();
		Map<String,List<SessionIDs> > mapList = new HashMap<>();
	   DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
	   int i=0;
	   List<LiveChatUser> liveChatUserList=new ArrayList<>();
		liveChatUserList = adminService.getLiveChatUsers();
		List<SessionIDs> sessionIds= new ArrayList<>();
		if(CollectionUtils.isNotEmpty(Arrays.asList(response.getBody().getStatus().getSessionIDs()))){
		for(SessionIDs session:Arrays.asList(response.getBody().getStatus().getSessionIDs())){
			SessionIDs sessionObj = new SessionIDs();
			//chat set
				if(CollectionUtils.isNotEmpty(liveChatUserList)){
						for(LiveChatUser objLiveChatUser: liveChatUserList){
							if(session.getUser().equals(objLiveChatUser.getUser())){
								sessionObj.setChat(true);
								System.out.println(sessionObj.isChat());
							}
						}
				}
				
			sessionObj.setId(session.getId());
			List<SessionIDs> sessionIdList= new ArrayList<>();
			if(mapList.containsKey(session.getUser())){
				System.out.println("Userrrrrr"+session.getUser());
				sessionIds = mapList.get(session.getUser());
				String dateString = session.getLastAcess();
				Date datee = (Date)formatter.parse(dateString);
				Long dateLe = datee.getTime();
				sessionObj.setUser(session.getUser());
				sessionObj.setId(session.getId());
				sessionObj.setTime(dateLe);
				i++;
				sessionObj.setInstance(i);
				sessionObj.setLastAcess(session.getLastAcess());
				sessionIds.add(sessionObj);
				mapList.put(session.getUser(), sessionIds);
				}else{
				String dateString = session.getLastAcess();
				Date datee = (Date)formatter.parse(dateString);
				Long dateLe = datee.getTime();
				sessionObj.setUser(session.getUser());
				sessionObj.setId(session.getId());
				sessionObj.setTime(dateLe);
				sessionObj.setLastAcess(session.getLastAcess());
				sessionIds.add(sessionObj);
				
				mapList.put(session.getUser(), sessionIds);
				sessionIds=new ArrayList<>();
			}
		}
		
		for(Map.Entry<String, List<SessionIDs>> entry:mapList.entrySet()){
			System.out.println(entry.getKey()+"="+entry.getValue());
			
			List<SessionIDs> sessObjList = entry.getValue();
			List<SessionIDs> sessObjListNew=null;
			 Collections.sort(entry.getValue(), SessionIDs.StuNameComparator);
			 if (entry.getValue().size() < 3) {
				  sessObjListNew = entry.getValue();
					System.out.println("when request is less than 5::::>>>   " + entry.getValue().size());

					} else {

						sessObjListNew  =  entry.getValue().subList(entry.getValue().size() - 3,
								entry.getValue().size());
					System.out.println("check Final List ALLL OR NOT  >> "+entry.getValue());

					}
			for(SessionIDs s:sessObjListNew){
				System.out.println(s.getTime());
				//System.out.println(s.getUser());
			}
		}
		
		mv.addObject("mapList", mapList);
		}
		
		
		}catch(Exception e){
			e.printStackTrace();
				
		return mv;
		}
		return mv;
		
	}
	
	@RequestMapping(value = "/getLiveUserList", method = RequestMethod.GET)
	public @ResponseBody List<SessionIDs> getLiveUserList() throws ConnectException{
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		HttpHeaders headers = AdminUtil.createHeaders();
		Map<String,List<SessionIDs> > mapList = new HashMap<>();
		List<SessionIDs> sessionIdsList = null;
		try{
		String url= env.getProperty("getActiveSessionNessaBot");
	//	try{
		HttpEntity<ActiveSession> response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(headers),
				ActiveSession.class);
		
		ObjectMapper mapper1 = new ObjectMapper();
		
		Gson gson = new Gson();
		System.out.println("Userrrrrr");
		Map<String,SessionIDs> map = new HashMap<>();
	   DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
	   int i=0;
	   List<LiveChatUser> liveChatUserList=new ArrayList<>();
		liveChatUserList = adminService.getLiveChatUsers();
		List<SessionIDs> sessionIds= new ArrayList<>();
		if(CollectionUtils.isNotEmpty(Arrays.asList(response.getBody().getStatus().getSessionIDs()))){
		for(SessionIDs session:Arrays.asList(response.getBody().getStatus().getSessionIDs())){
			SessionIDs sessionObj = new SessionIDs();
			//chat set
				if(CollectionUtils.isNotEmpty(liveChatUserList)){
						for(LiveChatUser objLiveChatUser: liveChatUserList){
							if(session.getUser().equals(objLiveChatUser.getUser())){
								sessionObj.setChat(true);
								System.out.println(sessionObj.isChat());
							}
						}
				}
				
			sessionObj.setId(session.getId());
			//List<SessionIDs> sessionIdList= new ArrayList<>();
			if(mapList.containsKey(session.getUser())){
				System.out.println("Userrrrrr"+session.getUser());
				sessionIds = mapList.get(session.getUser());
				String dateString = session.getLastAcess();
				Date datee = (Date)formatter.parse(dateString);
				Long dateLe = datee.getTime();
				sessionObj.setUser(session.getUser());
				sessionObj.setId(session.getId());
				sessionObj.setTime(dateLe);
				i++;
				sessionObj.setInstance(i);
				sessionObj.setLastAcess(session.getLastAcess());
				sessionIds.add(sessionObj);
				mapList.put(session.getUser(), sessionIds);
				}else{
				String dateString = session.getLastAcess();
				Date datee = (Date)formatter.parse(dateString);
				Long dateLe = datee.getTime();
				sessionObj.setUser(session.getUser());
				sessionObj.setId(session.getId());
				sessionObj.setTime(dateLe);
				sessionObj.setLastAcess(session.getLastAcess());
				sessionIds.add(sessionObj);
				
				mapList.put(session.getUser(), sessionIds);
				sessionIds=new ArrayList<>();
			}
		}
		sessionIdsList = sessionIds;
		for(Map.Entry<String, List<SessionIDs>> entry:mapList.entrySet()){
			System.out.println(entry.getKey()+"="+entry.getValue());
			
			List<SessionIDs> sessObjList = entry.getValue();
			List<SessionIDs> sessObjListNew=null;
			 Collections.sort(entry.getValue(), SessionIDs.StuNameComparator);
			 if (entry.getValue().size() < 3) {
				  sessObjListNew = entry.getValue();
					System.out.println("when request is less than 5::::>>>   " + entry.getValue().size());

					} else {

						sessObjListNew  =  entry.getValue().subList(entry.getValue().size() - 3,
								entry.getValue().size());
					System.out.println("check Final List ALLL OR NOT  >> "+entry.getValue());

					}
			for(SessionIDs s:sessObjListNew){
				System.out.println(s.getTime());
				//System.out.println(s.getUser());
			}
		}
		
		}
		
		}catch(Exception e){
			e.printStackTrace();
				
		return sessionIdsList;
		}
		return sessionIdsList;
		
	}
	
	@RequestMapping(value = "/updateLiveUSer", method = RequestMethod.GET)
	@ResponseBody
	public boolean updateLiveUSer() throws ConnectException{
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		HttpHeaders headers = AdminUtil.createHeaders();
		try{
		String url= env.getProperty("getActiveSessionNessaBot");
	//	try{
		HttpEntity<ActiveSession> response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(headers),
				ActiveSession.class);
		
		ObjectMapper mapper1 = new ObjectMapper();
		
		Gson gson = new Gson();
		System.out.println("Userrrrrr");
		Map<String,SessionIDs> map = new HashMap<>();
		Map<String,List<SessionIDs> > mapList = new HashMap<>();
	   DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
	   int i=0;
	   List<LiveChatUser> liveChatUserList=new ArrayList<>();
		liveChatUserList = adminService.getLiveChatUsers();
		List<SessionIDs> sessionIds= new ArrayList<>();
		if(CollectionUtils.isNotEmpty(Arrays.asList(response.getBody().getStatus().getSessionIDs()))){
		for(SessionIDs session:Arrays.asList(response.getBody().getStatus().getSessionIDs())){
			SessionIDs sessionObj = new SessionIDs();
			//chat set
				if(CollectionUtils.isNotEmpty(liveChatUserList)){
						for(LiveChatUser objLiveChatUser: liveChatUserList){
							if(session.getUser().equals(objLiveChatUser.getUser())){
								sessionObj.setChat(true);
								System.out.println(sessionObj.isChat());
							}
						}
				}
				
			sessionObj.setId(session.getId());
			List<SessionIDs> sessionIdList= new ArrayList<>();
			if(mapList.containsKey(session.getUser())){
				System.out.println("Userrrrrr"+session.getUser());
				sessionIds = mapList.get(session.getUser());
				String dateString = session.getLastAcess();
				Date datee = (Date)formatter.parse(dateString);
				Long dateLe = datee.getTime();
				sessionObj.setUser(session.getUser());
				sessionObj.setId(session.getId());
				sessionObj.setTime(dateLe);
				i++;
				sessionObj.setInstance(i);
				sessionObj.setLastAcess(session.getLastAcess());
				sessionIds.add(sessionObj);
				mapList.put(session.getUser(), sessionIds);
				}else{
				String dateString = session.getLastAcess();
				Date datee = (Date)formatter.parse(dateString);
				Long dateLe = datee.getTime();
				sessionObj.setUser(session.getUser());
				sessionObj.setId(session.getId());
				sessionObj.setTime(dateLe);
				sessionObj.setLastAcess(session.getLastAcess());
				sessionIds.add(sessionObj);
				
				mapList.put(session.getUser(), sessionIds);
				sessionIds=new ArrayList<>();
			}
		}
		
		for(Map.Entry<String, List<SessionIDs>> entry:mapList.entrySet()){
			System.out.println(entry.getKey()+"="+entry.getValue());
			
			List<SessionIDs> sessObjList = entry.getValue();
			List<SessionIDs> sessObjListNew=null;
			 Collections.sort(entry.getValue(), SessionIDs.StuNameComparator);
			 if (entry.getValue().size() < 3) {
				  sessObjListNew = entry.getValue();
					System.out.println("when request is less than 5::::>>>   " + entry.getValue().size());

					} else {

						sessObjListNew  =  entry.getValue().subList(entry.getValue().size() - 3,
								entry.getValue().size());
					System.out.println("check Final List ALLL OR NOT  >> "+entry.getValue());

					}
			for(SessionIDs s:sessObjListNew){
				System.out.println(s.getTime());
				//System.out.println(s.getUser());
			}
		}
		
		}
		
		}catch(Exception e){
			e.printStackTrace();
				
		return false;
		}
		return true;
		
	}


	@RequestMapping(value = "/adminLiveChatDashboard", method = RequestMethod.GET)
	public void adminLiveChatDashboard(){
		/*
		try{
		this.getLiveUser();}catch(Exception e){
			e.printStackTrace();
		}
		return null;*/
	
	}
	@RequestMapping(value = "/liveChatByInstanceId", method = RequestMethod.POST)
	
	public @ResponseBody JsonResult liveChatByInstanceId(@RequestParam String sessionId) {
		
		RestTemplate restTemplate = new RestTemplate();
		Gson gson = new Gson();
		JsonResult currencyReportResult = new JsonResult();

		Messages message = new Messages();
		Info info = new Info();
		Data data = new Data();
		try{
		
		
		String url= env.getProperty("readByInstanceId");
		url=url+sessionId;
		System.out.println("URLLL"+url);
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		HttpHeaders headers = AdminUtil.createHeaders();
		HttpEntity<JsonResult> responses = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(headers),
				JsonResult.class);
		System.out.println("jjjj"+gson.toJson(responses.getBody().getMessages()));
		/*for(SessionIDs session:response.getBody().getStatus()){
			System.out.println("DataSession"+session.getId());
		}*/
		message.setChat("fgrdth");
		message.setData(data);
		message.setFlag("1");
		data.setError("");
		info.setImage("");
		info.setAudio("");
		info.setVideo("");
		info.setFile("");
		info.setText("");
		info.setDocuments("");
		data.setInfo(info);
		data.setError("");
		currencyReportResult.setMessage(message);
		System.out.println("currencyReportResult--------------->>>>" + currencyReportResult.getMessage());

		
		}catch (Exception e) {
			System.out.println("ChatController: Error In method readLatestChat >>>>" + e);
		
		}
		return currencyReportResult;

	}
	/*@RequestMapping(value = "/liveAgentTrigger", method = RequestMethod.GET)
	@ResponseBody List<LiveChatUser> liveAgentTrigger(HttpServletRequest request ) {
		Map<String,String>map = new HashMap<>();
		List<LiveChatUser> failureLogList=new ArrayList<>();
		
		try {	 failureLogList = adminService.getLiveChatUsers();

		JsonResult currencyReportResult = new JsonResult();

		Messages message = new Messages();
		Info info = new Info();
		Data data = new Data();
	
		
		
		message.setChat("data set Richa");
		message.setData(data);
		message.setFlag("1");
		data.setError("");
		info.setImage("");
		info.setAudio("");
		info.setVideo("");
		info.setFile("");
		info.setText("");
		info.setDocuments("");
		data.setInfo(info);
		
		currencyReportResult.setMessage(message);
				
				
		} catch (Exception e) {

			slf4jLogger.error("Error in AdmniController liveAgentTrigger method", e);
			return failureLogList;
		}
		return failureLogList;
	}*/
	@RequestMapping(value ="/chatBot", method = RequestMethod.GET)
	public ModelAndView chatBot(@RequestParam(value="sessionId", required=false) String sessionId ) {
System.out.println("xdfsd"+sessionId);
		ModelAndView view = new ModelAndView();
		view.setViewName("chatBot");
		slf4jLogger.info(" IN WELCOME");

		return view;
	}

@RequestMapping(value = "/failureLog", method = RequestMethod.GET)
ModelAndView getFailureLogList() {
	ModelAndView mv = new ModelAndView("failure_log");
	try {

		List<FailureLogModel> failureLogList = adminService.getFailureLogList();
		mv.addObject("failureLogList", failureLogList);
		return mv;
	} catch (Exception e) {

		slf4jLogger.error("Error in AdmniController fetch failureLogList", e);
		return mv;
	}

}

//Get dialog Method
	@RequestMapping(value = "/saveFeedbackDialog", method = RequestMethod.GET)
	public @ResponseBody JsonResult saveFeedbackDialog(@RequestParam String sessionId) throws Exception {

		JsonResult jsonResult = new JsonResult();
		Messages message = new Messages();
		Info info = new Info();
		Data data = new Data();
		Date date = new Date();

		String url1 = env.getProperty("getDialog");
		String url = url1 + sessionId + "/" + "getDialog";
		System.out.println("url>>>" + url);
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers = this.createHeaders();

		String str = "";
		try {

			HttpEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(headers),
					String.class);

			System.out.println("response................." + response);

			ObjectMapper mapper = new ObjectMapper();
			System.out.println("body................." + response.getBody());
			String result1 = response.getBody();

			Gson g = new Gson();
			Result result = g.fromJson(result1, Result.class);

			System.out.println("resultttt>>>>>>>>" + gson.toJson(result));

			FeedBackModel feedBackModel = adminService.saveFeedbackDialog(result);

			message.setData(data);
			data.setError("");
			message.setChat("");
			info.setImage("");
			info.setAudio("");
			info.setVideo("");
			info.setFile("");
			info.setText("");
			info.setDocuments("");
			data.setInfo(info);

			jsonResult.setMessage(message);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonResult;
	}

	@RequestMapping(value = "/getDisikeList", method = RequestMethod.GET)
	ModelAndView getDiskLikeList(@RequestParam String userlogin) {
		ModelAndView mv = new ModelAndView("disLikeList");
		String fullName = "";
		String loginUser=userlogin;
		try {
			List<ServiceLogModel> feedBackListDto = new ArrayList<ServiceLogModel>();

			feedBackListDto = adminService.getDisikeList(loginUser);
			 Collections.reverse(feedBackListDto);

			mv.addObject("feedBackListDto" ,feedBackListDto);
		
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			slf4jLogger.error("Error in AdmniController getDisikeList", e);
			return mv;
		}

	}

	@RequestMapping(value = "/getDialogById", method = RequestMethod.POST)
	@ResponseBody FeedBackDto getDialogById(@RequestParam String sessionId) {
		String fullName = "";
		try {
			FeedBackDto feedBackModel = new FeedBackDto();

			feedBackModel = adminService.getDialogById(sessionId);

		
			return feedBackModel;
		} catch (Exception e) {
			e.printStackTrace();
			slf4jLogger.error("Error in AdmniController getDisikeList", e);
			return null;
		}

	}
	
	@RequestMapping(value = "/sendAgentStatus", method = RequestMethod.GET)
	@ResponseBody Agent  sendAgentStatus(@RequestParam String sessionId, String status,String agentId,String agentName) {
		System.out.println("AgentStatus>>>"+status);
		System.out.println("SessionId>>>>"+sessionId);
		Agent agent=null;
		try {

			Agent agent1=new Agent();
			
			agent1=adminService.checkSessionId(sessionId,status);
			
			if(agent1 == null){
			
			 agent = adminService.sendAgentStatus(status,agentId,agentName,sessionId);

			return agent;
			}else{
				
				System.out.println("Chat is alreday Engaged");
			}
		} catch (Exception e) {
			e.printStackTrace();
			slf4jLogger.error("Error in AdmniController sendAgentStatus", e);
			return null;
		}
		return agent;
	
	}
	
	@RequestMapping(value = "/changeAgentStatus", method = RequestMethod.GET)
	@ResponseBody AgentStatusModel  changeAgentStatus(@RequestParam String agentId,String sessionId) {
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers = this.createHeaders();
		try {
			String url1 = env.getProperty("getChatBySessionId");
			String url = url1 + sessionId ;
			System.out.println("url>>>"+url);
			
			HttpEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(headers),
					String.class);
			
			ObjectMapper mapper = new ObjectMapper();
			System.out.println("body................." + response.getBody());
			String result1 = response.getBody();

			Gson g = new Gson();
			LiveChatsMessage result = g.fromJson(result1, LiveChatsMessage.class);
			result.setCurrentTime1(new Date(Long.parseLong(result.getCurrentTime())));
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 Date firstDate=Calendar.getInstance().getTime();
			 Date secondDate = null;
			 long diffInMillies=0l;
			
				 secondDate = result.getCurrentTime1();
				diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
				//diffInMillies = TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
				System.out.println("DIfference in milli second >>>>>>>" + diffInMillies);


				if (diffInMillies > 10 * 60 * 1000) {
				    System.out.println("Last Message is greater than 10 minutes old");
				    boolean  flag= adminService.changeAgentStatus(sessionId,agentId);
				    
				    System.out.println("Update Agent Status flag is "+ flag);
				    
				}
			
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			slf4jLogger.error("Error in AdmniController changeAgentStatus", e);
			return null;
		}

	}
	
	@RequestMapping(value = "/getDislikeUserList", method = RequestMethod.GET)
	ModelAndView getDislikeUserList() {
		ModelAndView mv = new ModelAndView("dislikeUserList");
		try {
			
			List<ServiceLogModel> serviceLogModel = adminService.getDislikeUserList();
			mv.addObject("serviceLogModel", serviceLogModel);
			return mv;
		} catch (Exception e) {

			slf4jLogger.error("Error in AdmniController fetch failureLogList", e);
			return mv;
		}

	}
	
	@RequestMapping(value = "/getBusyAgent", method = RequestMethod.GET)
	@ResponseBody List<Agent>  getAgentBySessionId() {
		
		try {

			List<Agent> agentList = adminService.getBusyAgent();
			
			return agentList;
		} catch (Exception e) {
			e.printStackTrace();
			slf4jLogger.error("Error in AdmniController getAgentBySessionId", e);
			return null;
		}

	}
	
	@RequestMapping(value ="/getDislikeDialog", method = RequestMethod.GET)
	public ModelAndView getDislikeDialog(@RequestParam(value="sessionId", required=false) String sessionId ) {
System.out.println("xdfsd"+sessionId);
		ModelAndView view = new ModelAndView();
		view.setViewName("getDislikeDialog");
		slf4jLogger.info(" IN WELCOME");

		return view;
	}
	
	
	@RequestMapping(value ="/failureLogDialog", method = RequestMethod.GET)
	public ModelAndView failureLogDialog(@RequestParam(value="sessionId", required=false) String sessionId ) {
System.out.println("xdfsd"+sessionId);
		ModelAndView view = new ModelAndView();
		view.setViewName("failureLogDialog");
		slf4jLogger.info(" IN WELCOME");

		return view;
	}
	
	@RequestMapping(value = "/closeChat", method = RequestMethod.GET)
	boolean  closeChat(@RequestParam String sessionId) {
		try {
			String agentId="";
		    boolean  flag= adminService.changeAgentStatus(sessionId,agentId);
			return true;
		} catch (Exception e) {

			slf4jLogger.error("Error in AdmniController fetch failureLogList", e);
			return false;
		}

	}
	
	@RequestMapping(value = "/checkForAgent", method = RequestMethod.GET)
	@ResponseBody JsonResult  checkForAgent(@RequestParam String sessionId, String status,String agentId,String agentName) {
		System.out.println("AgentStatus>>>"+status);
		System.out.println("SessionId>>>>"+sessionId);
		Agent agent=null;
		Messages message = new Messages();
		Info info = new Info();
		Data data = new Data();
		JsonResult currencyReportResult = new JsonResult();

		try {

			Agent agent1=new Agent();
			
			agent1=adminService.checkSessionId(sessionId,status);
			
			if(agent1 == null){
			
			 agent = adminService.sendAgentStatus(status,agentId,agentName,sessionId);
			 
			 if(agent==null){
				 
				 	message.setChat("All agents are busy. Please wait for some time.");
					message.setData(data);
					message.setFlag("");
					data.setError("");
					info.setImage("");
					info.setAudio("");
					info.setVideo("");
					info.setFile("");
					info.setText("");
					info.setDocuments("");
					data.setInfo(info);
					data.setError("");
					currencyReportResult.setMessage(message);
				 
			 }else{
			 
				 message.setChat("");
				message.setData(data);
				message.setFlag("true");
				data.setError("");
				info.setImage("");
				info.setAudio("");
				info.setVideo("");
				info.setFile("");
				info.setText("");
				info.setDocuments("");
				data.setInfo(info);
				data.setError("");
				currencyReportResult.setMessage(message);
			 }

			return currencyReportResult;
			}else{
				
				System.out.println("Chat is alreday Engaged");
				
				message.setChat("");
				message.setData(data);
				message.setFlag("true");
				data.setError("");
				info.setImage("");
				info.setAudio("");
				info.setVideo("");
				info.setFile("");
				info.setText("");
				info.setDocuments("");
				data.setInfo(info);
				data.setError("");
				currencyReportResult.setMessage(message);			}
		} catch (Exception e) {
			e.printStackTrace();
			slf4jLogger.error("Error in AdmniController sendAgentStatus", e);
			return null;
		}
		return currencyReportResult;
	
	}

	
}
