package com.example.board.model;

import java.util.Date;

import lombok.Data;

@Data
public class Board {
	private long id;
	private String writer;
	private String title;
	private String content;
	private Date regDate;
	private long hitCount;

	public static int seekOffset(int page, int size) {
		if (page > 0) {
			return (page - 1) * size;
		}
		return 0;
	}
}
