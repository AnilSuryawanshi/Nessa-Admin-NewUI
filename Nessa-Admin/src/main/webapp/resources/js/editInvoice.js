function editInvoice(id){
	var temp = $.post(applicationContextPath+"/getDialogById",{"sessionId":id});
	var html = '<div class="modal fade" id="modal-dialog-popup" aria-hidden="true">'
		+ '                                                        <div class="modal-dialog modal-full"><div>X</div>'
		+ '                                                            <div class="modal-content"><div style="float: right;" onclick="closeFeedBack()">X</div>'
		+ '                                                                <div class="panel-body">'
		+ '                                                                    <div class="row">'
		+ '                                                                        <div class="col-md-12 col-sm-12 col-xs-12 table-responsive">'
		+ '                                                                            <div class="">'
		+ '                                                                                <div id="myTabContent" class="tab-content">'
		+ '<div class="row">'
		+ '                    <div class="col-md-12" style="height:300px;background-color:  whitesmoke;">'
		+ '                        <div class="">'
		+ '                            <div class="page-title"> <i class="icon-custom-left" style="margin-left:95%;"></i>'+
		'<div id="feedBackData" style="font-size: 19px;\"> </div>'+

		'                                </div>'+
		'                               </div>'+
		'                             </div>'+
		'                          </div>'+
		'                                                                                </div>'+
		'                                                                            </div>'+
		'                                                                        </div>'+
		'                                                                    </div>'+
		'                                                                </div>'+
		'                                                            </div>'+
		'                                                        </div>'+
		'                                                    </div>';
$('#main-content').html(html);
	
	$("#modal-dialog-popup").addClass("in");
	$("#modal-dialog-popup").attr("aria-hidden", "false");
	$("#modal-dialog-popup").css("display", "block");
	$('#main-content').show();
	temp.done(function(data){
		console.log(data);
    	
		var feedback="<table>";
		$(data.feedBAckDialogList).each(function(index, element){
			
			if(element.nessaUtterance!=null){
				
				feedback+="<tr><td style='width: 400px;font-weight: 600;'>NessaUtterance:  "+element.nessaUtterance+"</td></td></tr>";
			}
			else{
				feedback+="<tr><td></td><td style='font-weight: 600;'>UserUtterance:   "+element.userUtterance+"</td></tr>";
			}
			
		});
		$("#feedBackData").html(feedback);
	});
}
function closeFeedBack(){
	$('#main-content').hide();
	//$('#main-content').remove();
}