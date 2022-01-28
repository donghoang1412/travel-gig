/*
$(document).ready(function() {
	$("#searchBtn").click(function() {
		ajaxGetHotelByName();
		
		function ajaxGetHotelByName() {
			var name = $("#searchLocation").val();
			$.ajax({
				url: url + "/getHotelByCity/" + city, // localhost:8383/getHotelByCity/Chicago
				type: "GET",
				dataType: 'json',
				success: function(hotels) {
					console.log(hotels)
					$("#hotelDiv").html("")

					$.each(hotels, function(index, value) {

						var hotelName = hotels[index].hotelName;
						var address = hotels[index].address;
						var description = hotels[index].description;
						var imageURL = hotels[index].imageURL;

						$("#hotelDiv").append('<div class="card">');
						$("#hotelDiv").append('<div class="card-body">');
						$("#hotelDiv").append('<h5 id="hotelName" class="card-title">' + hotelName + '</h5>');
						$("#hotelDiv").append('<img id="hotelImage"class="card-img-top" src="' + imageURL + '">');
						$("#hotelDiv").append('<p id="hotelAddress" class="card-subtitle grey->' + '</p>');
					
				});
			}
		}
	});
	*/