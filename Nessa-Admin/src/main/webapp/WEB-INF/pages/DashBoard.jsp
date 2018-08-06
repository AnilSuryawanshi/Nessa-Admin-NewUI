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
                    <div class="col-sm-12 margintop-sm">
                    <div class="row">
                        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                            <div class="dashboard-stat2 ">
                                <div class="display">
                                    <div class="number">
                                        <h3 class="font-green-sharp">
                                            <a  href="${pageContext.request.contextPath}/getLoginUserLog"><span data-counter="counterup" data-value="7800">${userCount}</span></a>
                                           
                                        </h3>
                                        <small>Total Users</small>
                                    </div>
                                   
                                </div>
                               
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                            <div class="dashboard-stat2 ">
                                <div class="display">
                                    <div class="number">
                                        <h3 class="font-green-sharp">
                                            <span data-counter="counterup" data-value="7800">${count}</span>
                                           
                                        </h3>
                                        <small>Total Live Chat Count</small>
                                    </div>
                                   
                                </div>
                               
                            </div>
                        </div>
						<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                            <div class="dashboard-stat2 ">
                                <div class="display">
                                    <div class="number">
                                        <h3 class="font-green-sharp">
                                         <i class="fa fa-thumbs-o-up" aria-hidden="true"></i>
                                            <span data-counter="counterup" data-value="7800">${like}</span>
                                        </h3>
                                        <small>No. of Like</small>
                                    </div>
                                   
                                </div>
                               
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                            <div class="dashboard-stat2 ">
                                <div class="display">
                                    <div class="number">
                                         <h3 class="font-green-sharp">
                                         <i class="fa fa-thumbs-o-down" aria-hidden="true"></i>
                                            <a  href="${pageContext.request.contextPath}/getDislikeUserList"> <span data-counter="counterup" data-value="7800">${disLike}</span></a>
                                        </h3>
                                        <small>No. of Dislike</small>
                                    </div>
                                   
                                </div>
                               
                            </div>
                        </div>
						 <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                            <div class="dashboard-stat2 ">
                                <div class="display">
                                    <div class="number">
                                        <h3 class="font-green-sharp">
                                            <span data-counter="counterup" data-value="7800">${retentionRate}%</span>
                                           
                                        </h3>
                                        <small>Retention Rate</small>
                                    </div>
                                   
                                </div>
                               
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                            <div class="dashboard-stat2 ">
                                <div class="display">
                                    <div class="number">
                                         <h3 class="font-green-sharp">
                                       <span data-counter="counterup" data-value="7800">${activeSession}</span>
                                        </h3>
                                        <small>Active users</small>
                                    </div>
                                   
                                </div>
                               
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                            <div class="dashboard-stat2 ">
                                <div class="display">
                                    <div class="number">
                                         <h3 class="font-green-sharp">
                                          <span data-counter="counterup" data-value="7800">${count1}</span>
                                        </h3>
                                        <small>No. of Users Used LiveChat</small>
                                    </div>
                                   
                                </div>
                               
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                            <div class="dashboard-stat2 ">
                                <div class="display">
                                    <div class="number">
                                         <h3 class="font-green-sharp">
                                            <span data-counter="counterup" data-value="7800">4</span>
                                        </h3>
                                        <small>No. of LiveAgents's</small>
                                    </div>
                                   
                                </div>
                               
                            </div>
                        </div>
						<div class="clearfix"></div>
                    </div>
                    
                    </div>
					
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="portlet light portlet-fit ">
                                <div class="portlet-title">
                                    <div class="caption">
                                        <i class="icon-directions font-green hide"></i>
                                        <span class="caption-subject bold font-dark uppercase "> Engagement Statistics</span>
                                        
                                    </div>
                                    <div class="actions">
                                        <div class="btn-group">
                                            <a class="btn blue btn-outline btn-circle btn-sm" href="javascript:;" data-toggle="dropdown" data-hover="dropdown" data-close-others="true" style="border:none;"> This Week
                                                <i class="fa fa-angle-down"></i>
                                            </a>
                                            <ul class="dropdown-menu pull-right">
                                                <li>
                                                    <a href="javascript:;"> Action 1</a>
                                                </li>
                                                <li class="divider"> </li>
                                                <li>
                                                    <a href="javascript:;">Action 2</a>
                                                </li>
                                                <li>
                                                    <a href="javascript:;">Action 3</a>
                                                </li>
                                                <li>
                                                    <a href="javascript:;">Action 4</a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="portlet-body">
                                    <div id="chartdiv" style="width: 100%; height: 300px; background-color: #FFFFFF;" ></div>
                                </div>
                            </div>
                        </div>
                        
                   
					
						<div class="col-sm-6">
							<!-- BEGIN REGIONAL STATS PORTLET-->
								<div class="portlet light "  style="padding: 12px 0px 15px">
									<div class="portlet-title" style="padding: 10px 20px 10px">
										<div class="caption">
											<i class="icon-share font-dark hide"></i>
											<span class="caption-subject font-dark bold ">CHANNELS</span>
										</div>
									</div>
									<div class="portlet-body">
										<div id="chartdiv3" style="width: 100%; height: 310px; background-color: #FFFFFF;" ></div>
										
									</div>
								</div>
								<!-- END REGIONAL STATS PORTLET-->
						
							   
						</div>
					
					
					</div>
					
					
					
					<div class="row">
					
						<div class="col-sm-6">
							<!-- BEGIN REGIONAL STATS PORTLET-->
								<div class="portlet light ">
									<div class="portlet-title">
										<div class="caption">
											<i class="icon-share font-dark hide"></i>
											<span class="caption-subject font-dark bold ">Conversations & Fallbacks</span>
										</div>
									</div>
									<div class="portlet-body">
										<div id="chartdiv4" style="width: 100%; height: 300px; background-color: #FFFFFF;" ></div>
										
									</div>
								</div>
								<!-- END REGIONAL STATS PORTLET-->
						
							   
						</div>
						<div class="col-sm-6">
                            <!-- BEGIN PORTLET-->
                            <div class="portlet light ">
                                <div class="portlet-title tabbable-line">
                                    <div class="caption">
                                        <i class="icon-globe font-dark hide"></i>
                                        <span class="caption-subject font-dark bold uppercase">Queries</span>
                                    </div>
                                    
                                </div>
                                <div class="portlet-body">
                                    <!--BEGIN TABS-->
                                    <div class="tab-content">
                                        <div class="tab-pane active">
                                            <div class="scroller" style="height: 510px;" data-always-visible="1" data-rail-visible="0">
                                                <ul class="feeds">
                                                 <c:forEach items="${serviceLogList}" var="service" varStatus="status">
            
                                                    <li>
                                                        <div class="col1">
                                                            <div class="cont">
                                                                
                                                                <div class="cont-col2">
                                                                    <div class="desc"> ${service.name}
                                                                        
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col2">
                                                            <div class="date"> ${service.y} %</div>
                                                        </div>
                                                    </li>
                                                    </c:forEach>
                                                </ul>
                                            </div>
                                        </div>
                                        
                                    </div>
                                    <!--END TABS-->
                                </div>
                            </div>
                            <!-- END PORTLET-->
                        </div>
						
						
						<div class="clearfix"></div>
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
AmCharts.makeChart("chartdiv3",
		{
"type": "pie",
"balloonText": "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>",
"gradientType": "linear",
"innerRadius": "70%",
"labelRadius": 12,
"labelText": "[[title]]",
"groupedColor": "#FF0000",
"marginBottom": 0,
"marginTop": 0,
"outlineThickness": 0,
"titleField": "category",
"valueField": "column-1",
"handDrawScatter": 0,
"theme": "light",
"allLabels": [],
"balloon": {
"offsetX": -1
},
"legend": {
"enabled": false,
"align": "center",
"markerBorderThickness": 0,
"markerLabelGap": 2,
"markerSize": 11,
"markerType": "circle"
},
"titles": [],
"dataProvider": [
<c:forEach items="${serviceLogChannel}" var="channel" varStatus="status">
{
	"category":"Web",
	"column-1": '${channel.countChannelByUser}'
	},
	{
	"category": "Skype",
	"column-1": "220"
	
}
</c:forEach>
]
}
	);
</script>
<script>


AmCharts.makeChart("chartdiv4",
	{
		"type": "serial",
		"categoryField": "category",
		"startDuration": 1,
		"categoryAxis": {
			"gridPosition": "start"
		},
		"trendLines": [],
		"graphs": [
			{
				"balloonText": "[[title]] of [[category]]:[[value]]",
				"fillAlphas": 1,
				"fillColors": "#3787F2",
				"id": "AmGraph-1",
				"lineColor": "#FFFFFF",
				"title": "graph 1",
				"type": "column",
				"valueField": "Conversations"
			},
			{
				"balloonText": "[[title]] of [[category]]:[[value]]",
				"fillAlphas": 1,
				"fillColors": "#ACCEFD",
				"id": "AmGraph-2",
				"lineColor": "#FFFFFF",
				"title": "graph 2",
				"type": "column",
				"valueField": "Fallbacks"
			}
		],
		"guides": [],
		"valueAxes": [
			{
				"id": "ValueAxis-1",
				"stackType": "regular",
				"autoGridCount": false,
				"title": ""
			}
		],
		"allLabels": [],
		"balloon": {},
		"legend": {
			"enabled": false,
			"useGraphSettings": true
		},
		"titles": [
			{
				"id": "Title-1",
				"size": 15,
				"text": "Chart Title"
			}
		],
		"dataProvider": [
			{
				"category": "Nov 17",
				"Fallbacks": "500",
				"Conversations": "1000"
			},
			{
				"category": "Dec 17",
				"Fallbacks": "500",
				"Conversations": "1800"
			},
			{
				"category": "Jan 18",
				"Fallbacks": "400",
				"Conversations": "2100"
			},
			{
				"category": "Feb 18",
				"Fallbacks": "100",
				"Conversations": "2200"
			},
			{
				"category": "Mar 18",
				"Fallbacks": "112",
				"Conversations": "2445"
			}
		]
	}
);




</script>