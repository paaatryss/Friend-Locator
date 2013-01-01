<?php

require_once 'db.class.php';
require_once 'register.class.php';

$login = mysql_real_escape_string($_GET['login']);
$password = mysql_real_escape_string($_GET['password']);
$email = mysql_real_escape_string($_GET['email']);

$reg = new Register();

$ret = $ret->register($login, $email, $password);

if($ret!=FALSE)
{
	$response = Array('error' => '0');
	echo json_encode($response);
}
else {
	$response = Array('error' => '1');
	echo json_encode($response);
}
