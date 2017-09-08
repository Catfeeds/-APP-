package com.hcb.xigou.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcb.xigou.dao.interfaceClass.NoticesMapper;
import com.hcb.xigou.dto.Notices;
import com.hcb.xigou.service.INoticesService;

@Service("NoticesService")
public class NoticesServiceImpl implements INoticesService{
	
	@Autowired
	NoticesMapper noticesMapper;

	@Override
	public int deleteByPrimaryKey(Integer fakeId) {
		return noticesMapper.deleteByPrimaryKey(fakeId);
	}

	@Override
	public int insert(Notices record) {
		return noticesMapper.insert(record);
	}

	@Override
	public int insertSelective(Notices record) {
		return noticesMapper.insertSelective(record);
	}

	@Override
	public Notices selectByPrimaryKey(Integer fakeId) {
		return noticesMapper.selectByPrimaryKey(fakeId);
	}

	@Override
	public int updateByPrimaryKeySelective(Notices record) {
		return noticesMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Notices record) {
		return noticesMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Map<String, Object>> selectByPaging(Map<String, Object> map) {
		return noticesMapper.selectByPaging(map);
	}

	@Override
	public Integer totalCount(Map<String, Object> map) {
		return noticesMapper.totalCount(map);
	}

	@Override
	public Notices selectByNoticeUuid(String noticeUuid) {
		return noticesMapper.selectByNoticeUuid(noticeUuid);
	}
}
