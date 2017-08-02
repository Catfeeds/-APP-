package com.hcb.xigou.service;

import java.util.List;

import com.hcb.xigou.dto.Stores;

public interface IStoresService {

	Stores selectByPrimaryKey(String storeUuid);

	List<Stores> selectStoreAll();

}
