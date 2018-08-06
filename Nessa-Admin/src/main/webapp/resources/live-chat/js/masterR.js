// JavaScript Document
var conversation = "";
var portNumber = "";
var closeChat = false;
var labelArray= new Array();
var serviceNowGroupUser = new Array();
var requestType = new Array();
var serviceNowGroup = new Array();
var ADGroupList = new Array();
var sharedDiskAccess = new Array();
var sharedDisk = new Array();
var assignmentGroupPassword = new Array();
var assignmentList = new Array();
var configurationItem = new Array();
var user1=new Array();
var agent1=new Array();

var adGroupAccess=new Array();   // Define new Variable


var Lusername;
var prefix ="";
var arr = [];
var array=[];
var strOption="";
var strOption1="";
var strOption2="";
var strOption3="";
var strOption4="";
var strOption5="";
var strOption6="";
var strOption8="";
var strOption9="";
var strOption10="";
var menuOptionsItem="";
var menuOptionsItemOnOptions="";
var selectbox="";
var selectbox1="";
var selectbox2="";
var selectbox3="";
var selectbox4="";
var selectbox5="";
var strOption6="";
var selectbox8="";
var selectbox9="";
var selectbox10="";
var selectBoxLabel="";
var selectFromUser=[];
var formId="myForm";
var counter=0;
var newFormId="";
var textBox1="";
var textBox2="";
var textBox3="";
var str="";
var str1="";
var userUtterDiv="";
var agentUtterDiv="";
var readChatDiv="";
var sessId;
var Lpassword;



//getdateandtime
function getdateandtime(){	
// var dis = abc;
    // alert("OK its working...!!!!!!");
	var a_date = $(".dateclass:last").val();
	var a_time = $(".timeclass:last").val();
        //var a_date = document.getElementById("a_date").value;
        //var a_time = document.getElementById("a_time").value;
	
	var dateformat = a_date.split("-");	
	    var yyyy = dateformat[0];
	var mm = dateformat[1];
	var dd = dateformat[2];
	var dates=mm+"-"+dd+"-"+yyyy +" : "+a_time;
	
	//alert("dd-mm-yy>>"+dd+"-"+mm+"-"+yyyy +" : "+a_time);
	 area(dates)
	//alert("old Date & Time>>"+a_date+" : "+a_time);
	 }






function getMenuItemSelected(optionSelected)
{
	//alert("getValue:::"+optionSelected);
	if(optionSelected=='default')
	{
	
	}else
	{
	$('#loading-small').show();
	area(optionSelected)
	}
	
}

function test(){
	//for closing nessa frames on nessaLauncher
	var divelement = $('#test').parents();
	var body = divelement[4].baseURI;
	var appName = "ServiceNow";
	
	 // alert(appName);
	   window.parent.postMessage(appName,"*")
	  closeChat = true;
	  if(closeChat == true){
	  window.parent.postMessage("true","*")
	} 
}
//call to email function
	$("#test").click(function() {
        test();
    })


// disabled motivation fields
function motivationdisable(e){	
        var empty = false;
            if (e.value == '') {
                empty = true;
            }
        if (empty) {
            $(".btn").attr('disabled', 'disabled');
        } else {
            $(".btn").removeAttr('disabled');
        }
	 }
	 
	 function approvaldisable(e){	
        var empty = false;
            if (e.value == '') {
                empty = true;
            }
        if (empty) {
            $(".btn").attr('disabled', 'disabled');
        } else {
            $(".btn").removeAttr('disabled');
        }
	 }
	 
// disabled problemdisable fields
function problemdisable(e){	
        var empty = false;
            if (e.value == '') {
                empty = true;
            }
        if (empty) {
            $(".btn").attr('disabled', 'disabled');
        } else {
            $(".btn").removeAttr('disabled');
        }
	 }
 	
	 
/*	function latestChat(){
	    var sessionId= $("#sessionId").val();
	$.post('http://localhost:8085/ChatAPI/chat/readLatestChat', { 
	 instanceId:sessionId
	            }, function (data) {
	//console.log("data>>>"+data.message.chat)
	  if(data!=null && data!=""){
	var content = data.payload[0].liveChatsMessage;
	
			console.log("content>>>"+content)
			$('#loading-small').hide();
			 var userUtteranceVal = $("#userUtterance").val();
			 
			for(var i=0;i<content.length;i++){
				
				if(content[i].userMessage!=null&&content[i].userMessage!=""){
					readChatDiv ='<li class="mar-btm systemUtterance"> <div class="media-body pad-hor"> <div class="speech"><p id="systemUtterance">' + content[i].userMessage  + '</p><p></p> </div></div></li>';
					$("#utterance").append(readChatDiv);
					$("#content").scrollTop(1000000000);//Scroll Down Function


					
				}
				
			}
	} 
	            })
	            .fail(
	                function (xhRequest, status,
	                    thrownError) {
	                    showError(
	                        "Server error or service unavailable.",
	                        true);
	                });
	setTimeout(function(){ 
	    latestChat();}, 6000);
	}*/

function getServiceNowUser(){
	$.ajax({
	type:"GET",
	url:"http://ec2-54-149-104-150.us-west-2.compute.amazonaws.com:8085/techM/getADUserList",
	//url:"http://Localhost:8085/techM/getADUserList",
	//async:false,
	success:function (data) {
	var list = data.list.split(",");	
	   // alert("response of ajax::"+list);
	
	for(var i=0;i<list.length;i++)
	{
	serviceNowGroupUser.push(list[i]);
	    selectFromUser.push(list[i]); 
	}
	selectbox = $('.nameClassbox');
	populateSelectBox(selectbox, serviceNowGroupUser);
	}
	
            });
	  
    }	

//getting request type	
function getRequestType(){
	$.ajax({
	type:"GET",
	url:"http://ec2-54-149-104-150.us-west-2.compute.amazonaws.com:8085/techM/getRequestType",
	//url:"http://Localhost:8085/techM/getRequestType",
	//async:false,
	success:function (data) {
	var list = data.list.split(",");	
	    //alert("response of ajax::"+list);
	
	for(var i=0;i<list.length;i++)
	{
	   // alert("item:>>"+list[i]);
	requestType.push(list[i]);
	
	}
	//alert("sharedDiskName::"+sharedDiskName.length);
	selectbox1 = $('.requestTypeClassbox');
	console.log("inside getRequestType function");
	populateSelectBox1(selectbox1, requestType);
	}
	
            });
	  
    }	

//getting serviceNowGroup
function getServiceNowGroup(){
	$.ajax({
	type:"GET",
	url:"http://ec2-54-149-104-150.us-west-2.compute.amazonaws.com:8085/techM/getServicenowGroupList",
	//url:"http://localhost:8085/techM/getServicenowGroupList",
	//async:false,
	success:function (data) {
	var list = data.list.split(",");	
	    //alert("response of ajax::"+list);
	
	for(var i=0;i<list.length;i++)
	{
	   //alert("item:>>"+list[i]);
	serviceNowGroup.push(list[i]);
	
	}
	//alert("serviceNowGroup::"+serviceNowGroup.length);
	selectbox2 = $('.servicenowGroupClassbox');
	populateSelectBox2(selectbox2, serviceNowGroup);
	}
	
            });
	  
    }


//getting AD Groups
	function getADGroupList(){
	$.ajax({
	type:"GET",
	url:"http://ec2-54-149-104-150.us-west-2.compute.amazonaws.com:8085/techM/getADGroupList",
	//url:"http://localhost:8085/techM/getADGroupList",
	//async:false,
	success:function (data) {
	var list = data.list.split(",");	
	    //alert("response of ajax::"+list);
	
	for(var i=0;i<list.length;i++)
	{
	   //alert("item:>>"+list[i]);
	ADGroupList.push(list[i]);
	
	}
	//alert("ADGroupList::"+ADGroupList.length);
	selectbox3 = $('.AdGroupClassbox');
	populateSelectBox3(selectbox3, ADGroupList);
	}
	
            });
	  
    }
	
//getting shared disk access
function getSharedDiskAccess(){
	$.ajax({
	type:"GET",
	url:"http://ec2-54-149-104-150.us-west-2.compute.amazonaws.com:8085/techM/getSharedDiskAccessList",
	//url:"http://localhost:8085/techM/getSharedDiskAccessList",
	//async:false,
	success:function (data) {
	var list = data.list.split(",");	
	    //alert("response of ajax::"+list);
	
	for(var i=0;i<list.length;i++)
	{
	   //alert("item:>>"+list[i]);
	sharedDiskAccess.push(list[i]);
	
	}
	//alert("ADGroupList::"+ADGroupList.length);
	selectbox4 = $('.SharedDiskAccessClassBox');
	populateSelectBox4(selectbox4, sharedDiskAccess);
	}
	
            });
	  
}	
//getting shared disk
function getSharedDisk(){
	$.ajax({
	type:"GET",
	url:"http://ec2-54-149-104-150.us-west-2.compute.amazonaws.com:8085/techM/getSharedDiskList",
	//  url:"http://localhost:8085/techM/getSharedDiskList",
	//async:false,
	success:function (data) {
	var list = data.list.split(",");	
	    //alert("response of ajax::"+list);
	
	for(var i=0;i<list.length;i++)
	{
	   //alert("item:>>"+list[i]);
	sharedDisk.push(list[i]);
	
	}
	//alert("ADGroupList::"+ADGroupList.length);
	selectbox5 = $('#SharedDiskClassBox');
	populateSelectBox5(selectbox5, sharedDisk);
	}
	
            });
	  
}



//change by tushar 

//getting Ad Group access
function getAdGroupAccess(){
	$.ajax({
	type:"GET",
	url:"http://ec2-54-149-104-150.us-west-2.compute.amazonaws.com:8085/techM/getADGroupAccessList",
	//url:"http://localhost:8085/techM/getADGroupAccessList",
	//async:false,
	success:function (data) {
	var list = data.list.split(",");	
	    //alert("response of ajax::"+list);
	
	for(var i=0;i<list.length;i++)
	{
	   //alert("item:>>"+list[i]);
	adGroupAccess.push(list[i]);
	
	}
	//alert("ADGroupList::"+ADGroupList.length);
	selectbox10 = $('.AdGroupAccessClassBox');
	populateSelectBox10(selectbox10, adGroupAccess);
	}
	
            });
	  
}	
//change by tushar
	

//getting getAssignmentGroupPassword
function getAssignmentGroupPassword(){
	$.ajax({
	type:"GET",
	url:"http://ec2-54-149-104-150.us-west-2.compute.amazonaws.com:8085/techM/getAssignmentGroupPassword",
	//url:"http://localhost:8085/techM/getAssignmentGroupPassword",
	//async:false,
	success:function (data) {
	var list = data.list.split(",");	
	    //alert("response of ajax::"+list);
	
	for(var i=0;i<list.length;i++)
	{
	   //alert("item:>>"+list[i]);
	assignmentGroupPassword.push(list[i]);
	
	}
	//alert("assignmentGroupPassword::"+assignmentGroupPassword.length);
	selectbox6 = $('.assignmentGroupPasswordClassbox');
	populateSelectBox6(selectbox6, assignmentGroupPassword);
	}
	
            });
	  
    }	

	
	//getting getAssignmentList
function getAssignmentList(){
	$.ajax({
	type:"GET",
	url:"http://ec2-54-149-104-150.us-west-2.compute.amazonaws.com:8085/techM/getAssignmentList",
	//url:"http://localhost:8085/techM/getAssignmentList",
	//async:false,
	success:function (data) {
	var list = data.list.split(",");	
	    //alert("response of ajax::"+list);
	
	for(var i=0;i<list.length;i++)
	{
	   //alert("item:>>"+list[i]);
	assignmentList.push(list[i]);
	
	}
	//alert("assignmentList::"+assignmentList.length);
	selectbox8 = $('.assignmentListClassbox');
	populateSelectBox8(selectbox8, assignmentList);
	}
	
            });
	  
    }	
//getting getConfigurationItem
function getConfigurationItem(){
	$.ajax({
	type:"GET",
	url:"http://ec2-54-149-104-150.us-west-2.compute.amazonaws.com:8085/techM/getConfigurationItem",
	//url:"http://localhost:8085/techM/getConfigurationItem",
	//async:false,
	success:function (data) {
	var list = data.list.split(",");	
	    //alert("response of ajax::"+list);
	
	for(var i=0;i<list.length;i++)
	{
	   //alert("item:>>"+list[i]);
	configurationItem.push(list[i]);
	
	}
	//alert("configurationItem::"+configurationItem.length);
	selectbox9 = $('.configurationItemClassbox');
	populateSelectBox9(selectbox9, configurationItem);
	}
	
            });
	  
    }	

/*function firstAjaxCall() {
  //var success =function ldapuser(Lusername, Lpassword,projectname){
	  $.ajax({
	        type: 'GET',
	
	url: 'http://localhost:8085/ChatAPI/chat/readByInstanceId?instanceID=d4-EZTIUBBWQZNJ',
	        success: function(data){
	
	console.log("data" + data)
	
	var dialog=data.messages;
	var user="";
	var agent="";
	
	for(var i=0;i<dialog.length ;i++){
	
	if(dialog[i].chat!=null){
	user="U: "+dialog[i].chat +"<br>";
	console.log("user"+user)

	}
	if(dialog[i].chatMsg!=null){
	
	agent="A: "+dialog[i].chatMsg;
	console.log("agent"+agent)
	
	}
	
	str=str+agent+user;	

	}
	
console.log("str"+str)
	
	
	        }

	    });
	

	
	
	
}*/




function secondAjaxCall(Lusername, Lpassword,projectname,ipAdd,LdapEmailId,fullName) {
  //var success =function ldapuser(Lusername, Lpassword,projectname){
	 	 $.ajax({
	        type: 'POST',
	url: 'http://localhost:8085/techM/getajaxvalue',
	data: {"LdapuserName":Lusername,
	                "Ldappassword": Lpassword,
	"ipAdd":ipAdd,
	"LdapEmailId":LdapEmailId,
	"fullName":fullName,
	"channel":"web"
	               },
	        success: function(data){
	        }

	    });

	

}

function thirdAjaxCall(Lusername, Lpassword,projectname,ipAdd,LdapEmailId) {
  //var success =function ldapuser(Lusername, Lpassword,projectname){
	 	 $.ajax({
	        type: 'POST',
	url: 'http://localhost:8085/servicenow/getajaxvalue',
	data: {"Ldapuusername":Lusername,
	                "Ldappassword": Lpassword,
	"ipAdd":ipAdd,
	"LdapEmailId":LdapEmailId
	               },
	        success: function(data){
	        }

	    });

 }



//for serviceNow user
var populateSelectBox = function (selectbox, serviceNowGroupUser) {
	serviceNowGroupUser.forEach(function (data) {
        strOption+="<option value='"+data+"'>" + data + "</option>";
    });
	selectbox.html(strOption);
};
//for request type
var populateSelectBox1 = function (selectbox1, requestType) {
	requestType.forEach(function (data) {
    strOption1+="<option value='"+data+"'>" + data + "</option>";
    });
	console.log("inside populateSelectBox1 function");
	selectbox1.html(strOption1);
};
//for servicenow group
var populateSelectBox2 = function (selectbox2, serviceNowGroup) {
	serviceNowGroup.forEach(function (data) {
    strOption2+="<option value='"+data+"'>" + data + "</option>";
    });
	selectbox2.html(strOption2);
};

//for AdGroup List
var populateSelectBox3 = function (selectbox3, ADGroupList) {
	ADGroupList.forEach(function (data) {
    strOption3+="<option value='"+data+"'>" + data + "</option>";
    });
	selectbox3.html(strOption3);
};

//for sharedDiskAccess List
var populateSelectBox4 = function (selectbox4, sharedDiskAccess) {
	sharedDiskAccess.forEach(function (data) {
    strOption4+="<option value='"+data+"'>" + data + "</option>";
    });
	selectbox4.html(strOption4);
};

//for sharedDisk List
var populateSelectBox5 = function (selectbox5, sharedDisk) {
	sharedDisk.forEach(function (data) {
    strOption5+="<option value='"+data+"'>" + data + "</option>";
    });
	selectbox5.html(strOption5);
};

//for Assignment Group
var populateSelectBox6 = function (selectbox6, assignmentGroupPassword) {
	assignmentGroupPassword.forEach(function (data) {
    strOption6+="<option value='"+data+"'>" + data + "</option>";
    });
	console.log("inside populateSelectBox6 function");
	selectbox6.html(strOption6);
};
//for Configuration Item
var populateSelectBox9 = function (selectbox9, configurationItem) {
	configurationItem.forEach(function (data) {
    strOption9+="<option value='"+data+"'>" + data + "</option>";
    });
	console.log("inside populateSelectBox9 function");
	selectbox9.html(strOption9);
};


//change by tushar ad group start

var populateSelectBox10 = function (selectbox10, adGroupAccess) {
	adGroupAccess.forEach(function (data) {
    strOption10+="<option value='"+data+"'>" + data + "</option>";
    });
	console.log("inside populateSelectBox10 function");
	selectbox10.html(strOption10);
};

//change by tushar ad group end




//for Assignment List
var populateSelectBox8 = function (selectbox8, assignmentList) {
	assignmentList.forEach(function (data) {
    strOption8+="<option value='"+data+"'>" + data + "</option>";
    });
	console.log("inside populateSelectBox8 function");
	selectbox8.html(strOption8);
};
function getIcardValue(){
	$('#loading-small').show();
    var inputValArr=[];
	var lockSharedDisk ="";
	//alert("while submitiing form"+newFormId)
	
	var x = document.getElementById(newFormId).elements; 
	//var radioValue = $("input[name='lockedSubFolder']:checked").val();
	//alert("radioValue:::"+radioValue);
	//$('input[type=radio].radioButtons').remove();
	
	var obj ={};
	
	for(var i = 0 ; i <x.length ; i++){ 
	var item = x.item(i); 
	obj[item.name] = item.value; 
	if(item.value != ""){
	arr.push(item.value); 
	}
	}
    
	/*if(radioValue!="")
	{
	arr.push(radioValue);
	}*/
	
	removeItem(arr, 'Submit');
	newFormId="";
	//document.getElementById("myFormClick").reset(); 
	//document.getElementById("myFormClick").remove();
  }
  
function removeItem(array, item){
    for(var j in array){
        if(array[j]==item){
            array.splice(j,1);
            break;
        }
    }
	//alert("array lement"+array.length);
	sendAsUtterance(array);
}
function sendAsUtterance(array)
{
	var collectionOfUtter="";
	for(var i=0;i<array.length;i++)
	{
	if (i==array.length-1){
	collectionOfUtter+=array[i];
	}else{
	 collectionOfUtter+=array[i]+"; ";
	}
	  // collectionOfUtter+=array[i]+"; ";
	}
	//alert("collectionOfUtter:::"+collectionOfUtter);
	array = [];
	arr = [];
	area(collectionOfUtter);
}

function disabledADUser()
{
	//alert('in function');
	//document.getElementById("selectbox3").disabled=true;
	$('#selectbox3').find('option[value="ad group name"]').remove();
	$("#selectbox3").append('<option value="ad group name" selected >ad group name</option>');
	//strOption3+='<option selected disabled>Select AD Group Name</option>';
}

function disabledSharedDisk()
{
	//alert('in function');
	$('#selectbox5').find('option[value="shared disk"]').remove();
	$("#selectbox5").append('<option value="shared disk" selected>shared disk</option>');
}

 var username = $("#username").val();
function currentTimeOnClick() {
	    var msg;
        var date = new Date();
        var hours = date.getHours();
        if (hours < 12)
            msg = "Good Morning, How may I help you?";
        else if (hours < 18)
            msg = "Good Afternoon, How may I help you?";
        else
            msg = "Good Evening, How may I help you?";
        var minutes = date.getMinutes();
        var ampm = hours >= 12 ? 'pm' : 'am';
        hours = hours % 12;
        hours = hours ? hours : 12; // the hour '0' should be '12'

        minutes = minutes < 10 ? '0' + minutes : minutes;
        var strTime = hours + ':' + minutes + ' ' + ampm;
        return strTime;
    }
	

function markActiveLink(header)
{
	$('#loading-small').show();
	var divId = header.id;
	area(divId);
	
}

function setSelectedIndex(s, v) {

    for ( var i = 0; i < s.options.length; i++ ) {

        if ( s.options[i].text == v ) {

            s.options[i].selected = true;

            return;

        }

    }

}

function cancelTaskConfirm()
{
	
	if ($('#checkboxForCancelTask').is(':checked')) {
	area("yes");
      
    }
}


function createForm(data){

	if(data.iCard.type!="taskList")
	{
	//alert("form id in create Form::"+newFormId);
	
	var iCard = data.iCard.entities;
	iCard.sort(function(a,b){
	return a.id - b.id
	})
	var entitiesLabel="";
	var flag ="false";
	var datetab =data.result.intent.name;
	//change by richa
	var labelEntity = false;

                                 for(var i=0;i<iCard.length;i++)
                                  {
                                  var labels= iCard[i].label;
                                          if(labels==""){
                                                  labelEntity = true;
                                          }
                                  }
	
	if(labelEntity==false)
	////change by richa end
	{	
	var lengthFormArray="";
	var myFormContent = '<form id="'+newFormId+'" action="javascript:void(0);" class="form-horizontal" role="form">';
	
	 myFormContent+='<div class="panel panel-info">'; 
	 myFormContent+='<center>Please fill the below form.</p></center>';
	      for(var i=0;i<iCard.length;i++)
	  { 
	          var labels= iCard[i].label;
	  var labelType = iCard[i].type;
	 
	  if(iCard[i].label!="")
	  {
	  flag ="true";
	if(labelType=="list"&&labels=="Requested For:")
	
	{
	    //strOption+='<option selected disabled>'+iCard[i].label+'</option>';
	myFormContent+='<a href="#" data-toggle="tooltip" title="Please select or type valid Service Now username for whom you wish to request access for.">'+iCard[i].label+'</a>'; 
	//myFormContent+='<label class="btn-label">'+iCard[i].label+'</label>';
	if(strOption.indexOf("selected"))
	{
	var strOptiontemp = strOption.replace("selected","");
	myFormContent+='<select id="selectbox" class="nameClassbox form-control custom-select custom-select-open">'+strOptiontemp+' </select><br>';
	
	}else
	{
	myFormContent+='<select id="selectbox" class="nameClassbox form-control custom-select custom-select-open">'+strOption+' </select><br>';
	}
	
	}
	else if(labelType=="list"&&labels=="Request Type:")
	{
	myFormContent+='<a href="#" data-toggle="tooltip" title="There are two types of requests. Add Access will provide the access. Remove Access will revoke it.">'+iCard[i].label+'</a>'; 
	//myFormContent+='<label class="btn-label">'+iCard[i].label+'</label>';
	if(strOption1.indexOf("selected"))
	{
	var strOption1temp = strOption1.replace("selected","");
	myFormContent+='<select id="selectbox1" class="requestTypeClassbox form-control ">'+strOption1temp+' </select><br>';
	
	}else
	{
	myFormContent+='<select id="selectbox1" class="requestTypeClassbox form-control ">'+strOption1+' </select><br>';
	}
	
	}
	else if(labelType=="list"&&labels=="Choose Group:")
	{
	    //strOption2+='<option selected disabled>'+labels+'<option>';
	myFormContent+='<a href="#" data-toggle="tooltip" title="Select or type the team or group you the user operates in. e.g.) user body and trim, user chassie, project planner etc.">'+iCard[i].label+'</a>';
	//myFormContent+='<label class="btn-label">'+iCard[i].label+'</label>';
	if(strOption2.indexOf("selected"))
	{
	var strOption2temp = strOption2.replace("selected","");
	myFormContent+='<select id="selectbox2" class="servicenowGroupClassbox form-control ">'+strOption2temp+' </select><br>';
	
	}else
	{
	myFormContent+='<select id="selectbox2" class="servicenowGroupClassbox form-control ">  '+strOption2+' </select><br>';
	}
	
	}
	else if(labelType=="list"&&labels=="AD Group Name:")
	{
	    //strOption3+='<option selected disabled>'+labels+'</option>';
	myFormContent+='<a href="#" data-toggle="tooltip" title="The name of the Active directory group name to which user need to added to get the required access to shared disk. If you know the shared disk name you can skip this & opt to select shared disk name.">'+iCard[i].label+'</a>';
	//myFormContent+='<label class="btn-label">'+iCard[i].label+'</label>';
	if(strOption3.indexOf("selected"))
	{
	var strOption3temp = strOption3.replace("selected","");
	myFormContent+='<select id="selectbox3" class="AdGroupClassbox form-control " onChange="disabledSharedDisk()">'+strOption3temp+' </select><br>';
	
	}else
	{
	myFormContent+='<select id="selectbox3" class="AdGroupClassbox form-control " onChange="disabledSharedDisk()">'+strOption3+' </select> <br>';
	}
	
	}
	else if(labelType=="list"&&labels=="shared disk access:")
	{
	    //strOption4+='<option selected disabled>'+labels+'</option>';
	
	myFormContent+='<a href="#" data-toggle="tooltip" title="Type of access you require. There are 2 options Administrator access which will allow the access provisioning along with access to shared disk. Modify access will provide the read write access to shared drive.">'+iCard[i].label+'</a>';
	//myFormContent+='<label class="btn-label">'+iCard[i].label+'</label>';
	if(strOption4.indexOf("selected"))
	{
	var strOption4temp = strOption4.replace("selected","");
	myFormContent+='<select id="selectbox4" class="SharedDiskAccessClassBox form-control">'+strOption4temp+' </select> <br>';
	
	}else
	{
	myFormContent+='<select id="selectbox4" class="SharedDiskAccessClassBox form-control">'+strOption4+' </select> <br>';
	}
	
	}
	
	//change by tushar 
	
	
	
	else if(labelType=="list"&&labels=="AD Group Access:")
	{
	    //strOption4+='<option selected disabled>'+labels+'</option>';
	
	myFormContent+='<a href="#" data-toggle="tooltip" title="Type of access you require. There are 2 options Administrator access which will allow the access provisioning along with access to shared disk. Modify access will provide the read write access to shared drive.">'+iCard[i].label+'</a>';
	//myFormContent+='<label class="btn-label">'+iCard[i].label+'</label>';
	if(strOption10.indexOf("selected"))
	{
	var strOption10temp = strOption10.replace("selected","");
	myFormContent+='<select id="selectbox10" class="AdGroupAccessClassBox form-control">'+strOption10temp+' </select> <br>';
	
	}else
	{
	myFormContent+='<select id="selectbox10" class="AdGroupAccessClassBox form-control">'+strOption10+' </select> <br>';
	}
	
	}
	
	
	//change by tushar
	
	else if(labelType=="list"&&labels=="Shared Disk:")
	{
	    //strOption5+='<option selected disabled>'+labels+'</option>';
	myFormContent+='<a href="#" data-toggle="tooltip" title="The name  of the shared disk you require access for. If you know the AD group you can skip this& opt to select AD group name.">'+iCard[i].label+'</a>';
	//myFormContent+='<label class="btn-label">'+iCard[i].label+'</label>';
	if(strOption5.indexOf("selected"))
	{
	var strOption5temp = strOption5.replace("selected","");
	myFormContent+='<select id="selectbox5" class="SharedDiskClassBox form-control " onChange="disabledADUser()"> '+strOption5temp+' </select> <br>';
	
	}else
	{
	myFormContent+='<select id="selectbox5" class="SharedDiskClassBox form-control " onChange="disabledADUser()"> '+strOption5+' </select> <br>';
	}
	
	}
	else if(labelType=="list"&&labels=="Assignment Group:")
	{
	myFormContent+='<a href="#" data-toggle="tooltip" title="What is the name of Assignment Group.">'+iCard[i].label+'</a>'; 
	//myFormContent+='<label class="btn-label">'+iCard[i].label+'</label>';
	if(strOption6.indexOf("selected"))
	{
	var strOption6temp = strOption6.replace("selected","");
	myFormContent+='<select id="selectbox6" class="assignmentGroupPasswordClassbox form-control ">'+strOption6temp+' </select><br>';
	
	}else
	{
	myFormContent+='<select id="selectbox6" class="assignmentGroupPasswordClassbox form-control ">'+strOption6+' </select><br>';
	}
	
	}
	else if(labelType=="list"&&labels=="Configuration Item:")
	{
	myFormContent+='<a href="#" data-toggle="tooltip" title="What is the name of configuration item.">'+iCard[i].label+'</a>'; 
	//myFormContent+='<label class="btn-label">'+iCard[i].label+'</label>';
	if(strOption9.indexOf("selected"))
	{
	var strOption9temp = strOption9.replace("selected","");
	myFormContent+='<select id="selectbox9" class="configurationItemClassbox form-control ">'+strOption9temp+' </select><br>';
	
	}else
	{
	myFormContent+='<select id="selectbox9" class="configurationItemClassbox form-control ">'+strOption9+' </select><br>';
	}
	
	}
	else if(labelType=="list"&&labels=="Assignment Groups:")
	{
	myFormContent+='<a href="#" data-toggle="tooltip" title="What is the name of Assignment Group.">'+iCard[i].label+'</a>'; 
	//myFormContent+='<label class="btn-label">'+iCard[i].label+'</label>';
	if(strOption8.indexOf("selected"))
	{
	var strOption8temp = strOption8.replace("selected","");
	myFormContent+='<select id="selectbox8" class="assignmentListClassbox form-control ">'+strOption8temp+' </select><br>';
	
	}else
	{
	myFormContent+='<select id="selectbox8" class="assignmentListClassbox form-control ">'+strOption8+' </select><br>';
	}
	
	}
	else if(labelType=="text"&&labels=="Approval Motivation:"){
	     
	 myFormContent+='<a href="#"  data-toggle="tooltip" title="The short description which will tell why do you need the access, what is the motivation for this request.">'+iCard[i].label+'</a>';
	//myFormContent+='<i class="fa fa-question"></i>';	 
	 myFormContent+='<div style="margin-bottom: 25px"><input  onkeyup="approvaldisable(this)" id="textBox1" type="text" class="approvaltextBoxClass form-control" name="username" value="" placeholder="'+labels+'"></div>';  
	}
	else if(labelType=="text"&&labels=="motivation for request:"){
	     
	 myFormContent+='<a href="#"  data-toggle="tooltip" title="The short description which will tell why do you need the access, what is the motivation or justification for this request.">'+iCard[i].label+'</a>';
	//myFormContent+='<i class="fa fa-question"></i>';	 
	 myFormContent+='<div style="margin-bottom: 25px"><input  onkeyup="motivationdisable(this)" id="textBox2" type="text" class="motivationtextBoxClass form-control" name="username" value="" placeholder="'+labels+'"></div>';  
	}
	else if(labelType=="text"&&labels=="Problem:"){
	     
	 myFormContent+='<a href="#"  data-toggle="tooltip" title="Describe Problem">'+iCard[i].label+'</a>';
	//myFormContent+='<i class="fa fa-question"></i>';	 
	 myFormContent+='<div style="margin-bottom: 25px"><input  onkeyup="problemdisable(this)" id="textBox3" type="text" class="problemTextBoxClass form-control" name="username" value="" placeholder="'+labels+'"></div>';  
	}
	
	
	/*8	else if(labelType=="text"&&labels=="Approval Motivation:"){
	     
	 myFormContent+='<a href="#"  data-toggle="tooltip" title="The short description which will tell why do you need the access, what is the motivation for this request.">'+iCard[i].label+'</a>';
	//myFormContent+='<i class="fa fa-question"></i>';	 
	 myFormContent+='<div style="margin-bottom: 25px"><input  onkeyup="motivationdisable(this)" id="textBox1" type="text" class="textBoxClass form-control" name="username" value="" placeholder="'+labels+'"></div>';  
	}
	else if(labelType=="text"&&labels=="Problem:"){
	     
	 myFormContent+='<a href="#"  data-toggle="tooltip" title="Describe Problem">'+iCard[i].label+'</a>';
	//myFormContent+='<i class="fa fa-question"></i>';	 
	 myFormContent+='<div style="margin-bottom: 25px"><input  onkeyup="problemdisable(this)" id="textBox2" type="text" class="problemTextBoxClass form-control" name="username" value="" placeholder="'+labels+'"></div>';  
	}
	else if(labelType=="text"&&labels=="Description:"){
	     
	 myFormContent+='<a href="#"  data-toggle="tooltip" title="Enter the description">'+iCard[i].label+'</a>';
	//myFormContent+='<i class="fa fa-question"></i>';	 
	 myFormContent+='<div style="margin-bottom: 25px"><input  onkeyup="descriptiondisable(this)" id="textBox3" type="text" class="descriptionTextBoxClass form-control" name="username" value="" placeholder="'+labels+'"></div>';  
	} */
	/*else if(labelType=="radio"){
	 myFormContent+='<p class="text-info">Locked Subfolder:</p><p class="text-info">'+labels+'</p>';
	 myFormContent+='<p class="text-warning">Do not check this box if you need access to the whole disk.</p>'; 
	 myFormContent+='<div class="radio"><label><input type="radio" name="lockedSubFolder"  class="radioButtons" id="radioButton" value="YES">Yes  </label><label><input type="radio" name="lockedSubFolder" class="radioButtons" id="radioButton" value="NO" Checked>No</label> </div>'; 
	 
	}*/
	
	
	  } 
	  }
	if(flag=="true")
	{    //alert("flag is true");
	myFormContent += '<input type="submit" value="Submit" onclick="getIcardValue()" class="btn btn-primary" disabled="disabled"></div></form>';
	}
	else
	{
	//alert("flag is false");
	}
	  
	  //alert("arr::"+arr.length);
	  
	}
	}	
	return myFormContent;
}


function goToOptions() {
	
	  menuOptionsItemOnOptions+="<option value='Select from the options' selected='selected' disabled>Select from the options</option>";
	  for(var i=0;i<labelArray.length;i++){
	//alert("labelArray:::"+labelArray[i]);
	
	if(labelArray[i]=="Raise a Ticket")
	    {
                       menuOptionsItemOnOptions+="<option value='#askForRaiseTicket'>" + "Raise a Ticket" + "</option>";
	//}else if(labelArray[i]=="Get the status of a request"){
	 //  menuOptionsItemOnOptions+="<option value='"+labelArray[i]+"'>" + labelArray[i] + "</option>";	
	
	//}else if(labelArray[i]=="Raise an incident"){
	 //  menuOptionsItemOnOptions+="<option value='"+labelArray[i]+"'>" + labelArray[i] + "</option>";	
	
	//	}else if(labelArray[i]=="Report and troubleshoot an issue"){
	//	   menuOptionsItemOnOptions+="<option value='"+labelArray[i]+"'>" + labelArray[i] + "</option>";	
	
	//	}else if(labelArray[i]=="Get the status of an incident"){
	//	   menuOptionsItemOnOptions+="<option value='"+labelArray[i]+"'>" + labelArray[i] + "</option>";	
	
	}else if(labelArray[i]=="Get status of existing Ticket"){
	   menuOptionsItemOnOptions+="<option value='#askForGetStatusOfTicket'>" + "Get status of existing Ticket" + "</option>";	
	    }   


	   }
	
	   $(".speech:last").append('<select id="menuDropdownOptionTag" class="menuDropdown form-control" onChange="getMenuItemSelected(this.value)" >'+menuOptionsItemOnOptions+' </select> <br>');
	   menuOptionsItemOnOptions="";
	   
	   $(".fa-bars:last").css('display','none');
	
 }
function area(inText){
	$("#userUtterance").val('.');
	
	if(inText=="cancel task")
	{
	  prefix="";
	}
	 var userUtter
	if(prefix!="")
	{
	    userUtter  = inText;
	    var temp = userUtter;
	userUtter = prefix+temp;
	}
	else
	{
	userUtter = inText;
	}	

	$.post(locationInt, { 
	  //userUtterance: $("#userUtterance").val()
	userUtterance: userUtter

            }, function (data) {
	var content = "";
	var msg="";
	    prefix="";
	
	 var text = $('#userUtterance');
                 text.focus();
	
	
	if(data.message!=undefined){
	
	var infoMessage = data.message.data.info;
	
	if(infoMessage!="")
	{
	
	if(infoMessage.text!="")
	{
	content = content+ '<br>'+infoMessage.text;
	
	}
	if(infoMessage.image!="")
	{
	
	  content = content+ "<br>" +"<div id='imgDiv' class='imgClass'><img src='"+infoMessage.images+"'></div>";
	}
	
	if(infoMessage.video!="")
	{
	content = content+ "<br>" +"<div id='vidDiv' class='vidClass'><video width='320' height='240' controls> <source src='"+infoMessage.video+"' type='video/mp4'> <source src='movie.ogg' type='video/ogg'>Your browser does not support the video tag.</video></div>";
	}
	if(infoMessage.audio!="")
	{
	content = content+ "<br>" +"<audio controls> <source src='horse.ogg' type='audio/ogg'><source src='"+queAnsDTO.getAudio()+"' type='audio/mpeg'> Your browser does not support the audio tag. </audio>";
	}	
                
                if(infoMessage.file!=undefined)
	{
	content = content+'<br>'+infoMessage.file;
	}
                if(infoMessage.documents!="")
	{
	content = content+'<br>'+"<div><a href='"+queAnsDTO.getDocuments()+"' target='_blank' >Click Here To Download Document</a></div>";
	}


                if(infoMessage.incidentDetailsList!=undefined){
	if(infoMessage.incidentDetailsList.length>0)
	
	{
	var table = document.createElement("table");
	table.setAttribute("id", "detailslist", 0);
	var tableData="<table border='1'>";
	for(var s=0;s<infoMessage.incidentDetailsList.length;s++){
	tableData=tableData+"<tr><td>"+infoMessage.incidentDetailsList[s].incidentId+
	          "</td><td><b>Problem:</b>"+infoMessage.incidentDetailsList[s].problems+
	  "<br><b>AssignTo:</b>"+infoMessage.incidentDetailsList[s].assignTo+
	  "<br><b>State:</b>"+infoMessage.incidentDetailsList[s].state+
	 // "<br>"+infoMessage.incidentDetailsList[s].createdOn+
	  "<br><b>CreatedOn:</b>"+infoMessage.incidentDetailsList[s].createdOn+"</td></tr>";	
	}
	tableData=tableData+"</table><br>";
	//by vaibhav
	content = content+tableData+data.result.reply;
	}	
	}	

            //-------doe techM volvo incidentdetailstechMTopFive???????????
	    if(infoMessage.incidentdetailstechMTopFive!=undefined){
	if(infoMessage.incidentdetailstechMTopFive.length>0)
	{
	var table = document.createElement("table");
	table.setAttribute("id", "detailslist", 0);
	var tableData="<table border='1'>";
	for(var p=0;p<infoMessage.incidentdetailstechMTopFive.length;p++){
	tableData=tableData+"<tr><td><p><b>Request Number:</b><a id=" + infoMessage.incidentdetailstechMTopFive[p].incidentId + " onclick='markActiveLink(this)'>"+ " " + infoMessage.incidentdetailstechMTopFive[p].incidentId+"</a></p>"+
	   "<p><b>State: </b>"+infoMessage.incidentdetailstechMTopFive[p].state+"</p>"+
     	   "<p><b>Problem: </b>"+infoMessage.incidentdetailstechMTopFive[p].problems+"</p></td></td></tr>";	 	
	}
	//alert("welcome nessa");
	tableData=tableData+"</table><br> <a id='show all requests' onclick='markActiveLink(this)'>show all request</a><br>";
	
	
	   // content = content+tableData+data.result.reply;	
                     content = content+tableData;	
	}	
	}	
              
	 //-------doe techM volvo Top_five_incidentdetailstechM???????????
	    if(infoMessage.top_five_incidentdetailstechM!=undefined){
	if(infoMessage.top_five_incidentdetailstechM.length>0)
	{
	var table = document.createElement("table");
	table.setAttribute("id", "detailslist", 0);
	var tableData="<table border='1'>";
	for(var s=0;s<infoMessage.top_five_incidentdetailstechM.length;s++){
	tableData=tableData+"<tr><td><p><b>Request Number:</b><a id=" + infoMessage.top_five_incidentdetailstechM[s].incidentId + " onclick='markActiveLink(this)'>"+ " " + infoMessage.top_five_incidentdetailstechM[s].incidentId+"</a></p>"+
	   "<p><b>State: </b>"+infoMessage.top_five_incidentdetailstechM[s].state+"</p>"+
     	   "<p><b>Problem: </b>"+infoMessage.top_five_incidentdetailstechM[s].problems+"</p></td></td></tr>";	 	
	}
	//alert("welcome nessa");
	tableData=tableData+"</table><br> <a id='show all incidents' onclick='markActiveLink(this)'>show all incidents</a><br>";
	
	
	   // content = content+tableData+data.result.reply;	
                     content = content+tableData;	
	}	
	}	
               
	 //-------doe techM volvo incidentDetailsList
	    if(infoMessage.incidentdetailstechM!=undefined){
	if(infoMessage.incidentdetailstechM.length>0)
	{
	var table = document.createElement("table");
	table.setAttribute("id", "detailslist", 0);
	var tableData="<table border='1'>";
	for(var p=0;p<infoMessage.incidentdetailstechM.length;p++){
	tableData=tableData+"<tr><td><p><b>Request Number:</b><a id=" + infoMessage.incidentdetailstechM[p].incidentId + " onclick='markActiveLink(this)'>"+ " " + infoMessage.incidentdetailstechM[p].incidentId+"</a></p>"+
	   "<p><b>State: </b>"+infoMessage.incidentdetailstechM[p].state+"</p>"+
     	   "<p><b>Problem: </b>"+infoMessage.incidentdetailstechM[p].problems+"</p></td></td></tr>";	 	
	}
	//alert("welcome nessa");
	tableData=tableData+"</table><br>";
	
	
	   // content = content+tableData+data.result.reply;	
                    content = content+tableData;	
	}	
	}	
                //------for techM
	//=======================================================================
	
	 if(infoMessage.techMIncidnetDetailbyId!=undefined){
	if(infoMessage.techMIncidnetDetailbyId.length>0)
	{
	var table = document.createElement("table");
	table.setAttribute("id", "detailslist", 0);
	var tableData="<table border='1'>";
	for(var p=0;p<infoMessage.techMIncidnetDetailbyId.length;p++){
	tableData=tableData+"<p><b>Work Notes: </b><a id=" + infoMessage.techMIncidnetDetailbyId[p].worknote +"</p>"+
	         "<p><b>Status: </b>"+infoMessage.techMIncidnetDetailbyId[p].state+"</p>"+
     	          "<p><b>AssignTo: </b>"+infoMessage.techMIncidnetDetailbyId[p].assigned_toname+"</p>"+
                                              "<p><b>Assignment_Group: </b>"+infoMessage.techMIncidnetDetailbyId[p].assignment_group1+"</p>";	   
	}
	//alert("welcome nessa");
	tableData=tableData+"</table>";
	//content = content+tableData+data.result.reply;	
                       content = content+tableData;	
	}	
	}	
	//=======================================================================	
	//=======================================================================
	 
	   if(infoMessage.bothtechMTopFive!=undefined){
	if(infoMessage.bothtechMTopFive.length>0)
	{
	var table = document.createElement("table");
	table.setAttribute("id", "detailslist", 0);
	var tableData="<table border='1' style='width:245px;'>";
	for(var p=0;p<infoMessage.bothtechMTopFive.length;p++){
	tableData=tableData+"<tr><td><p><b>Incident Number:</b><a id=" + infoMessage.bothtechMTopFive[p].incidentId + " onclick='markActiveLink(this)'>"+ " " +infoMessage.bothtechMTopFive[p].incidentId+"</a></p>"+
	   "<p><b>State: </b>"+infoMessage.bothtechMTopFive[p].state+"</p>"+
     	   "<p><b>Problem: </b>"+infoMessage.bothtechMTopFive[p].problems+"</p></td></td></tr>";	 	
	
	}	
	tableData=tableData+"</table><br>";	
	   // content = content+tableData+data.result.reply;	
                    content = content+tableData;	
	}	
	if(infoMessage.top_five_incidentdetailstechMMM.length>0)
	{
	var table = document.createElement("table");
	table.setAttribute("id", "detailslist", 0);
	var tableData="<table border='1'style='width:245px;'>";
	for(var q=0;q<infoMessage.top_five_incidentdetailstechMMM.length;q++){
	tableData=tableData+"<tr><td><p><b>Request Number:</b><a id=" + infoMessage.top_five_incidentdetailstechMMM[q].incidentId + " onclick='markActiveLink(this)'>"+ " " +infoMessage.top_five_incidentdetailstechMMM[q].incidentId+"</a></p>"+
	   "<p><b>State: </b>"+infoMessage.top_five_incidentdetailstechMMM[q].state+"</p>"+
     	   "<p><b>Problem: </b>"+infoMessage.top_five_incidentdetailstechMMM[q].problems+"</p></td></td></tr>";	 	
	
	}
	tableData=tableData+"</table><br>Above are the open Tickets against your name, please select the request and Incident number for the status update.<br>";	
	tableData=tableData+"</table><br> <a id='show all requests' onclick='markActiveLink(this)'>show all Request</a>";
	tableData=tableData+"</table><br> <a id='show all incidents' onclick='markActiveLink(this)'>show all Incidents</a><br>";
	
	tableData=tableData+"</table><br>";
	
	   // content = content+tableData+data.result.reply;	
                    content = content+tableData;	
	}	
	   }	
	  
	//=======================================================================	
	
	
	}
	}	
	
	
	if(data.message!=undefined){
	   if(data.message.chat!="")
	{
	msg ="";
	
	}
	else{
	
	msg = data.result.reply;
	
	}
	   }else
	   {
	   msg = data.result.reply;
	   }
	conversation = conversation +"#User:"+ $("#userUtterance").val(),
	    conversation = conversation+"Nessa Bot:" + data.result.reply;
	    console.log('conversation ='+conversation);
               

	   $('#loading-small').hide();
                var userUtteranceVal = inText
	
               systemUtteranceContentResponse = '<li class="mar-btm systemUtterance"><div class="media-body pad-hor"> <div class="speech"><p id="">' + content +''+ msg+ '</p> </div></div></li>';
                userUtteranceContent = '<li class="mar-btm userUtterance"><div class="media-body pad-hor speech-right"><div class="speech"><p id="userUtteranceValue">' + userUtteranceVal + '</p></div></div></li>';
	
	
	// iCard implementation
	counter=counter+1;
	newFormId=formId+counter;
	//alert(newFormId);
	
	myFormContent = createForm(data);
	  //end of i card implementation
	
                if (!$.trim($("#userUtterance").val())) {
                    console.log('textaarea is empty');
                } else {
                    $("#utterance").append(userUtteranceContent).fadeIn(600);
                    $("#utterance").append(systemUtteranceContentResponse).fadeIn(600);
	
	
//---------------------------------------------------------------	
	//vaibhav agrawal for opendend start
	var openend = data.result.entities[0].type;
	var openended = data.result.currentEntity.type;
	   if(openend=="open_ended"||openended=="open_ended")
	{
	$('.speech:last').append("<a href='#'><i class='fa fa-bars' id='namedemo' aria-hidden='true' onClick='goToOptions()' style='margin-top: 7px; margin-right: -4px;' ></i></a>");
	//alert("its working "+openend);
	//alert("its working "+openended);	
	}
	else{	
	//alert("its not working "+openend);
	// alert("its not working "+openended);	
	}
	  //vaibhav agrawal for opendend end	
	//-----------------------------------------------------------------	
	
	
	//$('.speech').last().append(myFormContent);
	$('.speech:last').append(myFormContent);
	 $('[data-toggle="tooltip"]').tooltip(); 
	 $(".nameClassbox").customselect();
	 //$("#selectbox").customselect();
	/*$('.speech:last').append('<div id="i want to cancel the task" onclick="markActiveLink(this)"><button type="button" class="close" aria-label="Close" style="margin-top: -12px;  margin-right: -5px; font-size: 26px;"  <span aria-hidden="true">&times;</span></button></div>'); */
                        //$('.speech:last').append('<a href="#"><i class="fa fa-bars" aria-hidden="true" onClick="goToOptions()"></i></a>');
	}
                //setGuiEnabled(true);
                $("#userUtterance").val('');
                $("#bottom").get(0).scrollIntoView();
                //speechSynthesis.cancel();
            })
            .fail(
                function (xhRequest, status,
                    thrownError) {
                    showError(
                        "Server error or service unavailable.",
                        true);
                });

}
$(document).ready(function () {
	
	 
//$('[data-toggle="tooltip"]').tooltip(); 	
portNumber = window.location.port;
//latestChat();
console.log("PORT "+portNumber);
/*setTimeout(function(){ 
    latestChat();}, 2000);*/
    //config:
    /*------------ Local variable ------------------*/
    var debug = true;
   var location = "";
    var browserSupportsSpeech = false;
    var wants_speech = false;

    var systemUtteranceContent;
    var userUtteranceContent;
    
    /*-------------------------- Current Time Function Start --------------------------*/

    var msg;

    function currentTime() {
        var date = new Date();
        var hours = date.getHours();
        if (hours < 12)
            msg = "Good Morning! How may I help you?";
        else if (hours < 18)
            msg = "Good Afternoon! How may I help you?";
        else
            msg = "Good Evening! How may I help you?";
        var minutes = date.getMinutes();
        var ampm = hours >= 12 ? 'pm' : 'am';
        hours = hours % 12;
        hours = hours ? hours : 12; // the hour '0' should be '12'

        minutes = minutes < 10 ? '0' + minutes : minutes;
        var strTime = hours + ':' + minutes + ' ' + ampm;
        return strTime;
	alert("strTime::"+msg);
    }

    // currentTime();

    /*-------------------------- Current Time Function End --------------------------*/

    /*------------------ Login Start -------------------*/
    $(".chat-wrapper").hide();
    $('.ip-adress').hide();
    $('.toggle').click(function () {
        $('.ip-adress').slideToggle();
    });

    /*-------- Login Input focus start ------------------*/
    $(".input").focusin(function () {
        $(this).find("span").animate({
            "opacity": "0"
        }, 200);
    });

    $(".input").focusout(function () {
        $(this).find("span").animate({
            "opacity": "1"
        }, 300);
    });
    /*-------- Login Input focus End ------------------*/

    var username;
    var password;
    var passwordValid;
    var ipAddress;

    /*-------- Login Button Function Start ------------------*/
    var readLocation;
    //$("#login").click(function (e) {
	$(document).ready(function () {
	
	  
	  //code for hide ? start	here by vaibhav
	$('textarea').keypress(function(e) {
	var value = this.value + String.fromCharCode(e.keyCode);
	if (value == '?') {
	 $(this).val($(this).val()+'.');
	e.preventDefault();
	}
	});
        //code for hide ? start	here by vaibhav
	
         $('[data-toggle="tooltip"]').tooltip(); 
	  var text = $('#userUtterance');
          text.focus();
	  
    	 var divelement = $('#google_translate_element').parents();
	 
	 
	 var s = window.location.href; 
	
	
	
	
	 passwordValid = 'user123';
	 password = 'user123';
	
	
       // ipAddress = "http://localhost:8085/Nessa-Admin/liveChatByInstanceId";
	 var sessionId= $("#sessionId").val();
        ipAddress = "http://localhost:8085/Nessa-Admin/getDialogById?sessionId="+sessionId;
        console.log("url>>>>"+ipAddress)
       
        //alert(ipAddress);

        if (username != '' && password != '' && password == passwordValid) {
            //lert('login Successfully');

            $("#login-wrapper").hide();
            $(".chat-wrapper").show();
            $('#loading').show();
            $('#sidebar').show();
            location = ipAddress;
            //alert(location + ' Test Login click location url');
            /*-------------------------- Initial Post Call Function Start --------------------------*/

            $.post(location,{ user: Lpassword},function (data, textStatus, jqXHR) {
    	console.log("data" + data)
	
    	var dialog=data.feedBAckDialogList;
		var agent="";
		if(dialog!=null){
		for(var i=0;i<dialog.length;i++){
			
		if(dialog[i]!=null){
	
		if(dialog[i].nessaUtterance!=null&&dialog[i].nessaUtterance!=""){

userUtterDiv ='<li class="mar-btm systemUtterance"> <div class="media-body pad-hor"> <div class="speech"><p id="systemUtterance">' + dialog[i].nessaUtterance  + '</p><p></p> </div></div></li>';
$("#utterance").append(userUtterDiv);

}else if(dialog[i].userUtterance!=null&&dialog[i].userUtterance!=""){

agentUtterDiv = '<li class="mar-btm userUtterance"><div class="media-body pad-hor speech-right"><div class="speech"><p id="userUtteranceValue">' + dialog[i].userUtterance + '</p></div></div></li>';
	$("#utterance").append(agentUtterDiv).fadeIn(600);

}
	
}	

}	

		}	currentTime();
	   // systemUtteranceContent = '<li class="mar-btm systemUtterance"> <div class="media-body pad-hor"> <div class="speech"><p id="systemUtterance">' + str  + '</p><p></p> </div></div></li>';
	
	  $("#start-button-holder").hide();
	$('#loading').hide();
                   // $("#utterance").append(systemUtteranceContent);
                    // readLocation = location.replace('https', 'http');
                    //  console.log(readLocation + ' location.....');
                    $("#utterance li:hidden:first")
                        .fadeIn(600);
                    locationInt = jqXHR.getResponseHeader("location");
                })
                .fail(function (xhRequest, status, thrownError) {
                    showError("Initialisation failed.", true);
                });
	//alert("labelArray in Post::"+labelArray);
            /*-------------------------- Initial Post Call Function End --------------------------*/
        }

    });
    $(".validate").keyup(function (e) {
        $(this).parent().parent().removeClass('login-error');
    });
    $('#password').keydown(function (e) {
        if (e.keyCode == 13) {
            login.click();
        }
    });
    /*-------- Login Button Function End ------------------*/


    /*-------------------------- On Submit Function Click and Enter Start --------------------------*/

    $("#btnSubmit").click(function () {
        submit();
    });
	
	$("#div1").click(function () {
	//alert('in div1');
    var id = document.getElementById(div1);
    //alert(id.innerText);
    });
	
    //enter
    $('#userUtterance').keydown(function (e) {
        var divId = $('#userUtterance').val();
	if (e.keyCode == 13) {
            if(divId!=""){
	submit();
	}else{
	
	}
        }
    });
    /*-------------------------- On Submit Function Click and Enter End --------------------------*/

    /*-------------------------- On Submit Function Call Start  --------------------------*/
    var udateAvatar;
    //updateAvatar = 'img/avatar1.png';
	updateAvatar = "";
	var userUtteranceVal
	var translatedData
	var userQuestion
     function submit() {
	 var divId = document.getElementById('div1');
        // setGuiEnabled(false);
        // $("#loader").show();
        $('#loading-small').show();
	
	/*var langTarget = "en"; // The language Google will translate the text in.
	var description = $("#userUtterance").val(); // Fetch source of text to translate.
	var apiKey = "AIzaSyDpEGQNU1cBf6adk_mthbP0YQ93bHvHF5I";
	var langSource = "ja"
	var apiurl = "https://translation.googleapis.com/language/translate/v2?key=" + apiKey + "&source=" + langSource + "&target=" + langTarget + "&q=" + encodeURIComponent(description) + "";
	//alert(apiurl); // This is just to see if all data is sent through
	// Now we call the data
	$.ajax({
	url: apiurl,
	dataType: 'jsonp',
	success: function(data){
	// console.log(data);
	console.log(data.data.translations[0].translatedText);
	translatedData = data.data.translations[0].translatedText;
	//userUtterance: data.data.translations[0].translatedText
	if(prefix!="")
	{
	userQuestion  = translatedData;
	var temp = userQuestion;
	userQuestion = prefix+temp;
	if(temp=="cancel task")
	   {
	  prefix="";
	   }	
	}
	else
	{
	userQuestion = translatedData
	}
	userUtter();
	}}); // Inserts translated text.  */
	
	 
	// }
//function userUtter(){
        var sessionId= $("#sessionId").val()
	$.post('http://localhost:8085/ChatAPI/chat/saveAgentUtterance', { 
	                  
	  // userUtterance: userQuestion
	
	  utterance: $("#userUtterance").val(),
	 instanceId:sessionId
	
            }, function (data) {
	console.log("data>>>"+data)
	var content = data.message.chat//data.message.chat;//insert data
	console.log("content>>>"+content)
		$('#loading-small').hide();


	var msg1 = "";
	    prefix="";
	
                $('#loading-small').hide();
                var userUtteranceVal = $("#userUtterance").val();
	 
	
              //  systemUtteranceContentResponse = '<li class="mar-btm systemUtterance"> <div class="media-body pad-hor"> <div class="speech" id="speechId"> <p id="">' + content +'</p> </div></div></li>';
                userUtteranceContent = '<li class="mar-btm userUtterance"><div class="media-body pad-hor speech-right"><div class="speech"><p id="userUtteranceValue">' + userUtteranceVal + '</p></div></div></li>';
	
	if (!$.trim($("#userUtterance").val())) {
                    console.log('textaarea is empty');
                } else {
                    $("#utterance").append(userUtteranceContent).fadeIn(600);
                 //   $("#utterance").append(systemUtteranceContentResponse).fadeIn(600);
	
	

	
	
	/*$('.speech:last').append('<div id="i want to cancel the task" onclick="markActiveLink(this)"><button type="button" class="close" aria-label="Close" style="margin-top: -12px;  margin-right: -5px;  font-size: 26px;"  <span aria-hidden="true">&times;</span></button></div>');*/
                    //$('.speech:last').append('<a href="#"><i class="fa fa-bars" aria-hidden="true" onClick="goToOptions()"></i></a>');
	}
                //is user has used ASR before, automatically start TTS
                if (wants_speech) {
	//tts_msg.text = data.result.reply;
	if(data.message!=undefined){
	if(data.message.chat!="")
	{ 
	tts_msg.text = data.message.chat;
	}else{
	tts_msg.text = data.result.reply;
	}
	}
	else{
	tts_msg.text = data.result.reply;
	}
	
	
	speechSynthesis.speak(tts_msg);
                }
                //setGuiEnabled(true);
                $("#userUtterance").val('');
                $("#bottom").get(0).scrollIntoView();
                //speechSynthesis.cancel();
            })
            .fail(
                function (xhRequest, status,
                    thrownError) {
                    showError(
                        "Server error or service unavailable.",
                        true);
                });
	
}
	///////
    /*-------------------------- On Submit Function Call End  --------------------------*/
    
    /*-------------------------- Show Error Function Start  --------------------------*/
    function showError(error, fatal) {
        $("#error").html(error);
        if (fatal) {
            $('#loader').removeClass("loading");
            $('#container').delay(3500).animate({
                opacity: .2
            }, 2500);

        } else
            $("#error:hidden:first").fadeIn(1000).delay(
                2000).fadeOut(1000);
    }
    /*-------------------------- Show Error Function End  --------------------------*/


    /*-------------------------------- Google Web Speech API Start ---------------------*/

    if (!('SpeechRecognition' in window) && !('webkitSpeechRecognition' in window)) {
        showError(
            "Your Browser doesn't support speech recognition. Try Chrome.",
            false);
        $('#recobtn').prop('disabled', true);

    } else {
        browserSupportsSpeech = true;
        var auto_submit = true;
        //TTS
        var tts_msg = new SpeechSynthesisUtterance();
        tts_msg.lang = 'en-GB';
	//tts_msg.lang = 'ja-JP';
        tts_msg.onend = function (e) {
	//speechSynthesis.cancel();
            start_stop_recognition();
        };
        //ASR
        var recognizing = false;
        var final_transcript = '';
        var ignore_onend = false;
        var start_timestamp;
        var SpeechRecognition = SpeechRecognition || webkitSpeechRecognition;
        var recognition = new SpeechRecognition();
        recognition.continuous = false;
        recognition.interimResults = false;

        /*------------ speech recognition OnError Event Start------------------*/
        recognition.onerror = function (event) {
            ignore_onend = true;
            //updateGui(false);
            wants_speech = false;

            if (event.error == 'no-speech') {
                start_img.src = 'img/mic.gif';
                start_img1.src = 'img/mic-off.png';
                showInfo('info_no_speech');
            }
            if (event.error == 'audio-capture') {
                start_img.src = 'img/mic.gif';
                 start_img1.src = 'img/mic-off.png';
                showInfo('info_no_microphone');
            }
            if (event.error == 'not-allowed') {
                if (event.timeStamp - start_timestamp < 100) {
                    showInfo('info_blocked');
                    start_img1.src = 'img/mic-off.png';
                   $('#micOff').children('span').text = 'MIC OFF';
                } else {
                    showInfo('info_denied');                      
                    start_img1.src = 'img/mic-off.png';
                    $("#micOff span").contents()[0].nodeValue = "MIC OFF ";
                }
            }

            showError(event.error, false);
        };
        /*------------ speech recognition OnError Event End------------------*/

        /*------------ speech recognition OnStart Event Start------------------*/

        recognition.onstart = function () {
            recognizing = true;
            //updateGui(true);
            start_img.src = 'img/mic-animate.gif';
            showInfo('info_speak_now');
        };
        /*------------ speech recognition OnStart Event End------------------*/

        /*------------ speech recognition OnEnd Event Start------------------*/
        recognition.onend = function () {
            recognizing = false;
            if (ignore_onend) {
                //console.log('web speech on end...');
                return;
            }

            start_img.src = 'img/mic.gif';
            if (!final_transcript) {
                showInfo('info_start');
                // console.log('if not final script web speech on end...');
                return;
            }
            showInfo('');
            if (window.getSelection) {
                console.log('web speech window get selected ...');
                window.getSelection().removeAllRanges();
                var range = document.createRange();
                range.selectNode(document.getElementById('final_span'));
                window.getSelection().addRange(range);
            }
        };
        /*------------ speech recognition OnEnd Event End------------------*/

        /*------------ speech recognition OnResult Event Start------------------*/
        recognition.onresult = function (event) {
            var interim_transcript = '';
            if (typeof (event.results) == 'undefined') {
                recognition.onend = null;
                recognition.stop();
                showError("Result undefined", false);
                return;
            }

            for (var i = event.resultIndex; i < event.results.length; ++i) {
                if (event.results[i].isFinal) {
                    final_transcript += event.results[i][0].transcript;
                } else {
                    interim_transcript += event.results[i][0].transcript;
                }
            }
            //console.log('web speech on result ...');
            $("#userUtterance").val(final_transcript);
            if (!recognition.continuous) {
                recognition.stop();
            }
            if (auto_submit)
                submit();
        };
        /*------------ speech recognition OnResult Event End------------------*/
        /*------------ Function  start stop recognition start------------------*/
        function start_stop_recognition() {
            if (recognizing) {
                recognition.stop();
                wants_speech = false;
                start_img.src = 'img/mic.gif';
               // start_img1.src = 'img/mic-off.png';
              //$('#micOff').children('span').text = 'MIC OFF';
                return;
            }
            wants_speech = true;
            final_transcript = '';
            recognition.lang = 'en-GB';
            recognition.start();
            ignore_onend = false;
            start_img.src = 'img/mic-slash.gif';
            //start_img1.src = 'img/mic-on.png';
            //$('#micOff').children('span').text = 'MIC ON';
        }
        /*------------ Function  start stop recognition End------------------*/
        /*------------ Show Info Function start------------------*/
        function showInfo(s) {
            if (s) {
                for (var child = info.firstChild; child; child = child.nextSibling) {
                    if (child.style) {
                        child.style.display = child.id == s ? 'inline' : 'none';
                    }
                }
                $("#info").show().delay(3500).fadeOut(1000);

            } else {
                $("#info").hide();
            }
        }
        /*------------ Show Info Function end------------------*/

        /*------------ Record button start------------------*/
        $("#recobtn").click(function (event) {
            start_stop_recognition();
            start_img.src = 'img/mic-slash.gif';
        });
        /*------------ Record button End------------------*/
    }
    /*-------------------------------- Google Web Speech API End ---------------------*/

    /*----------------- Record Cancel Button start -----------------------*/
    $("#micOff").on('click', function () {
        var curSrc = $(this).children().attr('src');
        if (curSrc === 'img/mic-off.png') {            
            start_img1.src = 'img/mic-on.png';          
            $("#micOff").children('span').text('MIC ON');           
            wants_speech = false;
            start_stop_recognition();            
        }
        else if (curSrc === 'img/mic-on.png') {            
            start_img1.src = 'img/mic-off.png';
             $("#micOff").children('span').text('MIC OFF');
            wants_speech = true;
            start_stop_recognition();
        }
        // start_img1.src = 'img/mic-off.png';
         //$(this).children('span').text('MIC OFF');       
    });
    /*----------------- Record Cancel Button End -----------------------*/


    /*-------------------------------- SideBar Start ---------------------*/

    $('.sidebar').css('left', '-80px');
    var state, t;
    $('.toggler').click(function () {
        state = parseInt($('.sidebar').css('left'), 10) > -1;
        //alert(state);
        //$('.sidebar').animate({'left': (state ? -80 : 0)}, 'slow');
        if (state == false) {
            $('.sidebar').animate({
                'left': 0
            });
            $('.overlay').show();
            $('span.glyphicon-chevron-right').hide();
            $('span.glyphicon-chevron-left').show();
            t = setTimeout(function () {
                $('.sidebar').animate({
                    'left': -80
                });
                $('span.glyphicon-chevron-right').show();
                $('span.glyphicon-chevron-left').hide();
                $('.overlay').hide();
            }, 5000);

        } else if (state == true) {
            $('.sidebar').animate({
                'left': -80
            });
            $('.overlay').hide();
            clearTimeout(t);
            $('span.glyphicon-chevron-left').hide();
            $('span.glyphicon-chevron-right').show();
        }

    });
	
	
	
	
	
	//autocomplete used case
/* $( function() {
    var availableTags = [
      "LEQM Access",
      "Shared Disk Access"
    ];
    $( "#userUtterance" ).autocomplete({
      source: availableTags
    });
  } );*/
    /*-------------------------------- SideBar End ---------------------*/
    /*------------------ Update Avtar Start ------------------*/
   /* function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
               updateAvatar = e.target.result;
               $('.userAvatar').attr('src', e.target.result);       
               // console.log(updateAvatar);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }

    $("#changeAvatar").change(function () {
        readURL(this);
    });*/
    
    $('.list-group .thumbnail').on('click', function(){
         $('.list-group .thumbnail').removeClass('active');
        $(this).closest('a').addClass('active');
        var SelectAvtarSrc = $(this).children('img').attr('src');
        updateAvatar = SelectAvtarSrc;
         $('.userAvatar').attr('src', updateAvatar); 
        //$('#myModal').modal('hide');
        
    });
    /*------------------ Update Avtar End ------------------*/
    
});
