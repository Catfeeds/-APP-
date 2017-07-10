package com.hcb.xigou.dao.interfaceClass;

import java.util.List;
import java.util.Map;

import com.hcb.xigou.dto.UserManage;

public interface UserManageMapper {

	List<UserManage> searchUserMagageByMap(Map<String, Object> map);

	int countUserMagageByMap(Map<String, Object> map);

	List<UserManage> searchMemberMagageByMap(Map<String, Object> map);

	int countMemberMagageByMap(Map<String, Object> map);

}
