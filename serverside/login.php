<?php

require_once 'db.class.php';
require_once 'auth.class.php';

$login = $_GET['login'];
$password = $_GET['password'];


$auth = new Auth();

$ret = $auth->login($login, $password);
echo $ret." taki chuj";
if($ret!=FALSE)
{
	$response = Array('token' => $ret);
	echo json_encode($response);
}
else {
	$response = Array('error' => 1);
	echo json_encode($response);
}
