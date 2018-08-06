package com.connecticus.admin.dao;

import java.util.List;

import com.connecticus.admin.dto.ChartDataDto;
import com.connecticus.admin.dto.FailureLogActionStatusDto;
import com.connecticus.admin.dto.FailureLogDto;
import com.connecticus.admin.dto.FeedBackDto;
import com.connecticus.admin.dto.FeedBackDialog;
import com.connecticus.admin.dto.LikeDislikeDto;
import com.connecticus.admin.dto.LiveAgentCountDTO;
import com.connecticus.admin.dto.LiveChatUser;
import com.connecticus.admin.dto.LoginuserDTO;
import com.connecticus.admin.dto.ServiceLogChannelDto;
import com.connecticus.admin.dto.UserChannelCountDto;
import com.connecticus.admin.model.Agent;
import com.connecticus.admin.model.AgentStatusModel;
import com.connecticus.admin.model.FailureLogModel;
import com.connecticus.admin.model.FeedBackModel;
import com.connecticus.admin.model.GonogoModel;
import com.connecticus.admin.model.GroupModel;
import com.connecticus.admin.model.LiveChatTrigger;
import com.connecticus.admin.model.LogfileModel;
import com.connecticus.admin.model.ManagerModel;
import com.connecticus.admin.model.ServiceLogModel;
import com.connecticus.admin.model.cityModel;
import com.connecticus.admin.model.deptModel;
import com.connecticus.admin.model.nameModel;
import com.connecticus.admin.model.versionModel;

public interface AdminDao {

	Boolean saveFailureLog(FailureLogModel failureLogModel);

	List<ChartDataDto> getListServiceNameByGroup();

	List<FailureLogModel> getFailureLogList();

	FailureLogModel getFailureLogByI(String id);

	void updateFailureLog(FailureLogModel failureLogModel);

	  boolean AddfilereadData(FailureLogModel failureLogModel);

	List<versionModel> getAllversionRecord();

	List<nameModel> getAllnameRecord();

	List<cityModel> getAllcityRecord();

	List<deptModel> getAlldeptRecord();

	List<ManagerModel> getAllManagerRecord();

	boolean addFailureLogdtoData(FailureLogModel failureLogmodel);

	//boolean addgonogodetails(GonogoModel gonogoModel);

	ServiceLogModel serviceLogDao(ServiceLogModel serviceLogModel);

	List<ServiceLogModel> getLoginuserList();

	List<GroupModel> getgroupList();

	List<UserChannelCountDto> getServiceLogDataGroupByChannel();

	List<ServiceLogChannelDto> getNoOfUserEachChannel();

	List<FailureLogActionStatusDto> getCountActiveStatus();
	List<LikeDislikeDto> getCountLikeDislike();
	List<LiveChatTrigger> getLiveChatUsers();

	List<FailureLogModel> getAllFailureLog();

	FeedBackModel saveFeedbackDialog(FeedBackModel feedBackModel);

	List<ServiceLogModel> getDisikeList(String loginUser);

	FeedBackDto getDialogById(String sessionId);

	long getLiveAgentCount();

	long gethandOverUserCount();

	Agent sendAgentStatus(String status, String agentId, String agentName, String sessionId);

	boolean changeAgentStatus(String sessionId, String agentId);

	List<ServiceLogModel> getDislikeUserList();

	List<Agent> getBusyAgent();

	Agent checkSessionId(String sessionId, String status);

	FailureLogModel getFailureLogBySessionId(String sessionId);

}
