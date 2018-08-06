<html>
<head>
<title>WELCOME</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#chatWindow").hide();
	});

	window.addEventListener('message', function(e) {
		var message = e.data;
		$("#chatWindow").fadeOut();
	});
	var strWindowFeatures = "width=400,height=650,scrollbars=yes,left=950,";

	// for opening nessa bot window on button click
	/* function openNessa(){
	 windowObjectReference = window.open("https://192.168.1.9:8080/nessa", "Nessa Bot", strWindowFeatures);
	 } */

	// for opening iFrame 
	function previewUrl(url, target) {
		clearTimeout(window.ht);
		window.ht = setTimeout(
				function() {
					// var div = document.getElementById(target);
					// div.innerHTML = '<iframe id="iframeChatWindow" style="width:100%;height:100%;" frameborder="0" scrolling="yes" src="' + url + '" />';

					var frame = $('<iframe id="iframeChatWindow" style="width:100%;height:100%;" frameborder="0" scrolling="yes" src="'
							+ url + '" />');
					$('#div1').append(frame);
					frame.load(function() {
						$("#chatWindow").fadeIn("slow", function() {
							// Animation complete
						});

					});
				}, 10);
	}
</script>
</head>

<body style="background-color: #F5F5DC; border: 1px">

	<h1>
		<center>
			<font color="#3366ff">Hello Welcome to Nessa </font>
	</h1>
	<br>
	<br>
	<br>
	<!-- local url -->
	 <input type="button" value="Nessa Bot" onclick="previewUrl('https://192.168.1.101:8080/nessa','div1')">
	<!-- server url -->
	<!-- <input type="button" value="Nessa Bot"
		onclick="previewUrl('https://ec2-52-33-176-59.us-west-2.compute.amazonaws.com:9001/nessa','div1')"> -->
	</center>
	<div id="chatWindow"
		style="text-align: right; right: 0; position: fixed; display: inline">
		<!-- <div id="test" class="pull-right" style=" top: 4px; right: 0;position: absolute; font-size: large;">close</div> -->
		<div id="div1"
			style="width: 350px; height: 500px; bottom: 0; border: 1px solid #ddd;"></div>
	</div>
</body>
</html>