package com.my.app.command.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.my.app.command.service.CommandService;
import com.my.app.command.vo.ConnectionInfoVo;

@Controller
public class CommandController {

	@Autowired
	private CommandService commandService;

	@GetMapping(value = "/command/index")
	public String index() {
		return "command/index";
	}

	@GetMapping(value = "/command/index2")
	public String index2() {
		return "command/index2";
	}

	@PostMapping(value = "/command/connect")
	public ResponseEntity<Void> connect(HttpSession session, ConnectionInfoVo connectionInfoVo) {
		commandService.connect(session, connectionInfoVo);
		return ResponseEntity.ok().build();
	}

	@PostMapping(value = "/command/write")
	public ResponseEntity<Void> command(HttpSession session, String command) {
		commandService.command(session, command);
		return ResponseEntity.ok().build();
	}

}
