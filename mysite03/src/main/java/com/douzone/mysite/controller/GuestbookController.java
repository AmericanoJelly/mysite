package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.GuestBookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {
	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String list(Model model) {
		List<GuestBookVo>list = guestbookService.getMessageList();
		model.addAttribute("list", list);
		return "guestbook/list";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(GuestBookVo vo) {
		boolean guestbook = guestbookService.getAdd(vo);
		return "redirect:/guestbook";
	}
	
	@RequestMapping(value = "/delete/{no}", method = RequestMethod.GET)
	public String delete(@PathVariable("no") Long no, Model model) {
		model.addAttribute("no", no);
		return "/guestbook/delete";
	}
	
	@RequestMapping(value = "/delete/{no}", method = RequestMethod.POST)
	public String deleteMassage(@PathVariable("no") Long no,
			@RequestParam(value = "password", required = true, defaultValue = "")String password) {
		boolean guestbook = guestbookService.deleteMassage(no, password);
		return "redirect:/guestbook";
	}
	
	
}
