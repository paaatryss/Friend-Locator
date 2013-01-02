<?php

require_once 'db.class.php';
require_once 'auth.class.php';

$login = mysql_real_escape_string($_GET['login']);
$password = mysql_real_escape_string($_GET['password']);

if(empty($login) || empty($password) )
{
	$response = Array('error' => 1);
	echo json_encode($response);
	die();
}
$auth = new Auth();

$ret = $auth->login($login, $password);

if($ret!=FALSE)
{
	$response = Array('token' => $ret);
	echo json_encode($response);
}
else {
	$response = Array('error' => 1);
	echo json_encode($response);
}
