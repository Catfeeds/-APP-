package com.hcb.xigou.dao.interfaceClass;

import java.util.List;
import java.util.Map;

import com.hcb.xigou.dto.UserOrders;

public interface UserOrdersMapper {

	List<UserOrders> searchUsersOrderByMap(Map<String, Object> map);

	int countUsersOrderByMap(Map<String, Object> map);
	
}
