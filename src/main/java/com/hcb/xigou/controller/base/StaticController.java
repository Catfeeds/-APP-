package com.hcb.xigou.controller.base;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StaticController{

	@RequestMapping(value="user-cn.html" , method = RequestMethod.GET)
	public String userCn(ModelMap model) {
		return "user-cn";
	}
	
	@RequestMapping(value="support.html" , method = RequestMethod.GET)
	public String support(ModelMap model) {
		return "support";
	}
}
