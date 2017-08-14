package com.hcb.xigou.service;

import java.util.List;
import java.util.Map;

import com.hcb.xigou.dto.UserRechargers;

public interface UserRechargersService {

	List<UserRechargers> searchUserRechargersByMap(Map<String, Object> map);
	
	List<UserRechargers> searchUserRechargersExcelportByMap(Map<String, Object> map);

	int countUserRechargersByMap(Map<String, Object> map);

}
