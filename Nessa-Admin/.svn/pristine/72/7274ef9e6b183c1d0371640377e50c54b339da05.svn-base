package com.connecticus.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.connecticus.admin.model.ManagerModel;
import com.connecticus.admin.model.cityModel;
import com.connecticus.admin.model.deptModel;
import com.connecticus.admin.model.nameModel;
import com.connecticus.admin.model.versionModel;
import com.connecticus.admin.service.AdminService;

import com.google.gson.Gson;

@RestController
public class versioncontroller {
	@Autowired
	AdminService adminService;
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(AdminController.class);
	Gson gson = new Gson();

	

	@RequestMapping(value = { "/testingclass123" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView Presentation() {
		ModelAndView view = new ModelAndView();
		view.setViewName("AddUser");
		System.out.println("Presentation page is working good ");
		slf4jLogger.error(" IN WELCOME Presentation");
		slf4jLogger.debug("IN WELCOME DEBUG");

		return view;
	}
	
	

	
	@RequestMapping(value = "/getversionList", method = RequestMethod.GET)
	// public ModelAndView getRecordList() {
	@ResponseBody
	public List<versionModel> getversionList() {

		// ModelAndView mv = new ModelAndView("List");
		List<versionModel> alllist = new ArrayList<versionModel>();
		String deparment="";
		try {
			alllist = adminService.getAllversionRecord();

			for (int i = 0; i < alllist.size(); i++) {

				//alllist.get(i).getName();
				//System.out.println("type===" +alllist.get(i).getType());
				if(alllist.get(i).getType().equals("deparment")){
			    	System.out.println("name all list===" + alllist.get(i).getList());
			    	deparment= alllist.get(i).getList();
			       }
				
			}			
			return alllist;
		} catch (Exception e) {
			e.printStackTrace();
			// return mv;
			return alllist;
		}
	}
	
	
	@RequestMapping(value = "/getnameList", method = RequestMethod.GET)
	@ResponseBody
	public nameModel getnameList() {
		List<nameModel> alllist = new ArrayList<nameModel>();
		
		try {
			alllist = adminService.getAllnameRecord();	
			for (int i = 0; i < alllist.size(); i++) {
			    	System.out.println("name all list===" + alllist.get(0).getType());			    	
			       }
		
			return alllist.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return alllist.get(0);
		}
	}
	
	
	@RequestMapping(value = "/getdeptList", method = RequestMethod.GET)
	@ResponseBody
	public deptModel getdeptList() {
		List<deptModel> alllist = new ArrayList<deptModel>();
		try {
			alllist = adminService.getAlldeptRecord();	
			
			return alllist.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return alllist.get(0);
		}
	}
	
	@RequestMapping(value = "/getcityList", method = RequestMethod.GET)
	@ResponseBody
	public cityModel getcityList() {
		List<cityModel> alllist = new ArrayList<cityModel>();
		try {
			alllist = adminService.getAllcityRecord();	
			
			return alllist.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return alllist.get(0);
		}
	}
	
	@RequestMapping(value = "/getmanagerList", method = RequestMethod.GET)
	@ResponseBody
	public ManagerModel getmanagerList() {
		List<ManagerModel> alllist = new ArrayList<ManagerModel>();
		try {
			alllist = adminService.getAllManagerRecord();	
			
			return alllist.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return alllist.get(0);
		}
	}
}
