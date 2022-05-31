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
		System.out.println("인서트들어왔다아아");
		//String user_no = request.getParameter("user_no");
		
		//long userNo = Long.parseLong(user_no);
		
		BoardVo vo = new BoardVo();

		vo.setTitle(title);
		vo.setContents(contents);
		//vo.setUser_no(userNo);

		new BoardRepository().insert(vo);

		WebUtil.redirect(request, response, request.getContextPath()+"/board");


	}
}
