package com.ssafy.ws.model.dto;

public class File {
	int id;
	String originFile;
	String saveFolder;
	String saveFile;
	int articleId;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOriginFile() {
		return originFile;
	}
	public void setOriginFile(String originFile) {
		this.originFile = originFile;
	}
	public String getSaveFolder() {
		return saveFolder;
	}
	public void setSaveFolder(String saveFolder) {
		this.saveFolder = saveFolder;
	}
	public String getSaveFile() {
		return saveFile;
	}
	public void setSaveFile(String saveFile) {
		this.saveFile = saveFile;
	}
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	@Override
	public String toString() {
		return "File [originFile=" + originFile + ", saveFolder=" + saveFolder + ", saveFile=" + saveFile
				+ ", articleId=" + articleId + "]";
	}


}
