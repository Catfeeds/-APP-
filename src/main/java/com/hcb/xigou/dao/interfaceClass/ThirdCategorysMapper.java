package com.hcb.xigou.dao.interfaceClass;

import java.util.List;
import java.util.Map;

import com.hcb.xigou.dto.ThirdCategorys;

public interface ThirdCategorysMapper {
    int deleteByPrimaryKey(Integer fakeId);

    int insert(ThirdCategorys record);

    int insertSelective(ThirdCategorys record);

    ThirdCategorys selectByPrimaryKey(Integer fakeId);

    int updateByPrimaryKeySelective(ThirdCategorys record);

    int updateByPrimaryKey(ThirdCategorys record);
    
    List<Map<String, Object>> selectByPaging(Map<String, Object> map);
	
    Integer totalCount(Map<String, Object> map);
	
	ThirdCategorys selectByThirdUuid(String thirdUuid);
}