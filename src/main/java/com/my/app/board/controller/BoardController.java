package com.my.app.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.app.board.service.BoardService;
import com.my.app.board.vo.BoardVo;

@Controller
@RequestMapping(value = "/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@GetMapping(value = "/index")
	public String index() {
		System.out.println("test");
		return "board/index";
	}

	@GetMapping(value = "/list")
	@ResponseBody
	public List<BoardVo> getBoardList(ModelMap model) {
		return boardService.getBoardList();
	}

	@GetMapping(value = "/write")
	public String write() {
		return "board/write";
	}

}
