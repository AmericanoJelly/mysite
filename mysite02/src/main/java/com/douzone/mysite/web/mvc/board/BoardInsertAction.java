package com.douzone.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.WebUtil;

public class BoardInsertAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		long no =Long.parseLong( request.getParameter("no"));
		long user_no =Long.parseLong( request.getParameter("user_no"));
	
		
		BoardVo vo = new BoardVo();
		
		if(no == user_no) {
			
		} else {
			vo = new BoardRepository().findView(no);
			new BoardRepository().c_update(vo.getG_no());
			vo.setG_no(vo.getG_no());
			vo.setO_no(vo.getO_no());
			vo.setDept(vo.getDept());
			
			System.out.println(vo.getO_no());
			System.out.println(vo.getDept());
		}

		vo.setTitle(title);
		vo.setContents(contents);
		vo.setUser_no(user_no);
		
		
		new BoardRepository().insert(vo);

		
		WebUtil.redirect(request, response, request.getContextPath()+"/board");


	}
}
