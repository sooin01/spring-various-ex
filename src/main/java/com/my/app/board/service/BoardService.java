package com.my.app.board.service;

import java.util.ArrayList;
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

	public BoardVo getBoard(Integer seq) {
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("seq", seq);
		return commonDao.selectOne("board.getBoard", parameter);
	}

	public int insertBoardBulk() {
		List<BoardVo> boardVos = new ArrayList<>();
		boardVos.add(new BoardVo("title1", "content1", "test"));
		boardVos.add(new BoardVo("title2", "content2", "test"));
		boardVos.add(new BoardVo("title3", "content3", "test"));
		return commonDao.insert("board.insertBoardBulk", boardVos);
	}

}
