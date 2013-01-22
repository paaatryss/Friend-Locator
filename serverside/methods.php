<?php

require_once 'db.class.php';
$db = new DB();

if(isset($_GET['call']) && isset($_GET['token']))
{
	$token = $_GET['token'];	
	$method = $_GET['call'];
	$db->feedToken($token);
	$userid = $db->getUserIdFromToken($token);
	if(!is_numeric($userid))
	{
		echo json_encode(Array("error" => 1));
		die();
	}

switch ($method) {
	case 'getinvitations':
		$ret = $db->fetch_array("SELECT invid, login from `invitations`, `users` where recvrid=".$userid." AND users.id=invitations.senderid");
		foreach($ret as $line)
		{
			$invitations[] = Array("inv" => $line['login']);
		}
		echo json_encode($invitations);
		break;
	case 'sendinvitation':
		if(isset($_GET['to']))
			$recvr = $_GET['to'];
		else {
			echo json_encode(Array("error" => 1));
			die();
		}
		$recvr = $db->fetch_array("SELECT id FROM users WHERE login='".$recvr."' OR email='".$recvr."'");
		if(is_numeric($recvr[0]['id']))
		{
			if($userid != $recvr[0]['id'])
			{
				$ret = $db->insert("INSERT INTO `invitations`(`senderid`, `recvrid`) VALUES (".$userid.",".$recvr[0]['id'].")");
				if(is_numeric($ret))
				{
					echo json_encode(Array("error" => 0));
					die();
				}
			}
		}
		echo json_encode(Array("error" => 1));
		die();			
		break;
	case 'acceptinv':
		if(isset($_GET['from']))
			$inv = $_GET['from'];
		else {
			echo json_encode(Array("error" => 1));
			die();
		}
		$recvr = $db->fetch_array("SELECT id FROM users WHERE login='".$inv."' OR email='".$inv."'");
		if(is_numeric($recvr[0]['id']))
		{
			$invitation = $db->fetch_array("SELECT invid FROM `invitations` WHERE senderid=".$recvr[0]['id']." AND recvrid=".$userid);
			if(is_numeric($invitation[0]['invid']))
			{
				$ret = $db->insert("INSERT INTO `friendship`(`user1id`, `user2id`) VALUES (
				(SELECT senderid FROM `invitations` WHERE invid=".$invitation[0]['invid']."), 
				(SELECT recvrid FROM `invitations` WHERE invid=".$invitation[0]['invid']."))");
				if(is_numeric($ret))
				{
					$db->insert("DELETE FROM `invitations` WHERE invid=".$invitation[0]['invid']);
					echo json_encode(Array("error" => 0));
					die();
				}
			}
		}
		echo json_encode(Array("error" => 1));
		die();
		break;
		
	case 'rejectinv':
		if(isset($_GET['from']))
			$inv = $_GET['from'];
		else {
			echo json_encode(Array("error" => 1));
			die();
		}
		$recvr = $db->fetch_array("SELECT id FROM users WHERE login='".$inv."' OR email='".$inv."'");
		if(is_numeric($recvr[0]['id']))
		{
			$invitation = $db->fetch_array("SELECT invid FROM `invitations` WHERE senderid=".$recvr[0]['id']." AND recvrid=".$userid);
			if(is_numeric($invitation[0]['invid']))
			{
				$db->insert("DELETE FROM `invitations` WHERE invid=".$invitation[0]['invid']);
				echo json_encode(Array("error" => 0));
				die();
			}
		}
		echo json_encode(Array("error" => 1));
		break;
		
	case 'rmfr':
		if(isset($_GET['who']))
			$inv = $_GET['who'];
		else {
			echo json_encode(Array("error" => 1));
			die();
		}
		$recvr = $db->fetch_array("SELECT id FROM users WHERE login='".$inv."' OR email='".$inv."'");
		if(is_numeric($recvr[0]['id']))
		{
			$friendship = $db->fetch_array("SELECT frid FROM `friendship` WHERE user2id=".$recvr[0]['id']." OR user1id=".$recvr[0]['id']);
			if(is_numeric($friendship[0]['frid']))
			{
				$db->insert("DELETE FROM `friendship` WHERE frid=".$friendship[0]['frid']);
				echo json_encode(Array("error" => 0));
				die();
			}
		}
		echo json_encode(Array("error" => 1));
		break;
		
	case 'getfrlocation':
		$ret = $db->fetch_array("SELECT DISTINCT id, email, login, lat, lng FROM  `positions` ,  `friendship` ,  `users` 
WHERE `positions`.`userid` = `users`.`id` AND (`friendship`.`user2id` = `users`.`id` OR `friendship`.`user1id` = `users`.`id`)
 AND ( `friendship`.`user1id` =".$userid." OR `friendship`.`user2id` =".$userid.") AND `users`.`id` != ".$userid);
		foreach($ret as $line)
		{
			$frlocation[] = Array('id' => $line['id'], 'email' => $line['email'], 'login' => $line['login'], 'lat' => $line['lat'], 'lng' => $line['lng']);
		}
		echo json_encode($frlocation);
		break;
	case 'sendmylocation':
		if(isset($_GET['lat']) && is_numeric($_GET['lat']) && isset($_GET['lng']) && is_numeric($_GET['lng']))
		{
			$lat = $_GET['lat'];
			$lng = $_GET['lng'];
		}
		else {
			echo json_encode(Array("error" => 1));
			die();
		}
		$id = $db->insert("UPDATE `positions` SET `lat`='".$lat."',`lng`= '".$lng."' WHERE `userid` = ".$userid);
		if(is_numeric($id))
		{
			echo json_encode(Array("error" => 0));
		}
		else {
			echo json_encode(Array("error" => 1));
			die();
		}	
	default:
		
		break;
}
}
else {
	echo json_encode(Array("error" => 1));
	die();
}

