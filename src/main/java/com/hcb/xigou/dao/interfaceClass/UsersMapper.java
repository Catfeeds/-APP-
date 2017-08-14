package com.hcb.xigou.dao.interfaceClass;

import java.util.List;
import java.util.Map;

import com.hcb.xigou.pojo.Users;

public interface UsersMapper {

	public Users selectByUserAndPassword(Map<String, Object> map);
	
	Integer registerCount(Map<String, Object> map);
	
	List<Users> selectByPaging(Map<String,Object> map);
	
	List<Users> selectByMemberExcelport(Map<String,Object> map);

    Integer totalCount(Map<String,Object> map);
    
    List<Users> selectByUserList(Map<String,Object> map);
    
    List<Users> selectByUserListExcelport(Map<String,Object> map);
	
    Integer totalCountUserList(Map<String,Object> map);
}