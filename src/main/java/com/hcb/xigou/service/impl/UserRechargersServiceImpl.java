package com.hcb.xigou.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcb.xigou.dao.interfaceClass.UserRechargersMapper;
import com.hcb.xigou.dto.UserRechargers;
import com.hcb.xigou.service.UserRechargersService;

@Service("UserRechargersService")
public class UserRechargersServiceImpl implements UserRechargersService {
	
	@Autowired
	UserRechargersMapper userRechargersMapper;
	
	@Override
	public List<UserRechargers> searchUserRechargersByMap(Map<String, Object> map) {
		
		return userRechargersMapper.searchUserRechargersByMap(map);
	}

	@Override
	public int countUserRechargersByMap(Map<String, Object> map) {
		
		return userRechargersMapper.countUserRechargersByMap(map);
	}

	@Override
	public List<UserRechargers> searchUserRechargersExcelportByMap(Map<String, Object> map) {
		return userRechargersMapper.searchUserRechargersExcelportByMap(map);
	}

}
