package com.hcb.xigou.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcb.xigou.dao.interfaceClass.GoodsMapper;
import com.hcb.xigou.dto.FirstCategorys;
import com.hcb.xigou.dto.Goods;
import com.hcb.xigou.dto.UserActivity;
import com.hcb.xigou.service.GoodsService;

@Service("GoodsService")
public class GoodsServiceImpl implements GoodsService {
	
	@Autowired
	GoodsMapper goodsMapper;
	
	@Override
	public int deleteByGoodsId(int[] fakeId) {
		return goodsMapper.deleteByGoodsId(fakeId);
	}
	
	@Override
	public List<Goods> selectGoodDetails(Map<String,Object> map) {
		return goodsMapper.selectGoodDetails(map);
	}

	@Override
	public Goods selectGoodById(int fakeId) {
		return goodsMapper.selectGoodById(fakeId);
	}

	@Override
	public int selectCountByGood(Map<String, Object> map) {
		return goodsMapper.selectCountByGood(map);
	}

	@Override
	public Goods selectGoodByGoodUuid(String goodUuid) {
		return goodsMapper.selectGoodByGoodUuid(goodUuid);
	}

	@Override
	public int deleteByGoodUuids(Map<String, Object> map) {
		return goodsMapper.deleteByGoodUuids(map);
	}

	@Override
	public List<Goods> searchGoodsByMap(Map<String, Object> map) {
		
		return goodsMapper.searchGoodsByMap(map);
	}

	@Override
	public int countGoodsByMap(Map<String, Object> map) {
		return goodsMapper.countGoodsByMap(map);
	}

	@Override
	public int insertSelective(Goods record) {
		// TODO Auto-generated method stub
		return goodsMapper.insertSelective(record);
	}

	@Override
	public Goods selectByGoodUuid(String goodUuid) {
		// TODO Auto-generated method stub
		return goodsMapper.selectByGoodUuid(goodUuid);
	}

	@Override
	public int updateByGoodsUuid(Goods record) {
		// TODO Auto-generated method stub
		return goodsMapper.updateByGoodsUuid(record);
	}

	@Override
	public Goods selectByActivityGood(String activityUuid) {
		
		return goodsMapper.selectByActivityGood(activityUuid);
	}

	@Override
	public List<UserActivity> searchGood(String activity_uuid) {
		
		return goodsMapper.searchGood(activity_uuid);
	}

	@Override
	public int updateByPrimaryKeySelective(Goods good) {
		
		return goodsMapper.updateByPrimaryKeySelective(good);
	}

	@Override
	public List<Goods> secondUuid(Map<String, Object> map) {
		
		return goodsMapper.secondUuid(map);
	}

	@Override
	public List<Goods> searchGoodUuid(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return goodsMapper.searchGoodUuid(map);
	}

}
