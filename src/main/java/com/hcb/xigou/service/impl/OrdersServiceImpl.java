package com.hcb.xigou.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hcb.xigou.dao.interfaceClass.OrdersMapper;
import com.hcb.xigou.dto.Banners;
import com.hcb.xigou.dto.Orders;
import com.hcb.xigou.service.IOrdersService;

public class OrdersServiceImpl implements IOrdersService {
	@Autowired
	OrdersMapper ordersMapper;

	@Override
	public List<Orders> searchOrderByMap(Map<String, Object> map) {
		return ordersMapper.searchOrderByMap(map);
	}

	@Override
	public int countOrderyMap(Map<String, Object> map) {
		return ordersMapper.countOrderyMap(map);
	}
	
	
}
