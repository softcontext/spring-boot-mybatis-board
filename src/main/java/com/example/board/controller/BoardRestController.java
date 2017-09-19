package com.example.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.board.dto.Pager;
import com.example.board.model.Board;
import com.example.board.repository.BoardMapper;
import com.example.user.model.User;

@RestController
@RequestMapping("/boards")
public class BoardRestController {
	@Autowired
	private BoardMapper boardMapper;
	
	@GetMapping()
	public ModelAndView getBoardsView(
			@RequestParam(name="page", required=false, defaultValue="1") int page,
			@RequestParam(name="size", required=false, defaultValue="10") int size,
			@RequestParam(name="bsize", required=false, defaultValue="5") int bsize) {
		ModelAndView mav = new ModelAndView("board_list");
		mav.addObject("boards", boardMapper.selectByLimit(page, size));
		mav.addObject("pager", new Pager(page, size, bsize, boardMapper.count()));
		return mav;
	}
	
	@GetMapping("/{id}")
	public ModelAndView getBoardView(@PathVariable long id) {
		boardMapper.increment(id);
		ModelAndView mav = new ModelAndView("board_view");
		mav.addObject("board", boardMapper.selectById(id));
		return mav;
	}
	
	@GetMapping("/write")
	public ModelAndView getInsertView() {
		ModelAndView mav = new ModelAndView("board_write");
		mav.addObject("user", new User("user@google.com", "1234567"));
		return mav;
	}
	
	@PostMapping("/write")
	public ModelAndView postInsert(Board board) {
		boardMapper.insert(board);
		ModelAndView mav = new ModelAndView("board_list");
		mav.addObject("boards", boardMapper.selectAll());
		return mav;
	}
	
	@GetMapping("/update/{id}")
	public ModelAndView getUpdateView(@PathVariable long id) {
		ModelAndView mav = new ModelAndView("board_update");
		mav.addObject("board", boardMapper.selectById(id));
		return mav;
	}
	
	@PostMapping("/update")
	public ModelAndView postUpdate(Board board) {
		System.out.println(board);
		boardMapper.update(board);
		ModelAndView mav = new ModelAndView("board_view");
		mav.addObject("board", boardMapper.selectById(board.getId()));
		return mav;
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView getDelete(@PathVariable long id) {
		boardMapper.delete(id);
		ModelAndView mav = new ModelAndView("board_list");
		mav.addObject("boards", boardMapper.selectAll());
		return mav;
	}
	
}
