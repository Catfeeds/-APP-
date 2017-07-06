package com.hcb.xigou;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hcb.xigou.pojo.Users;
import com.hcb.xigou.service.LoginService;

@Controller
public class LoginController {
	
	private final String KEY_SESSION_USER = "userSession";
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping("login")
	public String loginIndex(HttpServletRequest req, HttpServletResponse res, ModelMap model,@RequestParam(required = true) String userUuid,@RequestParam(required = true) String password) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("userNum", userUuid);
		map.put("password", password);
		Users loginUser = loginService.selectByUserAndPassword(map);
		HttpSession session = req.getSession(true);
		session.setAttribute(KEY_SESSION_USER,loginUser);
		if (loginUser==null) {
			return "redirect:/login";
		}else{
			return "";
		}
	}
}
