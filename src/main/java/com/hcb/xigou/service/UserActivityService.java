package com.hcb.xigou.service;

import java.util.List;
import java.util.Map;

import com.hcb.xigou.dto.UserActivity;

public interface UserActivityService {

	int deleteByActivityUuid(Map<String, Object> map);

	UserActivity selectByActivityUuid(String activityUuid);

	int updateByActivityAndGoods(Map<String, Object> map);

	int insertByActivityUuids(Map<String, Object> map);

	List<UserActivity> searchUserActivityByMap(Map<String, Object> map);

	int countUserActivityByMap(Map<String, Object> map);

	List<UserActivity> selectAll();

}
