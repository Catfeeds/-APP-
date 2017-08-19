package com.hcb.xigou.dao.interfaceClass;

import java.util.List;
import java.util.Map;

import com.hcb.xigou.dto.ActivityZones;
import com.hcb.xigou.dto.PopularActivity;

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
    
    List<ActivityZones> searchActivityExcelportByMap(Map<String,Object> map);
    
    int countActivityByMap(Map<String,Object> map);
    
    int updateByActivityUuid(ActivityZones record);

	List<PopularActivity> searchPopularByMap(Map<String, Object> map);

	int countPopularByMap(Map<String, Object> map);

	int deleteByPopular(Map<String, Object> map);

	ActivityZones selectByPopularId(String activityUuid);

	List<ActivityZones> searchActivityList(Map<String, Object> map);
	
	List<ActivityZones> searchActivityExcelport(Map<String, Object> map);

	int countActivityInt(Map<String, Object> map);

	ActivityZones selectByActivity(String activityUuid);

	int selectByActivityCount();

	int selectByActivitySellingCount();
	
    List<Map<String, Object>> selectByPaging(Map<String, Object> map);
    
    List<Map<String, Object>> selectBypopularActivityExcelport(Map<String, Object> map);
	
    Integer totalCount(Map<String, Object> map);
    
    List<Map<String, Object>> selectByAll();
    
    List<ActivityZones> selectBySellingOfGroups(Map<String, Object> map);
}