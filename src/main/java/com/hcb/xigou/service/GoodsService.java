package com.hcb.xigou.service;

import java.util.List;
import java.util.Map;

import com.hcb.xigou.dto.Banners;
import com.hcb.xigou.pojo.Goods;
import com.hcb.xigou.pojo.GoodsWithBLOBs;

public interface GoodsService {

	public int deleteByGoodsId(int[] fakeId);

	public int selectCountByGood(Map<String,Object> map);

	public List<Goods> selectGoodDetails(Map<String,Object> map);

	public Goods selectGoodById(int fakeId);
	
	GoodsWithBLOBs selectGoodByGoodUuid(String goodUuid);
	
	int deleteByGoodUuids(Map<String,Object> map);

	public List<GoodsWithBLOBs> searchGoodsByMap(Map<String, Object> map);

	public int countGoodsByMap(Map<String, Object> map);
	
	int insertSelective(Goods record);
	
	Goods selectByGoodUuid(String goodUuid);
	
	int updateByGoodsUuid(GoodsWithBLOBs record);

	public int updateByGoodsStatus(Map<String, Object> map);

	public int updateByPrimaryKeySelective(Goods good);

	public List<Goods> searchGoodUuid(Map<String, Object> map);

	public List<Goods> searchActivityGood(Map<String, Object> map);
	
	public List<Map<String, Object>> selectByAll();
}
