package com.connecticus.admin.daoImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.connecticus.admin.dao.AdminDao;
import com.connecticus.admin.dto.ChartDataDto;

import com.connecticus.admin.dto.FailureLogActionStatusDto;
import com.connecticus.admin.dto.FailureLogDto;
import com.connecticus.admin.dto.FeedBackDto;
import com.connecticus.admin.dto.FeedBackDialog;
import com.connecticus.admin.dto.LikeDislikeDto;
import com.connecticus.admin.dto.LiveAgentCountDTO;
import com.connecticus.admin.dto.LiveChatUser;
import com.connecticus.admin.dto.LiveChatsDto;
import com.connecticus.admin.dto.ServiceLogChannelDto;
import com.connecticus.admin.dto.UserChannelCountDto;
import com.connecticus.admin.dto.UserSpecificChannelDto;
import com.connecticus.admin.model.ActivityLogModel;
import com.connecticus.admin.model.Agent;
import com.connecticus.admin.model.AgentStatusModel;
import com.connecticus.admin.model.FailureLogModel;
import com.connecticus.admin.model.FeedBackModel;
import com.connecticus.admin.model.GroupModel;
import com.connecticus.admin.model.LiveChatTrigger;
import com.connecticus.admin.model.LogfileModel;
import com.connecticus.admin.model.ManagerModel;
import com.connecticus.admin.model.ServiceLogModel;
import com.connecticus.admin.model.cityModel;
import com.connecticus.admin.model.deptModel;
import com.connecticus.admin.model.nameModel;
import com.connecticus.admin.model.versionModel;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

@Repository
public class AdminDaoImpl implements AdminDao {

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	MongoTemplate liveChatMongoTemplate;
	private static final String FAILURELOG_COLLECTION = "failurelog";
	private static final String SERVICELOG_COLLECTION = "servicelog";
	private static final String FILEREAD_COLLECTION = "fileread";
	private static final String FILELOG_COLLECTION = "filelog";
	private static final String FEEDBACK_COLLECTION = "feedBackDialog";
	private static final String AGENTSTATUS_COLLECTION = "agentStatus";

	//
	private static final String GONOGODB_COLLECTION = "GonogoDB";

	@Override
	public Boolean saveFailureLog(FailureLogModel failureLogModel) {

		try {
			mongoTemplate.insert(failureLogModel);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<ChartDataDto> getListServiceNameByGroup() {
		// TODO Auto-generated method stub
		List<ChartDataDto> chartDataDtoReturnList = new ArrayList<ChartDataDto>();
		try {
			Aggregation agg = newAggregation(group("serviceName").count().as("y"),
					project("y").and("serviceName").previousOperation(), sort(Sort.Direction.ASC, "y"));

			AggregationResults<ChartDataDto> groupResults = mongoTemplate.aggregate(agg, ServiceLogModel.class,
					ChartDataDto.class);
			for (ChartDataDto chartDataDto : groupResults) {
				if (chartDataDto.getServiceName() != null) {
					chartDataDto.setName(chartDataDto.getServiceName());

					System.out.println("ServiceName=" + chartDataDto.getServiceName());
					System.out.println("count=" + chartDataDto.getY());
					chartDataDtoReturnList.add(chartDataDto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chartDataDtoReturnList;
	}

	@Override
	public List<FailureLogModel> getFailureLogList() {
		// TODO Auto-generated method stub
		List<FailureLogModel> failureLogList = null;
		try {
			failureLogList = mongoTemplate.findAll(FailureLogModel.class, FAILURELOG_COLLECTION);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return failureLogList;
	}

	@Override
	public FailureLogModel getFailureLogByI(String id) {
		// TODO Auto-generated method stub
		FailureLogModel failureLogModel = null;
		try {
			failureLogModel = mongoTemplate.findById(id, FailureLogModel.class, FAILURELOG_COLLECTION);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return failureLogModel;
	}

	@Override
	public void updateFailureLog(FailureLogModel failureLogModel) {
		// TODO Auto-generated method stub
		try {
			mongoTemplate.save(failureLogModel, FAILURELOG_COLLECTION);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean AddfilereadData(FailureLogModel failureLogModel) {
		try {
			mongoTemplate.insert(failureLogModel, FAILURELOG_COLLECTION);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean addFailureLogdtoData(FailureLogModel FailureLogmodel) {

		try {
			mongoTemplate.insert(FailureLogmodel, FAILURELOG_COLLECTION);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<versionModel> getAllversionRecord() {
		// TODO Auto-generated method stub
		return mongoTemplate.findAll(versionModel.class);
	}

	@Override
	public List<nameModel> getAllnameRecord() {
		// TODO Auto-generated method stub
		return mongoTemplate.findAll(nameModel.class);
	}

	@Override
	public List<cityModel> getAllcityRecord() {
		// TODO Auto-generated method stub
		return mongoTemplate.findAll(cityModel.class);
	}

	@Override
	public List<deptModel> getAlldeptRecord() {
		// TODO Auto-generated method stub
		return mongoTemplate.findAll(deptModel.class);
	}

	@Override
	public List<ManagerModel> getAllManagerRecord() {
		// TODO Auto-generated method stub
		return mongoTemplate.findAll(ManagerModel.class);
	}

	public ServiceLogModel serviceLogDao(ServiceLogModel serviceLogModel) {

		try {
			mongoTemplate.insert(serviceLogModel, SERVICELOG_COLLECTION);
			return serviceLogModel;
		} catch (Exception e) {
			e.printStackTrace();
			return serviceLogModel;
		}
	}

	// ######################### admin daoimpl###########
	@Override
	public List<ServiceLogModel> getLoginuserList() {
		// TODO Auto-generated method stub
		List<ServiceLogModel> ServiceLogList = null;
		try {
			ServiceLogList = mongoTemplate.findAll(ServiceLogModel.class, SERVICELOG_COLLECTION);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ServiceLogList;
	}

	/////////////////// queary details code for using
	@Override
	public List<GroupModel> getgroupList() {
		// TODO Auto-generated method stub
		List<GroupModel> groupModellist = new ArrayList<GroupModel>();
		try {
			Aggregation agg = newAggregation(sort(Sort.Direction.ASC, "dateTime"),
					group("loginUser").first("dateTime").as("firstLoginDate").last("dateTime").as("lastLoginDate") // group("loginUser").last(SERVICELOG_COLLECTION).as("lastLoginDate")
			);
			AggregationResults<GroupModel> groupResults = mongoTemplate.aggregate(agg, ServiceLogModel.class,
					GroupModel.class);

			// List<GroupModel> result = groupResults.getMappedResults();
			// System.out.println("groupresult>>>"+groupResults);
			for (GroupModel groupModel : groupResults) {

				groupModel.setId(groupModel.getId());
				groupModel.setFirstLoginDate(groupModel.getFirstLoginDate());
				groupModel.setLastLoginDate(groupModel.getLastLoginDate());
				groupModellist.add(groupModel);

				// System.out.println(groupModel.getId());
				// System.out.println(groupModel.getFirstLoginDate());
				// System.out.println(groupModel.getLastLoginDate());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return groupModellist;
	}

	@Override
	public List<UserChannelCountDto> getServiceLogDataGroupByChannel() {
		// TODO Auto-generated method stub

		List<UserChannelCountDto> userChannelCountDtoList = new ArrayList<UserChannelCountDto>();
		List<UserSpecificChannelDto> listUserData = new ArrayList<>();
		try {

			Aggregation agg = newAggregation(group("channel", "loginUser").count().as("userCount").first("dateTime")
					.as("firstLoginDate").last("dateTime").as("lastLoginDate"));
			AggregationResults<UserChannelCountDto> serviceLogData = mongoTemplate.aggregate(agg, ServiceLogModel.class,
					UserChannelCountDto.class);
			Gson gson = new Gson();
			System.out.println("Mapped Data>>>>>>>>>" + gson.toJson(serviceLogData.getMappedResults()));
			System.out.println("df");

			List<UserChannelCountDto> mappedResultData = serviceLogData.getMappedResults();

			for (UserChannelCountDto userChannelCountDtoRowData : mappedResultData) {

				userChannelCountDtoList.add(userChannelCountDtoRowData);
			}
			// Gson gson = new Gson();
			System.out.println("MyData:" + gson.toJson(userChannelCountDtoList));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return userChannelCountDtoList;
	}

	public List<ServiceLogChannelDto> getNoOfUserEachChannel() {

		List<ServiceLogChannelDto> serviceModel = new ArrayList<>();

		try {

			Aggregation agg = newAggregation(
					group("channel").count().as("countChannelByUser").first("channel").as("channel"));

			AggregationResults<ServiceLogChannelDto> serviceLogData = mongoTemplate.aggregate(agg,
					ServiceLogModel.class, ServiceLogChannelDto.class);

			serviceModel = serviceLogData.getMappedResults();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return serviceModel;

	}

	public List<FailureLogActionStatusDto> getCountActiveStatus() {

		List<FailureLogActionStatusDto> failureLogModel = new ArrayList<>();

		try {
			MatchOperation matchOp = Aggregation.match(Criteria.where("actionStatus").in(Arrays.asList("Completed")));
			Aggregation agg = newAggregation(matchOp, group("actionStatus").count().as("completedCount"));

			AggregationResults<FailureLogActionStatusDto> failureLogData = mongoTemplate.aggregate(agg,
					FailureLogModel.class, FailureLogActionStatusDto.class);

			failureLogModel = failureLogData.getMappedResults();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return failureLogModel;

	}

	public List<LikeDislikeDto> getCountLikeDislike() {

		List<LikeDislikeDto> serviceModel = new ArrayList<>();

		try {

			MatchOperation matchOp = Aggregation.match(new Criteria("feedback").exists(true).ne(""));

			Aggregation agg = newAggregation(matchOp,
					group("feedback").count().as("feedbackCount").first("feedback").as("feedback"));

			AggregationResults<LikeDislikeDto> serviceLogData = mongoTemplate.aggregate(agg, ServiceLogModel.class,
					LikeDislikeDto.class);

			serviceModel = serviceLogData.getMappedResults();
			Gson gson = new Gson();
			System.out.println("ServiceDatatat>>" + gson.toJson(serviceModel));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return serviceModel;

	}

	public boolean insertActivityLogData(ActivityLogModel activityLogModel) {
		try {
			mongoTemplate.insert(activityLogModel);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<LiveChatTrigger> getLiveChatUsers() {

		UserCredentials userCredentials = new UserCredentials("", "");
		MongoClient mongoClient;
		List<LiveChatTrigger> failureLogModel = new ArrayList<>();
		try {
			mongoClient = new MongoClient("localhost:27017");

			failureLogModel = liveChatMongoTemplate.findAll(LiveChatTrigger.class);
			Gson gson = new Gson();
			return failureLogModel;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return failureLogModel;
	}

	@Override
	public List<FailureLogModel> getAllFailureLog() {
		// TODO Auto-generated method stub
		return mongoTemplate.findAll(FailureLogModel.class);
	}

	@Override
	public FeedBackModel saveFeedbackDialog(FeedBackModel feedBackModel) {

		mongoTemplate.insert(feedBackModel, FEEDBACK_COLLECTION);

		return feedBackModel;

	}

	@Override
	public List<ServiceLogModel> getDisikeList(String loginUser) {
		Query query = new Query();

		query.addCriteria(Criteria.where("feedback").is("dislike"));
		query.addCriteria(Criteria.where("loginUser").is(loginUser));
		List<ServiceLogModel> list = mongoTemplate.find(query, ServiceLogModel.class);

		return list;
	}

	@Override
	public FeedBackDto getDialogById(String sessionId) {
		List<FeedBackModel> feedBackModel = null;

		Query query = new Query();
		FeedBackDto feedBackDto = new FeedBackDto();

		query.addCriteria(Criteria.where("sessionId").is(sessionId));

		feedBackModel = mongoTemplate.find(query, FeedBackModel.class);
		ModelMapper modelMapper = new ModelMapper();
		for (FeedBackModel feedBackModel2 : feedBackModel) {

			feedBackDto = modelMapper.map(feedBackModel2, FeedBackDto.class);

		}

		return feedBackDto;
	}

	@Override
	public long getLiveAgentCount() {

		MongoClient mongoClient;
		long count = 0;
		List<LiveChatTrigger> failureLogModel = new ArrayList<>();
		try {
			mongoClient = new MongoClient("localhost:27017");

			failureLogModel = liveChatMongoTemplate.findAll(LiveChatTrigger.class);
			count = failureLogModel.size();
			System.out.println(count);
			return count;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;

	}

	@Override
	public long gethandOverUserCount() {

		MongoClient mongoClient;
		long count = 0;
		List<LiveChatTrigger> failureLogModel = new ArrayList<>();
		try {
			mongoClient = new MongoClient("localhost:27017");

			failureLogModel = liveChatMongoTemplate.findAll(LiveChatTrigger.class);

			Map<String, LiveChatTrigger> uniqueMap = new HashMap<String, LiveChatTrigger>();

			for (LiveChatTrigger liveChatTrigger : failureLogModel) {
				LiveChatTrigger liveChatTrigger1 = new LiveChatTrigger();

				if (!uniqueMap.containsKey(liveChatTrigger.getUser())) {

					liveChatTrigger1.setUser(liveChatTrigger.getUser());
					uniqueMap.put(liveChatTrigger.getUser(), liveChatTrigger1);

				}

			}
			count = uniqueMap.size();

			System.out.println(count);
			return count;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public Agent sendAgentStatus(String status, String agentId, String agentName, String sessionId) {

		try {
			List<Agent> agent = null;
			Agent agent1 = new Agent();
			Agent agentList = null;

			Query query = new Query();
			query.addCriteria(Criteria.where("status").is("available"));
			agent = mongoTemplate.find(query, Agent.class);
			if (agent.isEmpty()) {

				System.out.println("All Agents Are Busy");

				return null;

			} else {
				for (Agent agent2 : agent) {

					agent1 = agent2;

				}
				MongoClient mongo = new MongoClient("localhost", 27017);
				DB db = mongo.getDB("servicenowdb");
				DBCollection col = db.getCollection("agent");
				// Add a new field in a single document
				DBObject query1 = new BasicDBObject("agentId", agent1.getAgentId());
				DBObject update = new BasicDBObject();
				update.put("$set", new BasicDBObject("status", status).append("sessionId", sessionId));
				WriteResult result = col.updateMulti(query1, update);

				Query query2 = new Query();
				query2.addCriteria(Criteria.where("status").is(status));
				query2.addCriteria(Criteria.where("sessionId").is(sessionId));

				agent1 = mongoTemplate.findOne(query2, Agent.class);
				System.out.println("Details of updated>>> >>>" + agent);

			}

			return agent1;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public boolean changeAgentStatus(String sessionId, String agentId) {

		try {
			MongoClient mongo = new MongoClient("localhost", 27017);
			DB db = mongo.getDB("servicenowdb");
			DBCollection col = db.getCollection("agent");
			// Add a new field in a single document
			DBObject query1 = new BasicDBObject("sessionId", sessionId);

			DBObject update = new BasicDBObject();
			update.put("$set", new BasicDBObject("status", "available").append("sessionId", ""));
			WriteResult result = col.updateMulti(query1, update);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public List<ServiceLogModel> getDislikeUserList() {
		try {

			List<ServiceLogModel> serviceLogModel = null;

			Query query = new Query();
			query.addCriteria(Criteria.where("feedback").is("dislike"));
			serviceLogModel = mongoTemplate.find(query, ServiceLogModel.class);
			return serviceLogModel;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<Agent> getBusyAgent() {

		List<Agent> agentList = null;

		try {

			Query query = new Query();
			query.addCriteria(Criteria.where("status").is("busy"));
			agentList = mongoTemplate.find(query, Agent.class);

			return agentList;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public Agent checkSessionId(String sessionId, String status) {

		Agent agent = null;
		try {

			Query query = new Query();
			query.addCriteria(Criteria.where("status").is(status));
			query.addCriteria(Criteria.where("sessionId").is(sessionId));

			agent = mongoTemplate.findOne(query, Agent.class);

			return agent;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public FailureLogModel getFailureLogBySessionId(String sessionId) {
		FailureLogModel failureLogModel = null;

		try {

			Query query = new Query();
			query.addCriteria(Criteria.where("sessionId").is(sessionId));

			failureLogModel = mongoTemplate.findOne(query, FailureLogModel.class);

			return failureLogModel;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
