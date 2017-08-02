package com.hcb.xigou.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcb.xigou.dao.interfaceClass.StoresMapper;
import com.hcb.xigou.dto.Stores;
import com.hcb.xigou.service.IStoresService;

@Service("StoresService")
public class StoresServiceImpl implements IStoresService {
	
	@Autowired
	StoresMapper storesMapper;
	
	@Override
	public Stores selectByPrimaryKey(String storeUuid) {
		// TODO Auto-generated method stub
		return storesMapper.selectByPrimaryKey(storeUuid);
	}

	@Override
	public List<Stores> selectStoreAll() {
		// TODO Auto-generated method stub
		return storesMapper.selectStoreAll();
	}
	
}
