package com.connecticus.admin.service;

import java.util.List;

import com.connecticus.admin.dto.ActivityLog;
import com.connecticus.admin.dto.ChartDataDto;
import com.connecticus.admin.dto.FailureLogActionStatusDto;
import com.connecticus.admin.dto.FailureLogDto;
import com.connecticus.admin.dto.FeedBackDto;
import com.connecticus.admin.dto.FeedBackDialog;
import com.connecticus.admin.dto.LikeDislikeDto;
import com.connecticus.admin.dto.LiveAgentCountDTO;
import com.connecticus.admin.dto.LiveChatUser;
import com.connecticus.admin.dto.Result;
import com.connecticus.admin.dto.ServiceLogDto;
import com.connecticus.admin.dto.UserSpecificChannelDto;
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

public interface AdminService {
	


	public String EmailService(String emailBody, String configurationString, String emailId, String password);

	 /**
	   * method for save FailurLog.
	   * @param args emailBody. emailBody contain conversation of bot.
	   * @return Nothing.
	   */
	public void FailureLog(String emailBody);
	
	
	 /**
	   * method for return failureLogList excepted completed and rejected.
	   * @param 
	   * @return failureLogList excepted completed and rejected.
	   */
	public List<FailureLogModel> getFailureLogList();
	
	/**
	   * method for updating action status and remark 
	   * @param accepted actionStatus and remark pass it in model
	   * @return if record updated it return true else return false
	   */
	public boolean updateFailureLogRecord(FailureLogModel failureLogModel);
	
	/**
	   * method for getting Failure Log by id
	   * @param Failure log Id
	   * @return failure log model
	   */
	public FailureLogModel getFailureLogById(String id);
	
	/**
	   * method for return failureLogList only which action status completed and rejected.
	   * @param 
	   * @return failureLogList only completed and rejected.
	   */
	public List<FailureLogModel> getFailureLogCompletedRejectedList();
	
	/**
	   * method for get count of each service call and show in a pie chart.
	   * @param 
	   * @return count of each service call and service name in list.
	   */
	public List<ChartDataDto> getServiceLogListByCount();

	public  Boolean AddfilereadData(List<FailureLogDto> logfileDTOsList);
	//Boolean AddfilereadData(LogfileDTO logfileDTO);

	List<versionModel> getAllversionRecord();

	List<nameModel> getAllnameRecord();

	List<cityModel> getAllcityRecord();

	List<deptModel> getAlldeptRecord();

	List<ManagerModel> getAllManagerRecord();

	public Boolean addFailureLog(List<FailureLogDto> failureLogDtoList);

	//public Boolean addgonogodetails(List<GonogoDto> gonogoDtoList);

	public ServiceLogModel saveServiceLog(String ipAddress, String taskName, String applicationName,
			String nessaQuestion, String userUtterance, String serviceName, byte[] bytes);

	public List<ServiceLogDto> getLoginuserList();

	public List<GroupModel> getgroupList();

	public List<UserSpecificChannelDto> getServiceLogDataGroupByChannel();

	public List<com.connecticus.admin.dto.ServiceLogChannelDto> getNoOfUserEachChannel();

	public List<String> getUniqueListUser();

	
	public List<LikeDislikeDto> getLikeDisLike();
	public List<FailureLogActionStatusDto> getFailureLogCompletedRejectedCount();

	public Boolean insertActivityLogData(List<ActivityLog> activeLogList);

	public List<LiveChatUser> getLiveChatUsers();

	public List<FailureLogModel> getAllFailureLog();

	public FeedBackModel saveFeedbackDialog(Result result);

	public List<ServiceLogModel> getDisikeList(String loginUser);

	public FeedBackDto getDialogById(String sessionId);

	public long getLiveAgentCount();

	long gethandOverUserCount();

	public Agent sendAgentStatus(String status, String agentId, String agentName, String sessionId);

	public boolean changeAgentStatus(String sessionId, String agentId);

	public List<ServiceLogModel> getDislikeUserList();

	public Agent checkSessionId(String sessionId, String status);

	public List<Agent> getBusyAgent();

	public FailureLogModel getFailureLogBySessionId(String sessionId);


}
