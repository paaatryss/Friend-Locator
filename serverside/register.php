<?php

require_once 'db.class.php';
require_once 'register.class.php';

$login = mysql_real_escape_string($_GET['login']);
$password = mysql_real_escape_string($_GET['password']);
$email = mysql_real_escape_string($_GET['email']);

if(empty($login) || empty($password) || empty($email))
{
	$response = Array('error' => 1);
	echo json_encode($response);
	die();
}

$reg = new Register();

$ret = $reg->doregister($login, $email, $password);

if($ret==0)
{
	$response = Array('error' => '0');
	echo json_encode($response);
}
else {
	$response = Array('error' => '1');
	echo json_encode($response);
}
