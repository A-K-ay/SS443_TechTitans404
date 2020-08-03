<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title> Blog - EV Eutopia</title>

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
	
	
<link href="https://fonts.googleapis.com/css?family=Nunito:400,600,700" rel="stylesheet">
</head>
<body>

    <div class="brand"><img src="img/6.png" width="270" alt=""></div>
    <div class="address-bar">The Perfect World Of Electric Vehicles.</div>

    <!-- Navigation -->
    <?php require_once 'nav.php' ; ?>

    
  <div id="login_div" class="container">
        <div class="row">
            <div class="box">
			<div id="add_err3"></div>
                <div class="col-lg-12">
				<div class="alert alert-danger" >
				<strong>You must be logged in to view the blog.</strong>
				</div>
                    <hr>
                    <h2 class="intro-text text-center">USER
                        <strong>login</strong>
                    </h2>
                    <hr>     
					<div class="container  login_div">
                         <div class="form-group col-lg-11"> 
						 <label>Email Address</label>
						 <input type="email" placeholder="Enter your email address." class="form-control" id="email_field" />
						 </div>
                        <div class="form-group col-lg-11"> 
                         <label>Password</label>						
						<input type="password" placeholder="Enter your Password." class="form-control" id="password_field" />
						</div>
                         <div class="form-group col-lg-12">
                          <button onclick="login()" id="signup" class="btn btn-default">Login </button>
                    </div>
					</div><br>
					<div class="form-group col-lg-12">
						  <a href="preregister.php"><button type="submit" class="btn btn-default"> Not a  member? Register here.</button></a>
						</div>
                   
						
                    
                </div>
            </div>
        </div>

    </div>
   
    
    
	<div id="user_div" class="container">

        <div class="row">
            <div class="box">
			 <hr>
                    <h2 class="intro-text text-center">EV EUTOPIA
                        <strong>blog</strong>
                    </h2>
                    <hr>
                <div class="col-lg-12">
				<h2  class="text-center"><h3 class="text-center" id="user_para"><h3><div style="text-align: center;"><button  onclick="logout()">Logout</button></div></h2>
                  <hr> 
                </div>
				
	
                <div class="col-lg-12 text-center">
                    <div class="text-center">
					  <div class="brand"><img src="img/6b.png" width="270" alt=""></div>
						<h1 class="my-4" align="text-center" >Categories</h1>
						<div class="list-group">
						  <a href="blog.php" class="list-group-item"><h4>Features and Products related Updates</h4></a>
						  <a href="community.php" class="list-group-item"><h4>Community</h4></a>
						  <a href="govt.php" class="list-group-item"><h4>Government initiatives related Updates</h4></a>
						  
						</div>
					</div>
				</div>
				<div class="col-lg-12">
				<hr>
				</div>
			
            
        </div>

    </div>
    <!-- /.container -->
	<!-- Modal content -->

	
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
  </script>
  <!-- Bootstrap Core JavaScript -->
	<script src="index3.js"></script>
	
    <script src="js/bootstrap.min.js"></script>

</body>
</html>
