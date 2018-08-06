require(
		[ 'model/common/restcalls', 'model/common/zevents',
				'model/common/viewLoader', 'jquery.validation',
				'jquery.en.validation' ],
		function(restcalls, zevents, view) {
			can.view.cache = false;

			var SearchControl = can.Control
					.extend({
						'#searchHotels click' : function() {

							$("div#errMsg").html("");
							$('.sort_btn').removeClass("active");
							$(".sort_sec_slide,.bg_overlay").removeClass(
									"active");
							if (!($('#city').validationEngine('validate'))
									|| !($('#dpd1')
											.validationEngine('validate'))
									|| !($('#dpd2')
											.validationEngine('validate'))) {
								return;
							}
							$(".overlay").show();
							searchHotels();
						},
						".irs-single click" : function() {

						},
						".sort_,.common_title,.cd-tabs-navigation click" : function() {
							$('.sort_btn').removeClass("active");
							$(".sort_sec_slide,.bg_overlay").removeClass(
									"active");
						},
						"#dpd1,#dpd2,#city click" : function() {
							$('.sort_btn').removeClass("active");
							$(".sort_sec_slide,.bg_overlay").removeClass(
									"active");
						},
						".image_view .camera click" : function(e) {
							if (!$(".main").hasClass('flip')) {
								$(this).addClass("flip");
								$(".main").addClass("flip");
							} else {
								$(this).removeClass("flip");
								$(".main").removeClass("flip");
							}
							e.preventDefault();
						},
						'.sort_btn click' : function() {
							if (!$(".sort_sec_slide,.bg_overlay").hasClass(
									'active')) {
								$('.sort_btn').addClass("active");
								$(".sort_sec_slide,.bg_overlay").addClass(
										"active");
							} else {
								$('.sort_btn').removeClass("active");
								$(".sort_sec_slide,.bg_overlay").removeClass(
										"active");
							}
							$(".bg_overlay").click(
									function() {
										$(".bg_overlay,.sort_sec_slide")
												.removeClass("active");
									});

							rangeSlider();
						},
						".summery_btn click" : function(e) {

							if (!$(
									".view_summery_main,.cd-tabs,.bg_overlay_full")
									.hasClass('active')) {
								$('.summery_btn').addClass("active");
								$(
										".view_summery_main,.cd-tabs,.bg_overlay_full")
										.addClass("active");
								$('.accordion > li:eq(0) .tab_click').addClass(
										'active').next().slideDown();
							} else {
								$('.summery_btn').removeClass("active");
								$(
										".view_summery_main,.cd-tabs,.bg_overlay_full")
										.removeClass("active");
							}
						},
						".summery_close,.bg_overlay_full click" : function() {
							$(".view_summery_main,.cd-tabs,.bg_overlay_full")
									.removeClass("active");
						},
						'.Switch click' : function() {
							$('.Switch').toggleClass('On').toggleClass('Off');
						},
						'.accordion .tab_click click' : function(el, ev) {
							// $('.accordion > li:eq(0)
							// .tab_click').addClass('active').next().slideDown();
							var dropDown = $(el).closest('li').find(
									'.accordion_box');
							$(el).closest('.accordion').find('.accordion_box')
									.not(dropDown).slideUp();

							if ($(el).hasClass('active')) {
								$(el).removeClass('active');
							} else {
								$(el).closest('.accordion').find(
										'.tab_click.active').removeClass(
										'active');
								$(el).addClass('active');
							}

							dropDown.stop(false, true).slideToggle();

							ev.preventDefault();
						},
						"#sort_price click" : function() {
							console.log("sort_price");
							var sortArray = searchResults.zHotelList;
							sortArray.reverse(function(a, b) {
								return parseFloat(a.bestPrice) - parseFloat(b.bestPrice);
							});
							displaySortResult(sortArray);
						},
						'#hotelList li click' : function(li, event) {
							$('.cd-tabs').addClass('hotel_detail');
							$(this).removeClass("flip");
							$(".main").removeClass("flip");
							$("#loadMore").hide();
							$('#view_summary').hide();
							var itemCode = li.text();
							itemCode = /, (.+)/.exec(itemCode)[1];
							var hotelDetail = {};
							hotelDetail.hotelCode = city_code + "_" + itemCode;
							restcalls.zpost("/hotel/getHotelDetails",
									hotelDetail, displayHotelDetails,
									function() {
										$(".overlay").hide();
									})
						},
						"#hotel_Details click" : function(li, event) {
							var itemCode = li.attr('class');
							var hotelDetail = {};
							hotelDetail.hotelCode = city_code + "_" + itemCode;
							restcalls.zpost("/hotel/getHotelDetails",
									hotelDetail, displayAddhotel, function() {
										$(".overlay").hide();
									})
							event.stopPropagation();
						},
						'.close_btn_hotel click' : function(e) {
							view.load('#altplannerdiv', 'hotel/bookHotel.ejs');
							$('.cd-tabs').removeClass('hotel_detail');
							// $("#loadMore").show();
							loadMoreResults();
							$('#view_summary').show();
							SelectDates();
							$('#plannerdiv').toggle();
							$('#altplannerdiv').toggle();
							e.stopPropagation();
						},
						"#backToSearch click" : function() {
							view.load('#altplannerdiv', 'hotel/bookHotel.ejs');
							$('.cd-tabs').removeClass('hotel_detail');
							$('#view_summary').show();
							// $("#loadMore").show();
							loadMoreResults();
							SelectDates();
							$('#plannerdiv').toggle();
							$('#altplannerdiv').toggle();
							e.stopPropagation();
						},
						"#view_selection click":function(){
							alert("view Selection");
							$('.cd-tabs').removeClass('hotel_detail');
							view.load('#altplannerdiv', 'hotel/view_accomdation_details.ejs');
						},
						'#hotel_accomadation_summary click' : function() {
							$(".view_summery_main,.cd-tabs,.bg_overlay_full")
									.removeClass("active");
							console.log('Hotel Accomdation');
							hotel_accomadation_summary();
						},
						"#cancel_accomdation click" : function() {
							view.load('.booking_steps', 'hotel/cancel.ejs');
						},
						'#loadMore click' : function() {
							// alert(" X===>"+size_li);
							x = (x + 10 <= size_li) ? x + 10 : size_li;
							$('#hotelList li:lt(' + x + ')').show();
							if (size_li == x) {
								// $("#loadMore").hide();
								$("#loadMore").html('No More Results');

							} else {
								loadMoreResults();
							}
						},
						/*
						 * '#showLess click' :function () { var x=5; x=(x-5<0) ?
						 * 3 : x-5; $('#hotelList li').not(':lt('+x+')').hide(); },
						 */
						"#search_location keyup" : function() {
							$('.cityformError').hide();
							$("div#errMsg").html("");
							//alert("city value changed");
							//console.log('in city value change');
							// Do not do anything as long as we do not have at
							// least three characters in the city input
							 /*if ($('#search_location').val().trim().length <= 2) {
								 alert("2 characters only");
								 return;
							}*/

							var terms;
							$('input#search_location')
									.autocomplete(
											{
												max : 10,
												autoFocus : true,
												source : function(request,
														response) {
													var term = request.term
													var DestinationCode = {};
													DestinationCode.destinationMatchChars = term;
													restcalls
															.zpost(
																	'/hotel/getDestinationsAndHotelsMatchingChars',
																	DestinationCode,
																	function(
																			results) {
																		var optionsList = [];
																		var destinations = results['zCitySearchResults'];
																		if (destinations != null) {
																			for (var i = 0; i < destinations.length; i++) {
																				var destination = destinations[i];
																				optionsList
																						.push(destination.cityName
																								+ ','
																								+ destination.cityCode);
																			}
																		}
																		hotels = results['zHotelSearchList'];
																		if (hotels != null) {
																			/*
																			 * console.log("Not
																			 * null");
																			 * for
																			 * (var
																			 * i =
																			 * 0; i <
																			 * hotels.length;
																			 * i++) {
																			 * var
																			 * hotel =
																			 * hotels[i];
																			 * console.log("Not
																			 * null"+hotel.name);
																			 * optionsList.push(hotel.name +
																			 * ',' +
																			 * hotel.cityCode); }
																			 */
																			for (var j = 0; j < hotels.length; j++) {
																				var hotel = hotels[j];
																				for (var k = 0; k < destinations.length; k++) {
																					var destination = destinations[k];
																					optionsList
																							.push(destination.cityName
																									+ ','
																									+ destination.cityCode
																									+ ","
																									+ hotel.name);
																				}
																			}
																		}
																		locations = results['zHotelLocationCodelist'];
																		if (locations != null) {
																			for (var i = 0; i < locations.length; i++) {
																				var location = locations[i];
																				for (var j = 0; j < destinations.length; j++) {
																					var destination = destinations[j];
																					optionsList
																							.push(destination.cityName
																									+ ','
																									+ destination.cityCode
																									+ ","
																									+ location.descriptionEnglish);
																				}
																			}
																		}
																		response(optionsList);
																	});
												},
												select : function(event, ui) {
													terms = split(this.value);
												},
												minLength : 3
											});

							function split(val) {
								return val.split(/,s*/);
							}

							function extractLast(term) {
								return split(term).pop();
							}
						},
						'.room_qtyplus click' : function(e) {
							var currentVal = parseInt($(
									'input[name="room_quantity"]').val());
							if (!isNaN(currentVal)) {
								$('input[name="room_quantity"]').val(
										currentVal + 1);
							} else {
								$('input[name=' + fieldName + ']').val(1);
							}
							e.preventDefault();
						},
						".room_qtyminus click" : function(e) {
							var currentVal = parseInt($(
									'input[name="room_quantity"]').val());
							if (!isNaN(currentVal) && currentVal > 1) {
								$('input[name="room_quantity"]').val(
										currentVal - 1);
							} else {
								$('input[name=' + fieldName + ']').val(1);
							}
							e.preventDefault();
						},
						'.child_qtyplus click' : function(e) {
							var currentVa = parseInt($(
									'input[name="child_quantity"]').val());
							if (!isNaN(currentVa)) {
								$('input[name="child_quantity"]').val(
										currentVa + 1);
							} else {
								$('input[name=' + fieldName + ']').val(0);
							}
							e.preventDefault();
						},
						".child_qtyminus click" : function(e) {
							var currentVa = parseInt($(
									'input[name="child_quantity"]').val());
							if (!isNaN(currentVa) && currentVa > 0) {
								$('input[name="child_quantity"]').val(
										currentVa - 1);
							} else {
								$('input[name=' + fieldName + ']').val(0);
							}
							e.preventDefault();
						},
						'.qtyplus click' : function(e) {
							fieldName = $(this).attr('field');
							var currentVal = parseInt($(
									'input[name="quantity"]').val());
							if (!isNaN(currentVal)) {
								$('input[name="quantity"]').val(currentVal + 1);
							} else {
								$('input[name=' + fieldName + ']').val(0);
							}
							e.preventDefault();
						},
						".qtyminus click" : function(e) {
							fieldName = $(this).attr('field');
							var currentVal = parseInt($(
									'input[name="quantity"]').val());
							if (!isNaN(currentVal) && currentVal > 1) {
								$('input[name="quantity"]').val(currentVal - 1);
							} else {
								$('input[name=' + fieldName + ']').val(1);
							}
							e.preventDefault();
						},
						"input#lead_name keyup" : function() {
							$("div#errMsg1").html("");
							$("input#lead_name").css("border-color", "#e1e1e1");
						},
						"#book_hotels click" : function() {
							$("div#errMsg2").html("");
							if ((checkbox_checked() == true)) {
								var lead_name = $("input#lead_name").val();
								if (lead_name != "") {
									var noOfrooms = $(".no_of_rooms :selected")
											.val();
									if (noOfRooms == noOfrooms) {
										$(".overlay").show();
										// $(".inside_page_container").show();
										$(".left_60_width").css("overflow-y",
												"hidden");
										$('#backToSearch').attr('disabled',
												"disabled");
										var itemCode = $(".hotelcode").attr(
												'id');
										bookHotels = {};
										var categoryid = favorite;
										$
												.each(
														searchResults,
														function(el, hotels) {
															if (el == "zHotelList") {
																$
																		.each(
																				hotels,
																				function(
																						lis,
																						ev) {
																					if (ev.itemCode == itemCode) {
																						var room_category = ev.roomCategory;
																						$
																								.each(
																										room_category,
																										function(
																												rom,
																												sub_room_category) {
																											if (sub_room_category.categoryId == categoryid[0]) {
																												// var
																												// guest_type
																												// =
																												// $("#guest_type
																												// :selected").val();
																												var guests = [];
																												var guest = {};
																												guest.guestName = $(
																														"#lead_name")
																														.val();
																												// guest.guestType
																												// =
																												// guest_type;
																												guest.guestType = "ADULT";
																												guest.leadGuest = true;
																												guests
																														.push(guest);
																												bookHotels.itemCode = itemCode;
																												bookHotels.cityCode = ev.cityCode;
																												bookHotels.roomGuests = guests;
																												bookHotels.hotelRoomCategoryId = categoryid[0];
																												/*
																												 * var
																												 * noofAdults =
																												 * $("#no_adults
																												 * :selected").val();
																												 * var
																												 * noofRooms =
																												 * $("#no_rooms
																												 * :selected").val();
																												 */
																												bookHotels.numAdults = noOfAdults;
																												bookHotels.noOfRooms = noOfRooms;
																												bookHotels.hotelRoomCode = sub_room_category.hotelRoomCode;// selected_roomtype;
																												bookHotels.numExtraBed = $(
																														"#extra_bed")
																														.val();
																												// bookHotels.numCots
																												// =
																												// $("#extra_cots").val();
																												bookHotels.checkInDate = inDate;
																												bookHotels.checkOutDate = outDate;
																												bookHotels.primaryCustomerName = $(
																														"#lead_name")
																														.val();
																												bookHotels.expectedPrice = sub_room_category.zillionPriceDetails.value;
																											} else {
																												console
																														.log("category is not selected");
																											}
																										})
																						restcalls
																								.zpost(
																										'/hotel/bookHotel',
																										bookHotels,
																										displayBooking,
																										function() {
																											// $("#loading").css("display","none");
																											$(
																													".left_60_width")
																													.css(
																															"overflow-y",
																															"none");
																											// $(".red_btn_arrow").css("display","none");
																											$(
																													'.red_btn_arrow')
																													.attr(
																															'disabled',
																															"enabled");
																											$(
																													".overlay")
																													.hide();
																										})
																					}
																				});
															}
														});
									} else {
										alert("please select no of room");
									}
								} else {

									$("input#lead_name").css("border-color",
											"red");
									$("div#errMsg1").html(
											"Please enter Lead Guest Name!!");
								}
							}

						},
						'.selected_cat li click' : function(li, event) {
							var id = li.attr('id');
							console.log("id- --->" + id);

							/*
							 * if ($('.radio-custom').is(":checked")) {
							 * $(".no_of_rooms").prop("disabled", true); } else {
							 * $(".no_of_rooms").prop("disabled", false); }
							 */
						},
					});
			var favorite = [];
			var checkbox_checked = function() {
				favorite = [];
				$.each($("input[name='radio-group']:checked"), function() {
					favorite.push($(this).val());
				});
				if (favorite != "") {
					console.log("asdasdas===> " + favorite);
					return true;
				} else {
					$("div#errMsg2").html("please select room Category");
					return false;
				}
			}
			/*
			 * var lead_guest = []; var lead_guest_checked = function(){
			 * $.each($("input[name='radio-group49']:checked"), function(){
			 * lead_guest.push($(this).val()); }); if(lead_guest != ""){ return
			 * true; }else{ $("input#lead_guest").css("border-color","red");
			 * $("li#errMsg1").html("please select Lead guest"); return false; } }
			 */
			var displayBooking = function(bookhotels) {
				// $("#loading").css("display","none");
				$(".left_60_width").css("overflow-y", "scroll");
				$(".overlay").hide();
				if (bookhotels.bookingStatus != false) {
					alert("Booking Status : "
							+ bookhotels.bookingStatusValue
							+ " \n Booking Reference id : "
							+ bookhotels.hotelBookingItems[0].bookingConfirmationReference);
				} else {
					alert("Booking Status Failed " + bookhotels.bookingStatus);
				}

			}
			var displayAddhotel = function(hotelDetails) {
				alert("Adding hotel for book");
				$.each(hotelDetails, function(key, val) {
					$.each(searchResults, function(el, hotels) {
						$.each(hotels, function(l, addhotel) {
							if (addhotel.itemCode == val.itemCode) {
								$('#addToSummery').empty();
								view.append('#addToSummery',
										'hotel/addHotel.ejs', {
											addhotel : addhotel
										});
							}
						});
					});
				})
			}
			var hotel_accomadation_summary = function() {
				var ItineraryId = {};
				// ItineraryId.iternaryId = 'ABCD';
				// ItineraryId.customerId = '1231';
				// TODO get itinerary accomodation details from server
				// restcalls.zpost('/hotel/getItineraryAccomodationDetails',
				// ItineraryId, displayIternaryAccomodation)
			}
			var displayIternaryAccomodation = function(
					iternaryAccomodationResponse) {
				// TODO customer own accomodation management
				view
						.load(
								'.booking_steps',
								'hotel/view_accomdation_details.ejs',
								{
									iternaryAccomodationResponse : iternaryAccomodationResponse
								});
				for (var i = 0; i < iternaryAccomodationResponse.length; i++) {
					if (iternaryAccomodationResponse[i].zillionGTAHotels.length != 1
							|| iternaryAccomodationResponse[i].zillionGTAHotels.ownAccomodation == false
							|| iternaryAccomodationResponse[i].zillionGTAHotels.ownAccomodation == true) {
						$
								.each(
										iternaryAccomodationResponse[i].zillionGTAHotels,
										function(k, v) { // The contents
															// inside stars
											if (v.ownAccomodation == false) {
												view
														.append(
																'#accomodation',
																'hotel/accomodation.ejs',
																{
																	accom : iternaryAccomodationResponse[i]
																});
												console
														.log(iternaryAccomodationResponse[i].zillionGTAHotels.length);
											} else {
												view
														.append(
																'#accomodation',
																'hotel/ownAccomodation.ejs',
																{
																	accom : iternaryAccomodationResponse[i]
																});
												console
														.log(iternaryAccomodationResponse[i].zillionGTAHotels.length);
											}
										})
					} else {

						view.append('#accomodation', 'hotel/accomodation.ejs',
								{
									accom : iternaryAccomodationResponse[i]
								});
						console
								.log(iternaryAccomodationResponse[i].zillionGTAHotels.length);
					}
				}
			}

			var displayHotelDetails = function(val) {
				$('#plannerdiv').toggle();
				$('#altplannerdiv').toggle();
				$('#altplannerdiv').empty();
				$(".overlay").hide();
				$
						.each(
								val,
								function(key, hotelDetails) {
									$
											.each(
													searchResults,
													function(el, hotels) {
														$
																.each(
																		hotels,
																		function(
																				l,
																				ev) {
																			if (ev.itemCode == hotelDetails.itemCode) {
																				view
																						.append(
																								'#altplannerdiv',
																								'hotel/hotelDetails.ejs',
																								{
																									hotelDetails : hotelDetails
																								});
																				$(
																						".overlay")
																						.hide();
																				var room_category = ev.roomCategory;
																				for (var i = 0; i < room_category.length; i++) {
																					// $(".no_of_rooms").val(noOfRooms);
																					view
																							.append(
																									'#room_categroy',
																									'hotel/room_category.ejs',
																									{
																										sub_room_category : room_category[i]
																									});
																				}
																				// addList();
																				SelectDates();
																			}
																		});
													});
								});

			}
			/*
			 * var addList = function(){ var select = $(".no_of_rooms"); var n =
			 * noOfRooms; console.log(n+" No of rooms"); for(var i = n; i >= 0;
			 * --i) { console.log(i+" No of rooms iiii"); var option =
			 * document.createElement('option'); option.text = option.value = i;
			 * console.log(option.text+" option.text"); select.add(option, 0); } }
			 */

			var displayFacilityResult = function(hotelFacilityCodes) {
				console.log("Result: " + JSON.stringfy(hotelFacilityCodes));
			}
			var Router = can.Control.extend({
				init : function() {
					// var FacilityCode = {};
					// FacilityCode.langauge = 'en';
					// restcalls.zpost('/hotel/facilityCodes', FacilityCode,
					// displayFacilityResult)
				},
				'searchhotels route' : function() {
					$("#cruise").removeClass("selected");
					$("#attraction").removeClass("selected");
					$("#hotel").addClass("selected");
					// $("#loading").css("display","none");
					$(".overlay").hide();
					$("#itinerary").removeClass("selected");
					$("#loadMore").hide();
					view.load('#plannerdiv', 'hotel/bookHotel.ejs');
					SelectDates();
				}
			});
			var SelectDates = function() {
				/*
				 * var dates = $('.date-picker-widget').datepicker({ dateFormat:
				 * 'yy-mm-dd', minDate: 0, maxDate: "+2Y", numberOfMonths: 2,
				 * onSelect: function(date) { $(this).change(); for(var i = 0; i <
				 * dates.length; ++i) { if(dates[i].id < this.id)
				 * $(dates[i]).datepicker('option', 'maxDate', date); else
				 * if(dates[i].id > this.id) console.log(" dates[i].id
				 * "+dates[i].id +" this.id " +this.id);
				 * $(dates[i]).datepicker('option', 'minDate', date); }
				 * $(".dpd1formError").hide(); $(".dpd2formError").hide(); }
				 * 
				 * });
				 */
				/*
				 * $( "#dpd1" ).datepicker({ minDate: 0, dateFormat:
				 * "dd/mm/yy",//yy-mm-dd numberOfMonths: 2, onSelect: function(
				 * selectedDate, inst ) { var minDate = new
				 * Date(Date.parse(selectedDate));
				 * minDate.setDate(maxDate.getDate() + 1); $( "#dpd2"
				 * ).datepicker( "option", "minDate", minDate); } }); //
				 * $('#dpd2')[0].focus(); $( "#dpd2" ).datepicker({ minDate:
				 * "+1D", dateFormat: "dd/mm/yy",//yy-mm-dd numberOfMonths: 2,
				 * onSelect: function( selectedDate, inst ) { var maxDate = new
				 * Date(Date.parse(selectedDate));
				 * maxDate.setDate(maxDate.getDate() - 1); $( "#dpd1"
				 * ).datepicker( "option", "maxDate", maxDate); } });
				 */
				$.datepicker.setDefaults($.datepicker.regional['en']);
				$('#dpd1').datepicker({
					dateFormat : 'dd/mm/yy',
					minDate : 0,
					maxDate : "+2Y",
					numberOfMonths : 2,
					onSelect : function(selectedDate) {
						var date = $(this).datepicker('getDate');
						$('#dpd2').datepicker('option', 'minDate', date); // Reset
																			// minimum
																			// date
						date.setDate(date.getDate() + 1); // Add 7 days
						$('#dpd2').datepicker('setDate', date); // Set as
																// default
					}
				});
				$('#dpd2').datepicker(
						{
							dateFormat : 'dd/mm/yy',
							minDate : "1D",
							maxDate : "+2Y",
							numberOfMonths : 2,
							onSelect : function(selectedDate) {
								$('#dpd1').datepicker('option', 'maxDate',
										$(this).datepicker('getDate')); // Reset
																		// maximum
																		// date
							}
						});
				$(".dpd1formError").hide();
				$(".dpd2formError").hide();
			}
			var noOfAdults = "";
			var noOfRooms = "";
			var noOfChildren = "";
			var selected_roomtype = "";
			var Search = function() {
				noOfAdults = $("#adults").val();
				noOfRooms = $("#noOfRums").val();
				noOfChildren = $("#childs").val();
				selected_roomtype = "SB";// $('#room_type :selected').val();

				if (noOfAdults < 1) {
					return false;
				}
				if (noOfRooms > 0) {
					numOfRoomsRequired = noOfRooms;
				} else {
					return false;
				}
				console.log("inside noOfRooms " + noOfRooms);
				var roomCapacityForAdults = 0;
				if (selected_roomtype === "DB" || selected_roomtype === "TB") {
					roomCapacityForAdults = noOfRooms * 2;
					console.log("Persons roomCapacityForAdults "
							+ roomCapacityForAdults + " Selected roomtype==> "
							+ selected_roomtype);
					if (noOfAdults > roomCapacityForAdults) {
						alert("Room Capacity " + roomCapacityForAdults
								+ " and selected adults are exceeds "
								+ noOfAdults);
						return false;
					}

				}
				var totalCapacityWithChildren = 0;
				if ((noOfChildren > 0)) { // (noOfInfants > 0) ||
					console.log(" no of childrens " + noOfChildren);
					// In single bed room children/infants not allowed
					if (selected_roomtype === "SB") {
						roomCapacityForAdults = noOfRooms * 1;
						alert("childrens are not allowed to stay in single room ");
						return false;

					}
					// As 2 extra beds or cots can be placed in DB or TB type
					// rooms
					totalCapacityWithChildren = roomCapacityForAdults * 2;
					var totalGuests = parseInt(noOfChildren)
							+ parseInt(noOfAdults);// parseInt(noOfInfants) +
					console.log(totalCapacityWithChildren
							+ " totalCapacityWithChildren" + " totalGuests "
							+ totalGuests);
					if (totalGuests > totalCapacityWithChildren)
						// alert("total Capacity With Children "+
						// totalCapacityWithChildren+ " and totalGuests " +
						// totalGuests+ " Please add more rooms");
						return false;

					numOfChildren = noOfChildren;
					// numOfInfants = noOfInfants;

				}
				return true;
			}

			/* checked radio button facility codes into array */
			var facility = [];
			var facilityCodesRequested = {};
			var facility_checkbox = function() {
				facility = [];
				$.each($("input[name='radio-group1']:checked"), function() {
					facilityCodesRequested.facilityCode = $(this).val();
					facility.push($(this).val());
				});

				if (facility != "") {
					return true;
				} else {
					return false;
				}
			}

			var minStarRate = "";// {};
			var minStarRating_checkbox = function() {
				minStarRate = "";
				$.each($("input[name='radio-group']:checked"), function() {
					minStarRate = $(this).val();
				});

				if (minStarRate != "") {
					return true;
				} else {
					return false;
				}
			}

			var maxBudget = "";// {};
			var maxBudget_check = function() {
				maxbudget = $('.irs-single').text();
				if (maxbudget != "") {
					maxbudget = (maxbudget.split('$'))[2];
					maxBudget1 = maxbudget.replace(/\s/g, '');
					maxBudget = parseInt(maxBudget1);
					console.log(maxbudget + " maxbudget1 " + maxBudget1
							+ " maxbudget2 " + maxBudget);
					return true;
				} else {
					return false;
				}

			}

			function formatDate(date) {
				var ddate = date.split("/").reverse().join("-");
				return ddate;
			}
			var city_code = "";
			var inDate = "";
			var outDate = "";
			var locations = '';
			var hotels = '';
			var searchHotels = function() {
				// $("#loading").css("display","block");
				var item_code = null;
				var location_code = null;
				city_code = ($("#city").val().split(','))[1];
				location_Name = ($("#city").val().split(','))[2];
				console.log("city_code " + city_code + " location_code "
						+ location_Name + " item_code " + item_code);
				for (var i = 0; i < locations.length; i++) {
					var location = locations[i];
					if (location_Name == location.descriptionEnglish)
						location_code = location.code;
				}
				item_name = ($("#city").val().split(','))[2];
				for (var j = 0; j < hotels.length; j++) {
					var hotel = hotels[j];
					if (item_name == hotel.name)
						item_code = hotel.itemCode;
				}
				var checkInDate = $('#dpd1').val();
				var checkOutDate = $('#dpd2').val();
				inDate = formatDate(checkInDate.toString());
				outDate = formatDate(checkOutDate.toString());
				var hotel = {};
				var search = Search();
				if (search == false) {
					console.log("Please check combinations");
					$(".overlay").hide();
				} else {

					hotel.cityAirportCode = city_code;
					if (location_code == "") {
						hotel.locationCode = null;
					} else {

						hotel.locationCode = location_code;
					}

					hotel.itemCode = item_code;// "FOR3";
					hotel.checkInDate = inDate;
					hotel.checkOutDate = outDate;
					hotel.numOfRoomsRequired = noOfRooms;
					hotel.numOfAdults = noOfAdults;
					hotel.numOfChildren = noOfChildren;
					if (facility_checkbox() == true) {
						hotel.facilityCodesRequested = facility;
					} else {

					}
					hotel.roomTypeRequired = "SB";// $('#room_type
													// :selected').val();
					if (maxBudget_check() == true) {
						hotel.maxBudget = maxBudget;
					} else {
						hotel.maxBudget = 1000;
					}
					if (minStarRating_checkbox() == true) {
						hotel.minStarRating = minStarRate;
					} else {
						hotel.minStarRating = '0';
					}
					console.log(hotel);
					restcalls.zpost('/hotel/search', hotel, displayResult,
							function() {
								$(".overlay").hide();
								// $("#loading").css("display","none");
							})
				}
			}
			var loadMoreResults = function() {
				if (size_li >= x) {
					// alert("asdas"+size_li+" x "+x);
					$("#loadMore").show();
				} else {
					// $("#loadMore").hide();
					// alert("addddddd");
					// $("#loadMore").html('No More Results');
				}
			}
			var size_li = '';
			var x = 10;
			var searchResults = "";
			var city_name = "";
			// Called when the hotel search results are returned. Display the
			// hotels
			var displayResult = function(hotels) {
				// $("#loading").css("display","none");
				$("#loadAccord").empty();
				$('#city').each(function() {
					var kk = $(this).attr('value');
					city_name = $('#city').val();
					view.append('#loadAccord', 'hotel/loadCity.ejs', {
						city_name : city_name
					});
				});
				searchResults = hotels;
				$('#hotelSummary').empty();
				if (hotels.zHotelList != null && hotels.zHotelList != "") { // ,"responseError":false,"zillionError":null
					console.log("Not Nulll");
					$.each(hotels, function(key, hotelDetails) {
						$(".overlay").hide();
						if ((key == "zHotelList")) {
							view.load('#hotelSummary', 'hotel/hotelList.ejs', {
								hotelDetails : hotelDetails
							});
							for (var i = 0; i < hotelDetails.length; i++) {
								view.append('#hotelList', 'hotel/hotel.ejs', {
									hotel : hotelDetails[i]
								});
								size_li = $("#hotelList li").size();
							}
							$('#hotelList li').not(':lt(' + x + ')').hide();
							loadMoreResults();
						}
					});
				} else {
					// if(hotels.zillionError != "" && hotels.responseError ==
					// false)
					// $("div#errMsg").html("Zero Hotels Found for Destination
					// "+city_name+"!!");
					var hotelDetails = [];
					console.log(hotelDetails.length + " length");
					view.append('#hotelSummary', 'hotel/hotelList.ejs', {
						hotelDetails : hotelDetails
					});
					$(".overlay").hide();
				}
			}

			// Called when the hotel search results are returned. Display the
			// hotels
			var arrayLi = {};
			var displaySortResult = function(hotels) {
				// $("#loading").css("display","none");
				$(".overlay").hide();
				console.log(hotels);
				arrayLi.hotels = hotels;
				console.log("Array of Objects " + arrayLi.hotels);
				$('#hotelSummary').empty();
				if (arrayLi.hotels != "") {
					console.log("Not Nulll");
					$.each(arrayLi, function(key, hotelDetails) {
						$(".overlay").hide();
						view.load('#hotelSummary', 'hotel/hotelList.ejs', {
							hotelDetails : hotelDetails
						});
						for (var i = 0; i < hotelDetails.length; i++) {
							view.append('#hotelList', 'hotel/hotel.ejs', {
								hotel : hotelDetails[i]
							});
							size_li = $("#hotelList li").size();
						}
						$('#hotelList li').not(':lt(' + x + ')').hide();
						loadMoreResults();
					});
				} else {
					alert("dasdas");
				}
				$(".overlay").hide();
			}

			var rangeSlider = function() {

				$("#range").ionRangeSlider({
					hide_min_max : true,
					keyboard : true,
					min : 0,
					max : 5000,
					from : 1000,
					to : 4000,
					type : 'single',
					step : 1,
					prefix : "$",
					grid : true
				});

				$("#range2").ionRangeSlider({
					hide_min_max : true,
					keyboard : true,
					min : 0,
					max : 5000,
					from : 1000,
					to : 4000,
					type : 'single',
					step : 1,
					prefix : "$",
					grid : true
				});
				$("#range3").ionRangeSlider({
					hide_min_max : true,
					keyboard : true,
					min : 0,
					max : 5000,
					from : 1000,
					to : 4000,
					type : 'single',
					step : 1,
					prefix : "$",
					grid : true
				});

				$("#range4").ionRangeSlider({
					hide_min_max : true,
					keyboard : true,
					min : 0,
					max : 5000,
					from : 1000,
					to : 4000,
					type : 'single',
					step : 1,
					prefix : "$",
					grid : true
				});

			}

			var lightslider = function() {
				$('#lightSlider').lightSlider({
					gallery : true,
					minSlide : 1,
					maxSlide : 1,
					currentPagerPosition : 'left',
					prevHtml : '<i class="fa fa-angle-double-left"></i>',
					nextHtml : '<i class="fa fa-angle-double-right"></i>',
				});
			}
			new SearchControl('#zilliondiv');
			// can.route.ready(true);
			new Router(window);
			can.route.ready();
		});
