<?php
$serverip = "localhost";
$info = json_decode( file_get_contents( 'http://mcapi.ca/query/'.$serverip.'/players' ), true ); 
if(!$info['status']) {
	echo 'The server is offline';
} else {
	echo $info['players']['online'].'/'.$info['players']['max'];
}
?>