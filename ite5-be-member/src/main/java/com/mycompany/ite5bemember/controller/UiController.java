package com.mycompany.ite5bemember.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.ite5bemember.dto.HomeOrderDto;
import com.mycompany.ite5bemember.service.UiService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UiController {
	
	@Resource
	private UiService uiService;
	
	@GetMapping("/gethomeorderimg")
	public List<HomeOrderDto> gethomeorder() {
		return uiService.getHomeOrder();
	}
	
}
