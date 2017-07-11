package com.hcb.xigou.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hcb.xigou.dao.interfaceClass.UserActivityMapper;
import com.hcb.xigou.dto.UserActivity;
import com.hcb.xigou.service.UserActivityService;

public class UserActivityServiceImpl implements UserActivityService {
	
	@Autowired
	UserActivityMapper userActivityMapper;
	
	
	@Override
	public int deleteByActivityUuid(Map<String, Object> map) {
		
		return userActivityMapper.deleteByActivityUuid(map);
	}

	@Override
	public UserActivity selectByActivityUuid(String activityUuid) {
		
		return userActivityMapper.selectByActivityUuid(activityUuid);
	}

	@Override
	public int updateByActivityAndGoods(Map<String, Object> map) {
		
		return userActivityMapper.updateByActivityAndGoods(map);
	}

	@Override
	public int insertByActivityUuids(Map<String, Object> map) {
		
		return userActivityMapper.insertByActivityUuids(map);
	}

	@Override
	public List<UserActivity> searchUserActivityByMap(Map<String, Object> map) {
		
		return userActivityMapper.searchUserActivityByMap(map);
	}

	@Override
	public int countUserActivityByMap(Map<String, Object> map) {

		return userActivityMapper.countUserActivityByMap(map);
	}
	
}
