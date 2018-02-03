package com.my.app.command.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.my.app.command.service.CommandService;
import com.my.app.command.vo.SessionVo;

@Controller
public class CommandController {

	@Autowired
	private CommandService commandService;

	@GetMapping(value = "/cli/index")
	public String index() {
		return "cli/index";
	}

	@PostMapping(value = "/cli/command")
	public ResponseEntity<String> command(HttpSession session, @RequestBody String command) {
		SessionVo sessionVo = commandService.command(session, command);
		String response = sessionVo.getResponse();
		return ResponseEntity.ok(response);
	}

}
