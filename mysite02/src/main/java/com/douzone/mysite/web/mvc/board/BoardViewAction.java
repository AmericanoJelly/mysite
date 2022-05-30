package com.douzone.mysite.web.mvc.board;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.WebUtil;

public class BoardViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		String no = request.getParameter("no");
		BoardVo vo = new BoardRepository().findView(Long.parseLong(no));
		request.setAttribute("vo", vo);
		
		WebUtil.forward(request, response, "board/view");

	}

}
