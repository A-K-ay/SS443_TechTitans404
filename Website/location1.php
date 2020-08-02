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
                    <h2 class="intro-text text-center">EV EUTOPIA
                        <strong>ecosytem</strong>
                    </h2>
                    <hr>
                </div>
                <div class="col-lg-12">
                  <div id="my_map_add" style="width:100%;height:300px;"></div>

            <script type="text/javascript">
              function my_map_add() {
              var myMapCenter = new google.maps.LatLng(30.690895, 75.40276573628016);
              var myMapProp = {center:myMapCenter, zoom:12, scrollwheel:false, draggable:false, mapTypeId:google.maps.MapTypeId.ROADMAP};
              var map = new google.maps.Map(document.getElementById("my_map_add"),myMapProp);
              var marker = new google.maps.Marker({position:myMapCenter});
              marker.setMap(map);
              }
            </script>

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCGST4c2Qj914X6hqAjCBAWymZH4Q5_wtc&callback=my_map_add"></script>
                </div>
                
                <div class="clearfix"></div>
            </div>
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

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>

</html>
