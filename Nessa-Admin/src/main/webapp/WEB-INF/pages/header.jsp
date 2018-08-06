<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
    <!--<![endif]-->
    <!-- BEGIN HEAD -->

    <head>
        <meta charset="utf-8" />
        <title>Nessa Dashboard</title>
        

        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta c
        ontent="Preview page of Metronic Admin Theme #2 for statistics, charts, recent events and reports" name="description" />
        <meta content="" name="author" />
        <!-- BEGIN GLOBAL MANDATORY STYLES -->
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css" />
         <link rel="stylesheet" href="https://cdn.datatables.net/1.10.13/css/dataTables.bootstrap.min.css">
        <link href="${pageContext.request.contextPath}/resources/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/resources/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/resources/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/resources/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
        <!-- END GLOBAL MANDATORY STYLES -->
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <link href="${pageContext.request.contextPath}/resources/assets/global/plugins/bootstrap-daterangepicker/daterangepicker.min.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/resources/assets/global/plugins/morris/morris.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/resources/assets/global/plugins/fullcalendar/fullcalendar.min.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/resources/assets/global/plugins/jqvmap/jqvmap/jqvmap.css" rel="stylesheet" type="text/css" />
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
		<!-- amCharts javascript sources -->
		<script type="text/javascript" src="https://www.amcharts.com/lib/3/amcharts.js"></script>
		<script type="text/javascript" src="https://www.amcharts.com/lib/3/serial.js"></script>
		<script type="text/javascript" src="https://www.amcharts.com/lib/3/themes/light.js"></script>
		 <script src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
	  	<script src="https://cdn.datatables.net/1.10.13/js/dataTables.bootstrap.min.js"></script>
		<script src="https://code.highcharts.com/modules/exporting.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
		<!-- amCharts javascript code -->
		<script type="text/javascript">
			AmCharts.makeChart("chartdiv",
				{
	"type": "serial",
	"categoryField": "category",
	"startDuration": 1,
	"theme": "light",
	"categoryAxis": {
		"gridPosition": "start"
	},
	"trendLines": [],
	"graphs": [
		{
			"balloonText": "[[title]] of [[category]]:[[value]]",
			"id": "AmGraph-1",
			"lineColor": "#CC0000",
			"markerType": "circle",
			"title": "Users",
			"valueField": "Users"
		},
		{
			"balloonColor": "#FF0000",
			"balloonText": "[[title]] of [[category]]:[[value]]",
			"bulletSize": 0,
			"fillColors": "#F95B83",
			"id": "AmGraph-2",
			"lineColor": "#3787F2",
			"markerType": "circle",
			"maxBulletSize": 52,
			"title": "Tickets Raised",
			"valueField": "Tickets Raised"
		}
	],
	"guides": [],
	"valueAxes": [
		{
			"id": "ValueAxis-1",
			"title": " "
		}
	],
	"allLabels": [],
	"balloon": {},
	"legend": {
		"enabled": true,
		"align": "right",
		"position": "top",
		"useGraphSettings": true
	},
	"titles": [
		{
			"id": "Title-1",
			"size": 15,
			"text": " "
		}
	],
	"dataProvider": [
		{
			"category": "Mar 10",
			"Users": "150",
			"Tickets Raised": "400"
		},
		{
			"category": "Mar 11",
			"Users": "220",
			"Tickets Raised": "420"
		},
		{
			"category": "Mar 12",
			"Users": "240",
			"Tickets Raised": "390"
		},
		{
			"category": "Mar 13",
			"Users": "170",
			"Tickets Raised": "300"
		},
		{
			"category": "Mar 14",
			"Users": "219",
			"Tickets Raised": "365"
		},
		{
			"category": "Mar 15",
			"Users": "340",
			"Tickets Raised": "455"
		},
		{
			"category": "Mar 16",
			"Users": "417",
			"Tickets Raised": "600"
		}
	]
}
			);
		</script>
		
		<!-- amCharts javascript sources -->
		<script type="text/javascript" src="https://www.amcharts.com/lib/3/amcharts.js"></script>
		<script type="text/javascript" src="https://www.amcharts.com/lib/3/serial.js"></script>
		

		<!-- amCharts javascript code -->
		<script type="text/javascript">
			AmCharts.makeChart("chartdiv2",
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
							"Conversations": "1200"
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
		
		<!-- amCharts javascript sources -->
		<script type="text/javascript" src="https://www.amcharts.com/lib/3/amcharts.js"></script>
		<script type="text/javascript" src="https://www.amcharts.com/lib/3/pie.js"></script>
		<script type="text/javascript" src="https://www.amcharts.com/lib/3/themes/light.js"></script>
		

		<!-- amCharts javascript code -->
		<script type="text/javascript">
		
		</script>
		
		</head>
    <!-- END HEAD -->