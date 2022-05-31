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
		long user_no =Long.parseLong( request.getParameter("user_no"));
	
		
		BoardVo vo = new BoardVo();

		vo.setTitle(title);
		vo.setContents(contents);
		vo.setUser_no(user_no);
		
		
		new BoardRepository().insert(vo);

		
		WebUtil.redirect(request, response, request.getContextPath()+"/board");


	}
}
