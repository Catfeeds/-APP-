package com.hcb.xigou.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcb.xigou.dao.interfaceClass.GoodsMapper;
import com.hcb.xigou.pojo.Goods;
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

}
