<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@include file="header.jsp" %>
<script src="https://code.highcharts.com/highcharts.js"></script>

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
                <!-- END SIDEBAR -->
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
                                    <span>Failure Log List</span>
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
                                            <span class="caption-subject bold uppercase"> Failure Log List</span>
                                        </div>
                                    </div>
                                    <div class="portlet-body">
                                        <div class="table-toolbar">
                                            <div class="row">
                                                <div class="col-md-6">
                                                   <!--  <div class="btn-group">
                                                        <button id="sample_editable_1_new" class="btn sbold green"> Add New
                                                            <i class="fa fa-plus"></i>
                                                        </button>
                                                    </div> -->
                                                </div>
                                            
                                            </div>
                                        </div>
										<table id="example" class="table table-bordered table-striped table-hover dataTable no-footer" width="100%" height="70%" role="grid" aria-describedby="example_info" style="width: 100%;">
											<thead>
											  <tr class="bg-header" role="row">
												  <th class="sorting_asc" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Application Name: activate to sort column descending" style="width: 133px;">Application Name</th>
												  <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-label="IP Address: activate to sort column ascending" style="width: 84px;">IP Address</th>
												  <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-label="Nessa Question: activate to sort column ascending" style="width: 122px;">Nessa Question</th>
												  <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-label="User Utterence: activate to sort column ascending" style="width: 203px;">User Utterence</th>
												  <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-label="Action Status: activate to sort column ascending" style="width: 102px;">Action Status</th>
												  <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-label="Remark: activate to sort column ascending" style="width: 170px;">Remark</th>
												  <th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-label="Action: activate to sort column ascending" style="width: 52px;">Action</th>
											  </tr>
											</thead>
											<tbody>
											  <c:forEach var="failureLog" items="${failureLogList}">
											      <tr>
											        <td>${failureLog.applicationName}</td>
											        <td>${failureLog.ipAddress}</td>
											        <td>${failureLog.nessaQuestion}</td>
											        <td>${failureLog.userUtterence}</td>
											        
											        <td><select name="actionStatus" id="actionStatusID">
															<option value="${failureLog.actionStatus}" selected="">
															</option><option value="New">New
															</option><option value="Inprogress">Inprogress
															</option><option value="Completed">Completed
															</option><option value="On Hold">On Hold
															</option><option value="Reject">Reject
															</option></select>
													</td>
	        
												<td><input type="text" id="remarkID${failureLog.id}" name="remark" value="${failureLog.remark}"></td>
												<td><a  onclick="updateRecord('${failureLog.id}')" >Submit</a></td>
	      </tr>
      </c:forEach>
											</tbody>
										  </table>
										
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
$(document).ready(function() {
    $('#example').DataTable();
} );


function updateRecord(id){
	alert("jj"+id);
	var actionStatus= $("#actionStatusID"+id).val();
	var remark= $("#remarkID"+id).val();
	//var id= Number(id);
	alert(actionStatus);
	alert(remark);
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