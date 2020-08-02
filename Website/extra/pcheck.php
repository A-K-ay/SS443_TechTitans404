<?php 
session_start();
$email=val($_POST['email']);
$password=val($_POST['password']);

function val($data){
	$data=htmlspecialchars($data);
	$data=stripslashes($data);
	$data=trim($data);
	return $data;
}

$mysqli= new mysqli('localhost','root','1234','eveutopia');
if($mysqli->connect_error){
	die("error while connecing".$mysqli->connect_error);
}

$query="SELECT * FROM premembers WHERE email='$email';";
$result=mysqli_query($mysqli,$query);
$num_row=mysqli_num_rows($result);
$row=mysqli_fetch_array($result);

if($num_row >=1){
	
	if(password_verify($password,$row['password'])){
		
		$_SESSION['login']=$row['id'];
		$_SESSION['fname']=$row['fname'];
		$_SESSION['lname']=$row['lname'];
		echo 'true';
		
	}else{
		echo "false";
	}
}else{
	echo "false";
}

?>