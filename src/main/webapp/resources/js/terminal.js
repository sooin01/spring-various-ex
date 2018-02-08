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
	term.textarea.onkeydown = function (e) {
		console.log('User pressed key with keyCode: ', e.keyCode);
		
		if (e.keyCode == 13) {
			var command = commands.join('');
			commands = [];
		    $.post('/command/write', {command: command}).then(function(data) {
		    	term.write(data);
		    	term.focus();
		    	
		    	var shellprompt = '$ ';

		    	  term.prompt = function () {
		    	    term.write('\r\n' + shellprompt);
		    	  };
		    });
		} else if (e.keyCode == 8) {
			if (commands.length > 0) {
				commands.splice(commands.length - 1, 1);
				term.write('\b \b');
			}
		} else if (e.keyCode == 9) {
			
		} else if (e.keyCode == 16) {
			
		} else {
			commands.push(e.key);
			term.write(e.key);
		}
   	};
}