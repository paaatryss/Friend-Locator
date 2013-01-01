<?php

require_once 'db.class.php';

$db = new DB();

class Register{
		
	public function register($login, $email, $pass)
	{
		global $db;
		$db_pass = md5($pass.$db->getSalt());
		
		$query = "INSERT INTO `users`(`login`, `email`, `password`) VALUES ('".$login."','".$email."','".$password."')";
		$ret = $db->fetch_array($query);
		
		if(!empty($ret[0][id])>0)
		{
			return 0;
		}
		else 
			return FALSE;
	}
	
}

