package com.douzone.mysite.web.mvc.board;

import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		
		Action action = null;
		
		if("modify".equals(actionName)) {
			action = new BoardModifyAction();
			
		}else if("update".equals(actionName)) {
			System.out.println("update");
			action = new BoardUpdateAction();
			
		}else if("view".equals(actionName)) {
			action = new BoardViewAction();
			
		}else if("write".equals(actionName)) {
			action = new BoardWriteAction();
			
		}else if("comment".equals(actionName)) {
			action = new  BoardCommentAction();
			
		}else if("delete".equals(actionName)) {
			action = new BoardDeleteAction();
			
		}else if("insert".equals(actionName)) {
			System.out.println("인서트1");
			action = new BoardInsertAction();
			
		}else {
			action = new IndexAction(); 
			
		}
		return action;
	}
}
