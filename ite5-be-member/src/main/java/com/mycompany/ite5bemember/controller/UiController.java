package com.mycompany.ite5bemember.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.ite5bemember.service.UiService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UiController {
	
	@Resource
	private UiService uiService;
	
	@GetMapping("/gethomeorderimg")
	public Map<String,Object> gethomeorder() {
		return uiService.getHomeOrder();
	}
	
}
