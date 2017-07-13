package com.hcb.xigou.service;

import java.util.List;
import java.util.Map;

import com.hcb.xigou.dto.FirstCategorys;

public interface IFirstCategorysService {
	int deleteByPrimaryKey(Integer fakeId);

    int insert(FirstCategorys record);

    int insertSelective(FirstCategorys record);

    FirstCategorys selectByPrimaryKey(Integer fakeId);

    int updateByPrimaryKeySelective(FirstCategorys record);

    int updateByPrimaryKey(FirstCategorys record);
    
    FirstCategorys selectByFirstUuid(String firstUuid);
    
    List<FirstCategorys> searchCategoryByMap(Map<String,Object> map);
    
    int countCategoryByMap(Map<String,Object> map);
}
