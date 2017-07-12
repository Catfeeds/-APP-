package com.hcb.xigou.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcb.xigou.dao.interfaceClass.FirstCategorysMapper;
import com.hcb.xigou.dto.FirstCategorys;
import com.hcb.xigou.service.IFirstCategorysService;

@Service("FirstCategorysService")
public class FirstCategorysServiceImpl implements IFirstCategorysService{

	@Autowired
	FirstCategorysMapper firstCategorysMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer fakeId) {
		// TODO Auto-generated method stub
		return firstCategorysMapper.deleteByPrimaryKey(fakeId);
	}

	@Override
	public int insert(FirstCategorys record) {
		// TODO Auto-generated method stub
		return firstCategorysMapper.insert(record);
	}

	@Override
	public int insertSelective(FirstCategorys record) {
		// TODO Auto-generated method stub
		return firstCategorysMapper.insertSelective(record);
	}

	@Override
	public FirstCategorys selectByPrimaryKey(Integer fakeId) {
		// TODO Auto-generated method stub
		return firstCategorysMapper.selectByPrimaryKey(fakeId);
	}

	@Override
	public int updateByPrimaryKeySelective(FirstCategorys record) {
		// TODO Auto-generated method stub
		return firstCategorysMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(FirstCategorys record) {
		// TODO Auto-generated method stub
		return firstCategorysMapper.updateByPrimaryKey(record);
	}

	@Override
	public FirstCategorys selectByFirstUuid(String firstUuid) {
		// TODO Auto-generated method stub
		return firstCategorysMapper.selectByFirstUuid(firstUuid);
	}

}
