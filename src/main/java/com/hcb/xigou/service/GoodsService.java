package com.hcb.xigou.service;

import java.util.List;
import java.util.Map;

import com.hcb.xigou.pojo.Goods;

public interface GoodsService {
	
	/**
	 * 删除商品
	 * @param fakeId
	 */
	public int deleteByGoodsId(int[] fakeId);
	
	/**
	 * 总条数
	 * @param map
	 * @return
	 */
	public int selectCountByGood(Map<String,Object> map);
	
	/**
	 * 商品列表
	 * @param map
	 * @return
	 */
	public List<Goods> selectGoodDetails(Map<String,Object> map);
	
	/**
	 * 查询单个商品详情
	 * @param fakeId
	 * @return
	 */
	public Goods selectGoodById(int fakeId);
	
	
	Goods selectGoodByGoodUuid(String goodUuid);
	
	int deleteByGoodUuids(Map<String,Object> map);
}
