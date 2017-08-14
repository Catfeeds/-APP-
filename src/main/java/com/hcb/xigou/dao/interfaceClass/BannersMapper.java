package com.hcb.xigou.dao.interfaceClass;

import java.util.List;
import java.util.Map;

import com.hcb.xigou.dto.Banners;

public interface BannersMapper {
    int deleteByPrimaryKey(Integer fakeId);

    int insert(Banners record);

    int insertSelective(Banners record);

    Banners selectByPrimaryKey(Integer fakeId);

    int updateByPrimaryKeySelective(Banners record);

    int updateByPrimaryKey(Banners record);
    
    Banners selectByBannerUuid(String bannerUuid);
    
    int deleteByBannerUuids(Map<String,Object> map);
    
    List<Banners> searchBannerByMap(Map<String,Object> map);
    
    List<Banners> searchBannerExcelByMap(Map<String,Object> map);
    
    int countBannerByMap(Map<String,Object> map);

	int insertByBanner(Banners banner);

	int selectByBannerStatus();
	
	Banners selectByCurrentindex(Map<String, Object> map);
}