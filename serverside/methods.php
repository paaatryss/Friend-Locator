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
			$invitations[] = Array($line['invid'] => $line['login']);
		}
		echo json_encode($invitations);
		break;
	case 'sendinvitation':
		if(isset($_GET['toid']) && is_numeric($_GET['toid']))
			$recvrid = $_GET['toid'];
		else {
			echo json_encode(Array("error" => 1));
			die();
		}
		$ret = $db->insert("INSERT INTO `invitations`(`senderid`, `recvrid`) VALUES (".$userid.",".$recvrid.")");
		if(is_numeric($ret))
		{
			echo json_encode(Array("error" => 0));
		}
		else {
			echo json_encode(Array("error" => 1));
			die();
		}
			
		break;
	case 'acceptinv':
		if(isset($_GET['id']) && is_numeric($_GET['id']))
			$invid = $_GET['id'];
		else {
			echo json_encode(Array("error" => 1));
			die();
		}
		$ret = $db->insert("INSERT INTO `friendship`(`user1id`, `user2id`) VALUES (
		(SELECT senderid FROM `invitations` WHERE invid=".$invid."), 
		(SELECT recvrid FROM `invitations` WHERE invid=".$invid."))");
		if(is_numeric($ret))
		{
			$db->insert("DELETE FROM `invitations` WHERE invid=".$invid);
			echo json_encode(Array("error" => 0));
		}
		else {
			echo json_encode(Array("error" => 1));
			die();
		}
		break;
	case 'rejectinv':
		if(isset($_GET['id']) && is_numeric($_GET['id']))
			$invid = $_GET['id'];
		else {
			echo json_encode(Array("error" => 1));
			die();
		}
		$db->insert("DELETE FROM `invitations` WHERE invid=".$invid);
			echo json_encode(Array("error" => 0));
	case 'rejectinv':
		if(isset($_GET['id']) && is_numeric($_GET['id']))
			$invid = $_GET['id'];
		else {
			echo json_encode(Array("error" => 1));
			die();
		}
		$db->insert("DELETE FROM `invitations` WHERE invid=".$invid);
			echo json_encode(Array("error" => 0));
		break;
	case 'getfrlocation':
		$ret = $db->fetch_array("SELECT login, lat, lng FROM `positions`, `friendship`, `users`
WHERE `friendship`.`user1id` = `positions`.`userid` AND `users`.`id` = `friendship`.`user2id` AND `positions`.`userid` = ".$userid);
		foreach($ret as $line)
		{
			$frlocation[] = Array($line['login'] => Array('lat' => $line['lat'], 'lng' => $line['lng']));
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

