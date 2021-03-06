package com.my.app.board.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.my.app.board.vo.BoardVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/root-context.xml", "classpath:spring/database-context.xml" })
public class BoardServiceTest {

	@Autowired
	private BoardService boardService;

	@Test
	public void testInsertBoard() {
		BoardVo boardVo = new BoardVo();
		boardVo.setTitle("테스트? 제목");
		boardVo.setContent("테스트? 내용");
		boardVo.setUserId("test");
		boardService.insertBoard(boardVo);
	}

	@Test
	public void testGetBoardList() {
		boardService.getBoardList();
	}

	@Test
	public void testGetBoard() {
		boardService.getBoard(1);
	}

	@Test
	public void testInsertBoardBulk() {
		int count = boardService.insertBoardBulk();
		System.out.println(count);
	}

}
