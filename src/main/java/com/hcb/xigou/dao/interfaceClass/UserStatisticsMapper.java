package com.hcb.xigou.dao.interfaceClass;

import java.util.Map;

import com.hcb.xigou.dto.UserStatistics;

public interface UserStatisticsMapper {
    int deleteByPrimaryKey(Integer fakeId);

    int insert(UserStatistics record);

    int insertSelective(UserStatistics record);

    UserStatistics selectByPrimaryKey(Integer fakeId);

    int updateByPrimaryKeySelective(UserStatistics record);

    int updateByPrimaryKey(UserStatistics record);
    
    Integer loginCount(Map<String, Object> map);
}