package com.ssafit.board.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Board {
	private int boardId;
	private String name;
	private String description;
	private String createdAt;

	@Builder
	public Board(final int boardId, final String name, final String description, final String createdAt){
		this.boardId = boardId;
		this.name = name;
		this.description = description;
		this.createdAt = createdAt;
	}

}
