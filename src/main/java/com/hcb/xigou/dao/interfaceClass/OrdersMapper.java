package com.hcb.xigou.dao.interfaceClass;

import java.util.List;
import java.util.Map;

import com.hcb.xigou.dto.Orders;

public interface OrdersMapper {
    int deleteByPrimaryKey(Integer fakeId);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(Integer fakeId);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKeyWithBLOBs(Orders record);

    int updateByPrimaryKey(Orders record);
    
    List<Orders> searchOrderByMap(Map<String, Object> map);

	int countOrderyMap(Map<String, Object> map);
	
	Orders selectByOrderNumber(String orderNumber);
}