package com.hcb.xigou.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hcb.xigou.dao.interfaceClass.UserOrdersMapper;
import com.hcb.xigou.dto.UserOrders;
import com.hcb.xigou.service.UserOrdersService;

public class UserOrdersServiceImpl implements UserOrdersService {
	
	@Autowired
	UserOrdersMapper userOrdersMapper;
	
	
	@Override
	public List<UserOrders> searchUsersOrderByMap(Map<String, Object> map) {
		return userOrdersMapper.searchUsersOrderByMap(map);
	}

	@Override
	public int countUsersOrderByMap(Map<String, Object> map) {
		return userOrdersMapper.countUsersOrderByMap(map);
	}
	
}