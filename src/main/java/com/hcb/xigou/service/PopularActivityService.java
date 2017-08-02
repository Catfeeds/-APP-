package com.hcb.xigou.service;

import java.util.List;
import java.util.Map;

import com.hcb.xigou.dto.PopularActivity;

public interface PopularActivityService {

	int deleteByActivityUuids(Map<String, Object> map);

	PopularActivity selectByPopularActivityUuid(String activityUuid);

	int updateByPopularActivityIsSTop(Map<String, Object> map);

	PopularActivity selectByPopularActivityId(String activity_uuid);

	List<PopularActivity> searchPopularActivityByMap(Map<String, Object> map);

	int countPopularActivityByMap(Map<String, Object> map);

	int insertActivity(PopularActivity popAct);

	List<PopularActivity> searchPopularByMap(Map<String, Object> map);

}
