Terminal.applyAddon(attach); // Apply the 'attach' addon
Terminal.applyAddon(fit); // Apply the 'fit' addon
Terminal.applyAddon(winptyCompat); // Apply the 'winptyCompat' addon

function terminal() {
	var term = new Terminal({
		cursorBlink: true,
		scrollback: 1000,
		tabStopWidth: 8
	});
	term.open(document.getElementById('terminal'));
	term.winptyCompatInit();
	term.fit();
	term.focus();
	
	var socket = new WebSocket('ws://localhost:8080/ws/command');
	socket.onopen = function()
    {
		term.attach(socket); // Attach the above socket to 'term'
    }
}