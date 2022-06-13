package com.douzone.mysite.controller;


import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.security.AuthUser;
import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("")
	public String index(@RequestParam(value = "p", required = true, defaultValue = "1") Integer page,
			@RequestParam(value = "kwd", required = true, defaultValue = "") String kwd, Model model) {

		Map<String, Object> map = boardService.getPageAndList(page, kwd);
		model.addAttribute("map", map);
		return "board/index";
	}
	
	@RequestMapping(value="/view/{no}")
	public String view(@PathVariable("no") Long no, Model model) {
		BoardVo vo = boardService.getContents(no);
		boardService.updateHit(no);
		model.addAttribute("boardVo", vo);
		
		return "board/view";
	}
	

	@Auth
	@RequestMapping(value ={"/write", "/write/{no}"}, method = RequestMethod.GET)
	public String write(@PathVariable(value="no", required = false) Long no, Model model) {
		BoardVo vo = boardService.getContents(no);
		model.addAttribute("boardVo", vo);
		return "/board/write";
	}

	@Auth
	@RequestMapping(value = {"/write","/write/{no}"}, method = RequestMethod.POST)
	public String write(@AuthUser UserVo authUser, @PathVariable(value="no", required = false) Long no ,BoardVo vo) {
		vo.setUser_no(authUser.getNo());
		boardService.write(vo); 
		System.out.println("컨트롤러"+vo);
		return "redirect:/board";
	}
	

	@Auth
	@RequestMapping("/modify/{no}")
	public String modify(@PathVariable("no") Long no, Model model) {
		BoardVo vo = boardService.getContents(no);
		model.addAttribute("boardVo", vo);
		return "/board/modify";
	}
	
	@Auth
	@RequestMapping(value = "/modify/{no}", method = RequestMethod.POST)
	public String modify(@PathVariable("no") Long no, BoardVo vo) {
		boardService.Update(vo);
		return "redirect:/board/view/{no}";
	}
	
	@Auth
	@RequestMapping("/delete/{no}")
	public String delete(@PathVariable("no") Long no) {
		boardService.delete(no);
		return "redirect:/board";
	}
	
	
}

