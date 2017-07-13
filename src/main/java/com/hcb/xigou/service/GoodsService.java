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
	
	int insertSelective(Goods record);
	
	Goods selectByGoodUuid(String goodUuid);
	
	int updateByGoodsUuid(Goods record);

	public Goods selectByActivityGood(String activityUuid);

	public List<Goods> searchGood(String activity_uuid);
}
