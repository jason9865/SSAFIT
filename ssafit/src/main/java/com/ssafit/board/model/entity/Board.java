package com.ssafit.board.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Board {
	private int boardId;
	private String name;
	private String description;
	private String createdAt;


}
