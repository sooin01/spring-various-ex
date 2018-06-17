package com.my.app.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.app.board.vo.BoardVo;
import com.my.app.common.dao.CommonDao;

@Service
public class BoardService {

	@Autowired
	private CommonDao commonDao;

	public int insertBoard(BoardVo boardVo) {
		return commonDao.insert("board.insertBoard", boardVo);
	}

	public List<BoardVo> getBoardList() {
		return commonDao.selectList("board.getBoard");
	}

	public BoardVo getBoard(Integer id) {
		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("id", id);
		return commonDao.selectOne("board.getBoard", parameterMap);
	}

}
