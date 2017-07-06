package com.hcb.xigou.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcb.xigou.dao.interfaceClass.UsersMapper;
import com.hcb.xigou.pojo.Users;
import com.hcb.xigou.service.LoginService;

@Service("LoginService")
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	UsersMapper usesrMapper;
	
	@Override
	public Users selectByUserAndPassword(Map<String, Object> map) {
		return usesrMapper.selectByUserAndPassword(map);
	}

}
