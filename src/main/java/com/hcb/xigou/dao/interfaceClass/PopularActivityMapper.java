package com.hcb.xigou.dao.interfaceClass;

import java.util.List;
import java.util.Map;

import com.hcb.xigou.dto.Banners;
import com.hcb.xigou.dto.PopularActivity;
import com.hcb.xigou.pojo.Goods;

public interface PopularActivityMapper {

	int deleteByActivityUuids(Map<String, Object> map);

	PopularActivity selectByPopularActivityUuid(String activityUuid);

	int updateByPopularActivityIsSTop(Map<String, Object> map);

	PopularActivity selectByPopularActivityId(String activity_uuid);

	int insertActivity(PopularActivity popAct);

	List<PopularActivity> searchPopularActivityByMap(Map<String, Object> map);

	int countPopularActivityByMap(Map<String, Object> map);

	int updateByPrimaryKeySelective(Goods good);
	
}
