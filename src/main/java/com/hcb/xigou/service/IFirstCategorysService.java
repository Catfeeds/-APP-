package com.hcb.xigou.service;

import com.hcb.xigou.dto.FirstCategorys;

public interface IFirstCategorysService {
	int deleteByPrimaryKey(Integer fakeId);

    int insert(FirstCategorys record);

    int insertSelective(FirstCategorys record);

    FirstCategorys selectByPrimaryKey(Integer fakeId);

    int updateByPrimaryKeySelective(FirstCategorys record);

    int updateByPrimaryKey(FirstCategorys record);
    
    FirstCategorys selectByFirstUuid(String firstUuid);
}