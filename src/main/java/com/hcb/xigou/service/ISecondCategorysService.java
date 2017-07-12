package com.hcb.xigou.service;

import com.hcb.xigou.dto.SecondCategorys;

public interface ISecondCategorysService {
	int deleteByPrimaryKey(Integer fakeId);

    int insert(SecondCategorys record);

    int insertSelective(SecondCategorys record);

    SecondCategorys selectByPrimaryKey(Integer fakeId);

    int updateByPrimaryKeySelective(SecondCategorys record);

    int updateByPrimaryKey(SecondCategorys record);
    
    SecondCategorys selectAll();
}
