package com.hcb.xigou.dao.interfaceClass;

import java.util.List;
import java.util.Map;

import com.hcb.xigou.dto.Dailys;

public interface DailysMapper {
    int deleteByPrimaryKey(Integer fakeId);

    int insert(Dailys record);

    int insertSelective(Dailys record);

    Dailys selectByPrimaryKey(Integer fakeId);

    int updateByPrimaryKeySelective(Dailys record);

    int updateByPrimaryKey(Dailys record);
    
    List<Dailys> selectByPaging(Map<String, Object> map);
	
    Integer totalCount(Map<String, Object> map);
    
    Dailys selectByDailyUuid(String dailyUuid);
}