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
                       <div id="sample_1_wrapper" class="dataTables_wrapper no-footer">
                        <div class="row">
                            <div class="col-md-12">
                                <!-- BEGIN PORTLET-->
                                <div class="portlet light bordered margintop-sm">
                                   
                                    <div class="portlet-body">
                                        <div class="table-toolbar">
                                       
                                        
                                        <div style="min-height:483px;">
<div class="container border-container" >
<!-- <div class="table-responsive" style="margin-top:1%">      -->
<div><center><h3 class="bg-title">Invalid User Information </h3></center></div>     
<br>
  <table id="example" class="table table-bordered table-striped table-hover" width="100%" height="100%">
<h3 style="margin-left:6%"><p><b>Invalid Information or <i>'UID name'</i> is already in record. Please add valid Information</b></p></h3>
  
  <p style="margin-left:40%"><a href="${pageContext.request.contextPath}/getaddUser">Back to Add user page </a></p>
 

  
  </table>
  </div>
</div>

					 </div>					
                                    </div>
                                </div>
                                <!-- END PORTLET-->
                            </div>
                            
                        </div>
                        </div>
                    </div>
                    <!-- END CONTENT BODY -->
                </div>
                <!-- END CONTENT -->
                
            </div>
            <!-- END CONTAINER -->
<%@include file="footer-admin.jsp" %>  
<c:if test="${not empty message}">
<script>
alert('<c:out value="${message}"/>');
</script>
</c:if>
<script>
function resetField(){
$('#pFile').val("");
}
</script>