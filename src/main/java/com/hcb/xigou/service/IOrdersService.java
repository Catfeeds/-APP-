package com.hcb.xigou.service;

import java.util.List;
import java.util.Map;

import com.hcb.xigou.dto.Orders;

public interface IOrdersService {

	List<Orders> searchOrderByMap(Map<String, Object> map);

	int countOrderyMap(Map<String, Object> map);

}
