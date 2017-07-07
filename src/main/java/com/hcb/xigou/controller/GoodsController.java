package com.hcb.xigou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcb.xigou.pojo.Goods;
import com.hcb.xigou.service.GoodsService;

@Controller
@RequestMapping("goods/")
public class GoodsController {
	
	@Autowired
	GoodsService goodsService;
	
	@RequestMapping("delete")
	public void delete(ModelMap model,@RequestParam(value = "fakeId[]") int[] fakeId){
		goodsService.deleteByGoodsId(fakeId);
	}
	
	@RequestMapping("select")
	@ResponseBody
	public Goods selectGoodid(ModelMap model,@RequestParam(required = false) int fakeId){
		Goods goods = goodsService.selectGoodById(fakeId);
		model.put("goods",goods);
		return null;
	}
	
	
	
	
}
