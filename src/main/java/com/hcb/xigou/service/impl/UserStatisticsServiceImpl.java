package com.hcb.xigou.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcb.xigou.dao.interfaceClass.UserStatisticsMapper;
import com.hcb.xigou.dto.UserStatistics;
import com.hcb.xigou.service.IUserStatisticsService;

@Service("UserStatisticsService")
public class UserStatisticsServiceImpl implements IUserStatisticsService{

	@Autowired
	UserStatisticsMapper userStatisticsMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer fakeId) {
		return userStatisticsMapper.deleteByPrimaryKey(fakeId);
	}

	@Override
	public int insert(UserStatistics record) {
		return userStatisticsMapper.insert(record);
	}

	@Override
	public int insertSelective(UserStatistics record) {
		return userStatisticsMapper.insertSelective(record);
	}

	@Override
	public UserStatistics selectByPrimaryKey(Integer fakeId) {
		return userStatisticsMapper.selectByPrimaryKey(fakeId);
	}

	@Override
	public int updateByPrimaryKeySelective(UserStatistics record) {
		return userStatisticsMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(UserStatistics record) {
		return userStatisticsMapper.updateByPrimaryKey(record);
	}

	@Override
	public Integer loginCount(Map<String, Object> map) {
		return userStatisticsMapper.loginCount(map);
	}

}
