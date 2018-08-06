function getLiveUserList(){
	$.ajax({
		  type: 'GET',
	      url: 'http://localhost:8085/Nessa-Admin/getLiveUserList',
		  success: function(data) {
			  var str = '';
			 list=data;
			 $("#liveAgent").html("");
			 $(list).each(function(index, value){
				 if(value.chat){        
					str+=' <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12" id="left"  style="width: 20%;">'+
					
             	'<div class="dashboard-stat2 ">'+
              '<div><i class="fa fa-circle fa-1x text-danger blink_me" id="+'value.id+'"style="margin-left: -51%;"></i>&nbsp; LIVE User:&nbsp;'+index+' </div> ';
           
			}
			 });
			$("#liveAgent").html(str);
		  }
	    });
}

