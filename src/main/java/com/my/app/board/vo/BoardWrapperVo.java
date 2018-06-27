package com.my.app.board.vo;

import java.util.List;

import com.my.app.user.vo.UserVo;

public class BoardWrapperVo {

	private List<BoardVo> boardVos;
	private List<UserVo> userVos;

	public List<BoardVo> getBoardVos() {
		return boardVos;
	}

	public void setBoardVos(List<BoardVo> boardVos) {
		this.boardVos = boardVos;
	}

	public List<UserVo> getUserVos() {
		return userVos;
	}

	public void setUserVos(List<UserVo> userVos) {
		this.userVos = userVos;
	}

	@Override
	public String toString() {
		return "BoardWrapperVo [boardVos=" + boardVos + "]";
	}

}
