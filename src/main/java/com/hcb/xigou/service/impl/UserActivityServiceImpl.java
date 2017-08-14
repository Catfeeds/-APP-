package com.hcb.xigou.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcb.xigou.dao.interfaceClass.UserActivityMapper;
import com.hcb.xigou.dto.ActivityZones;
import com.hcb.xigou.dto.UserActivity;
import com.hcb.xigou.service.UserActivityService;

@Service("UserActivityService")
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
	public int insertByActivityUuids(List<ActivityZones> listAct) {
		
		return userActivityMapper.insertByActivityUuids(listAct);
	}

	@Override
	public List<UserActivity> searchUserActivityByMap(Map<String, Object> map) {
		
		return userActivityMapper.searchUserActivityByMap(map);
	}

	@Override
	public int countUserActivityByMap(Map<String, Object> map) {

		return userActivityMapper.countUserActivityByMap(map);
	}

	@Override
	public int updateByActivity(UserActivity userActivity) {
		// TODO Auto-generated method stub
		return userActivityMapper.updateByActivity(userActivity);
	}

	@Override
	public int deleteByActivityGoodUuid(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userActivityMapper.deleteByActivityGoodUuid(map);
	}

	@Override
	public UserActivity selectGroupsMax() {
		// TODO Auto-generated method stub
		return userActivityMapper.selectGroupsMax();
	}

	@Override
	public int selectByActiviAndGood(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userActivityMapper.selectByActiviAndGood(map);
	}

	@Override
	public List<UserActivity> searchUserActivityExcelportByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userActivityMapper.searchUserActivityExcelportByMap(map);
	}
	
}
