<?php

require_once 'db.class.php';

$db = new DB();

class Auth{
		
	public function login($login, $pass)
	{
		global $db;
		$db_pass = md5($pass.$db->getSalt());
		
		$query = "SELECT id FROM baza70_friendlocator.users WHERE login='".$login."' AND password='".$db_pass."'";
		$ret = $db->fetch_array($query);
		
		if(!empty($ret['0']['id'])>0)
		{
			return $this->setToken($ret['0']['id']);
		}
		else 
			return FALSE;
	}
	
	private function setToken($userid)
	{
		global $db;
		$token = $this->generateToken();
		
		$ret = $db->insert("INSERT INTO baza70_friendlocator.tokens (`userid`, `token`) VALUES ('".$userid."','".$token."')");
		
		if(is_numeric($ret))
			return $token;
		else
			return FALSE;
	}
	
	/**
	 * Sprawdza czy dany token jest ważny
	 */
	private function checkToken($userid, $token)
	{
		global $db;
		
		$ret = $db->fetch_array("SELECT tokenid, timestamp FROM baza70_friendlocator.tokens WHERE userid='".$userid."' AND token='".$token."'");
		
		return print_r($ret, true);
		
	}
	
	/**
	 * Tworzy token
	 */
	private function generateToken()
	{
		return md5(time()."sdfsd23d@a".rand(0, 100));
	}
}

