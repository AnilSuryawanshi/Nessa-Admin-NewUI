<!DOCTYPE html>
<html lang="en">

<head>
    <title>Webb</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/live-chat/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/live-chat/css/master.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/live-chat/css/media-queries.css">
	 <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/live-chat/css/index.css">
    <!--link rel="stylesheet" href="css/BootSideMenu.css"-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/live-chat/css/font-awesome.min.css">
    <link href='https://fonts.googleapis.com/css?family=Roboto:400,700' rel='stylesheet' type='text/css'>
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/resources/live-chat/img/favicon-32x32.png" sizes="16x16" />
	<link href="${pageContext.request.contextPath}/resources/css/jquery-customselect.css" rel='stylesheet'/>
	 <!--Add by vaibhav-->
	 <link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/demos/style.css">
  <link href="${pageContext.request.contextPath}/resources/css/jquery-customselect.css" rel='stylesheet'/>
    
	
	  <!-- end By vaibhav -->
	
	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/live-chat/css/P21-styles.css">

	
<style>
<!--input[type="radio"]{display:none;} -->	
label.btn span {
      font-size: 110%;
}

label input[type="radio"] ~ i.fa.fa-circle-o{
    color: #333;    display: inline; font-size: 16px;
}
label input[type="radio"] ~ i.fa.fa-dot-circle-o{
    <!--display: none; --> font-size: 16px;
}
label input[type="radio"]:checked ~ i.fa.fa-circle-o{
    <!--display: none;--> font-size: 16px;
}
label input[type="radio"]:checked ~ i.fa.fa-dot-circle-o{
    color: #FFC300;    display: inline; font-size: 16px;
}
label:hover input[type="radio"] ~ i.fa {
color: #FFC300;
}

label input[type="checkbox"] ~ i.fa.fa-square-o{
    color: #333;    display: inline;
}
label input[type="checkbox"] ~ i.fa.fa-check-square-o{
    display: none;
}
label input[type="checkbox"]:checked ~ i.fa.fa-square-o{
    display: none;
}
label input[type="checkbox"]:checked ~ i.fa.fa-check-square-o{
    color: #FFC300;    display: inline;
}
label:hover input[type="checkbox"] ~ i.fa {
color: #FFC300;
}

div[data-toggle="buttons"] label.active{
}

div[data-toggle="buttons"] label {
display: inline-block;
padding: 5px 7px;
margin-bottom: 0;
font-size: 11px;
font-weight: normal;
line-height: 2em;
text-align: left;
white-space: nowrap;
vertical-align: top;
cursor: pointer;
background-color: none;
border: 0px solid 
#c8c8c8;
border-radius: 3px;
color: #333;
-webkit-user-select: none;
-moz-user-select: none;
-ms-user-select: none;
-o-user-select: none;
user-select: none;
}

div[data-toggle="buttons"] label:hover {
color: #FFC300;
}

div[data-toggle="buttons"] label:active, div[data-toggle="buttons"] label.active {
-webkit-box-shadow: none;
box-shadow: none;
}


</style>

</head>

<body style="height: 1%;">
<script type="text/javascript" src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>
    <!-- ------------------ Login Screen Start ------------------ -->
    <div id="login-wrapper" class="">

        <!--<div class=" vertical-center">
            <div class="col-xs-12 col-md-5">
                <div class="login-panel panel panel-default">
                    <div class="panel-body">
                        <div class="toggle" title="Change IP"><i class="fa fa-times fa-pencil"> IP</i> </div>
                        <div class="col-xs-12 col-md-8 col-md-offset-2">
                            <h2 class="pg-title">Login to your account</h2>
                            <form role="form">
                                <fieldset>
                                   <!-- <div class="form-group">
                                        <div class="input">
                                            <input class="form-control validate" id="username" type="text" placeholder="Username" value="" required>
                                            <span style="opacity: 1;"><i class="fa fa-user"></i></span> </div>
                                    </div> -->
                                    <!--<div class="form-group">
                                        <div class="input">
                                            <input class="form-control validate" id="password" type="password" placeholder="Password" value="" required>
                                            <span style="opacity: 1;"><i class="fa fa-lock"></i></span> </div>
                                    </div>
                                    <div class="form-group ip-adress">
                                        <div class="input">
                                            <input class="form-control" id="ip" type="text" value="http://10.77.100.19:8080" required>
                                            <span style="opacity: 1;"><i class="fa fa-file-o"></i></span> </div>
                                    </div>
                                    <a href="#." id="login" class="btn btn-lg button-primary btn-block">Login</a>
                                </fieldset>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>-->
    </div>
    <!-- ---------------Login End---------------- -->
    <!-- Page Loader -->
    <div id="loading"> <img id="loading-image" src="img/page-loader.gif" alt="Loading..." /> </div>
    <!-- small loader -->
    <div id="loading-small"> <img id="loading-image" src="img/loader.gif" alt="Loading..." /> </div>

    <!-- ------------------ Sidebar Start ------------------ -->
    
    <!-- ------------------ Sidebar End ------------------ -->
    <!-- Modal -->
    
    <!--- -------Show Modal window to select Avatar End ----------------- -->
<%
String sessionId;
    if (request.getParameter("sessionId") == null) {
    	sessionId="";
    } else {
    	sessionId=request.getParameter("sessionId");
    }
%>

 <input type="hidden" id="sessionId" value="<%= request.getParameter("sessionId")%>">
     <!-- Chat Wrapper Start -->
    <div class="chat-wrapper ">

        <div id="error"></div>
        <!-- Header Start -->
        <div id="header">

            <div class="panel-heading">
                <!--<div class="col-xs-2 col-md-1 nessa-logo"><img src="img/bot-logo.png" class="" alt="" /></div>-->
				 <div class="col-xs-2 col-md-1 nessa-logo"><img src="${pageContext.request.contextPath}/resources/images/volvoLogo.png" class="" alt="" /></div>
                <div class="col-xs-10 col-md-10 padding-left-0">
                    <h3 class="panel-title padding-left-0">Webb</h3>
					
					<div id="test"><button id="closeBtn" style="top: 7px; right: 25px;position: absolute; font-size: large;background: #1d3356; border: 0;"/><i class="fa fa-times" aria-hidden="true"></i></a></div>

					
                    <!--<h6>Personal Service, Extensive Experience & Virtually by Your Side!</h6>-->
					<h6>Your Virtual Assistant!</h6>
						
					<!--<i class="fa fa-times fa-2x" aria-hidden="true"></i></a>-->
					<!--<img src="img/volvoLogo.png" class="" alt="" />-->
					</div>
					<div id="mySidenav" class="sidenav">
					<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
					<a href="#">HR Services</a>
					<a href="#">FAQ</a>
					<a href="#">BANKING QUERIES</a>
					<a href="#">Contact</a>
					</div>
					<!--<span class="pull-right" style="top: 4px; right: 0; position: absolute; font-size:30px; cursor:pointer" onclick="openNav()">&#9776; </span>-->
           </div>
				

            </div>
        </div>
        <!-- Header End -->
        
        
         <div id="start-button-holder">
                        <a href="#" id="start-btn" class="startChat button-primary">
                            <span>Begin Here..</span>
                        </a>
                    </div>
                    
        <!-- Content Start -->
        <div id="content" style="background: rgb(240,239,237);border: 1px solid #a7a7a7;" class="parent-scroll">
           
            <div id="chat-body" class="collapse in child-scroll ">
               
                <!--div class="nano-container"-->
                    <div class="nano has-scrollbar">
                        <div class="nano-content pad-all" tabindex="0" style="right: -17px;">

                            <ul class="list-unstyled media-block" id="utterance"></ul>
                        </div>
                        <div class="nano-pane">
                            <div class="nano-slider" style="height: 141px; transform: translate(0px, 0px);"></div>
                        </div>
                        <div id="scrollBtm"></div>
                    </div>
                   
                    

                    <!--div class="panel-footer">
                        <div class="row">
                            <div class="col-xs-8">
                                <textarea id="userUtterance" placeholder="Type your message" class="form-control"></textarea>
                            </div>
                            <div class="col-xs-4 btn-send-col">
                                <div id="loader"></div>
                                <button id="recobtn"> <img id="start_img" src="img/mic.gif" alt="Start"> </button>
                                &nbsp;&nbsp;
                                <div id="btnSubmit" class="btn-send"></div>
                                &nbsp;&nbsp;&nbsp; </div>
                        </div>
                    </div-->
                   
                <!--/div-->
                <div id="bottom">
                        <div id="info">
                            <p id="info_start">Click on the MIC icon and speak</p>
                            <p id="info_speak_now">Speak Now</p>
                            <p id="info_no_speech">No speech detected. You may need to adjust your <a href="//support.google.com/chrome/bin/answer.py?hl=en&amp;answer=1407892"> microphone settings</a>.</p>
                            <p id="info_no_microphone" style="display:none"> No microphone was found. Please Ensure that a microphone is installed and that <a href="//support.google.com/chrome/bin/answer.py?hl=en&amp;answer=1407892"> microphone settings</a> are configured correctly.</p>
                            <p id="info_allow">Click the "Allow" button above to enable your microphone.</p>
                            <p id="info_denied">Permission to use microphone was denied.</p>
                            <p id="info_blocked">Permission to use microphone is blocked. To change, go to chrome://settings/contentExceptions#media-stream</p>
                            <p id="info_upgrade">Web Speech API is not supported by this browser. Upgrade to <a href="//www.google.com/chrome">Chrome</a> version 25 or later.</p>
                        </div>
                    </div>
            </div>
           
            <!-- Content End -->
               <div class="clearfix"></div>
             
              <div id="debug" class="box"></div>
               <!-- Footer Start -->
       <!--   <div id="footer" style="border: 1px solid #a7a7a7;">
            <div class="panel-footer">
                <div class="row">
                    <div class="col-xs-8">
                        <textarea id="userUtterance" placeholder="Type your message" class="textArea"></textarea>
                    </div>
                    <div class="col-xs-4 btn-send-col">
                        <div id="loader"></div>
                       <button id="recobtn"> <img id="start_img" src="img/mic.gif" alt="Start"> </button>
                        &nbsp;&nbsp;
                        <div id="btnSubmit" class="btn-send"></div>
                        &nbsp;&nbsp;&nbsp; </div>
                </div>
            </div>
        </div> 
        Footer End
            
       
        <div class="clearfix"></div>
        Chat Wrapper End
        </div> -->
      
        <script src="${pageContext.request.contextPath}/resources/live-chat/js/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/live-chat/js/bootstrap.min.js"></script>
      <script src="${pageContext.request.contextPath}/resources/live-chat/js/masterR.js"></script>
		 <script src="${pageContext.request.contextPath}/resources/live-chat/js/chunkify.js"></script>
        <script src="${pageContext.request.contextPath}/resources/live-chat/js/sidebar.js"></script>
		
	   <script src="${pageContext.request.contextPath}/resources/live-chat/js/jquery-customselect.js"></script>
	   <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		
<script>
function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
}
</script>





 </body>

</html>