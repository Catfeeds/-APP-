package com.hcb.xigou.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcb.xigou.dao.interfaceClass.UserOrdersMapper;
import com.hcb.xigou.dto.Orders;
import com.hcb.xigou.dto.UserOrders;
import com.hcb.xigou.service.UserOrdersService;

@Service("UserOrdersService")
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

	@Override
	public List<Orders> searchOrderByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userOrdersMapper.searchOrderByMap(map);
	}

	@Override
	public int countOrderyMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userOrdersMapper.countOrderyMap(map);
	}

	@Override
	public List<Map<String, Object>> searchOrderExcelportByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userOrdersMapper.searchOrderExcelportByMap(map);
	}
	
}
