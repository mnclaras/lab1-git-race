<%--@elvariable id="message" type="java.lang.String"--%>
<%--@elvariable id="time" type="java.util.Date"--%>
<!DOCTYPE html>

<html lang="en">
<title>Hello world</title>
<link rel="stylesheet" type="text/css"
      href="webjars/bootstrap/3.3.5/css/bootstrap.min.css"/>
<body>

<kbd>${time}<span class="glyphicon glyphicon-console"></span>${message}</kbd>
<kbd id="countdown_clock" class="text_danger"></kbd>
<script>
// Set the date we're counting down to
var countDownDate = new Date("Oct 7, 2017 00:00:00").getTime();

// Update the count down every 1 second
var x = setInterval(function() {

    // Get todays date and time
    var now = new Date().getTime();
    
    // Find the distance between now an the count down date
    var distance = countDownDate - now;
    
    // Time calculations for days, hours, minutes and seconds
    var hours = Math.floor(distance/ (1000 * 60 * 60));
    var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
    var seconds = Math.floor((distance % (1000 * 60)) / 1000);
    
    // Output the result in an element with id="demo"
    document.getElementById("countdown_clock").innerHTML = "Tiempo hasta final de Git Race: "
    + hours + ":" + minutes + ":" + seconds;
    
    // If the count down is over, write some text 
    if (distance < 0) {
        clearInterval(x);
        document.getElementById("countdown_clock").innerHTML = "PLAZO FINALIZADO";
    }
}, 1000);
</script>
<script type="text/javascript" src="webjars/jquery/2.1.4/jquery.min.js"></script>
</body>

</html>
