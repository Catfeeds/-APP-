package com.hcb.xigou.dao.interfaceClass;

import java.util.List;
import java.util.Map;

import com.hcb.xigou.dto.ActivityZones;

public interface ActivityZonesMapper {
    int deleteByPrimaryKey(Integer fakeId);

    int insert(ActivityZones record);

    int insertSelective(ActivityZones record);

    ActivityZones selectByPrimaryKey(Integer fakeId);

    int updateByPrimaryKeySelective(ActivityZones record);

    int updateByPrimaryKey(ActivityZones record);
    
    int deleteByActivityUuids(Map<String,Object> map);
    
    ActivityZones selectByActivityUuid(String activityUuid);
    
    List<ActivityZones> searchActivityByMap(Map<String,Object> map);
    
    int countActivityByMap(Map<String,Object> map);
    
    int updateByActivityUuid(ActivityZones record);
}