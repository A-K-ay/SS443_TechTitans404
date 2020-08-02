<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>EV Eutopia - Register</title>

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
	 <script type="text/javascript"
  src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCGST4c2Qj914X6hqAjCBAWymZH4Q5_wtc&sensor=false">
</script>

	<!-- jQuery -->
    <script src="js/jquery.js"></script>
	


</head>

<body>

    <div class="brand"><img src="img/6.png" width="270" alt=""></div>
    <div class="address-bar">The Perfect World Of Electric Vehicles.</div>

    <!-- Navigation -->
    <?php require_once 'nav.php'; ?>

    <div class="container">
        <div class="row">
            <div class="box">
                <div class="col-lg-12">
                    <hr>
                    <h2 class="intro-text text-center">Registration
                        <strong>form</strong>
                    </h2>
					<div id="add_err2"></div>
                    <hr>       
                    <form id="formReg" role="form">
                        <div class="row">
						
                            <div class="form-group col-lg-6">
                                <label for="fname">First Name</label>
                                <input type="text" id="fname" name="fname" maxlength="25" class="form-control" placeholder="Enter your first name.">
                            </div>
                            <div class="form-group col-lg-6">
                                <label for="lname">Last Name</label>
                                <input type="text" id="lname" name="lname" maxlength="25" class="form-control" placeholder="Enter your last name.">
                            </div>
                            <div class="form-group col-lg-12">
                                <label for="email">Email Address</label>
                                <input type="email" id="email" name="email" maxlength="25" class="form-control" placeholder="Enter your email address.">
                            </div>
							<div class="form-group col-lg-12">
                                <label for="address">Address</label>
                                <input type="address" id="address" name="address" maxlength="250" class="form-control" placeholder="Enter your permanent address. Use the following format : ( House no. , Street address , City , State )">
                            </div>
							
                            <div class="clearfix"></div>
                            <div class="form-group col-lg-12">
                                <label for="password">Password</label>
                                <input type="password" id="password" name="password" maxlength="10" class="form-control" placeholder="Enter your password.">
                            </div>
							<div class="form-group col-lg-12">
                                <label  for="vehicle">Choose: Vehicle Type</label>
                                <select id="type" name="Choose a vehicle type">
								<optgroup label="2 Wheelers">
								<option value="2W1">2W-1</option>
								<option value="2W2">2W-2</option>
								<option value="2W3">2W-3</option>
								</optgroup>
								<optgroup label="3 Wheelers">
								<option value="3W1">Auto-Z1</option>
								<option value="3W2">E-rickshaw</option>
								<option value="3W3">3w-1</option>
								</optgroup>
								<optgroup label="Passenger Cars">
								<option value="Car 1">Model S</option>
								<option value="Car 2">ZS EV</option>
								<option value="Car 3">Bolt EV </option>
								</optgroup>
								<optgroup label="Passenger Cars">
								<option value="DC">DC Wall Charger</option>
								<option value="TYPE2">Type 2 Wall Charger</option>
								<option value="AC">AC Wall Charger </option>
								</optgroup>
								</select>
                            </div>
                            <div class="form-group col-lg-12">
                                <button  type="submit" id="register" class="btn btn-default">Submit</button>
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
                    <p>Copyright &copy;  EV EUTOPIA 2020</p>
                </div>
            </div>
        </div>
    </footer> 
	
	  <script src="https://www.gstatic.com/firebasejs/4.8.1/firebase.js"></script>

  <script>
    // Initialize Firebase
    var config = {
      apiKey: "AIzaSyBwWjOdDVjPd4Bkfl6RQPMeGomf0Y_EK9k",
    authDomain: "ev-eutopia-81a2d.firebaseapp.com",
    databaseURL: "https://ev-eutopia-81a2d.firebaseio.com",
    projectId: "ev-eutopia-81a2d",
    storageBucket: "ev-eutopia-81a2d.appspot.com",
    messagingSenderId: "673244521650",
    appId: "1:673244521650:web:0d0d54052a9474038f55ab",
    measurementId: "G-XKF877501P"
    };
    firebase.initializeApp(config);
	 console.log(firebase);
  </script>

  
	
  </script>
    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>
	<script src="main.js"></script>
	

</body>

</html>
