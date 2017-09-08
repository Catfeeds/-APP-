package com.hcb.xigou.service;

import java.util.List;
import java.util.Map;

import com.hcb.xigou.dto.Notices;

public interface INoticesService {
	public int deleteByPrimaryKey(Integer fakeId);

	public int insert(Notices record);

	public int insertSelective(Notices record);

	public Notices selectByPrimaryKey(Integer fakeId);

	public int updateByPrimaryKeySelective(Notices record);

	public int updateByPrimaryKey(Notices record);
	
    public List<Map<String, Object>> selectByPaging(Map<String, Object> map);
    
    public Integer totalCount(Map<String, Object> map);
    
    public Notices selectByNoticeUuid(String noticeUuid);
}
