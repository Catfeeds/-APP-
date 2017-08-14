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

	@Override
	public List<Users> selectByUserList(Map<String, Object> map) {
		return usersMapper.selectByUserList(map);
	}

	@Override
	public Integer totalCountUserList(Map<String, Object> map) {
		return usersMapper.totalCountUserList(map);
	}

	@Override
	public List<Users> selectByUserListExcelport(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return usersMapper.selectByUserListExcelport(map);
	}

	@Override
	public List<Users> selectByMemberExcelport(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return usersMapper.selectByMemberExcelport(map);
	}
}
