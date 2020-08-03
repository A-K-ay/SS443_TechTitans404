<!doctype html>
<html>
<head>
<title>ZS EV</title>
<style>
     body{
	 background:#222;
	 color:white;
	 font-family:arial;
	 margin-top:10px;
	 }
	 
	 #thumbnail img{
	 padding:1px;
     border:4px solid gray;
     height:80px;
	 width:120px;
     margin:5px 5px 5px 0;
     }
     
	 #thumbnail img:hover{
	 border:4px solid red;
	 }	 
	 
	 #preview img{
	 padding:1px;
	 border:4px solid gray;
	 width:800px;
	 height:450px;
	 
	 }
</style>



</head>
<body>

<div id="container" align="center"><h1>EV Eutopia ZS EV
      <div id="thumbnail">
	  <img onmouseover="preview.src=img1.src" name="img1" src="img/e91.jpg" />
	  <img onmouseover="preview.src=img2.src" name="img2" src="img/e92.jpg" />
	  <img onmouseover="preview.src=img3.src" name="img3" src="img/e93.jpg" />
	  <img onmouseover="preview.src=img4.src" name="img4" src="img/e94.jpg" />
	  <img onmouseover="preview.src=img5.src" name="img5" src="img/e95.jpg" />
	  </div>
	  
	  <div id="preview">
	  <img name ="preview" src="img/e91.jpg" />
	  
	  </div>
	  
</div>


</body>
</html>
