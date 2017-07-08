package com.hcb.xigou.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcb.xigou.dao.interfaceClass.BannersMapper;
import com.hcb.xigou.dto.Banners;
import com.hcb.xigou.service.IBannersService;

@Service("BannersService")
public class BannersServiceImpl implements IBannersService{
     
	@Autowired
	BannersMapper bannersMapper;

	@Override
	public int deleteByPrimaryKey(Integer fakeId) {
		return bannersMapper.deleteByPrimaryKey(fakeId);
	}

	@Override
	public int insert(Banners record) {
		return bannersMapper.insert(record);
	}

	@Override
	public int insertSelective(Banners record) {
		return bannersMapper.insertSelective(record);
	}

	@Override
	public Banners selectByPrimaryKey(Integer fakeId) {
		return bannersMapper.selectByPrimaryKey(fakeId);
	}

	@Override
	public int updateByPrimaryKeySelective(Banners record) {
		return bannersMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Banners record) {
		return bannersMapper.updateByPrimaryKey(record);
	}

	@Override
	public Banners selectByBannerUuid(String bannerUuid) {
		return bannersMapper.selectByBannerUuid(bannerUuid);
	}

	@Override
	public int deleteByBannerUuids(Map<String, Object> map) {
		return bannersMapper.deleteByBannerUuids(map);
	}
}
