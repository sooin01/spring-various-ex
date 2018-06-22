package com.my.app.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/main")
public class MainController {

	@GetMapping("/index")
	public String index() {
		return "main/index";
	}

	@GetMapping("/dup")
	@ResponseBody
	public String dup() {
		return "{\"result\" : \"success\"}";
	}

}
