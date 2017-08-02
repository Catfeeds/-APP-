package com.hcb.xigou.service;

import java.util.List;
import java.util.Map;

import com.hcb.xigou.dto.SecondCategorys;
import com.hcb.xigou.pojo.Goods;

public interface ISecondCategorysService {
	int deleteByPrimaryKey(Integer fakeId);

    int insert(SecondCategorys record);

    int insertSelective(SecondCategorys record);

    SecondCategorys selectByPrimaryKey(Integer fakeId);

    int updateByPrimaryKeySelective(SecondCategorys record);

    int updateByPrimaryKey(SecondCategorys record);
    
    List<SecondCategorys> selectAll();

	List<SecondCategorys> searchCategoryByMap(Map<String, Object> map);

	int countCategoryByMap(Map<String, Object> map);

	SecondCategorys selectBySecondUuid(String secondUuid);

	int updateBySecondUuid(SecondCategorys second);

	int deleteBySecondUuids(Map<String, Object> map);

	List<Goods> secondUuid(Map<String, Object> map);
}
