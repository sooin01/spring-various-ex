Terminal.applyAddon(attach); // Apply the 'attach' addon
Terminal.applyAddon(fit); // Apply the 'fit' addon
Terminal.applyAddon(winptyCompat); // Apply the 'winptyCompat' addon

function terminal2(val) {
	var term = new Terminal({
		cursorBlink: true,
		scrollback: 1000,
		tabStopWidth: 8
	});
	var socket = new WebSocket('ws://localhost:8080/ws/command');
	
	term.attach(socket, true, true); // Attach the above socket to 'term'
	
	term.open(document.getElementById('terminal'));
	term.write(val);
	term.winptyCompatInit();
	term.fit();
	term.focus();
	term.textarea.onkeydown = function (e) {
		console.log('User pressed key with keyCode: ', e.keyCode);
	};
	term.attachCustomKeyEventHandler(function (e) {
		if (e.keyCode == 9) {
			return false;
		}
	});
}