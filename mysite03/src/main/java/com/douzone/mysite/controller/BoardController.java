package com.douzone.mysite.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;


@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("")
	public String index(@RequestParam(value = "p", required = true, defaultValue = "1") int page,
			@RequestParam(value = "kwd", required = true, defaultValue = "") String kwd, Model model) {
		int lastPage = (boardService.count(kwd) - 1) / 5 + 1;

		model.addAttribute("currentPage", page);
		model.addAttribute("list", boardService.getMessageList(page, kwd));
		model.addAttribute("count", boardService.count(kwd) - (5 * (page - 1)));
		model.addAttribute("startPage", boardService.getPage(page, lastPage).get("startPage"));
		model.addAttribute("endPage", boardService.getPage(page, lastPage).get("endPage"));
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("kwd", kwd);
		return "board/index";
	}
	
	@RequestMapping("/view/{no}")
	public String view(@PathVariable("no") Long no, Model model) {
		BoardVo vo = boardService.getContents(no);
		model.addAttribute("boardVo", vo);
		return "board/view";
	}
	
//	@RequestMapping("")
//	public String getContents(@PathVariable("num") Long num, Model model) {
//		BoardVo vo = boardService.getContents(num);
//		model.addAttribute("boardVo", vo);
//		return "board/index";
//	}

}
