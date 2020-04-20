$(document).ready(function() {
        $(" #submitButton ").click(function(){
                event.preventDefault();
                var plane = {};
                plane.name = $('#nameInput').val();
                plane.airport_code = $('#currentLocationInput').val();
                plane.fuel = $('#fuelInput').val();

               $.ajax({
                url: "planes/save",
                type:"POST",
                data: JSON.stringify(plane),
                contentType: "application/json",
                success: function(result){
                    alert(result);
                    window.location.href="landing-page.html";
                  },
                  error: function(){
                    alert('failure');
                   }
                });
        });
 });