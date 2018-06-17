package com.my.app.board.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@Autowired
	private CacheManager cacheManager;

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
	public String writeForm() {
		return "board/write";
	}

	@PostMapping(value = "/write")
	@ResponseBody
	public BoardVo write(@RequestBody BoardVo boardVo) {
		System.out.println("requestBody: " + boardVo.getContent());
		return boardVo;
	}

	@GetMapping(value = "/cache")
	public String cache() {
		Cache cache = cacheManager.getCache("default");
		cache.put("aaa", "123");
		System.out.println(cache.get("aaa").get());
		cache.evict("aaa");
		System.out.println(cache.get("aaa"));
		return "board/index";
	}

}
