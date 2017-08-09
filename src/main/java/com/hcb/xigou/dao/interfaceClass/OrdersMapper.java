package com.hcb.xigou.dao.interfaceClass;

import java.util.List;
import java.util.Map;

import com.hcb.xigou.dto.Orders;

public interface OrdersMapper {
	
    int deleteByPrimaryKey(Integer fakeId);
    
    Orders selectByPrimaryKey(Integer fakeId);

	List<Orders> searchOrderByMap(Map<String, Object> map);

	int countOrderyMap(Map<String, Object> map);
	
	Orders selectByOrderNumber(String orderNumber);
}