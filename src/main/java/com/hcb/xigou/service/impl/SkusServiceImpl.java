package com.hcb.xigou.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcb.xigou.dao.interfaceClass.SkusMapper;
import com.hcb.xigou.dto.Skus;
import com.hcb.xigou.service.ISkusService;

@Service("SkusService")
public class SkusServiceImpl implements ISkusService{

	@Autowired
	SkusMapper skusMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer fakeId) {
		// TODO Auto-generated method stub
		return skusMapper.deleteByPrimaryKey(fakeId);
	}

	@Override
	public int insert(Skus record) {
		// TODO Auto-generated method stub
		return skusMapper.insert(record);
	}

	@Override
	public int insertSelective(Skus record) {
		// TODO Auto-generated method stub
		return skusMapper.insertSelective(record);
	}

	@Override
	public Skus selectByPrimaryKey(Integer fakeId) {
		// TODO Auto-generated method stub
		return skusMapper.selectByPrimaryKey(fakeId);
	}

	@Override
	public int updateByPrimaryKeySelective(Skus record) {
		// TODO Auto-generated method stub
		return skusMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Skus record) {
		// TODO Auto-generated method stub
		return skusMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Skus> selectAllCategory(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return skusMapper.selectAllCategory(map);
	}

	@Override
	public List<Skus> selectAllParameter(String category) {
		// TODO Auto-generated method stub
		return skusMapper.selectAllParameter(category);
	}



}
