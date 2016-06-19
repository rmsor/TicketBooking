(function() {
	var listLevelAPI = "http://localhost:8080/TicketBooking/rest/level/list/v1_0";
	$.getJSON(flickerAPI, {}).done(function(data) {
		console.log(data);
		$.each(data.items, function(i, item) {
			console.log(item);
		});
	});

})();