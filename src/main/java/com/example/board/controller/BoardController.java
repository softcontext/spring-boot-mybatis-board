package com.example.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.board.repository.BoardMapper;

//@Controller
//@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardMapper boardMapper;

	@GetMapping("/count")
	public String count(Model model) {
		model.addAttribute("count", boardMapper.count());
		return "board_list";
	}
}
