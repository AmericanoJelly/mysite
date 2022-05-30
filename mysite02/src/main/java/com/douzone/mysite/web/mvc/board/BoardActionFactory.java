package com.douzone.mysite.web.mvc.board;

import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		
		Action action = null;
		
		if("modify".equals(actionName)) {
			System.out.println("modify");
			action = new BoardModifyAction();
		}else if("view".equals(actionName)) {
			System.out.println("view");
			action = new BoardViewAction();
		}else if("write".equals(actionName)) {
			System.out.println("write");
			action = new BoardWriteAction();
		}else {
			action = new IndexAction(); 
		}
		return action;
	}
}
