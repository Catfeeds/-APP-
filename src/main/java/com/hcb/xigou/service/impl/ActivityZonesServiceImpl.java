package com.hcb.xigou.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcb.xigou.dao.interfaceClass.ActivityZonesMapper;
import com.hcb.xigou.dto.ActivityZones;
import com.hcb.xigou.dto.PopularActivity;
import com.hcb.xigou.service.IActivityZonesService;

@Service("ActivityZonesService")
public class ActivityZonesServiceImpl implements IActivityZonesService{

	@Autowired
	ActivityZonesMapper activityZonesMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer fakeId) {
		return activityZonesMapper.deleteByPrimaryKey(fakeId);
	}

	@Override
	public int insert(ActivityZones record) {
		return activityZonesMapper.insert(record);
	}

	@Override
	public int insertSelective(ActivityZones record) {
		return activityZonesMapper.insertSelective(record);
	}

	@Override
	public ActivityZones selectByPrimaryKey(Integer fakeId) {
		return activityZonesMapper.selectByPrimaryKey(fakeId);
	}

	@Override
	public int updateByPrimaryKeySelective(ActivityZones record) {
		return activityZonesMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(ActivityZones record) {
		return activityZonesMapper.updateByPrimaryKey(record);
	}

	@Override
	public int deleteByActivityUuids(Map<String, Object> map) {
		return activityZonesMapper.deleteByActivityUuids(map);
	}

	@Override
	public ActivityZones selectByActivityUuid(String activityUuid) {
		return activityZonesMapper.selectByActivityUuid(activityUuid);
	}

	@Override
	public List<ActivityZones> searchActivityByMap(Map<String, Object> map) {
		return activityZonesMapper.searchActivityByMap(map);
	}

	@Override
	public int countActivityByMap(Map<String, Object> map) {
		return activityZonesMapper.countActivityByMap(map);
	}

	@Override
	public int updateByActivityUuid(ActivityZones record) {
		return activityZonesMapper.updateByActivityUuid(record);
	}

	@Override
	public List<PopularActivity> searchPopularByMap(Map<String, Object> map) {
		return activityZonesMapper.searchPopularByMap(map);
	}

	@Override
	public int countPopularByMap(Map<String, Object> map) {
		return activityZonesMapper.countPopularByMap(map);
	}

	@Override
	public int deleteByPopular(Map<String, Object> map) {
		return activityZonesMapper.deleteByPopular(map);
	}

	@Override
	public ActivityZones selectByPopularId(String activityUuid) {
		return activityZonesMapper.selectByPopularId(activityUuid);
	}

	@Override
	public List<ActivityZones> searchActivityList(Map<String, Object> map) {
		return activityZonesMapper.searchActivityList(map);
	}

	@Override
	public int countActivityInt(Map<String, Object> map) {
		return activityZonesMapper.countActivityInt(map);
	}

	@Override
	public ActivityZones selectByActivity(String activityUuid) {
		return activityZonesMapper.selectByActivity(activityUuid);
	}

	@Override
	public int selectByActivityCount() {
		return activityZonesMapper.selectByActivityCount();
	}

	@Override
	public int selectByActivitySellingCount() {
		return activityZonesMapper.selectByActivitySellingCount();
	}

	@Override
	public List<Map<String, Object>> selectByPaging(Map<String, Object> map) {
		return activityZonesMapper.selectByPaging(map);
	}

	@Override
	public Integer totalCount(Map<String, Object> map) {
		return activityZonesMapper.totalCount(map);
	}

	@Override
	public List<Map<String, Object>> selectByAll() {
		return activityZonesMapper.selectByAll();
	}
}
