package com.hcb.xigou.dao.interfaceClass;

import java.util.List;
import java.util.Map;

import com.hcb.xigou.dto.Notices;

public interface NoticesMapper {
    int deleteByPrimaryKey(Integer fakeId);

    int insert(Notices record);

    int insertSelective(Notices record);

    Notices selectByPrimaryKey(Integer fakeId);

    int updateByPrimaryKeySelective(Notices record);

    int updateByPrimaryKey(Notices record);
    
    List<Map<String, Object>> selectByPaging(Map<String, Object> map);
    
    Integer totalCount(Map<String, Object> map);
    
    Notices selectByNoticeUuid(String noticeUuid);
}