package com.douzone.mysite.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public String handlerException(Model model, Exception e) {
		//1. 로깅(logging)
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));//화면에 안나오고 객체의 스트링버퍼안으로 들어감
		e.printStackTrace(); //화면출력
		
		//2. 사과 페이지(종료)
		model.addAttribute("exception", errors.toString());
		return "error/exception";
	}

}