package com.hcb.xigou.service;

import java.util.Map;

import com.hcb.xigou.pojo.Users;

public interface LoginService {
	
	public Users selectByUserAndPassword(Map<String, Object> map);
	
}
