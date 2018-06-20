package com.my.app.websocket.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "websocket")
public class WebSocketController {

	@GetMapping(value = "/index")
	public String index() {
		return "websocket/index";
	}

	@MessageMapping("/websocket/test1")
	@SendTo("/topic/websocket/test1")
	public String stomp(String message) {
		System.out.println("Message: " + message);
		return "{\"result\":\"success\"}";
	}

}
