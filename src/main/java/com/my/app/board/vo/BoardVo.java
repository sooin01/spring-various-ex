package com.my.app.board.vo;

import java.sql.Timestamp;

public class BoardVo {

	private Integer seq;
	private String title;
	private String content;
	private String userId;
	private Boolean isDelete;
	private Timestamp createDt;
	private Timestamp updateDt;

	public BoardVo() {

	}

	public BoardVo(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Timestamp getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
	}

	public Timestamp getUpdateDt() {
		return updateDt;
	}

	public void setUpdateDt(Timestamp updateDt) {
		this.updateDt = updateDt;
	}

	@Override
	public String toString() {
		return "BoardVo [seq=" + seq + ", title=" + title + ", content=" + content + ", userId=" + userId
				+ ", isDelete=" + isDelete + ", createDt=" + createDt + ", updateDt=" + updateDt + "]";
	}

}
