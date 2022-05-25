package com.douzone.mysite.web.mvc.guest;

import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class GuestBookActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
Action action = null;
		
		if("deleteform".equals(actionName)) {
			action = new DeleteFromAction();
		}else if("add".equals(actionName)) {
			action = new AddAction();
		}else if("delete".equals(actionName)) {
			action = new DeleteAction();
		}else {
			action = new IndexAction();
		}
		return action;
	}

}
