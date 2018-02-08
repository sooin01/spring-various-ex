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
	
	var commands = [];
	term.on('key', function (key, ev) {
		var printable = (!ev.altKey && !ev.altGraphKey && !ev.ctrlKey && !ev.metaKey);

	    if (ev.keyCode == 13) {
	    	var command = commands.join('');
			commands = [];
	    	$.post('/command/write', {command: command}).then(function(data) {
		    	term.write(data);
		    	term.focus();
		    });
	    } else if (ev.keyCode == 8) {
	    	if (commands.length > 0) {
				commands.splice(commands.length - 1, 1);
				term.write('\b \b');
			}
	    } else if (printable) {
	    	commands.push(ev.key);
			term.write(ev.key);
	    }
	});
	
	term.on('paste', function (data, ev) {
		term.write(data);
	});
}