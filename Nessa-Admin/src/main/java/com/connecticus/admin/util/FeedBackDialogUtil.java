package com.connecticus.admin.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.connecticus.admin.dto.Result;
import com.connecticus.admin.model.FeedBackDialog;
import com.connecticus.admin.model.FeedBackModel;
import com.google.gson.Gson;

@Component
public class FeedBackDialogUtil {

	public static FeedBackModel saveFeedbackDialog(Result result) {

		Gson gson = new Gson();
		System.out.println("dialogUtil>>>>>>" + gson.toJson(result));

		FeedBackModel feedBackModel = new FeedBackModel();
		List<FeedBackDialog> feedBackDialogList = new ArrayList<FeedBackDialog>();

		feedBackModel.setUser(result.getUser());
		feedBackModel.setSessionId(result.getInstanceID());

		for (int i = 0; i < result.getDialog().length; i++) {
			FeedBackDialog feedBackDialog = new FeedBackDialog();

			feedBackDialog.setUserUtterance(result.getDialog()[i].getU());
			System.out.println("forUser>>>>>>" + result.getDialog()[i].getU());

			feedBackDialog.setNessaUtterance(result.getDialog()[i].getS());
			System.out.println("forNessa>>>>>>" + result.getDialog()[i].getS());

			feedBackDialogList.add(feedBackDialog);

		}
		feedBackModel.setFeedBAckDialogList(feedBackDialogList);

		return feedBackModel;
	}

}
