package com.hcb.xigou.dao.interfaceClass;

import java.util.Map;

import com.hcb.xigou.pojo.Users;

public interface UsersMapper {

	public Users selectByUserAndPassword(Map<String, Object> map);
	
	
}