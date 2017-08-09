package com.hcb.xigou.dao.interfaceClass;

import java.util.List;
import java.util.Map;

import com.hcb.xigou.pojo.Users;

public interface UsersMapper {

	public Users selectByUserAndPassword(Map<String, Object> map);
	
	Integer registerCount(Map<String, Object> map);
	
	List<Users> selectByPaging(Map<String,Object> map);

    Integer totalCount(Map<String,Object> map);
}