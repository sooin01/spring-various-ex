package com.my.app.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.app.board.common.dao.CommonDao;
import com.my.app.board.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private CommonDao commonDao;

	public List<BoardVo> getBoardList() {
		return commonDao.selectList("board.getBoard");
	}

	public BoardVo getBoard(Integer id) {
		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("id", id);
		return commonDao.selectOne("board.getBoard", parameterMap);
	}

}