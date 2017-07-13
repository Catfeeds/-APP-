package com.hcb.xigou.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcb.xigou.dao.interfaceClass.ActivityZonesMapper;
import com.hcb.xigou.dto.ActivityZones;
import com.hcb.xigou.service.IActivityZonesService;

@Service("ActivityZonesService")
public class ActivityZonesServiceImpl implements IActivityZonesService{

	@Autowired
	ActivityZonesMapper activityZonesMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer fakeId) {
		// TODO Auto-generated method stub
		return activityZonesMapper.deleteByPrimaryKey(fakeId);
	}

	@Override
	public int insert(ActivityZones record) {
		// TODO Auto-generated method stub
		return activityZonesMapper.insert(record);
	}

	@Override
	public int insertSelective(ActivityZones record) {
		// TODO Auto-generated method stub
		return activityZonesMapper.insertSelective(record);
	}

	@Override
	public ActivityZones selectByPrimaryKey(Integer fakeId) {
		// TODO Auto-generated method stub
		return activityZonesMapper.selectByPrimaryKey(fakeId);
	}

	@Override
	public int updateByPrimaryKeySelective(ActivityZones record) {
		// TODO Auto-generated method stub
		return activityZonesMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(ActivityZones record) {
		// TODO Auto-generated method stub
		return activityZonesMapper.updateByPrimaryKey(record);
	}

	@Override
	public int deleteByActivityUuids(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return activityZonesMapper.deleteByActivityUuids(map);
	}

	@Override
	public ActivityZones selectByActivityUuid(String activityUuid) {
		// TODO Auto-generated method stub
		return activityZonesMapper.selectByActivityUuid(activityUuid);
	}

	@Override
	public List<ActivityZones> searchActivityByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return activityZonesMapper.searchActivityByMap(map);
	}

	@Override
	public int countActivityByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return activityZonesMapper.countActivityByMap(map);
	}

	@Override
	public int updateByActivityUuid(ActivityZones record) {
		// TODO Auto-generated method stub
		return activityZonesMapper.updateByActivityUuid(record);
	}


}
