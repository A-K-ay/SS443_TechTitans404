<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Contact - EV Eutopia</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/theme-casual1.css" rel="stylesheet">

    <!-- Fonts -->
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- jQuery -->
    <script src="js/jquery.js"></script>
	
		<script type="text/javascript">
navigator.geolocation.getCurrentPosition(function(position){
					console.log(position);
					window.latitude= position.coords.latitude;
					window.longitude= position.coords.longitude;
					
    });
  $(document).ready(function() {



      $('#submit').click(function(e){

        e.preventDefault();


                
	

        var fname = $("#fname").val();

        var email = $("#email").val();

        var tel = $("#tel").val();

        var message = $("#message").val();
		var latitude = window.latitude;
		var longitude = window.longitude;



        $.ajax({

            type: "POST",

            url: "sendmsgs.php",

            data: {fname:fname, email:email, tel:tel, message:message,latitude:latitude,longitude:longitude},

            success : function (html) {
                    if (html == 'true') {

                            $("#add_err2").html('<div class="alert alert-success"> \
                                                 <strong>Message Sent!</strong>  \ \
                                                 </div>');
					}else if(html == 'fshort'){
					        $("#add_err2").html('<div class="alert alert-danger"> \
                                                 <strong>First name</strong> must exceed 2 characters.  \ \
                                                 </div>');
					}else if(html == 'eformat'){
					        $("#add_err2").html('<div class="alert alert-danger"> \
                                                 <strong>Email address</strong> format not valid.  \ \
                                                 </div>');
					}else if(html == 'mshort'){
					        $("#add_err2").html('<div class="alert alert-danger"> \
                                                 <strong>Message</strong> must exceed 3 characters.  \ \
                                                 </div>');
					}else if(html == 'tshort'){
					        $("#add_err2").html('<div class="alert alert-danger"> \
                                                 <strong>Contact number</strong> must be of 10 characters.  \ \
                                                 </div>');
					}else {
                            $("#add_err2").html('<div class="alert alert-danger"> \
                                                 <strong>Error</strong> processing request. Please try again. \ \
                                                 </div>');
                        }
                    },
                    beforeSend: function () {
                        $("#add_err2").html("loading...");
                    }
                });
                return false;
            });
        });
</script>

                
<body>

    <div class="brand"><img src="img/6.png" width="270" alt=""></div>
    <div class="address-bar">The Perfect World Of Electric Vehicles.</div>

    <!-- Navigation -->
    <?php require_once "nav.php"; ?>

    <div class="container">

        <div class="row">
            <div class="box">
                <div class="col-lg-12">
                    <hr>
                    <h2 class="intro-text text-center">Contact
                        <strong>EV EUTOPIA</strong>
                    </h2>
                    <hr>
                </div>
                <div class="col-md-8">
                    <!-- Embedded Google Map using an iframe - to select your location find it on Google maps and paste the link as the iframe src. If you want to use the Google Maps API instead then have at it! -->
<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3431.132481965134!2d76.66368571447869!3d30.686548381653292!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x390fef156a3942e5%3A0x65ef2d487904b8f3!2sChandigarh%20Engineering%20College-Block%203%2C%20Wilson%20Block!5e0!3m2!1sen!2sin!4v1589355094684!5m2!1sen!2sin" width="100%" height="450" frameborder="0" style="border:0;" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>
                </div>
                <div class="col-md-4">
                    <p>Phone:
                        <strong>+91 98055 53924</strong>
                    </p>
                    <p>Email:
                        <strong><a href="mailto:eveutopiacgc@gmail.com">eveutopiacgc@gmail.com</a></strong>
                    </p>
                    <p>Address:
                        <strong>Chandigarh Engineering College,
                            <br>Landran, Mohali.</strong>
                    </p>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>

        <div class="row">
            <div class="box">
                <div class="col-lg-12">
                    <hr>
                    <h2 class="intro-text text-center">Contact
                        <strong>form</strong>
                    </h2>
					<div id="add_err2"></div>
                    <hr>
                   
                    <form role="form">
                        <div class="row">
                            <div class="form-group col-lg-4">
                                <label>Name</label>
                                <input type="text" id="fname" name="fname" maxlength="25" class="form-control">
                            </div>
                            <div class="form-group col-lg-4">
                                <label>Email Address</label>
                                <input type="email" id="email" name="email" maxlength="50" class="form-control">
                            </div>
                            <div class="form-group col-lg-4">
                                <label>Phone Number</label>
                                <input type="tel" id="tel" name="tel" maxlength="10"  class="form-control">
                            </div>
                            <div class="clearfix"></div>
                            <div class="form-group col-lg-12">
                                <label>Message</label>
                                <textarea class="form-control" id="message" name="message" maxlength="100" rows="6"></textarea>
                            </div>
							
                            <div class="form-group col-lg-12">
                                
                                <button type="submit" id="submit" class="btn btn-default">Submit</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

    </div>
    <!-- /.container -->

    <footer>
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <p>Copyright &copy; EV EUTOPIA 2020</p>
                </div>
            </div>
        </div>
    </footer>

   

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>

</html>
