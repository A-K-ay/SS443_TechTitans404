<?php

//Open a new connection to the MySQL server
$mysqli = new mysqli('localhost', 'root', '1234', 'eveutopia');

//Output any connection error
if ($mysqli->connect_error) {
    die('Error : (' . $mysqli->connect_errno . ') ' . $mysqli->connect_error);
}

$fname = val($_POST['fname']);
$email = val($_POST['email']);
$tel = val($_POST['tel']);
$message= val($_POST['message']);
$latitude= val($_POST['latitude']);
$longitude= val($_POST['longitude']);

function val($data){
	$data=htmlspecialchars($data);
	$data=stripslashes($data);
	$data=trim($data);
	return $data;
}

$email2 = "eveutopiacgc@gmail.com";
$subject = "EV EUTOPIA GREETINGS";

if (strlen($fname) > 25) {
    echo 'flong';

} elseif (strlen($fname) < 2) {
    echo 'fshort';

} elseif (filter_var($email, FILTER_VALIDATE_EMAIL) === false) {
    echo 'eformat';

} elseif (strlen($tel) < 10) {
    echo 'tshort';

} elseif (strlen($message) < 3) {
    echo 'mshort';

} elseif (strlen($latitude) < 3) {
    echo 'lshort';

} else {
require 'phpmailer/PHPMailerAutoload.php';

    $mail = new PHPMailer;
	
	//$mail->SMTPDebug =  SMTP::DEBUG_SERVER;                               // Enable verbose debug output

    $mail->isSMTP();                                      // Set mailer to use SMTP
    $mail->Host = 'smtp.gmail.com';  // Specify main and backup SMTP servers
    $mail->SMTPAuth = true;                               // Enable SMTP authentication
    $mail->Username = 'eveutopiacgc@gmail.com';                 // SMTP username
    $mail->Password = 'eveutopia8080';                           // SMTP password
    $mail->SMTPSecure = 'tls';                            // Enable TLS encryption, `ssl` also accepted
    $mail->Port = 587;                                    // TCP port to connect to

	$mail->AddReplyTo($email);
    $mail->From = $email2;
    $mail->FromName = $fname;
    $mail->addAddress('eveutopiacgc@gmail.com', 'Admin');     // Add a recipient

    $mail->isHTML(true);                                  // Set email format to HTML

    $mail->Subject = $subject;
    $mail->Body = "contact number:".$tel." Message: ".$message." Latitude: ".$latitude." Longitude: ".$longitude ;
	$mail->addAttachment = $tel;
    $mail->AltBody = 'This is the body in plain text for non-HTML mail clients';

    if (!$mail->send()) {
        echo 'Message could not be sent.';
        echo 'Mailer Error: ' . $mail->ErrorInfo;
    } else {
        echo 'true';
    }


}
?>