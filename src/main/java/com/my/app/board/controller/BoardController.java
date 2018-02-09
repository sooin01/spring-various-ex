package com.my.app.board.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.my.app.board.service.BoardService;
import com.my.app.board.vo.BoardVo;

@Controller
@RequestMapping(value = "/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@Autowired
	private MessageSource messageSource;

	@GetMapping(value = "/index")
	public String index(Locale locale, HttpServletRequest request) {
		String message = messageSource.getMessage("name", null, RequestContextUtils.getLocale(request));
		System.out.println("message: " + message);
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
