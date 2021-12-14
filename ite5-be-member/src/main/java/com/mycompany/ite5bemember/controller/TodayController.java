package com.mycompany.ite5bemember.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.ite5bemember.service.TodayService;

@RestController
public class TodayController {
	
	@Resource private TodayService todayService;
	
	@PostMapping("/visit")
	public Map<String, String> visit() throws Exception{
		Map<String, String> map = todayService.visit();
		return map;
	}
}
