package com.hcb.xigou.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcb.xigou.dao.interfaceClass.UserManageMapper;
import com.hcb.xigou.dao.interfaceClass.UsersMapper;
import com.hcb.xigou.dto.UserManage;
import com.hcb.xigou.service.UserManageService;

@Service("UserManageService")
public class UserManageServiceImpl implements UserManageService {
	@Autowired
	UserManageMapper userManageMapper;
	@Autowired
	UsersMapper usersMapper;
	
	@Override
	public List<UserManage> searchUserMagageByMap(Map<String, Object> map) {
		return userManageMapper.searchUserMagageByMap(map);
	}

	@Override
	public int countUserMagageByMap(Map<String, Object> map) {
		return userManageMapper.countUserMagageByMap(map);
	}

	@Override
	public List<UserManage> searchMemberMagageByMap(Map<String, Object> map) {
		return userManageMapper.searchMemberMagageByMap(map);
	}

	@Override
	public int countMemberMagageByMap(Map<String, Object> map) {
		return userManageMapper.countMemberMagageByMap(map);
	}

	@Override
	public int deleteByUsersUuids(Map<String, Object> map) {
		return userManageMapper.deleteByUsersUuids(map);
	}

	@Override
	public int countUsers(Map<String, Object> map) {
		return userManageMapper.countUsers(map);
	}

	@Override
	public int countMemberUsers(Map<String, Object> map) {
		return userManageMapper.countMemberUsers(map);
	}

	@Override
	public Integer registerCount(Map<String, Object> map) {
		return usersMapper.registerCount(map);
	}

}
