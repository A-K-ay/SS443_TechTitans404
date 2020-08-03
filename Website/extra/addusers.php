<?php 
session_start();

$mysqli = new mysqli('localhost','root','1234','eveutopia');

if($mysqli->connect_error){
	die("Error whle connecting".$mysqli->connect_error);
	
}
$fname = val( $_POST['fname']);
$lname = val( $_POST['lname']);
$email = val( $_POST['email']);
$password = val( $_POST['password']);
$address = val( $_POST['address']);

function val($data){
	$data=htmlspecialchars($data);
	$data=trim($data);
	$data=stripslashes($data);
	return $data;
}

//VALIDATION

if (strlen($fname) < 2) {
    echo 'fname';
} elseif (strlen($lname) < 2) {
    echo 'lname';
} elseif (strlen($email) <= 4) {
    echo 'eshort';
} elseif (filter_var($email, FILTER_VALIDATE_EMAIL) === false) {
    echo 'eformat';
} elseif (strlen($password) <= 4) {
    echo 'pshort';
}elseif(strlen($address) > 250){
	echo 'along';

//VALIDATION
	
} else {
	
	//PASSWORD ENCRYPT
	$spassword = password_hash($password, PASSWORD_BCRYPT, array('cost' => 12));
	
	$query = "SELECT * FROM premembers WHERE email='$email';";
	$result = mysqli_query($mysqli, $query) or die(mysqli_error());
	$num_row = mysqli_num_rows($result);
	$row = mysqli_fetch_array($result);
	
		if ($num_row < 1) {

			$insert_row = $mysqli->query("INSERT INTO premembers (fname, lname, email, password,address) VALUES ('$fname', '$lname', '$email', '$spassword','$address')");

			if ($insert_row) {

				$_SESSION['login'] = $mysqli->insert_id;
				$_SESSION['fname'] = $fname;
				$_SESSION['lname'] = $lname;

				echo 'true';

			}

		} else {

			echo 'false';

		}
		
}
?>