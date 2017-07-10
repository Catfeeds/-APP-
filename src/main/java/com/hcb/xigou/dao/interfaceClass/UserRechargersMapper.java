package com.hcb.xigou.dao.interfaceClass;

import java.util.List;
import java.util.Map;

import com.hcb.xigou.dto.UserRechargers;

public interface UserRechargersMapper {

	List<UserRechargers> searchUserRechargersByMap(Map<String, Object> map);

	int countUserRechargersByMap(Map<String, Object> map);

}
