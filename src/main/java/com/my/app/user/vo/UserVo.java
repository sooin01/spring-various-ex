package com.my.app.user.vo;

public class UserVo {

	private String userId;

	public UserVo() {
		super();
	}

	public UserVo(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "UserVo [userId=" + userId + "]";
	}

}
