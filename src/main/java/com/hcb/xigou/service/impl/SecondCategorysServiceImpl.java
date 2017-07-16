package com.hcb.xigou.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcb.xigou.dao.interfaceClass.SecondCategorysMapper;
import com.hcb.xigou.dto.Goods;
import com.hcb.xigou.dto.SecondCategorys;
import com.hcb.xigou.service.ISecondCategorysService;

@Service("SecondCategorysService")
public class SecondCategorysServiceImpl implements ISecondCategorysService{
	
	@Autowired
	SecondCategorysMapper secondCategorysMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer fakeId) {
		// TODO Auto-generated method stub
		return secondCategorysMapper.deleteByPrimaryKey(fakeId);
	}

	@Override
	public int insert(SecondCategorys record) {
		// TODO Auto-generated method stub
		return secondCategorysMapper.insert(record);
	}

	@Override
	public int insertSelective(SecondCategorys record) {
		// TODO Auto-generated method stub
		return secondCategorysMapper.insertSelective(record);
	}

	@Override
	public SecondCategorys selectByPrimaryKey(Integer fakeId) {
		// TODO Auto-generated method stub
		return secondCategorysMapper.selectByPrimaryKey(fakeId);
	}

	@Override
	public int updateByPrimaryKeySelective(SecondCategorys record) {
		// TODO Auto-generated method stub
		return secondCategorysMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SecondCategorys record) {
		// TODO Auto-generated method stub
		return secondCategorysMapper.updateByPrimaryKey(record);
	}

	@Override
	public SecondCategorys selectAll() {
		// TODO Auto-generated method stub
		return secondCategorysMapper.selectAll();
	}

	@Override
	public List<Goods> secondUuid(String firstUuid) {
		// TODO Auto-generated method stub
		return secondCategorysMapper.secondUuid(firstUuid);
	}

}
