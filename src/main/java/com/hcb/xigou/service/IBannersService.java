package com.hcb.xigou.service;

import java.util.Map;

import com.hcb.xigou.dto.Banners;

public interface IBannersService {
	int deleteByPrimaryKey(Integer fakeId);

    int insert(Banners record);

    int insertSelective(Banners record);

    Banners selectByPrimaryKey(Integer fakeId);

    int updateByPrimaryKeySelective(Banners record);

    int updateByPrimaryKey(Banners record);
    
    Banners selectByBannerUuid(String bannerUuid);
    
    int deleteByBannerUuids(Map<String,Object> map);
}
