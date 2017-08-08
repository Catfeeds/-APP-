package com.hcb.xigou.service;

import java.util.Map;

import com.hcb.xigou.dto.UserStatistics;

public interface IUserStatisticsService {
	public int deleteByPrimaryKey(Integer fakeId);

	public int insert(UserStatistics record);

	public int insertSelective(UserStatistics record);

	public UserStatistics selectByPrimaryKey(Integer fakeId);

	public int updateByPrimaryKeySelective(UserStatistics record);

	public int updateByPrimaryKey(UserStatistics record);
	
	public Integer loginCount(Map<String, Object> map);
}
