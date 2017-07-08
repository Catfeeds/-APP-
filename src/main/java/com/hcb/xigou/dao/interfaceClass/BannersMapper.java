package com.hcb.xigou.dao.interfaceClass;

import com.hcb.xigou.dto.Banners;

public interface BannersMapper {
    int deleteByPrimaryKey(Integer fakeId);

    int insert(Banners record);

    int insertSelective(Banners record);

    Banners selectByPrimaryKey(Integer fakeId);

    int updateByPrimaryKeySelective(Banners record);

    int updateByPrimaryKey(Banners record);
}