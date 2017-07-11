package com.hcb.xigou.service;

import java.util.List;
import java.util.Map;

import com.hcb.xigou.pojo.Goods;

public interface GoodsService {

	public int deleteByGoodsId(int[] fakeId);

	public int selectCountByGood(Map<String,Object> map);

	public List<Goods> selectGoodDetails(Map<String,Object> map);

	public Goods selectGoodById(int fakeId);
	
	Goods selectGoodByGoodUuid(String goodUuid);
	
	int deleteByGoodUuids(Map<String,Object> map);

	public List<Goods> searchGoodsByMap(Map<String, Object> map);

	public int countGoodsByMap(Map<String, Object> map);
}
