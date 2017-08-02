package com.hcb.xigou.dao.interfaceClass;

import java.util.List;

import com.hcb.xigou.dto.Stores;

public interface StoresMapper {
    int deleteByPrimaryKey(Integer fakeId);

    int insert(Stores record);

    int insertSelective(Stores record);

    Stores selectByPrimaryKey(String storeUuid);

    int updateByPrimaryKeySelective(Stores record);

    int updateByPrimaryKey(Stores record);

	List<Stores> selectStoreAll();
}