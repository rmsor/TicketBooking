<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta name="description" content="Ticket Booking System" />
<title>Ticket Booking System</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />

</head>

<body id="page-top" class="index">

	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header page-scroll">
				<a class="navbar-brand" href="#page-top">Ticket Booking System</a>
			</div>
		</div>
	</nav>
	<div class="container" style="padding-top:75px">
		<div class="row">
			<div class="col-lg-6">
				<div class="panel panel-primary">
					<div class="panel-heading">Seating Arrangement</div>						
					<div class="panel-body">
						<table id="levelData" class="table table-bordered table-striped">
							<thead>
								<tr>
									<th>Level Name</th>
									<th>Price</th>
									<th>Total Seats</th>
								</tr>
							</thead>
							<tbody id="tbody">
							</tbody>
						</table>
					</div>
				</div>	
			</div>
			<div class="col-lg-6">
				<div class="panel panel-primary">
					<div class="panel-heading">Check Availability</div>						
					<div class="panel-body">
						<form id="availableSeatForm">
							<fieldset class="form-group">
								<label for="levelsSelect">Select Level</label>
								<select class="form-control" name="levelsSelect" id="levelsSelect">
									<option value="">---All----</option>
								</select>
							</fieldset>	
							<button type="submit" id="availableSeatSubmit" class="btn btn-primary">Check</button>
							<fieldset class="form-group">
								<label for="availableSeatText">No of Seats Available</label>
								<input type="text" class="form-control" id="availableSeatText" readonly="readonly"/>
							</fieldset>								
						</form>
					</div>
				</div>	
			</div>
		</div>
		<div class="row">			
			<div class="col-lg-6">
				<div class="panel panel-primary">
					<div class="panel-heading">Book Seats</div>						
					<div class="panel-body">
						<form id="bookingForm">
							<fieldset class="form-group">
								<label for="minLevel">Select Minimumm Level</label>
								<select class="form-control" name="minLevel" id="minLevel">
									<option value="">---All----</option>
								</select>
							</fieldset>
							<fieldset class="form-group">
								<label for="maxLevel">Select Maximum Level</label>
								<select class="form-control" name="maxLevel" id="maxLevel">
									<option value="">---All----</option>
								</select>
							</fieldset>		
							<fieldset class="form-group">
								<label for="numSeats">No of Seats</label>
								<input type="number" required class="form-control" id="numSeats" placeholder="No of seats"/>
							</fieldset>
							<fieldset class="form-group">
								<label for="customerEmail">Customer Email</label>
								<input type="email" required class="form-control" id="customerEmail" placeholder="Customer Email"/>
							</fieldset>
							<button type="submit" id="bookingSubmit" class="btn btn-primary">Book</button>	
						</form>
					</div>
				</div>	
			</div>
			<div class="col-lg-6">
				<div class="panel panel-primary">
					<div class="panel-heading">Search Booking</div>						
					<div class="panel-body">
						<form id="searchBookingForm">
							<fieldset class="form-group">
								<label for="customerEmailSearch">Customer Email</label>
								<input type="email" required class="form-control" id="customerEmailSearch" placeholder="Email"/>
							</fieldset>
							<button type="submit" id="searchBookingSubmit" class="btn btn-primary">Search</button>
						</form>
						<br/>
						<table id="bookingData" class="table table-bordered table-striped">
							<thead>
								<tr>
									<th>BookingID</th>
									<th>Date</th>
									<th>Seats</th>
									<th>Status</th>
									<th>Code</th>
									<th>Reserve</th>
								</tr>
							</thead>
							<tbody id="tbody">
							</tbody>
						</table>
					</div>
				</div>	
			</div>
		</div>
	</div>

	<!-- Footer -->
	<footer class="text-center">

		<div class="footer-below">
			<div class="container">
				<div class="row">
					<div class="col-lg-12">Copyright &copy; Ticket Booking System</div>
				</div>
			</div>
		</div>
	</footer>



	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
	
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

	<script language="javascript">
		
		$(document).ready(function(){
			
			var restAPIEndPoint="http://localhost:8080/TicketBooking/";		
			var listLevelAPI = restAPIEndPoint+"rest/level/list/v1_0";
			var listAvailableSeatsAPI = restAPIEndPoint+"rest/booking/seatsavailable/v1_0";
			var addBookingAPI = restAPIEndPoint+"rest/booking/add/v1_0";
			var reserveBookingAPI = restAPIEndPoint+"rest/booking/reserve/v1_0";
			var searchBookingAPI = restAPIEndPoint+"rest/booking/list/v1_0";
			
			(function() {	
				
				var lastRow=$('#levelData > tbody:last-child');
				
				var levelsSelect=$('#levelsSelect');
				var minLevel=$('#minLevel');
				var maxLevel=$('#maxLevel');
				
				$.getJSON(listLevelAPI, {}).done(function(data) {
					$.each(data.levels, function(i, item) {
						var row="<tr><td>"+item.levelName+"</td><td>"+item.price+"</td><td>"+item.noOfRows*item.seatsInRow+"</td></tr>";
						var option='<option value="'+item.levelId+'">'+item.levelName+'</option>';
						lastRow.append(row);
						levelsSelect.append(option);
						minLevel.append(option);
						maxLevel.append(option);
					});
				});	

			})();
			
			$('#availableSeatSubmit').click( function(e) {
				e.preventDefault();	
				var levelId=$('#levelsSelect').val();				
			    $.ajax({
			    	async:true,
			        url: listAvailableSeatsAPI+"?level="+levelId,
			        type: 'GET',
			        success: function(data) {
			        	$("#availableSeatText").val(data.availableSeats);       
			        },
			        error:function(){
			        	alert("Ajax Error");
			        }
			    });
			});
			
			$('#bookingSubmit').click( function(e) {
				e.preventDefault();	
				
				var lastRow=$('#bookingData > tbody:last-child');
				$("#bookingData > tbody").html("");
				$('#customerEmailSearch').val("");
				
			    $.ajax({
			    	async:true,
			        url: addBookingAPI,
			        data: JSON.stringify({
					        	minLevel:$('#minLevel').val(),
					        	maxLevel:$('#maxLevel').val(),
					        	numSeats:$('#numSeats').val(),
					        	customerEmail:$('#customerEmail').val()
				        	}),
			        type: 'POST',
			        contentType : "application/json",
			        success: function(data) {
			        	$("#customerEmailSearch").val($('#customerEmail').val()); 
			        	var totalSeats=0;
			        	$.each(data.bookingDetails, function(i, item) {
							totalSeats+=item.bookedSeats;
						});
			        	var reservationCode=(typeof data.reservationCode != 'undefined')? data.reservationCode:"";
			        	var reserveHtml='<button class="btn btn-primary  btn-sm reserve-bttn" id="'+data.bookingId+'">Reserve</button>';
			        	var row="<tr><td>"+data.bookingId+"</td><td>"+data.bookedDate+"</td><td>"
			        			+totalSeats+"</td><td>"+data.bookingType+"</td><td>"
			        			+reservationCode+"</td><td>"+reserveHtml+"</td></tr>";
			        	lastRow.append(row);
			        },
			        error:function(){
			        	alert("Ajax Error");
			        }
			    });
			});
			
			
			$('#searchBookingSubmit').click( function(e) {
				e.preventDefault();	
				$("#bookingData > tbody").html("");
				var lastRow=$('#bookingData > tbody:last-child');
				var customerEmailSearch=$('#customerEmailSearch').val();
				
				$.ajax({
			    	async:true,
			        url: searchBookingAPI+"?customerEmail="+customerEmailSearch,
			        type: 'GET',
			        success: function(dataSearch) {
			        	$.each(dataSearch.bookings, function(i, item) {
			        		var totalSeats=0;
			        		$.each(item.bookingDetails, function(i, itemDtl) {
								totalSeats+=itemDtl.bookedSeats;
							});
			        		var reservationCode=(typeof item.reservationCode != 'undefined')? item.reservationCode:"";
			        		var reserveHtml='<button class="btn btn-primary  btn-sm reserve-bttn" id="'+item.bookingId+'">Reserve</button>';
			        		var row="<tr><td>"+item.bookingId+"</td><td>"+item.bookedDate+"</td><td>"
				        			+totalSeats+"</td><td>"+item.bookingType+"</td><td>"
				        			+reservationCode+"</td><td>"+reserveHtml+"</td></tr>";
				        	lastRow.append(row);
						});
			        	       
			        },
			        error:function(){
			        	alert("Ajax Error");
			        }
			    });				
			    
			});
			
			$(document).on('click', '.reserve-bttn', function(e){

				var seatHoldId=$(this).attr('id');
				
				var $this=$(this);
				
				$.ajax({
			    	async:true,
			        url: reserveBookingAPI,
			        data: JSON.stringify({
			        			seatHoldId:seatHoldId,
					        	customerEmail:$('#customerEmailSearch').val()
				        	}),
			        type: 'PUT',
			        contentType : "application/json",
			        success: function(data) {
			        	if((typeof data.bookingId != 'undefined')){
			        		$this.parents("tr:first").find("td:eq(4)").text(data.reservationCode);
			        		$this.parents("tr:first").find("td:eq(3)").text(data.bookingType);
			        	}
			        },
			        error:function(){
			        	alert("Ajax Error");
			        }
			    });
			});
			
		})
	</script>


</body>

</html>