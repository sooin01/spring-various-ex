package com.my.app.command.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.my.app.command.service.CommandService;
import com.my.app.command.vo.ConnectionInfoVo;
import com.my.app.command.vo.SessionVo;

@Controller
public class CommandController {

	@Autowired
	private CommandService commandService;

	@GetMapping(value = "/command/index")
	public String index() {
		return "command/index";
	}

	@PostMapping(value = "/command/connect")
	public ResponseEntity<String> connect(HttpSession session, ConnectionInfoVo connectionInfoVo) {
		SessionVo sessionVo = commandService.connect(session, connectionInfoVo);
		String response = sessionVo.getResponse();
		return ResponseEntity.ok(response);
	}

	@PostMapping(value = "/command/write")
	public ResponseEntity<String> command(HttpSession session, String command) {
		SessionVo sessionVo = commandService.command(session, command);
		String response = sessionVo.getResponse();
		return ResponseEntity.ok(response);
	}

}
