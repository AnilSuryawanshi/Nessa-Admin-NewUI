<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@include file="header.jsp" %>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white">
        <div class="page-wrapper">
          <%@include file="header_low.jsp" %>  
            <!-- END HEADER -->
            <!-- BEGIN HEADER & CONTENT DIVIDER -->
            <div class="clearfix"> </div>
            <!-- END HEADER & CONTENT DIVIDER -->
            <!-- BEGIN CONTAINER -->
            <div class="page-container">
              <%@include file="sideBar.jsp" %>
                <!-- BEGIN CONTENT -->
                <div class="page-content-wrapper">
                    <!-- BEGIN CONTENT BODY -->
                    <div class="page-content">
                        <!-- BEGIN PAGE HEADER-->
                        
                        <!-- BEGIN PAGE BAR -->
                        <!-- <div class="page-bar">
                            <ul class="page-breadcrumb">
                                <li>
                                    <a href="index.html">Home</a>
                                    <i class="fa fa-circle"></i>
                                </li>
                                <li>
                                    <span>Completed and Rejected Failure Log List</span>
                                </li>
                            </ul>
                            
                        </div> -->
                        <!-- END PAGE BAR -->
                        <!-- END PAGE HEADER-->
                       
                        <div class="row">
                            <div class="col-md-12">
                                <!-- BEGIN PORTLET-->
                                <div class="portlet light bordered margintop-sm">
                                    <div class="portlet-title">
                                        <div class="caption font-dark">
                                            <i class="icon-settings font-dark"></i>
                                            <span class="caption-subject bold uppercase"> Edit User Record</span>
                                        </div>
                                    </div>
                                    <div class="portlet-body">
	 
                                    <form id="data" action="${pageContext.request.contextPath}/addedituser" class="form-horizontal form-align" method="POST"> 
											<div class="form-body">
											
												<div class="row">
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label">Full Name:</label>
															<input type="text" class="form-control" rows="1"  placeholder="Enter Full Name" name="name" id="name" required value='${ldapuser.name}'>
														</div>
													</div>
													<!--/span-->
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label">Uid Name:</label>
															<input type="text" class="form-control" rows="1"  placeholder="Enter user Name" name="uid" id="uid" required value='${ldapuser.uid}'>
														
														</div>
													</div>
													<!--/span-->
												</div>
												<!--/row-->
												<div class="row">
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label">Email Id:</label>
															<input type="text" class="form-control" rows="1" placeholder="Enter Email" name="mail" id="mail" required value='${ldapuser.mail}'>
														</div>
													</div>
													<!--/span-->
													<div class="col-md-6">
														<div class="form-group">
															<label class="control-label">Mobile No:</label>
															<input type="text" class="form-control" rows="1" placeholder="Enter Mobile no" name="mobile" id="mobile" required value='${ldapuser.mobile}'>
														</div>
													</div>
													<!--/span-->
												</div>

												</div>
												<!--/row-->
												 

											
											
											<div class="form-actions ">
												 <button type="submit" class="signupbtn">Submit</button>
													<!-- //<a type="submit" class="btn green" href="ldap_user_list.html">Submit</a> -->
											</div>
										</form>
                                       
										
                                    </div>
                                </div>
                                <!-- END PORTLET-->
                            </div>
                            
                        </div>
                        
                    </div>
                    <!-- END CONTENT BODY -->
                </div>
                <!-- END CONTENT -->
                
            </div>
            <!-- END CONTAINER -->
           <%@include file="footer-admin.jsp" %>
           <script>
         

        	function updateRecord(id){
        		var actionStatus= $("#actionStatusID"+id).val();
        		var remark= $("#remarkID"+id).val();
        		var id= Number(id);
        		$.ajax({
        			  type: "POST",
        		      url: "/Nessa-Admin/updateRemark",
        			  contentType: "application/json",
        			  dataType: "json",
        			  data:JSON.stringify({"id":id,"actionStatus":actionStatus,"remark":remark}),
        			  success: function(data) {
        				  if(data==true){
        					  alert("Updated Successfully");
        				  } else{
        					  alert("Fail in update record");
        				  }
        			  }
        		    });


        	}
</script>