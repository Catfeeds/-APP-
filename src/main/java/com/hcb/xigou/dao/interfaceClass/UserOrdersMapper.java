package com.hcb.xigou.dao.interfaceClass;

import java.util.List;
import java.util.Map;

import com.hcb.xigou.dto.Orders;
import com.hcb.xigou.dto.UserOrders;

public interface UserOrdersMapper {

	List<UserOrders> searchUsersOrderByMap(Map<String, Object> map);

	int countUsersOrderByMap(Map<String, Object> map);

	List<Orders> searchOrderByMap(Map<String, Object> map);
	
	List<Map<String, Object>> searchOrderExcelportByMap(Map<String, Object> map);

	int countOrderyMap(Map<String, Object> map);
	
}
