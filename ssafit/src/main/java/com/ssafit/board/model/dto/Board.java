package com.ssafit.board.model.dto;

public class Board {
	int id;
	String name;
	String description;
	String createdAt;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	@Override
	public String toString() {
		return "Board [name=" + name + ", description=" + description + ", createdAt=" + createdAt + "]";
	}

}
