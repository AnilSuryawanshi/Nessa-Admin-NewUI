package com.connecticus.admin.serviceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.connecticus.admin.dao.AdminDao;
import com.connecticus.admin.dto.ActivityLog;
import com.connecticus.admin.dto.ChartDataDto;
import com.connecticus.admin.dto.EmailContent;
import com.connecticus.admin.dto.FailureLogActionStatusDto;
import com.connecticus.admin.dto.FailureLogDto;
import com.connecticus.admin.dto.FeedBackDto;
import com.connecticus.admin.dto.FeedBackDialog;
import com.connecticus.admin.dto.LikeDislikeDto;
import com.connecticus.admin.dto.LiveAgentCountDTO;
import com.connecticus.admin.dto.LiveChatUser;
import com.connecticus.admin.dto.Result;
import com.connecticus.admin.dto.ServiceLogChannelDto;
import com.connecticus.admin.dto.ServiceLogDto;
import com.connecticus.admin.dto.UserChannelCountDto;
import com.connecticus.admin.dto.UserSpecificChannelDto;
import com.connecticus.admin.model.ActivityLogModel;
import com.connecticus.admin.model.Agent;
import com.connecticus.admin.model.AgentStatusModel;
import com.connecticus.admin.model.FailureLogModel;
import com.connecticus.admin.model.FeedBackModel;
import com.connecticus.admin.model.GroupModel;
import com.connecticus.admin.model.LiveChatTrigger;
import com.connecticus.admin.model.ManagerModel;
import com.connecticus.admin.model.ServiceLogModel;
import com.connecticus.admin.model.cityModel;
import com.connecticus.admin.model.deptModel;
import com.connecticus.admin.model.nameModel;
import com.connecticus.admin.model.versionModel;
import com.connecticus.admin.repository.FeedBackRepository;
import com.connecticus.admin.service.AdminService;
import com.connecticus.admin.util.FeedBackDialogUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

@Transactional
@Service("adminService")
public class AdminServiceImpl implements AdminService {

	private static final Logger slf4jLogger = LoggerFactory.getLogger(AdminServiceImpl.class);

	private static String FAILURE_QUESTION = "How may i help you?";

	// Email Service For Sending Emails

	/*
	 * @Autowired FailureLogRepo failureLogRepo;
	 * 
	 * @Autowired ServiceLogRepo serviceLogRepo;
	 */

	@Autowired
	AdminDao adminDao;

	/*
	 * @Autowired FeedBackRepository feedBackRepository;
	 */

	@Autowired
	FeedBackDialogUtil feedBackDialogUtil;

	Gson gson = new Gson();

	@Override
	public String EmailService(String emailBody, String configurationProperty, String emailId, String password) {

		HashMap<String, String> emailConfigMap = new HashMap<String, String>();

		ObjectMapper objMapping = new ObjectMapper();

		EmailContent emailContentObj = new EmailContent();
		try {

			emailContentObj = objMapping.readValue(emailBody, EmailContent.class);
			// for getting port number and emailids
			// if (configurationProperty.length() > 0) {
			// String configArray[] = configurationProperty.split("!");

			// for (int i = 0; i < configArray.length; i++) {
			// String configString1 = configArray[i];
			String configArray1[] = configurationProperty.split("#");
			for (int j = 0; j < configArray1.length;) {
				System.out.println("configArray1============>" + configArray1);
				String port = configArray1[0];
				System.out.println("port============>" + port);
				String email = configArray1[1];
				System.out.println("email============>" + email);
				emailConfigMap.put(port, email);
				break;
			}
			// }
			// }
			if (emailConfigMap.containsKey(emailContentObj.getPortNumber())) {
				String emailIds = (String) emailConfigMap.get(emailContentObj.getPortNumber());
				System.out.println("emailContenObj::" + emailIds);
				String emailIdArray[] = null;
				if (emailIds.contains(",")) {
					emailIdArray = emailIds.split(",");
				} else {
					emailIdArray = new String[] { emailIds };
				}

				// list of recipient email addresses
				String[] to = emailIdArray;
				System.out.println("To::" + to.toString());
				String subject = "Coversation With Nessa Bot";
				String body = emailContentObj.getEmailBody();
				Properties props = System.getProperties();
				String host = "smtp.gmail.com";
				/*
				 * hostname for connecticus domain String host =
				 * "103.53.42.233";
				 */
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.host", host);
				props.put("mail.smtp.user", emailId);
				props.put("mail.smtp.password", password);
				props.put("mail.smtp.port", "587");
				props.put("mail.smtp.auth", "true");

				Session session = Session.getDefaultInstance(props);
				MimeMessage message = new MimeMessage(session);

				message.setFrom(new InternetAddress(emailId));
				InternetAddress[] toAddress = new InternetAddress[to.length];

				System.out.println("toAddress==mail send====" + toAddress);

				// To get the array of addresses
				for (int i = 0; i < to.length; i++) {
					toAddress[i] = new InternetAddress(to[i]);
				}

				for (int i = 0; i < toAddress.length; i++) {
					message.addRecipient(Message.RecipientType.TO, toAddress[i]);

					System.out.println("toAddress==mail send vaibhav====" + toAddress[i]);
				}

				message.setSubject(subject);
				message.setText(body);
				Transport transport = session.getTransport("smtp");
				transport.connect(host, emailId, password);
				transport.sendMessage(message, message.getAllRecipients());
				transport.close();
				slf4jLogger.error("Mail Sent Successfully");
				System.out.println("Mail Sent Successfully");
			}
		} catch (AddressException ae) {
			slf4jLogger.error("AddressException In SendMail:- ", ae);
			ae.printStackTrace();
		} catch (MessagingException me) {
			slf4jLogger.error("MessagingException In SendMail:- ", me);
			me.printStackTrace();
		} catch (Exception e) {
			slf4jLogger.error("Exception In SendMail:- ", e);
			e.printStackTrace();
		}

		return "Email functionality";
	}

	/*
	 * @Override public void FailureLog(String emailBody) {
	 * 
	 * try { InetAddress ipAddr; ipAddr = InetAddress.getLocalHost(); String
	 * ipAddress = ipAddr.getHostAddress(); ObjectMapper objMapping = new
	 * ObjectMapper(); EmailContent emailContentObj =
	 * objMapping.readValue(emailBody, EmailContent.class); String eBody =
	 * emailContentObj.getEmailBody(); String conversation[] = eBody.split("#");
	 * Properties properties = new Properties(); InputStream inputStream = null;
	 * 
	 * inputStream =
	 * getClass().getClassLoader().getResourceAsStream("messages.properties");
	 * properties.load(inputStream); // read errorMessages from messages
	 * configuration file String errMsgs[] =
	 * properties.getProperty("errorConf").split("#");
	 * 
	 * ArrayList<String> errorMessageList = new
	 * ArrayList<String>(Arrays.asList(errMsgs)); // split conversation from
	 * emailBody and replace \n\nNessa Bot: with blank
	 * 
	 * for (int i = 0; i < conversation.length; i++) { conversation[i] =
	 * conversation[i].replace("\n\nNessa Bot:", "");
	 * 
	 * if (errorMessageList.contains(conversation[i])) { String userUttrance =
	 * conversation[i - 1];
	 * 
	 * String portNo = emailContentObj.getPortNumber(); if (i > 1) { String
	 * failureQuestion = conversation[i - 2]; // call Failure_log Method For
	 * Save Data failureLogMessage(userUttrance, ipAddress, portNo,
	 * failureQuestion); } else { // call Failure_log Method For Save Data For
	 * First Question String failureLogMessage(userUttrance, ipAddress, portNo,
	 * FAILURE_QUESTION); } } } } catch (Exception ex) { slf4jLogger.error(
	 * "Error in FailureLog method in AdminServiceImpl"); ex.printStackTrace();
	 * }
	 * 
	 * }
	 */

	@Override
	public void FailureLog(String emailBody) {

		try {
			System.out.println("emailBody:::" + emailBody);
			InetAddress ipAddr;
			ipAddr = InetAddress.getLocalHost();
			String ipAddress = ipAddr.getHostAddress();
			ObjectMapper objMapping = new ObjectMapper();
			EmailContent emailContentObj = objMapping.readValue(emailBody, EmailContent.class);
			String eBody = emailContentObj.getEmailBody();
			String conversation[] = eBody.split("\n");
			List<String> usserUtter = new ArrayList<String>();
			List<String> botUtter = new ArrayList<String>();
			List<String> finalConversionList = new ArrayList<String>();
			Properties properties = new Properties();
			InputStream inputStream = null;

			inputStream = getClass().getClassLoader().getResourceAsStream("messages.properties");
			properties.load(inputStream);
			// read errorMessages from messages configuration file
			String errMsgs[] = properties.getProperty("errorConf").split("#");

			ArrayList<String> errorMessageList = new ArrayList<String>(Arrays.asList(errMsgs));

			/*
			 * for (int i = 0; i < conversation.length; i++) {
			 * 
			 * System.out.println("conversationList::" + conversation[i]); if
			 * (errorMessageList.contains(conversation.length - 1)) {
			 * System.out.println("It is contains");
			 * 
			 * } }
			 */
			String finalConvert[] = {};
			// String finalSplit[]={};

			for (int i = 0; i < conversation.length; i++) {
				finalConvert = conversation[i].split("#");
				String finalSplit[] = finalConvert[0].split(":");
				for (int j = 0; j < finalSplit.length; j++) {
					System.out.println("finalSplit::" + finalSplit[j]);
				}
				finalConversionList.add(finalSplit[1]);
				if (finalConvert.length > 1) {
					finalSplit = finalConvert[1].split(":");

					finalConversionList.add(finalSplit[1]);
				}

			}
			// finalConversionList=(Arrays.asList(finalConvert));

			for (int j = 0; j < finalConversionList.size(); j++) {

				if (errorMessageList.contains(finalConversionList.get(j))) {

					String botUtter1 = finalConversionList.get(j - 2);

					String usserUtte = finalConversionList.get(j - 1);
					String portNo = emailContentObj.getPortNumber();

					failureLogMessage(usserUtte, ipAddress, portNo, botUtter1);
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			slf4jLogger.error("Error in FailureLog method in AdminServiceImpl");
		}
	}

	// save FailureLog
	private void failureLogMessage(String userUttrance, String ipAddress, String portNo, String failureQuestion) {

		try {
			ServletRequest servletRequest = null;
			@SuppressWarnings("null")
			String applicatioName1 = null;
			Properties properties = new Properties();
			InputStream inputStream = null;
			inputStream = getClass().getClassLoader().getResourceAsStream("messages.properties");
			properties.load(inputStream);

			// read application name from messages.properties
			String applicationName = properties.getProperty("appName");

			// split applicationName with , and split to get PortNo From appName
			String split[] = applicationName.split(",");
			for (int i = 0; i <= split.length - 1; i++) {
				String splitporNo[] = split[i].split(":");
				for (int j = 0; j <= splitporNo.length - 1; j++) {
					if (portNo.equals(splitporNo[j])) {
						applicatioName1 = splitporNo[j + 1];
						break;
					}
				}
			}

			/*
			 * // split user utterence by :and save each answer String
			 * userUtterenceSplit[] = userUttrance.split(":"); String
			 * answerUserUtt = null; for (int i = 0; i <=
			 * userUtterenceSplit.length - 1; i++) { answerUserUtt =
			 * userUtterenceSplit[i + 1]; break; }
			 */
			FailureLogDto failureLogDto = new FailureLogDto();
			failureLogDto.setApplicationName(applicatioName1);
			failureLogDto.setIpAddress(ipAddress);
			failureLogDto.setNessaQuestion(failureQuestion);
			failureLogDto.setUserUtterence(userUttrance);

			FailureLogModel failureLogModel = new FailureLogModel();
			failureLogModel.setApplicationName(failureLogDto.getApplicationName());
			failureLogModel.setIpAddress(failureLogDto.getIpAddress());
			failureLogModel.setNessaQuestion(failureLogDto.getNessaQuestion());
			failureLogModel.setUserUtterence(failureLogDto.getUserUtterence());
			failureLogModel.setActionStatus("");
			failureLogModel.setRemark("");
			// failureLogRepo.save(failureLogModel);
			// mongodb
			Boolean datasave = adminDao.saveFailureLog(failureLogModel);
			if (datasave) {
				System.out.println("failure log saved");
			} else {
				System.out.println("Error in save failurelog");
			}
		} catch (IOException e) {
			e.printStackTrace();
			slf4jLogger.error("Error in FailureLogMessage method in AdminServiceImpl");

		}

	}

	@Override
	public List<FailureLogModel> getFailureLogList() {
		try {
			List<FailureLogModel> returnFailureLogList = new ArrayList<FailureLogModel>();
			// failureLogRepo.findAll();
			List<FailureLogModel> failureLogList = adminDao.getFailureLogList();
			// Checking action status if it is completed and reject skip adding
			// in list
			for (FailureLogModel failureLogModel : failureLogList) {
				if (failureLogModel.getActionStatus() != null) {
					if (!failureLogModel.getActionStatus().equals("Completed")
							&& !failureLogModel.getActionStatus().equals("Reject")) {
						returnFailureLogList.add(failureLogModel);
					}
				} else {
					returnFailureLogList.add(failureLogModel);
				}
			}
			return returnFailureLogList;
		} catch (Exception e) {
			slf4jLogger.error("Error while fetching failure log list:", e);
			return null;
		}
	}

	@Override
	public boolean updateFailureLogRecord(FailureLogModel failureLogModel) {
		try {
			// failureLogRepo.save(failureLogModel);
			adminDao.updateFailureLog(failureLogModel);
			// mongodb
			return true;
		} catch (Exception e) {
			slf4jLogger.error("Error while updating failure log", e);
			return false;
		}

	}

	@Override
	public FailureLogModel getFailureLogById(String id) {

		FailureLogModel failureLogModel = null;
		try {
			// failureLogModel = failureLogRepo.getFailureLogById(id);
			failureLogModel = adminDao.getFailureLogByI(id);
			return failureLogModel;
		} catch (Exception e) {
			slf4jLogger.error("Error while get failure lod data by id in serviceImpl", e);
			return null;
		}
	}

	public List<FailureLogModel> getFailureLogCompletedRejectedList() {

		try {
			List<FailureLogModel> returnFailureLogList = new ArrayList<FailureLogModel>();
			// failureLogRepo.findAll();
			List<FailureLogModel> failureLogList = adminDao.getFailureLogList();
			// mongodb
			// added to list which action status completed and rejected
			for (FailureLogModel failureLogModel : failureLogList) {
				if (failureLogModel.getActionStatus() != null) {
					if (failureLogModel.getActionStatus().equals("Completed")
							|| failureLogModel.getActionStatus().equals("Reject")) {
						returnFailureLogList.add(failureLogModel);
					}
				}
			}
			return returnFailureLogList;
		} catch (Exception e) {
			slf4jLogger.error("Error while fetching failure log list completed and rejected", e);
			return null;
		}
	}

	public List<ChartDataDto> getServiceLogListByCount() {

		List<ChartDataDto> chartDtoListReturn = new ArrayList<ChartDataDto>();
		try {
			// serviceLogRepo.getListServiceNameByGroup();
			chartDtoListReturn = adminDao.getListServiceNameByGroup();
			/*
			 * List<ServiceLogModel[]> rows = null; for (Object[] row : rows) {
			 * ChartDataDto chartDataDto = new ChartDataDto(); String
			 * serviceName = (String) row[0];
			 * 
			 * //check service name is null. If not null added to return list
			 * if(serviceName!=null && !serviceName.isEmpty()){ int count =
			 * Integer.parseInt(row[1].toString());
			 * chartDataDto.setServiceName(serviceName);
			 * chartDataDto.setY(count); chartDtoListReturn.add(chartDataDto); }
			 * }
			 */

		} catch (Exception e) {

			slf4jLogger.error("Error in getServiceLogListByCount method in adminServiceImpl", e);
			e.printStackTrace();
		}
		return chartDtoListReturn;
	}

	/*
	 * public Boolean AddfilereadData(LogfileDTO logfileDTO) {
	 * 
	 * boolean flag = false; LogfileModel logfileModel =new LogfileModel();
	 * logfileModel.setWeek(logfileDTO.getWeek());
	 * logfileModel.setMonth(logfileDTO.getMonth());
	 * logfileModel.setDate(logfileDTO.getDate());
	 * logfileModel.setTime(logfileDTO.getTime());
	 * logfileModel.setYear(logfileDTO.getYear());
	 * logfileModel.setDomain(logfileDTO.getDomain());
	 * logfileModel.setUser(logfileDTO.getUser());
	 * logfileModel.setTask(logfileDTO.getTask());
	 * logfileModel.setType(logfileDTO.getType()); flag =
	 * adminDao.AddfilereadData(logfileModel);
	 * 
	 * return flag; }
	 */

	public Boolean AddfilereadData(List<FailureLogDto> logfileDTOsList) {
		boolean flag = false;
		List<FailureLogModel> model = adminDao.getAllFailureLog();
		System.out.println("save serviceimpl test2");
		for (int i = 0; i < logfileDTOsList.size(); i++) {
			FailureLogModel FailureLogModel = new FailureLogModel();
			FailureLogModel.setApplicationName(logfileDTOsList.get(i).getApplicationName());
			FailureLogModel.setIpAddress(logfileDTOsList.get(i).getIpAddress());
			FailureLogModel.setNessaQuestion(logfileDTOsList.get(i).getNessaQuestion());
			FailureLogModel.setUserUtterence(logfileDTOsList.get(i).getUserUtterence());
			FailureLogModel.setDate(logfileDTOsList.get(i).getDate());
			FailureLogModel.setSessionId(logfileDTOsList.get(i).getSessionId());
			

			if (model == null || model.size() == 0) {
				flag = adminDao.AddfilereadData(FailureLogModel);
			} else if (model.get(model.size() - 1).getDate().compareTo(FailureLogModel.getDate()) < 0) {
				String[] userMsg=FailureLogModel.getUserUtterence().split("S:  U: ");
				String userUtternace=userMsg[1];
				FailureLogModel.setUserUtterence(userUtternace);
				flag = adminDao.AddfilereadData(FailureLogModel);
			}
		}
		System.out.println("save serviceimpl test4");
		return flag;
	}

	public Boolean addFailureLog(List<FailureLogDto> FailureLogDtoList) {
		boolean flag = false;
		System.out.println("save addFailure Log");
		FailureLogModel FailureLogmodel = new FailureLogModel();
		for (int i = 0; i < FailureLogDtoList.size(); i++) {
			FailureLogmodel.setApplicationName(FailureLogDtoList.get(i).getApplicationName());
			FailureLogmodel.setIpAddress(FailureLogDtoList.get(i).getIpAddress());
			FailureLogmodel.setNessaQuestion(FailureLogDtoList.get(i).getNessaQuestion());
			FailureLogmodel.setUserUtterence(FailureLogDtoList.get(i).getUserUtterence());
			// System.out.println("FailureLogmodel
			// ===========>>"+FailureLogmodel);
			flag = adminDao.addFailureLogdtoData(FailureLogmodel);
		}
		System.out.println("save addFailure test4");

		return flag;
	}

	@Override
	public List<versionModel> getAllversionRecord() {
		// TODO Auto-generated method stub
		// return all record list
		try {
			List<versionModel> alllist = adminDao.getAllversionRecord();
			System.out.println("EmailTemplateListReturn in serviceImpl:::" + alllist.size());

			return alllist;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<nameModel> getAllnameRecord() {
		try {
			List<nameModel> alllist = adminDao.getAllnameRecord();
			System.out.println("EmailTemplateListReturn in serviceImpl:::" + alllist.size());
			return alllist;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<cityModel> getAllcityRecord() {
		try {
			List<cityModel> alllist = adminDao.getAllcityRecord();
			System.out.println("EmailTemplateListReturn in serviceImpl:::" + alllist.size());
			return alllist;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<deptModel> getAlldeptRecord() {
		try {
			List<deptModel> alllist = adminDao.getAlldeptRecord();
			System.out.println("EmailTemplateListReturn in serviceImpl:::" + alllist.size());
			return alllist;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ManagerModel> getAllManagerRecord() {
		try {
			List<ManagerModel> alllist = adminDao.getAllManagerRecord();
			System.out.println("EmailTemplateListReturn in serviceImpl:::" + alllist.size());
			return alllist;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ServiceLogModel saveServiceLog(String ipAddress, String taskName, String applicationName,
			String nessaQuestion, String userUtterance, String serviceName, byte[] bytes) {
		// TODO Auto-generated method stub

		ServiceLogDto serviceLogDto = new ServiceLogDto();
		ServiceLogModel serviceLogModel = new ServiceLogModel();
		System.out.println("save saveServiceLog serviceimpl");
		try {
			serviceLogDto.setIpAddress(ipAddress);
			serviceLogDto.setTaskName(taskName);
			serviceLogDto.setApplicationName(applicationName);
			serviceLogDto.setNessaQuestion(nessaQuestion);
			serviceLogDto.setUserUtterence(userUtterance);
			serviceLogDto.setServiceName(serviceName);
			serviceLogDto.setServerresponse(new String(bytes));

			serviceLogModel.setIpAddress(serviceLogDto.getIpAddress());
			serviceLogModel.setApplicationName(serviceLogDto.getApplicationName());
			serviceLogModel.setNessaQuestion(serviceLogDto.getNessaQuestion());
			serviceLogModel.setTaskName(serviceLogDto.getTaskName());
			serviceLogModel.setUserUtterence(serviceLogDto.getUserUtterence());
			serviceLogModel.setServiceName(serviceLogDto.getServiceName());
			serviceLogModel.setServerresponse(serviceLogDto.getServerresponse());

			serviceLogModel = adminDao.serviceLogDao(serviceLogModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serviceLogModel;
	}

	// ###################################################admin login user
	// serviceimpl###############
	/*
	 * @Override public List<ServiceLogDto> getLoginuserList() { try {
	 * List<ServiceLogDto> returnserviceLogModelList = new
	 * ArrayList<ServiceLogDto>(); List<ServiceLogModel> ServiceLogList =
	 * adminDao.getLoginuserList(); System.out.println(
	 * "list of ServiceLogList in serviceIMPL:::" + ServiceLogList.size());
	 * List<String> list = new ArrayList<String>();
	 * 
	 * for (int j = 0; j < ServiceLogList.size(); j++) {
	 * 
	 * if (ServiceLogList.get(j).getLoginUser() != null) {
	 * list.add(ServiceLogList.get(j).getLoginUser()); } }
	 * 
	 * Set<String> unique = new HashSet<String>(list);
	 * System.out.println("unique>>" + unique.size()); for (String key : unique)
	 * { //System.out.println(key + ":: " + Collections.frequency(list, key));
	 * // System.out.println("key>>"+key);
	 * 
	 * }
	 * 
	 * } catch (Exception e) { e.printStackTrace(); slf4jLogger.error(
	 * "Error while fetching failure log list:", e); return null; } return null;
	 * 
	 * }
	 */

	@Override
	public List<ServiceLogDto> getLoginuserList() {
		List<ServiceLogDto> returnserviceLogModelList = new ArrayList<ServiceLogDto>();
		try {

			List<ServiceLogModel> ServiceLogList = adminDao.getLoginuserList();

			for (int j = 0; j < ServiceLogList.size(); j++) {

				ServiceLogDto serviceLogDto = new ServiceLogDto();
				serviceLogDto.setLoginUser(ServiceLogList.get(j).getLoginUser());
				serviceLogDto.setDateTime(ServiceLogList.get(j).getDateTime());
				serviceLogDto.setIpAddress(ServiceLogList.get(j).getIpAddress());
				returnserviceLogModelList.add(serviceLogDto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnserviceLogModelList;

	}

	// ===============================
	public List<GroupModel> getgroupList() {

		List<GroupModel> GroupModelResponce = new ArrayList<GroupModel>();
		try {
			GroupModelResponce = adminDao.getgroupList();
		} catch (Exception e) {
			slf4jLogger.error("Error in getgroupList method in adminServiceImpl", e);
			e.printStackTrace();
		}
		return GroupModelResponce;
	}

	// @Richa added for channel funtionality

	// Added for channel
	public List<ServiceLogChannelDto> getNoOfUserEachChannel() {
		List<ServiceLogChannelDto> serviceLogChannelByUser = new ArrayList<ServiceLogChannelDto>();
		List<ServiceLogChannelDto> serviceLogChannelList = new ArrayList<ServiceLogChannelDto>();
		try {
			serviceLogChannelByUser = adminDao.getNoOfUserEachChannel();
			for (ServiceLogChannelDto objService : serviceLogChannelByUser) {
				if (objService.getChannel() != null) {

					serviceLogChannelList.add(objService);
				}
			}
		} catch (Exception e) {

			slf4jLogger.error("Error in getServiceLogListByCount method in adminServiceImpl", e);
			e.printStackTrace();
		}
		return serviceLogChannelList;
	}

	public List<UserSpecificChannelDto> getServiceLogDataGroupByChannel() {
		List<UserSpecificChannelDto> listUserData = new ArrayList<>();
		List<UserSpecificChannelDto> serviceLogChannelByUser = new ArrayList<UserSpecificChannelDto>();
		List<ServiceLogChannelDto> serviceModel = new ArrayList<>();
		List<ServiceLogChannelDto> serviceModellist = new ArrayList<>();

		// List<UserSpecificChannelDto> listUserData = new ArrayList<>();
		Gson gson = new Gson();
		try {

			for (ServiceLogChannelDto serviceLogRow : serviceModel) {
				if (serviceLogRow.getChannel() != null) {
					System.out.println("Channel>>>>" + serviceLogRow.getChannel());
					serviceLogRow.setChannel(serviceLogRow.getChannel());
					serviceLogRow.setCountChannelByUser(serviceLogRow.getCountChannelByUser());
					serviceModellist.add(serviceLogRow);
				}
			}

			List<UserChannelCountDto> userChannelCountDtoList = new ArrayList<UserChannelCountDto>();
			userChannelCountDtoList = adminDao.getServiceLogDataGroupByChannel();
			UserChannelCountDto objUserChannelCountDto = new UserChannelCountDto();
			List<String> unameList = new ArrayList<String>();

			Map<String, String> userData = new HashMap<>();
			List<Map<String, Map>> listJson = new ArrayList();

			Map<String, List> map = new HashMap<>();
			List<Map<String, String>> uRowData = new ArrayList<>();

			for (UserChannelCountDto listData : userChannelCountDtoList) {
				if (!unameList.contains(listData.getLoginUser()) && listData.getLoginUser() != null
						&& listData.getLoginUser() != "" && listData.getChannel() != null) {

					unameList.add(listData.getLoginUser());
				}
			}

			UserSpecificChannelDto objUserSpecificChannelDto = new UserSpecificChannelDto();

			for (int unameListCounter = 0; unameListCounter < unameList.size(); unameListCounter++) {
				for (UserChannelCountDto userChannelCountDtoRowData : userChannelCountDtoList) {
					if (userChannelCountDtoRowData.getChannel() != null
							&& userChannelCountDtoRowData.getLoginUser() != null
							&& !map.containsKey(userChannelCountDtoRowData.getLoginUser())
							&& unameList.get(unameListCounter).equals(userChannelCountDtoRowData.getLoginUser())) {

						userData.put(userChannelCountDtoRowData.getChannel(),
								userChannelCountDtoRowData.getUserCount());
						userData.put("firstLogin", userChannelCountDtoRowData.getFirstLoginDate());
						userData.put("userLogin", userChannelCountDtoRowData.getLoginUser());
						userData.put("lastLogin", userChannelCountDtoRowData.getLastLoginDate());
						uRowData.add(userData);
						map.put(userChannelCountDtoRowData.getLoginUser(), uRowData);
						// System.out.println("UserList" +
						// userChannelCountDtoRowData.getLoginUser() +
						// "JsonData"
						// + userData);

					} else if (userChannelCountDtoRowData.getLoginUser() != null
							&& userChannelCountDtoRowData.getChannel() != null
							&& map.containsKey(userChannelCountDtoRowData.getLoginUser())
							&& unameList.get(unameListCounter).equals(userChannelCountDtoRowData.getLoginUser())) {

						uRowData = map.get(userChannelCountDtoRowData.getLoginUser());

						userData.put("userLogin", userChannelCountDtoRowData.getLoginUser());

						userData.put(userChannelCountDtoRowData.getChannel(),
								userChannelCountDtoRowData.getUserCount());
						// userData.put("Channel",userChannelCountDtoRowData.getChannel());
						userData.put("firstLogin", userChannelCountDtoRowData.getFirstLoginDate());

						userData.put("lastLogin", userChannelCountDtoRowData.getLastLoginDate());
						System.out.println("USERDATA:::" + userData);
						uRowData.add(userData);
						map.put(userChannelCountDtoRowData.getLoginUser(), uRowData);

					}

				}

				UserSpecificChannelDto userSpecificChannelDto = new UserSpecificChannelDto();

				Map<String, Map> finalMap = new HashMap<>();

				finalMap.put(unameList.get(unameListCounter), userData);
				JsonElement jsonElement = gson.toJsonTree(userData);
				System.out.println("FirstData" + jsonElement);
				UserSpecificChannelDto pojo = gson.fromJson(jsonElement, UserSpecificChannelDto.class);
				listJson.add(finalMap);
				userData.clear();
				uRowData.clear();
				listUserData.add(pojo);
			}

			System.out.println("final list" + gson.toJson(listUserData));
		} catch (Exception e) {

			slf4jLogger.error("Error in getServiceLogListByCount method in adminServiceImpl", e);
			e.printStackTrace();
		}
		return listUserData;
	}

	public List<String> getUniqueListUser() {
		List<UserChannelCountDto> userChannelCountDtoList = new ArrayList<UserChannelCountDto>();
		userChannelCountDtoList = adminDao.getServiceLogDataGroupByChannel();
		List<String> unameList = new ArrayList<String>();
		for (UserChannelCountDto listData : userChannelCountDtoList) {
			if (!unameList.contains(listData.getLoginUser()) && listData.getLoginUser() != null
					&& listData.getLoginUser() != "" && listData.getChannel() != null) {

				unameList.add(listData.getLoginUser());
			}
		}
		Gson gson = new Gson();
		System.out.println("UserName" + gson.toJson(userChannelCountDtoList));
		return unameList;
	}

	public List<FailureLogActionStatusDto> getFailureLogCompletedRejectedCount() {

		try {
			// failureLogRepo.findAll();
			List<FailureLogActionStatusDto> failureLogList = adminDao.getCountActiveStatus();
			Gson gson = new Gson();
			System.out.println("kskfke" + gson.toJson(failureLogList));
			return failureLogList;
		} catch (Exception e) {
			slf4jLogger.error("Error while fetching failure log list completed and rejected", e);
			return null;
		}
	}

	@Override
	public List<LikeDislikeDto> getLikeDisLike() {

		List<LikeDislikeDto> userChannelCountDtoList = new ArrayList<LikeDislikeDto>();
		userChannelCountDtoList = adminDao.getCountLikeDislike();
		return userChannelCountDtoList;

	}

	public Boolean insertActivityLogData(List<ActivityLog> activeLogDtoList) {
		boolean flag = false;
		ActivityLog activityLog = new ActivityLog();
		ActivityLogModel activityLogModel = new ActivityLogModel();
		System.out.println("save serviceimpl test2");
		if (CollectionUtils.isNotEmpty(activeLogDtoList)) {
			for (ActivityLog activity : activeLogDtoList) {
				activityLog.setClientIP(activity.getClientIP());
				activityLog.setUser(activity.getUser());
				activityLog.setDomain(activity.getDomain());
				activityLog.setTask(activity.getTask());
				activityLog.setSessionId(activity.getSessionId());

				flag = activityLogModel.insertActivityLogData(activityLog);

				System.out.println("save serviceimpl test4");

			}

		}
		return flag;
	}

	public List<LiveChatUser> getLiveChatUsers() {
		LiveChatUser liveChatUser = new LiveChatUser();
		List<LiveChatUser> liveChatUserList = new ArrayList<>();

		List<LiveChatTrigger> userChannelCountDtoList = userChannelCountDtoList = adminDao.getLiveChatUsers();
		if (CollectionUtils.isNotEmpty(userChannelCountDtoList)) {
			for (LiveChatTrigger liveChatUsers : userChannelCountDtoList) {
				liveChatUser.setHandoverDecision(liveChatUsers.getHandoverDecision());
				liveChatUser.setSessionId(liveChatUsers.getSessionId());
				liveChatUser.setUser(liveChatUsers.getUser());
				liveChatUserList.add(liveChatUser);
			}
		}

		return liveChatUserList;

	}

	@Override
	public List<FailureLogModel> getAllFailureLog() {
		// TODO Auto-generated method stub
		try {
			List<FailureLogModel> alllist = adminDao.getAllFailureLog();
			System.out.println("Failure Log  in serviceImpl:::" + alllist.size());

			return alllist;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public FeedBackModel saveFeedbackDialog(Result result) {
		FeedBackModel feedBackModel = new FeedBackModel();
		FeedBackModel feedBackModel1 = new FeedBackModel();

		System.out.println("seriveimpl>>>>>>" + gson.toJson(result));

		feedBackModel = feedBackDialogUtil.saveFeedbackDialog(result);

		feedBackModel1 = adminDao.saveFeedbackDialog(feedBackModel);

		return feedBackModel1;
	}

	@Override
	public List<ServiceLogModel> getDisikeList(String loginUser) {

		List<ServiceLogModel> feedBackListDto = adminDao.getDisikeList(loginUser);

		// TODO Auto-generated method stub
		return feedBackListDto;
	}

	@Override
	public FeedBackDto getDialogById(String sessionId) {

		FeedBackDto feedBackDto = adminDao.getDialogById(sessionId);
		// TODO Auto-generated method stub
		return feedBackDto;
	}

	@Override
	public long getLiveAgentCount() {

		long count = adminDao.getLiveAgentCount();
		// TODO Auto-generated method stub
		return count;
	}

	@Override
	public long gethandOverUserCount() {

		long count = adminDao.gethandOverUserCount();
		// TODO Auto-generated method stub
		return count;
	}

	@Override
	public Agent sendAgentStatus(String status, String agentId, String agentName,String sessionId) {

		try {

			Agent agent = adminDao.sendAgentStatus(status, agentId, agentName,sessionId);
			return agent;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public boolean changeAgentStatus(String sessionId, String agentId) {

		try {
			boolean flag = adminDao.changeAgentStatus(sessionId, agentId);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<ServiceLogModel> getDislikeUserList() {
		
		Map<String, ServiceLogModel> map = new HashMap<String, ServiceLogModel>();
		
		try{
			
			List<ServiceLogModel> serviceLogModel=adminDao.getDislikeUserList();
		for (ServiceLogModel serviceLogModel2 : serviceLogModel) {
			if(!map.containsKey(serviceLogModel2)){
				map.put(serviceLogModel2.getLoginUser(), serviceLogModel2);
			}else{
				map.put(serviceLogModel2.getLoginUser(), serviceLogModel2);
			}
			
		}
		serviceLogModel.clear();
		for(Map.Entry<String, ServiceLogModel> me : map.entrySet()){
			serviceLogModel.add(me.getValue());
		}
			
			
			
			return serviceLogModel;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Agent> getBusyAgent() {
		try {

			List<Agent> agentList = adminDao.getBusyAgent();
			
			return agentList;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Agent checkSessionId(String sessionId, String status) {
		
		Agent agent=null;
		
		agent=adminDao.checkSessionId(sessionId,status);
		
		// TODO Auto-generated method stub
		return agent;
	}

	@Override
	public FailureLogModel getFailureLogBySessionId(String sessionId) {
		try {

			FailureLogModel failureLogModel = adminDao.getFailureLogBySessionId(sessionId);
			
			return failureLogModel;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
