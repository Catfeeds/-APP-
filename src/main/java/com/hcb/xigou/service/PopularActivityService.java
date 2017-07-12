package com.hcb.xigou.service;

import java.util.Map;

import com.hcb.xigou.dto.PopularActivity;

public interface PopularActivityService {

	int deleteByActivityUuids(Map<String, Object> map);

	PopularActivity selectByPopularActivityUuid(String activityUuid);

	int updateByPopularActivityIsSTop(Map<String, Object> map);

}
