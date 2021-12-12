package com.mycompany.ite5bemember.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.ite5bemember.dto.Today;
import com.mycompany.ite5bemember.service.TodayService;

@RestController
public class TodayController {
	
	@Resource private TodayService todayService;
	
	@PostMapping("/visit")
	public Map<String, String> visit(){
		int result = 0;
		Today todayVisit = todayService.getToday();
		if(todayVisit == null) {
			result = todayService.makeToday();
		}else {
			result = todayService.updateToday();
		}
		Map<String,String> map = new HashMap<String, String>();
		if(result == 0) {
			map.put("result", "fail");
		}else {
			map.put("result", "success");
		}
		return map;
	}
	
}
