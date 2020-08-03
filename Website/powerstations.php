<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>EV Eutopia</title>

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
                    <h2 class="intro-text text-center">Power stations to be
                        <strong>Deployed</strong>
                    </h2>
                    <hr>
                </div>
				<div class="col-lg-12 text-center">
			 <ol>
			     <h4><li><a href="location.php">Latitude: 30.690895 <br>Longitude: 76.664150</a></li></h4>
			     <h4><li><a href="location1.php">Latitude: 32.03964037585337 <br>Longitude: 75.40276573628016</a></li></h4>
				 <h4><li><a href="location2.php">Latitude: 30.938190857501496 <br>Longitude: 76.2703155165526</a></li></h4>
				 <h4><li><a href="location3.php">Latitude: 30.301215486525066 <br>Longitude: 74.31666671834533</a></li></h4>
				 <h4><li><a href="location4.php">Latitude: 30.089586686366005 <br>Longitude: 75.61565771570636</a></li></h4>
			 
			 
			 </ol>
			   </div>
			
			
			</div>
			</div>
			
			<div class="row">
            <div class="box">
			    <div class="col-lg-12">
                    <hr>
                    <h2 class="intro-text text-center">Power stations already
                        <strong>Deployed</strong>
                    </h2>
					
                    <hr>
					<div class="col-lg-12">
					   <h4><h3 class="text-center" id="user_para1"></h4>
					   <h4><h3 class="text-center" id="user_para2"></h4>
  
					</div>
                </div>
			</div>
			</div>
			</div>
			
			
			
			
			
	<!-- /.footer -->
	 <footer>
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <p>Copyright &copy; EV EUTOPIA 2020</p>
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

    <!-- jQuery -->
    <script src="js/jquery.js"></script>
	<script src="ps.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>

</html>
