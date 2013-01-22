<?php
require_once 'db.class.php';
$db = new DB();
$db->insert("DELETE FROM tokens WHERE timestamp < (NOW() - INTERVAL 60 MINUTE)");
