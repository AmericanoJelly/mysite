package com.douzone.mysite.web.mvc.guest;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.GuestbookRepository;
import com.douzone.mysite.vo.GuestBookVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.WebUtil;

public class IndexAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<GuestBookVo> list = new GuestbookRepository().findAll();

		request.setAttribute("list", list);
		
		WebUtil.forward(request, response, "guestbook/list");
	}

}
