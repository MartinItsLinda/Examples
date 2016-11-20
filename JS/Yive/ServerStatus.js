function ServerStatus(serverip){
	$.getJSON('http://mcapi.ca/query/' + serverip + '/players', function(json){
		if (json.status !== true) {
			$('#players').html("0");
		} else {
			$("#players").html(json.players.online);	
		}
	});
}
