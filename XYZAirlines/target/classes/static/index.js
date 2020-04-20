$(document).ready(function() {
        $(" #submitButton ").click(function(){
                event.preventDefault();
                var user = {};
                user.userName = $('#userName').val();
                user.password = $('#passwordInput').val();

               $.ajax({
                url: "users/checklogon",
                type:"POST",
                data: JSON.stringify(user),
                contentType: "application/json",
                success: function(result){
                    alert(result);
                    if(result == "Logging in...") {
                    window.location.href="landing-page.html"
                    }
                    else {
                    window.location.href="index.html";
                    }
                  },
                  error: function(){
                    alert('Apologies, we do not recognise those details. Please try logging in again.');
                    window.location.href="index.html";
                   }
                });
        });
 });