package com.hcb.xigou.service;

import java.util.List;
import java.util.Map;

import com.hcb.xigou.dto.Skus;

public interface ISkusService {
    int deleteByPrimaryKey(Integer fakeId);

    int insert(Skus record);

    int insertSelective(Skus record);

    Skus selectByPrimaryKey(Integer fakeId);

    int updateByPrimaryKeySelective(Skus record);

    int updateByPrimaryKey(Skus record);
    
    List<Skus> selectAllCategory(Map<String, Object> map);
	
    List<Skus> selectAllParameter(String category);
}
