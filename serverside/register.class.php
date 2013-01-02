<?php

require_once 'db.class.php';

$db = new DB();

class Register{
		
	public function doregister($login, $email, $pass)
	{
		global $db;
		$db_pass = md5($pass.$db->getSalt());
				
		$query = "INSERT INTO `users`(`login`, `email`, `password`) VALUES ('".$login."','".$email."','".$db_pass."')";
		$ret = $db->insert($query);
		if(is_numeric($ret) && $ret>0)
		{
			$db->insert("INSERT INTO `positions`(`userid`, `lat`, `lng`) VALUES ('".$ret."','50.040935','21.999264')");
			return 0;
		}
		else 
			return FALSE;
	}
	
}

