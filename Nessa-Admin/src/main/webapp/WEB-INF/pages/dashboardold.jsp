<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nessa Dashboard</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/modern-business.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<link href="${pageContext.request.contextPath}/resources/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<%-- <script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script> --%>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>

	
</head>
<style>
.highcharts-credits{
	display:none;
}
</style>
<body>
<nav class="navbar navbar-default navbar-custom navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
             <img style="float: left;height:20px;height: 39px;margin-top: 21px;margin-right: 25px;" src="resources/images/favicon.png" alt="Nessa Bot" class="siteLogo siteLogo--tiny">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${pageContext.request.contextPath}/">
               
                Nessa Dashboard</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                <li ><a href="${pageContext.request.contextPath}/"><i
						class="fa fa-home fa-lg"></i></a></li>
                   <!--  <li>
                        <a href="about.html">About</a>
                    </li> -->
                    <!-- <li>
                        <a href="services.html">Services</a>
                    </li> -->
                    
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Services <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                         <li>
                                <a href="${pageContext.request.contextPath}/">Welcome</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/getFailureLog">Failure Log List</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/getFailureLogComp">Completed And Rejected Log</a>
                            </li>
                            <li>
                                <a href="http://localhost:8085/nessa/">FAQ Admin</a>
                            </li>
                             <li>
                               <a href="${pageContext.request.contextPath}/allgetldap"">LDap Users List</a>
                            </li>
                        </ul>
                    </li>
                   <!--  <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Blog <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="blog-home-1.html">Blog Home 1</a>
                            </li>
                        </ul>
                    </li> -->
                    <!-- <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Other Pages <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="full-width.html">Full Width Page</a>
                            </li>
                        </ul>
                    </li> -->
                </ul>
            </div>
            
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
        
    </nav>
    
    <div class="container border-container" style="min-height:475px;">
    <div><a  style="font-size: initial;padding-left: 1000px;"href="${pageContext.request.contextPath}/getLoginUserLog"><b>Realistic failure log: 0</b><b>${realisticCount}</b></a></div>
     <div><a  style="font-size: initial;padding-left: 970px;">RetensionRate: ${retentionRate}%<b></b></b></a></div>
    <div><a  style="font-size: initial;padding-left: 970px;">No. of User liked: ${like}<b></b></b></a></div>
     <div><a  style="font-size: initial;padding-left: 970px;">No. of User Disliked: ${disLike}<b></b></b></a></div>
    <div><a  style="font-size: initial;padding-left: 970px;"href="${pageContext.request.contextPath}/getLoginUserLog"><b>No.of User: 0</b><b>${userCount}</b></a></div>
<div id="piecontainer"></div> 
<br>
<div id="barcontainer"></div>
</div>
 
</body>

</html>
<%@include file="footer.jsp" %>

<script>
$(function () {
	var listData;
	/* $.ajax({
		  type: "GET",
	      url: "/Nessa-Admin/getServiceLogList",
		  contentType: "application/json",
		  async : false,
		  success: function(data) {
			  listData=data;
		  }
	    }); */
	
    Highcharts.chart('piecontainer', {
        chart: {
        	spacingTop: 50,
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        title: {
            text: 'Service Call Log In Percentage',
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            },
            showInLegend: true
        },
        series: [{
            name: 'ServiceCall',
            colorByPoint: true,
            data: 
            	[
               <c:forEach items="${serviceLogList}" var="service" varStatus="status">
            {
	            name: '${service.name}',
 	            y: Number('${service.y}')	
            }
            <c:if test="${!status.last}">    
               ,    
           </c:if>  
            </c:forEach>
            	 
					
            		 
            	]
        }]
    });
});


$(function () {
    Highcharts.chart('barcontainer', {
        chart: {
            type: 'column'
        },
        title: {
            text: 'Users count by Channel'
        },
        /* subtitle: {
            text: 'Source: <a href="http://en.wikipedia.org/wiki/List_of_cities_proper_by_population">Wikipedia</a>'
        }, */
        xAxis: {
            type: 'category',
            labels: {
                rotation: 0,
                style: {
                    fontSize: '13px',
                    fontFamily: 'Verdana, sans-serif'
                }
            }
        },
        yAxis: {
            min: 0,
            title: {
                text: 'Channel Count (number)'
            }
        },
        legend: {
            enabled: false
        },
        tooltip: {
            pointFormat: 'Channel Count: <b>{point.y}</b>'
        },
        series: [{
            name: 'Channel',
            data: [
             		<c:forEach items="${serviceLogChannel}" var="channel" varStatus="status">
					[
						'${channel.channel}',
					 	Number('${channel.countChannelByUser}')	
					]
					<c:if test="${!status.last}">    
					  ,    
					</c:if>  
					</c:forEach>
            ],
            dataLabels: {
                enabled: true,
                rotation: -90,
                color: '#FFFFFF',
                align: 'right',
                format: '{point.countChannelByUser}', // one decimal
                y: 10, // 10 pixels down from the top
                style: {
                    fontSize: '13px',
                    fontFamily: 'Verdana, sans-serif'
                }
            }
        }]
    });
});

</script>