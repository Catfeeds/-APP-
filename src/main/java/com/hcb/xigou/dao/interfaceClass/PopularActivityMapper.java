package com.hcb.xigou.dao.interfaceClass;

import java.util.Map;

import com.hcb.xigou.dto.PopularActivity;

public interface PopularActivityMapper {

	int deleteByActivityUuids(Map<String, Object> map);

	PopularActivity selectByPopularActivityUuid(String activityUuid);

	int updateByPopularActivityIsSTop(Map<String, Object> map);

}
