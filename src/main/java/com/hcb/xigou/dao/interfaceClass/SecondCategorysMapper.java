package com.hcb.xigou.dao.interfaceClass;

import java.util.List;

import com.hcb.xigou.dto.Goods;

import com.hcb.xigou.dto.SecondCategorys;

public interface SecondCategorysMapper {
    int deleteByPrimaryKey(Integer fakeId);

    int insert(SecondCategorys record);

    int insertSelective(SecondCategorys record);

    SecondCategorys selectByPrimaryKey(Integer fakeId);

    int updateByPrimaryKeySelective(SecondCategorys record);

    int updateByPrimaryKey(SecondCategorys record);

	List<Goods> secondUuid(String firstUuid);

    List<SecondCategorys> selectAll();
}