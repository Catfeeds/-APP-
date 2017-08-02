package com.hcb.xigou.service;

import java.util.List;
import java.util.Map;

import com.hcb.xigou.dto.UserManage;

public interface UserManageService {

	List<UserManage> searchUserMagageByMap(Map<String, Object> map);

	int countUserMagageByMap(Map<String, Object> map);

	List<UserManage> searchMemberMagageByMap(Map<String, Object> map);

	int countMemberMagageByMap(Map<String, Object> map);

	int deleteByUsersUuids(Map<String, Object> map);

	int countUsers(Map<String, Object> map);

	int countMemberUsers(Map<String, Object> map);
}
