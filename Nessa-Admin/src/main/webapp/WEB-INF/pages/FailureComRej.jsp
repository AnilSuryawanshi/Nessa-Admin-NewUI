<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@include file="header.jsp" %>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script>
$(document).ready(function() {
    //$('#example').DataTable();
    
    $('#example').dataTable({
        "order": []
    });
} );

</script>
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
         
</head>
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
                                            <span class="caption-subject bold uppercase"> Completed and Rejected Failure Log List</span>
                                        </div>
                                    </div>
                                    <div class="portlet-body">
                                        <div class="table-toolbar">
                                           
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
										<table id="example" class="table table-bordered table-striped table-hover dataTable no-footer" width="100%" role="grid" aria-describedby="example_info" style="width: 100%;">
											<thead>
											  <tr class="bg-header" role="row"><th class="sorting_asc" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Application Name: activate to sort column descending" style="width: 133px;">Application Name</th><th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-label="IP Address: activate to sort column ascending" style="width: 84px;">IP Address</th><th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-label="Nessa Question: activate to sort column ascending" style="width: 318px;">Nessa Question</th><th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-label="User Utterence: activate to sort column ascending" style="width: 206px;">User Utterence</th><th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-label="Action Status: activate to sort column ascending" style="width: 103px;">Action Status</th><th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-label="Remark: activate to sort column ascending" style="width: 61px;">Remark</th></tr>
											</thead>
											<tbody>
											<c:forEach var="failureLog" items="${failureLogList}">
										      <tr>
										        <td>${failureLog.applicationName}</td>
										        <td>${failureLog.ipAddress}</td>
										        <td>${failureLog.nessaQuestion}</td>
										        <td>${failureLog.userUtterence}</td>
												<td>${failureLog.actionStatus}</td>
												<td>${failureLog.remark}</td>
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