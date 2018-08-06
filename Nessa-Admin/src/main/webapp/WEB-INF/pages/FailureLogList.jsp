<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@include file="header.jsp" %>
<script src="https://code.highcharts.com/highcharts.js"></script>
<head>
		<meta charset="utf-8" />
        <title>Nessa Dashboard</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />
 		<!-- BEGIN GLOBAL MANDATORY STYLES -->
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/resources/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/resources/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/resources/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/resources/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
        <!-- END GLOBAL MANDATORY STYLES -->
       <!-- BEGIN PAGE LEVEL PLUGINS -->
        <link href="${pageContext.request.contextPath}/resources/assets/global/plugins/datatables/datatables.min.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/resources/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css" rel="stylesheet" type="text/css" />
        <!-- END PAGE LEVEL PLUGINS -->
        <!-- BEGIN THEME GLOBAL STYLES -->
        <link href="${pageContext.request.contextPath}/resources/assets/global/css/components.min.css" rel="stylesheet" id="style_components" type="text/css" />
        <link href="${pageContext.request.contextPath}/resources/assets/global/css/plugins.min.css" rel="stylesheet" type="text/css" />
        <!-- END THEME GLOBAL STYLES -->
        <!-- BEGIN THEME LAYOUT STYLES -->
        <link href="${pageContext.request.contextPath}/resources/assets/layouts/layout/css/layout.min.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/resources/assets/layouts/layout/css/themes/darkblue.min.css" rel="stylesheet" type="text/css" id="style_color" />
        <link href="${pageContext.request.contextPath}/resources/assets/layouts/layout/css/custom.css" rel="stylesheet" type="text/css" />
        <!-- END THEME LAYOUT STYLES -->
        <link rel="shortcut icon" href="favicon.ico" /> 
          <script>
$(document).ready(function() {
    //$('#example').DataTable();
    
    $('#example').dataTable({
        "order": []
    });
} );

</script>
</head>
    <!-- END HEAD -->
    <body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white">
        <div class="page-wrapper">
            <!-- BEGIN HEADER -->
                <%@include file="header_low.jsp" %>  
                <!-- BEGIN HEADER INNER -->
              
                <%-- <div class="page-header-inner ">
                    <!-- BEGIN LOGO -->
                    <!-- END LOGO -->
                    <!-- BEGIN RESPONSIVE MENU TOGGLER -->
                    <a href="javascript:;" class="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse">
                        <span></span>
                    </a>
                    <!-- END RESPONSIVE MENU TOGGLER -->
                    <!-- BEGIN TOP NAVIGATION MENU -->
                 <%@include file="sideBar.jsp" %>
                    <!-- END TOP NAVIGATION MENU -->
                </div> --%>
                <!-- END HEADER INNER -->
            </div>
            <!-- END HEADER -->
            <!-- BEGIN HEADER & CONTENT DIVIDER -->
            <div class="clearfix"> </div>
            <!-- END HEADER & CONTENT DIVIDER -->
            <!-- BEGIN CONTAINER -->
            <div class="page-container">
            <%@include file="sideBar.jsp" %>
                <!-- BEGIN SIDEBAR -->
               <!--  <div class="page-sidebar-wrapper">
                    BEGIN SIDEBAR
                    DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing
                    DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed
                    <div class="page-sidebar navbar-collapse collapse">
                        BEGIN SIDEBAR MENU
                        DOC: Apply "page-sidebar-menu-light" class right after "page-sidebar-menu" to enable light sidebar menu style(without borders)
                        DOC: Apply "page-sidebar-menu-hover-submenu" class right after "page-sidebar-menu" to enable hoverable(hover vs accordion) sub menu mode
                        DOC: Apply "page-sidebar-menu-closed" class right after "page-sidebar-menu" to collapse("page-sidebar-closed" class must be applied to the body element) the sidebar sub menu mode
                        DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing
                        DOC: Set data-keep-expand="true" to keep the submenues expanded
                        DOC: Set data-auto-speed="200" to adjust the sub menu slide up/down speed
                        <ul class="page-sidebar-menu  page-header-fixed " data-keep-expanded="false" data-auto-scroll="false" data-slide-speed="200" style="padding-top: 20px">
                            DOC: To remove the sidebar toggler from the sidebar you just need to completely remove the below "sidebar-toggler-wrapper" LI element
                            BEGIN SIDEBAR TOGGLER BUTTON
                            <li class="sidebar-toggler-wrapper hide">
                                <div class="sidebar-toggler">
                                    <span></span>
                                </div>
                            </li>
                            END SIDEBAR TOGGLER BUTTON
                            DOC: To remove the search box from the sidebar you just need to completely remove the below "sidebar-search-wrapper" LI element
                            <li class="sidebar-search-wrapper">
                                BEGIN RESPONSIVE QUICK SEARCH FORM
                                DOC: Apply "sidebar-search-bordered" class the below search form to have bordered search box
                                DOC: Apply "sidebar-search-bordered sidebar-search-solid" class the below search form to have bordered & solid search box
                                <form class="sidebar-search  " action="page_general_search_3.html" method="POST">
                                    <a href="javascript:;" class="remove">
                                        <i class="icon-close"></i>
                                    </a>
                                    <div class="input-group">
                                        <input type="text" class="form-control" placeholder="Search...">
                                        <span class="input-group-btn">
                                            <a href="javascript:;" class="btn submit">
                                                <i class="icon-magnifier"></i>
                                            </a>
                                        </span>
                                    </div>
                                </form>
                                END RESPONSIVE QUICK SEARCH FORM
                            </li>
                            <li class="nav-item start ">
                                <a href="index.html" class="nav-link nav-toggle">
                                    <i class="icon-home"></i>
                                    <span class="title">Dashboard</span>
                                </a>
                            </li>
                            <li class="nav-item active  ">
                                <a href="failure_log_list.html" class="">
                                    <i class="fa fa-th-list"></i>
                                    <span class="title">Failure Log List</span>
									<span class="selected"></span>
                                </a>
                            </li>
							<li class="nav-item  ">
                                <a href="comp_rej_log.html" class="">
                                    <i class="fa fa-bar-chart"></i>
                                    <span class="title">Comp &amp; Rej Failure Log List</span>
                                </a>
                            </li>
							<li class="nav-item  ">
                                <a href="javascript:;" class="nav-link nav-toggle">
                                    <i class="icon-diamond"></i>
                                    <span class="title">FAQ Admin</span>
									<span class="arrow"></span>
                                </a>
								<ul class="sub-menu">
									<li class="nav-item ">
                                        <a href="faq.html" class="nav-link ">
                                            <span class="title">Question Answer List</span>
											<span class="selected"></span>
                                        </a>
                                    </li>
                                    <li class="nav-item  ">
                                        <a href="#" class="nav-link ">
                                            <span class="title">Knowledge Sync For Python</span>
                                        </a>
                                    </li>
                                    <li class="nav-item  ">
                                        <a href="#" class="nav-link ">
                                            <span class="title">Email Template List</span>
                                        </a>
                                    </li>
								</ul>
                            </li>
							<li class="nav-item  ">
                                <a href="ldap_user_list.html" class="">
                                    <i class="fa fa-users"></i>
                                    <span class="title">LDap Users List</span>
                                </a>
                            </li>
                        </ul>
                        END SIDEBAR MENU
                        END SIDEBAR MENU
                    </div>
                    END SIDEBAR
                </div> -->
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
                                               <!--  <div class="col-md-6">
                                                    <div class="btn-group pull-right">
                                                        <button class="btn green  btn-outline dropdown-toggle" data-toggle="dropdown">Tools
                                                            <i class="fa fa-angle-down"></i>
                                                        </button>
                                                        <ul class="dropdown-menu pull-right">
                                                            <li>
                                                                <a href="javascript:;">
                                                                    <i class="fa fa-print"></i> Print </a>
                                                            </li>
                                                            <li>
                                                                <a href="javascript:;">
                                                                    <i class="fa fa-file-pdf-o"></i> Save as PDF </a>
                                                            </li>
                                                            <li>
                                                                <a href="javascript:;">
                                                                    <i class="fa fa-file-excel-o"></i> Export to Excel </a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </div> -->
                                            </div>
                                        </div>
										<table id="example" class="table table-bordered table-striped table-hover dataTable no-footer" width="100%" height="70%" role="grid" aria-describedby="example_info" style="width: 100%;">
											<thead>
											  <tr class="bg-header" role="row"><th class="sorting_asc" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Application Name: activate to sort column descending" style="width: 133px;">Application Name</th><th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-label="IP Address: activate to sort column ascending" style="width: 84px;">IP Address</th><th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-label="Nessa Question: activate to sort column ascending" style="width: 122px;">Nessa Question</th><th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-label="User Utterence: activate to sort column ascending" style="width: 203px;">User Utterence</th><th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-label="Action Status: activate to sort column ascending" style="width: 102px;">Action Status</th><th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-label="Remark: activate to sort column ascending" style="width: 170px;">Remark</th><th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-label="Action: activate to sort column ascending" style="width: 52px;">Action</th><th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-label="Action: activate to sort column ascending" style="width: 52px;">View Dialog</th></tr>
											</thead>
											<tbody>
											  <c:forEach var="failureLog" items="${failureLogList}">
									      <tr>
									        <td>${failureLog.applicationName}</td>
									        <td>${failureLog.ipAddress}</td>
									        <td>${failureLog.nessaQuestion}</td>
									        <td>${failureLog.userUtterence}</td>
									       
									        <td><select name="actionStatus" id="actionStatusID${failureLog.id}">
									        <option value="${failureLog.actionStatus}" selected>${failureLog.actionStatus}</option>	
									        <option value="New">New</option>	
									        <option value="Inprogress">Inprogress
												</option><option value="Completed">Completed
												</option><option value="On Hold">On Hold
												</option><option value="Reject">Reject</option>
											
												
												</option></select>
												<script type="text/javascript">
												</script>
											</td>
											<td><input type="text" id="remarkID${failureLog.id}" name="remark" value="${failureLog.remark}"></td>
																				<td><a  onclick="updateRecord('${failureLog.id}')" >Submit</a></td>
																			<td><a onclick="window.open('failureLogDialog.html?sessionId=${failureLog.sessionId}', 
  '_blank', 
  'width=400, \
   height=500, \
   directories=no, \
   location=no, \
   menubar=no, \
   resizable=no, \
   scrollbars=no, \
   status=no, \
   titlebar=no,\
   toolbar=no'); 
   
  return false;" style="font-size: 15px;">View Conversation</a></td>
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
            <!-- BEGIN FOOTER -->
              <%@include file="footer-admin.jsp" %>  
          
            <!-- END FOOTER -->
        </div>
       
        <!--[if lt IE 9]>
<script src="${pageContext.request.contextPath}/resources/assets/global/plugins/respond.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/global/plugins/excanvas.min.js"></script> 
<script src="${pageContext.request.contextPath}/resources/assets/global/plugins/ie8.fix.min.js"></script> 
<![endif]-->
        <!-- BEGIN CORE PLUGINS -->
        <script src="${pageContext.request.contextPath}/resources/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/assets/global/plugins/js.cookie.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
        <!-- END CORE PLUGINS -->
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <script src="${pageContext.request.contextPath}/resources/assets/global/scripts/datatable.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/assets/global/plugins/datatables/datatables.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
        <!-- END PAGE LEVEL PLUGINS -->
        <!-- BEGIN THEME GLOBAL SCRIPTS -->
        <script src="${pageContext.request.contextPath}/resources/assets/global/scripts/app.min.js" type="text/javascript"></script>
        <!-- END THEME GLOBAL SCRIPTS -->
        <!-- BEGIN PAGE LEVEL SCRIPTS -->
        <script src="${pageContext.request.contextPath}/resources/assets/pages/scripts/table-datatables-managed.min.js" type="text/javascript"></script>
        <!-- END PAGE LEVEL SCRIPTS -->
        <!-- BEGIN THEME LAYOUT SCRIPTS -->
        <script src="${pageContext.request.contextPath}/resources/assets/layouts/layout/scripts/layout.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/assets/layouts/layout/scripts/demo.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/assets/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/assets/layouts/global/scripts/quick-nav.min.js" type="text/javascript"></script>
        <!-- END THEME LAYOUT SCRIPTS -->
    </body>
<script>

function updateRecord(id){
	//alert("jj"+id);
	var actionStatus= $("#actionStatusID"+id).val();
	var remark= $("#remarkID"+id).val();
	//var id= Number(id);
	//alert(actionStatus);
	//alert(remark);
	$.ajax({
		  type: "POST",
	      url: "http://ec2-34-211-48-142.us-west-2.compute.amazonaws.com:8085/Nessa-Admin/updateRemark",
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
