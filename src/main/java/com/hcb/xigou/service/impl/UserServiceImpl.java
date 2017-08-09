package com.hcb.xigou.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcb.xigou.dao.interfaceClass.UsersMapper;
import com.hcb.xigou.pojo.Users;
import com.hcb.xigou.service.IUserService;

@Service("UserService")
public class UserServiceImpl implements IUserService{

	@Autowired
	UsersMapper usersMapper;
	
	@Override
	public List<Users> selectByPaging(Map<String, Object> map) {
		return usersMapper.selectByPaging(map);
	}

	@Override
	public Integer totalCount(Map<String, Object> map) {
		return usersMapper.totalCount(map);
	}
}
