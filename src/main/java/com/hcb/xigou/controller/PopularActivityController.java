package com.hcb.xigou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hcb.xigou.service.PopularActivityService;

@Controller
@RequestMapping("popularActivity/")
public class PopularActivityController {
	
	@Autowired
	PopularActivityService  popularActivityService;
	
	
	
}
