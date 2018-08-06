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
                                            <span class="caption-subject bold uppercase"> DisLiked Users List</span>
                                        </div>
                                    </div>
                                    <div class="portlet-body">
                 
										<table id="example" class="table table-bordered table-striped dataTable no-footer" width="100%" height="70%" role="grid" aria-describedby="example_info" style="width: 100%;">
											<thead class="bg-header">
											  <tr role="row"><th class="sorting_asc" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Full Name: activate to sort column descending" style="width: 171px;">User Name</th><th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-label="Uid Name: activate to sort column ascending" style="width: 125px;">ipAddress</th><th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-label="Email ID: activate to sort column ascending" style="width: 316px;">Date & Time</th></tr>
											</thead>
											<tbody>
									<c:forEach var="item" items="${serviceLogModel}"> 
      <tr>
      	
      	<td><a  href="${pageContext.request.contextPath}/getDisikeList?userlogin=${item.loginUser}">${item.loginUser}</a></td>
      	<td>${item.ipAddress}</td>
        <td>${item.dateTime}</td>
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

</script>