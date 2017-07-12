package com.hcb.xigou.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcb.xigou.dao.interfaceClass.PopularActivityMapper;
import com.hcb.xigou.dto.PopularActivity;
import com.hcb.xigou.service.PopularActivityService;

@Service("PopularActivityService")
public class PopularActivityServiceImpl implements PopularActivityService {
	
	@Autowired
	PopularActivityMapper PopularActivityMapper;
	
	@Override
	public int deleteByActivityUuids(Map<String, Object> map) {
		
		return PopularActivityMapper.deleteByActivityUuids(map);
	}

	@Override
	public PopularActivity selectByPopularActivityUuid(String activityUuid) {
		
		return PopularActivityMapper.selectByPopularActivityUuid(activityUuid);
	}

	@Override
	public int updateByPopularActivityIsSTop(Map<String, Object> map) {
		
		return PopularActivityMapper.updateByPopularActivityIsSTop(map);
	}
	
}
