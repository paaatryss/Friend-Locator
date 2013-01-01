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
		if($ret!=FALSE)
		{
			echo json_encode(Array("error" => 0));
		}
		else {
			echo json_encode(Array("error" => 1));
			die();
		}
			
		break;
	case 'acceptinvid':
		if(isset($_GET['toid']) && is_numeric($_GET['toid']))
			$recvrid = $_GET['toid'];
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

