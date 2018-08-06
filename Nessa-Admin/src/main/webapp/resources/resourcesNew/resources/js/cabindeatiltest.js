$(document).ready(function() {
	$("input[name=food-pref-radio]").attr("disabled",true);
	$("input[name=food-pref-radio-nonveg]").attr("checked",false);
	$("input[name=food-pref-nonveg]").attr("disabled",true);
	$("input[name=food-pref-radio-nonveg]").attr("checked",false);

	/* 
	$('#search_form').validate({
		rules: {
			search_city: "required",
			search_date1: "required",
			search_date2: "required"
		},
		messages: {
			search_city: "Please enter a city or a hotel",
			search_date1: "Please enter the Checkin Date",
			search_date2: "Please enter the Checkout Date"
		},
		submitHandler: function(form) {
				form.submit();
			}
	});*/
	
	$(function(){
		$('input[name=radio-group-room]').change(function(){ 
 		    var checked = $(this).is(':checked');
 		    var checkboxid = $(this).attr("id");
 			var roomtype =$(this).attr("value");
 			var selectedroomid = checkboxid.replace("radio", "noroomsid");
 			var selectedrooms = $("select#"+selectedroomid).val();
 			var extrabedsid = selectedroomid.replace("noroomsid", "extrabedid");
 			var extrabeds = $("select#"+extrabedsid).val();
 			
		if(checked){
		    $.ajax({
		        type: 'POST',
		        url: 'roomcheck.html',
		        data: {"checkedvalue":"checked",
		        	"roomType": roomtype,
		        	"noRooms": selectedrooms,
		        	"extraBeds": extrabeds
		               },
		        success: function(data){
		        }

		    });
		}

		else{

		    $.ajax({
		        type: 'POST',
		        url: 'roomuncheck.html',
		        data: {
		        	"checkedvalue":"unchecked",
		        	"roomType": roomtype,
		        	"noRooms": selectedrooms,
		        	"extraBeds": extrabeds
		               },
		        success: function(data){
		        }
		    });


		}

		});

		 });

	$('select[name=norooms]').change(function(event) {

		var selectedid = $(this).attr("id");
		var selectedid1 = $("select#"+selectedid);
		var norooms = $("select#"+selectedid).val();
		var selectedRoomid1 = "select#"+selectedid;
		var extrabedid = selectedRoomid1.replace("noroomsid", "extrabedid");
		
		var select = $(extrabedid);
		select.find('option').remove();
			for (i = 0; i <= norooms; i++) { 
				$('<option>').val(i).text(i).appendTo(select);
			}	

			var radioid = extrabedid.replace("extrabedid", "radio");
		
            var radioid1 = radioid.substring(radioid.indexOf("#"));
        	var checked = $(radioid1).is(':checked');
 			var roomtype =$(radioid1).val();
 		    var extrabeds = 0;
 			//alert(extrabeds);
 			//var extrabedid = selectedRoomid1.replace("noroomsid", "extrabedid");
 			
		if(checked){
		    $.ajax({
		        type: 'POST',
		        url: 'roomcheck.html',
		        data: {"checkedvalue":"checked",
		        	"roomType": roomtype,
		        	"noRooms": norooms,
		        	"extraBeds": extrabeds
		               },
		        success: function(data){
		        }

		    });
		}else{

		    $.ajax({
		        type: 'POST',
		        url: 'roomuncheck.html',
		        data: {
		        	"checkedvalue":"unchecked",
		        	"roomType": roomtype,
		        	"noRooms": selectedrooms,
		        	"extraBeds": extrabeds
		               },
		        success: function(data){
		        }
		    });
		}
	});
	
	$('select[name=extrabed]').change(function(event) {

		var selectedid = $(this).attr("id");
		var extrabeds = $("select#"+selectedid).val();
		var selectedRoomid1 = "select#"+selectedid;
		var norroomsid = selectedid.replace("extrabedid", "noroomsid");
		var norooms = $("select#"+norroomsid).val();
		var radioid = norroomsid.replace("noroomsid", "radio");
		var radioid1 = radioid.substring(radioid.indexOf("#"));
        var checked = $("#"+radioid1).is(':checked');
 		var roomtype =$("#"+radioid1).val();
 			
		if(checked){
		    $.ajax({
		        type: 'POST',
		        url: 'roomcheck.html',
		        data: {"checkedvalue":"checked",
		        	"roomType": roomtype,
		        	"noRooms": norooms,
		        	"extraBeds": extrabeds
		               },
		        success: function(data){
		        }

		    });
		}else{

		    $.ajax({
		        type: 'POST',
		        url: 'roomuncheck.html',
		        data: {
		        	"checkedvalue":"unchecked",
		        	"roomType": roomtype,
		        	"noRooms": selectedrooms,
		        	"extraBeds": extrabeds
		               },
		        success: function(data){
		        }
		    });


		}

			
			
		});
	$("#view_selection").click(function(){
		//view-accomodation-details.html?hotelCode=${hotelid}&hotelname=${hotelname}
		alert("hiii");
		var hotelCode=$("#view_hotelcode").val();
		var hotelname=$("#view_hotelname").val();
		var specialReq= $("#spec_req").val();
		
		var href="view-accomodation-details.html?hotelCode="+hotelCode+"&hotelname="+hotelname+"&specReq="+specialReq;
		 document.getElementById("view_selection").setAttribute("href",href);
		
	});
	$("#add-to-iti-ok-id").click(function(){
		alert("hii");
		var costval = $("#costviewvalue").val();
		
		var href="add-to-itinerary.html?cv="+costval;
		 document.getElementById("add-to-iti-ok-id").setAttribute("href",href);
		
	});
	

	/* Added by sagar for food filters*********************************************/
	$("input[name=food-pref-radio-main-veg]").click(function(){
		if(document.getElementById("radio-1").checked == true){
			if( document.getElementById("radio-2").checked == false){
				$("input[name=food-pref-radio]").attr("disabled",false);
				$("input[name=food-pref-radio-nonveg]").attr("disabled",true);
			}else{
				$("input[name=food-pref-radio]").attr("disabled",false);
				$("input[name=food-pref-radio]").attr("checked",false);
				$("input[name=food-pref-radio-nonveg]").attr("disabled",false);
				$("input[name=food-pref-radio-nonveg]").attr("checked",false);
			}
		}
		else if(document.getElementById("radio-2").checked == false){
				$("input[name=food-pref-radio]").attr("disabled",true);
				$("input[name=food-pref-radio]").attr("checked",false);
				$("input[name=food-pref-radio-nonveg]").attr("disabled",true);
				$("input[name=food-pref-radio-nonveg]").attr("checked",false);
		}
	});
	
	$("input[name=food-pref-radio-main-nonveg]").click(function(){
		if(document.getElementById("radio-2").checked == true){
			if(document.getElementById("radio-1").checked == true){
				$("input[name=food-pref-radio-nonveg]").attr("disabled",false);
			}
			else{
				$("input[name=food-pref-radio]").attr("disabled",false);
				$("input[name=food-pref-radio-nonveg]").attr("disabled",false);
			}
		}
		else if(document.getElementById("radio-1").checked == true){
				$("input[name=food-pref-radio-nonveg]").attr("disabled",true);
				$("input[name=food-pref-radio-nonveg]").attr("checked",false);
				}else{
					$("input[name=food-pref-radio]").attr("disabled",true);
					$("input[name=food-pref-radio]").attr("checked",false);
					$("input[name=food-pref-radio-nonveg]").attr("disabled",true);
					$("input[name=food-pref-radio-nonveg]").attr("checked",false);
			}
	});
	
	/* Added by sagar for food filters********/
	
	$('#populrity-sort').click(function() {
		var href= "populrity-sort.html";
			 document.getElementById("populrity-sort").setAttribute("href",href);
	});
	
	 
	  
	  $('.img-hover').click(function() {
			var selectval =$(this).attr("id");		   
		   document.getElementById("popupSelectwin").setAttribute("href",(window.location.href+"&select="+selectval+"#openModal"));
		});	    
});


jQuery(window).load(function(){
	
	
	jQuery(".share").click(function(e){
    e.preventDefault();
      if (!jQuery(".bottom_fixed").hasClass('active')) {
           jQuery(this).addClass("active");
           jQuery(".bottom_fixed").addClass("active");
        }
      else{
          jQuery(this).removeClass("active");
          jQuery(".bottom_fixed").removeClass("active");
      } 
   });


  jQuery(".sort_btn").click(function(e){
      e.preventDefault();
      if (!jQuery(".sort_sec_slide,.bg_overlay").hasClass('active')) {
           jQuery(this).addClass("active");
           jQuery(".sort_sec_slide,.bg_overlay").addClass("active");
        }
      else{
          jQuery(this).removeClass("active");
          jQuery(".sort_sec_slide,.bg_overlay").removeClass("active");
      } 
  });

  jQuery(".bg_overlay").click(function(){
      jQuery(".bg_overlay,.sort_sec_slide").removeClass("active");
  });


  jQuery(".summery_btn").click(function(e){
      e.preventDefault();
      if (!jQuery(".view_summery_main,.cd-tabs,.bg_overlay_full").hasClass('active')) {
           jQuery(this).addClass("active");
           jQuery(".view_summery_main,.cd-tabs,.bg_overlay_full").addClass("active");
        }
      else{
          jQuery(this).removeClass("active");
          jQuery(".view_summery_main,.cd-tabs,.bg_overlay_full").removeClass("active");
      } 
  });

  jQuery(".summery_close,.bg_overlay_full").click(function(){
      jQuery(".view_summery_main,.cd-tabs,.bg_overlay_full").removeClass("active");
  });


    (function($) {
      $('.accordion > li:eq(0) .tab_click').addClass('active').next().slideDown();

      $('.accordion .tab_click').click(function(j) {
          var dropDown = $(this).closest('li').find('.accordion_box');

          $(this).closest('.accordion').find('.accordion_box').not(dropDown).slideUp();

          if ($(this).hasClass('active')) {
              $(this).removeClass('active');
          } else {
              $(this).closest('.accordion').find('.tab_click.active').removeClass('active');
              $(this).addClass('active');
          }

          dropDown.stop(false, true).slideToggle();

          j.preventDefault();
      });
  })(jQuery);


    jQuery(".image_view .camera").click(function(e){
    e.preventDefault();
      if (!jQuery(".main").hasClass('flip')) {
           jQuery(this).addClass("flip");
           jQuery(".main").addClass("flip");
        }
      else{
          jQuery(this).removeClass("flip");
          jQuery(".main").removeClass("flip");
      } 
   });


   //  jQuery(".name_icon.first,.name_icon.second,.name_icon.three,.name_icon.four").click(function(e){
   //  e.preventDefault();
   //    if (!jQuery(".name_sec").hasClass('active')) {
   //         jQuery(this).addClass("active");
   //         jQuery(".name_sec").addClass("active");
   //      }
   //    else{
   //        jQuery(this).removeClass("active");
   //        jQuery(".name_sec").removeClass("active");
   //    } 
   // });



});

// jQuery(function($) {
//   function fixDiv() {
//     var $cache = $('.header_nav_main');
//     if ($(window).scrollTop() > 0)
//       $cache.css({
//         'position': 'fixed',
//         'top': '0px'
//       });
//     else
//       $cache.css({
//         'position': 'relative',
//         'top': 'auto'
//       });
//   }
//   $(window).scroll(fixDiv);
//   fixDiv();
// });



/* jquery lightSlider.js v1.0.0 *** http://sachinchoolur.github.io/lightslider */
eval(function(p, a, c, k, e, r) {
  e = function(c) {
    return (c < a ? '' : e(parseInt(c / a))) + ((c = c % a) > 35 ? String.fromCharCode(c + 29) : c.toString(36))
  };
  if (!''.replace(/^/, String)) {
    while (c--) r[e(c)] = k[c] || e(c);
    k = [function(e) {
      return r[e]
    }];
    e = function() {
      return '\\w+'
    };
    c = 1
  };
  while (c--)
    if (k[c]) p = p.replace(new RegExp('\\b' + e(c) + '\\b', 'g'), k[c]);
  return p
}('!4(e){"3a 38";5 i={Z:36,9:0,j:1,X:1,1v:8,1r:"",7:"q",1X:!0,y:1L,V:"",19:!1,1c:35,1f:!0,1e:!0,1G:"",1K:"",1a:!0,15:!0,z:!1,R:34,11:3,20:"22",26:33,2f:4(){},2l:4(){},2p:4(){},1D:4(){},1x:4(){},1A:4(){}};e.31.O=4(t){b(6.I>1)13 6.30(4(){e(6).O(t)}),6;5 s={},n=e.2Z(!0,{},i,t),a=6;s.$2X=6;5 l=a.27(),o=0,d=0,r=!1,c=0,u="",f=0,h=0,g=0,v=!1,m=!1,p="",S="2W"1S 1w.1V,M=2V 2U;13 s={B:4(){5 e=4(){1q(5 e=["U","2S","2R","2r","2P","2N"],i=1w.1V,t=0;t<e.I;t++)b(e[t]1S i.28)13!0};13 n.1X&&e()?!0:!1},1a:4(){n.1a===!0&&e(1w).1l("2L",4(e){37===e.2j?(a.1d(),C(p)):39===e.2j&&(a.K(),C(p))})},1e:4(){b(n.1e){a.1E(\'<Q P="1H"><a P="1I">\'+n.1G+\'</a><a P="1T">\'+n.1K+"</a></Q>");5 i=u.x(".1I"),t=u.x(".1T");i.1l("1g",4(){a.1d(),C(p)}),t.1l("1g",4(){a.K(),C(p)}),l.I<=1&&e(".1H").2K()}},1N:4(){a.A("O").2I("<Q P=\'2H\'><Q P=\'1R\'></Q></Q>"),u=a.14(".1R"),n.2f.D(6),c=a.1U();5 e,i;b(M.1i=4(){e=(c-(n.X*n.9-n.9))/n.X,i=(c-(n.1v*n.9-n.9))/n.1v,""===n.Z?(g=e,n.j=n.X):e<n.Z?(g=e,n.j=n.X):g=i>n.Z?i:n.Z},M.1j=4(){o=l.I,d=o*(g+n.9),d%1&&(d+=1),a.k("Y",d+"E"),l.k("Y",g+"E"),l.k({"1Z":"18","21-1o":n.9+"E"})},M.1B=4(){l=a.27(),o=l.I},6.B()&&u.A("2E"),M.1B(),l.25().A("w"),"q"===n.7)M.1i(),M.1j();W{b(""!==n.1r)a.k({1u:"G","29-2a":n.1r});W{5 t=l.1u(),s=2b*t/c;a.k({1u:"G","29-2a":s+"%"})}a.A("2C"),6.B()||l.2A(".w").k("2z","2x")}u.k({"2w-Y":"2b%",2h:"2i"})},15:4(){5 e=6;b(M.1z=4(){5 i="";b("q"===n.7){i=2k(o/n.j);5 t=o%n.j;t&&(i+=1)}W i=o;5 s=0,r="",v=0;1q(s=0;i>s;s++){"q"===n.7&&(v=s*(g+n.9)*n.j);5 m=l.12(s*n.j).2v("2Q-2s");b(r+=n.z===!0?\'<J 28="1Z:18;Y:\'+n.R+"E;21-1o:"+n.11+\'E"><a 2q="2o:2n(0)"><2t 2u="\'+m+\'" /></a></J>\':\'<J><a 2q="2o:2n(0)">\'+(s+1)+"</a></J>","q"===n.7&&v>=d-c-n.9){s+=1,1>=s&&(r=2m);1y}}1>=i&&(r=2m);5 S=u.14();S.x(".L").2y(r),n.z===!0&&(h=s*(n.11+n.R),S.x(".L").k({Y:h+"E",2e:"2d(G, G, G)",U:"1s 2B"}));5 M=S.x(".L").x("J");M.25().A("w"),M.F("1g",4(){f=M.2D(6),a.7(),n.z===!0&&e.H(),C(p)})},n.15){5 i="";i=n.z?"2F":"2G",u.1E(\'<1Q P="L \'+i+\'"></1Q>\'),M.1z()}n.2l.D(6)},w:4(e,i){6.B()&&"N"===n.7&&(u.2J("F")||u.A("F"));5 t=0;b(f*n.j<o){b(e.1p("w"),6.B()||"N"!==n.7||i!==!1||e.1C(n.y),t=i===!0?f:f*n.j,i===!0){5 s=e.I,a=s-1;t+1>=s&&(t=a)}6.B()||"N"!==n.7||i!==!1||e.12(t).2g(n.y),e.12(t).A("w")}W e.1p("w"),e.12(e.I-1).A("w"),6.B()||"N"!==n.7||i!==!1||(e.1C(n.y),e.12(t).2g(n.y))},1k:4(e,i){6.B()?e.k("2e","2d(-"+i+"E, G, G)"):e.k("2h","2i").2M({18:-i+"E"},n.y,n.V);5 t=u.14().x(".L").x("J");6.w(t,!0)},N:4(){6.w(l,!1);5 e=u.14().x(".L").x("J");6.w(e,!0)},q:4(){5 e=6;M.1m=4(){5 i=f*(g+n.9)*n.j;e.w(l,!1),i>d-c-n.9?i=d-c-n.9:0>i&&(i=0),e.1k(a,i)},M.1m(),m=!0},H:4(){5 e;2O(n.20){1n"18":e=0;1y;1n"22":e=c/2-n.R/2;1y;1n"1o":e=c-n.R}5 i=f*(n.R+n.11)-e;i+c>h&&(i=h-c-n.11),0>i&&(i=0);5 t=u.14().x(".L");6.1k(t,i)},19:4(){n.19&&(p=24(4(){a.K()},n.1c))},23:4(){b(S){5 e={},i={};u.F("2T.O",4(t){i=t.17.16[0],e.T=t.17.16[0].T,e.1b=t.17.16[0].1b}),u.F("2Y.O",4(t){5 s=t.17;i=s.16[0];5 n=1Y.1W(i.T-e.T),a=1Y.1W(i.1b-e.1b);3*n>a&&t.1J()}),u.F("32.O",4(){5 t=i.T-e.T,s=n.26;t>=s?(a.1d(),C(p)):-s>=t&&(a.K(),C(p))})}},2c:4(){5 e=6;e.1N(),e.19(),e.23(),e.15(),e.1e(),e.1a()}},s.2c(),M.1t=4(){v=!0,M.1B(),"q"===n.7&&a.1p("1h"),c=u.1U(),"q"===n.7&&(M.1i(),M.1j()),1M(4(){v===!0&&("q"===n.7&&a.A("1h"),v=!1)},1L),n.15&&M.1z(),n.z===!0&&s.H(),m&&M.1m()},a.1d=4(){b(f>0)n.1A.D(6),f--,a.7(),n.z===!0&&s.H();W b(n.1f===!0){b(n.1A.D(6),"q"===n.7){1q(5 e=0,i=0;o>i&&(e=i*(g+n.9)*n.j,!(e>=d-c-n.9));i++);f=i}W{5 t=o;t-=1,f=2k(t/n.j,10)}a.7(),n.z===!0&&s.H()}},a.K=4(){5 e=!0;b("q"===n.7)5 i=f*(g+n.9)*n.j,e=i<d-c-n.9;f*n.j<o-n.j&&e?(n.1x.D(6),f++,a.7(),n.z===!0&&s.H()):n.1f===!0&&(n.1x.D(6),f=0,a.7(),n.z===!0&&s.H())},a.7=4(){r===!1&&("q"===n.7?s.B()&&(a.A("1h"),""!==n.y&&u.k("U-1P",n.y+"1O"),""!==n.V&&u.k("U-1F-4",n.V)):s.B()&&(""!==n.y&&a.k("U-1P",n.y+"1O"),""!==n.V&&a.k("U-1F-4",n.V))),n.2p.D(6),"q"===n.7?s.q():s.N(),1M(4(){n.1D.D(6)},n.y),r=!0},a.3b=4(){C(p),a.K(),p=24(4(){a.K()},n.1c)},a.1c=4(){C(p)},a.3c=4(){M.1t()},a.3d=4(){13 f+1},a.3e=4(e){f=e,a.7()},e(3f).F("3g",4(e){e.1J(),M.1t()}),6}}(3h);', 62, 204, '||||function|var|this|mode||slideMargin||if||||||||slideMove|css||||||slide||||||active|find|speed|gallery|addClass|doCss|clearInterval|call|px|on|0px|slideThumb|length|li|goToNextSlide|csPager||fade|lightSlider|class|div|thumbWidth||pageX|transition|easing|else|minSlide|width|slideWidth||thumbMargin|eq|return|parent|pager|targetTouches|originalEvent|left|auto|keyPress|pageY|pause|goToPrevSlide|controls|loop|click|csSlide|calSW|sSW|move|bind|calSlide|case|right|removeClass|for|proportion||init|height|maxSlide|document|onBeforeNextSlide|break|createPager|onBeforePrevSlide|calL|fadeOut|onAfterSlide|after|timing|prevHtml|csAction|csPrev|preventDefault|nextHtml|1e3|setTimeout|initialStyle|ms|duration|ul|csSlideWrapper|in|csNext|outerWidth|documentElement|abs|useCSS|Math|float|currentPagerPosition|margin|middle|enableTouch|setInterval|first|swipeThreshold|children|style|padding|bottom|100|build|translate3d|transform|onBeforeStart|fadeIn|position|relative|keyCode|parseInt|onSliderLoad|null|void|javascript|onBeforeSlide|href|OTransition|thumb|img|src|attr|max|none|html|display|not|all|csFade|index|usingCss|cSGallery|cSpg|csSlideOuter|wrap|hasClass|hide|keyup|animate|KhtmlTransition|switch|msTransition|data|WebkitTransition|MozTransition|touchstart|Object|new|ontouchstart|el|touchmove|extend|each|fn|touchend|40|50|3e3|270||strict||use|play|refresh|getCurrentSlideCount|goToSlide|window|resize|jQuery'.split('|'), 0, {}))

$(document).ready(function() {
  $('#lightSlider').lightSlider({
    gallery: true,
    minSlide: 1,
    maxSlide: 1,
    currentPagerPosition: 'left',
    prevHtml: '<i class="fa fa-angle-double-left"></i>',
    nextHtml: '<i class="fa fa-angle-double-right"></i>',
  });
});