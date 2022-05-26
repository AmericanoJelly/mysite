package com.douzone.mysite.web.mvc.user;

import com.douzone.mysite.web.mvc.main.DefaultAction;
import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class UserActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if("joinform".equals(actionName)) {
			System.out.println("joinform");
			action = new JoinFormAction();
		}else if("join".equals(actionName)) {
			System.out.println("join");
			action = new JoinAction();
		}else if("joinsuccess".equals(actionName)) {
			System.out.println("joinsuccess");
			action = new JoinSuccess();
		}else if("loginform".equals(actionName)) {
			System.out.println("loginform");
			action = new LoginFormAction();
		}else if("login".equals(actionName)) {
			System.out.println("login");
			action = new LoginAction();
		}else {
			action = new DefaultAction();
		}
		return action;
	}

}
