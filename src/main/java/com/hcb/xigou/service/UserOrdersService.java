package com.hcb.xigou.service;

import java.util.List;
import java.util.Map;

import com.hcb.xigou.dto.Orders;
import com.hcb.xigou.dto.UserOrders;

public interface UserOrdersService {

	List<UserOrders> searchUsersOrderByMap(Map<String, Object> map);

	int countUsersOrderByMap(Map<String, Object> map);

	List<Orders> searchOrderByMap(Map<String, Object> map);

	List<UserOrders> searchOrderExcelportByMap(Map<String, Object> map);
	
	int countOrderyMap(Map<String, Object> map);

}
