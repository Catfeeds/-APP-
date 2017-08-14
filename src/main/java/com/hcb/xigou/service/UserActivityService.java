package com.hcb.xigou.service;

import java.util.List;
import java.util.Map;

import com.hcb.xigou.dto.ActivityZones;
import com.hcb.xigou.dto.UserActivity;

public interface UserActivityService {

	int deleteByActivityUuid(Map<String, Object> map);

	UserActivity selectByActivityUuid(String activityUuid);

	int updateByActivityAndGoods(Map<String, Object> map);

	int insertByActivityUuids(List<ActivityZones> listAct);

	List<UserActivity> searchUserActivityByMap(Map<String, Object> map);
	
	List<UserActivity> searchUserActivityExcelportByMap(Map<String, Object> map);

	int countUserActivityByMap(Map<String, Object> map);

	int updateByActivity(UserActivity userActivity);

	int deleteByActivityGoodUuid(Map<String, Object> map);

	UserActivity selectGroupsMax();

	int selectByActiviAndGood(Map<String, Object> map);

}
