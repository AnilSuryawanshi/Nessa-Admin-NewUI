<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@include file="header.jsp" %>
<script src="https://code.highcharts.com/highcharts.js"></script>
<style>
body {font-family: Arial, Helvetica, sans-serif;}

/* The Modal (background) */
.modal {
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
    padding-top: 100px; /* Location of the box */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content {
    background-color: #fefefe;
    margin: auto;
    padding: 20px;
    border: 1px solid #888;
    width: 80%;
}

/* The Close Button */
.close {
    color: #aaaaaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
}

.close:hover,
.close:focus {
    color: #000;
    text-decoration: none;
    cursor: pointer;
}
</style>
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
          var applicationContextPath = "${pageContext.request.contextPath}";
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
                                            <span class="caption-subject bold uppercase">Users Instance</span>
                                            
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
										<table id="example" class="table table-bordered table-striped dataTable no-footer" width="100%" height="70%" role="grid" aria-describedby="example_info" style="width: 100%;">
											<thead class="bg-header">
											  <tr role="row"><th class="sorting_asc" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Full Name: activate to sort column descending" style="width: 171px;">Instance</th><th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-label="Uid Name: activate to sort column ascending" style="width: 125px;">ipAddress</th><th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-label="Email ID: activate to sort column ascending" style="width: 316px;">Date & Time</th><th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-label="Email ID: activate to sort column ascending" style="width: 316px;">View Dialog history</th></tr>
											</thead>
											<tbody>
											<c:forEach var="item" items="${feedBackListDto}"> 
      <tr>
      	
      	<%-- <td><a onclick="editInvoice('${item.sessionId}')">${item.sessionId}</a></td> --%>
      	<td>${item.sessionId}</td>
       	<td>${item.ipAddress}</td>
        <td>${item.dateTime}</td>
        <%-- <td><a onclick="editInvoice('${item.sessionId}')">View Conversation</a></td>
         <td><a onclick="window.open('chatBot.html?sessionId=${item.sessionId}'">View Conversation</a></td> --%>
        <td><a onclick="window.open('getDislikeDialog.html?sessionId=${item.sessionId}', 
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
                             

<!-- The Modal -->

            
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
       <div  id="main-content"></div>
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
         <script src="${pageContext.request.contextPath}/resources/js/editInvoice.js" type="text/javascript"></script>
         <script>
// Get the modal
var modal = document.getElementById('myModal');

// Get the button that opens the modal
var btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal 
btn.onclick = function() {
    modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
</script>
         
        <!-- END THEME LAYOUT SCRIPTS -->
    </body>
