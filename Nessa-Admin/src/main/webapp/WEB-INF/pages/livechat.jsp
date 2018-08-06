<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@include file="header.jsp" %>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src=”http://code.jquery.com/jquery-1.9.1.js”></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/live-chat/js/liveChatAgent.js"></script>
<script>
function blinker()
{
$('.blink_me').fadeOut(500);
$('.blink_me').fadeIn(500);
}
setInterval(blinker,2000);
</script>

 <script type="text/javascript">
	 $(document).ready(function() {
		  $('#message').fadeOut(5000); // 5 seconds x 1000 milisec = 5000 milisec
		});
</script> 


<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
.card {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
  max-width: 200px;
  max-height:200px;
  margin: auto;
  text-align: center;
  font-family: arial;
}

/* .title {
  color: grey;
  font-size: 18px;
} */

button1 {
  border: none;
  outline: 0;
  display: inline-block;
  padding: 8px;
  color: white;
  background-color: #000;
  text-align: center;
  cursor: pointer;
  width: 100%;
  font-size: 18px;
}

a {
  text-decoration: none;
  font-size: 22px;
  color: black;
}

button:hover, a:hover {
  opacity: 0.7;
}

.Blink {
  animation: blinker 1.5s cubic-bezier(.5, 0, 1, 1) infinite alternate;  
}
@keyframes blinker {  
  from { opacity: 1; }
  to { opacity: 0; }
}

/* .stileone {
    //background: green
} */
.button {
    background-color: #f44336; /* Green */
    border: none;
    color: white;
    padding: 10px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 23px;
    margin: 4px 2px;
    cursor: pointer;
    width:100%;
}

.button1 {border-radius: 2px;}
.button2 {border-radius: 4px;}
.button3 {border-radius: 8px;}
.button4 {border-radius: 20px;}
.button5 {border-radius: 50%;}
</style>


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
                        
                            <div class="col-md-12">
                                <!-- BEGIN PORTLET-->
                                <div class="portlet light bordered margintop-sm">
                                     <div class="portlet-title">
                                        <div class="caption font-dark">
                                            <i class="icon-settings font-dark"></i>
                                            <span class="caption-subject bold uppercase">Live Users List</span>
                                             
                                        </div>
                                        <div class="col-md-4" style="margin-top: 10px;">	
											<span id="message" style="color:red" ></span>
										</div>  
                                    </div> 
                                    
                                  </div>
                                  
                                  
             <div id="liveAgent" class="row">  
             <% int count = 0, index = 1; %>        
                <c:forEach var="map" items="${mapList}">
		    		<c:forEach var="mapnew" items="${map.value}">
						<c:choose>
							<c:when test="${mapnew.chat}">         
	    	   					<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12" id="left"  style="width: 20%;max-height: 15%;">
	    	   					
                            	<div class="dashboard-stat2 ">
                             <div><i class="fa fa-circle fa-1x text-danger blink_me" id="${mapnew.id}"style="margin-left: -51%;"></i>&nbsp; LIVE User:&nbsp;<%=index++ %> </div> 
                          <%-- <div><i style="margin-right: -66%;"></i>&nbsp; User <%=index++ %></div>  --%>
                          <br>              
	  <img src="${pageContext.request.contextPath}/resources/images/download.jpg" class="img-circle" alt="User Image" style="width: 34%;">
 <div onclick="liveChat('${mapnew.id}','div2')" style="border: solid -3px #ffffff; color: red;font-size: 21px;">
 
 <button class="button button4" id="button${mapnew.id}" onclick=" 
   changeColor('${mapnew.id}',this);
  return false;" style="font-size: 15px;">chat</button>
 <!--  <p><button>chat</button></p> -->
</div>		
					
                                        
                                       <!--  <p><font size="3" color="black">Want to chat</font></p> -->
                                    
                                   
                              </div>
                               
                            </div>
                            </c:when>
						<c:otherwise>
						<% if(count < 3){ %>
						<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12" id="left" style="width: 20%;max-height: 15%;">
                            <div class="dashboard-stat2 ">
                               
                                        <h1 class="font-green-sharp">
                                        
                                      
		  <img src="${pageContext.request.contextPath}/resources/images/blank-user.jpg" class="img-circle" alt="User Image" style="width: 34%;margin-bottom:-21%">
 <div style="font-size: 30px;">
 <br>Active User</div>		
							
					
                                        </h1>
                                      <!--   <p><font size="3" color="black">Active User</font></p>
 -->
                                    
                                   
                              </div>
                               
                            </div>
                          <% count++; } %>
						</c:otherwise>
			</c:choose>

</c:forEach>

</c:forEach>


</div>

                             <%-- <c:forEach var="map" items="${mapList}">
	     
	     
	    	 <c:forEach var="mapnew" items="${map.value}">
	<c:choose>
	<c:when test="${mapnew.user}">
                            	   
                            </c:when>
						<c:otherwise>
						
						</c:otherwise>
			</c:choose>

</c:forEach>

</c:forEach>
                --%>                                                     </div>
                                <!-- END PORTLET-->
                        
                      <!--   </div> -->
                        </div>
                    </div>
                    <!-- END CONTENT BODY -->
                </div>
                <!-- END CONTENT -->
                
            </div>
            <!-- END CONTAINER -->
<%@include file="footer-admin.jsp" %>  
    <script type="text/javascript">
 setTimeout(function() {
	  location.reload();
	}, 100000);
 
</script> 
<script>
$(document).ready(function() {
	$("#chat").click(function(){
	});

} );

 /* setInterval(function(){
	 //var location=	"http:localhost:8085/Nessa-Admin/getLiveUser";
 $.ajax({
		url: "http:localhost:8085/Nessa-Admin/getLiveUserList",
		type: 'GET',
		success: function(data){
			
				}
	});
 
 },30000); */

function changeColor(divId,th){
	$(th).css("background-color", "green");
	$.ajax({
        type: 'GET',
url: 'http://localhost:8085/Nessa-Admin/checkForAgent',

	data : {
				"sessionId" : divId,
				"status" : "busy",
				"agentId" : "",
				"agentName" : ""
			},

			success : function(data) {
				if(data.message.flag== "true"){
				window.open('chatBot.html?sessionId='+divId, 
						 '_blank', 
						  'width=400, \
						   height=500, \
						   directories=no, \
						   location=no, \
						   menubar=no, \
						   resizable=no, \
						   scrollbars=no, \
						   status=no, \
						   titlebar=no');
				}
				else{
					$("#message").html(data.message.chat);
				}
			}

		});

	}
</script>
 <script>

 setInterval(function(){
		$.ajax({
		        type: 'GET',
		url: 'http://localhost:8085/Nessa-Admin/getBusyAgent',
		        success: function(data){
		        	$(data).each(function(index, value){
		        		$("#button"+value.sessionId).css("background-color", "green");	        		
		        	});
		        }

		    });
	},1000);


</script>   

 


