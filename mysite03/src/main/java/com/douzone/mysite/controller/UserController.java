package com.douzone.mysite.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.security.AuthUser;
import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;

//@Auth //admin controller시에는 여기 붙여야한다.
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(@ModelAttribute UserVo userVo) { //메서드
		return "user/join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	   public String join(@ModelAttribute @Valid UserVo userVo, BindingResult result, Model model) {
	      if(result.hasErrors()) {
//	         List<ObjectError> list = result.getAllErrors();
//	         for(ObjectError error : list) {
//	            System.out.println(error);
//	         }
	    	  
	    	 model.addAllAttributes(result.getModel());
//	    	 model.addAttribute("userVo",userVo);	//@ModelAttribute가 자동으로 넘겨줌
	         return "user/join";
	      }
	      
	      userService.join(userVo);
	      return "redirect:/user/joinsuccess";
	   }
	
	@RequestMapping("/joinsuccess")
	public String joinSuccess() {
		return "user/joinsuccess";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() { 
		return "user/login";
	}
	
	@RequestMapping(value="/auth")
	public void auth() { 
	}
	
	@RequestMapping(value="/logout")
	public void logout() { 
	}
	
	@Auth
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(@AuthUser UserVo authUser, Model model) { 
		
		Long no = authUser.getNo();
		UserVo userVo = userService.getUser(no);
		model.addAttribute("userVo", userVo);
		return "user/update";
	}
	
	@Auth
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(@AuthUser UserVo authUser, UserVo vo) { 
		vo.setNo(authUser.getNo());
		userService.updateUser(vo);
		authUser.setName(vo.getName());
		
		return "redirect:/";
	}
	

	
//	@RequestMapping(value="/login", method=RequestMethod.POST)
//	public String login(HttpSession session, UserVo vo, Model model) { 
//		UserVo authUser = userService.getUser(vo); //세션처리를 위해 user받아와야함
//		if(authUser == null) {
//			model.addAttribute("result","fail");
//			model.addAttribute("email",vo.getEmail());
//			return "user/login";
//		}
//		
//		/*인증처리*/
//		session.setAttribute("authUser", authUser);
//		return "redirect:/";
//	}
//    
//	@RequestMapping("/logout")
//	public String logout(HttpSession session) {
//		session.removeAttribute("authUser");
//		session.invalidate();
//		return "redirect:/";
//	}
}
