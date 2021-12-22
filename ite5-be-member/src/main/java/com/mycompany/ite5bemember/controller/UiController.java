package com.mycompany.ite5bemember.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.ite5bemember.dto.HomeOrderDto;
import com.mycompany.ite5bemember.service.UiService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UiController {
	
	@Resource
	private UiService uiService;
	
	@Cacheable(value="homeorderimg", key="#homeorderimg", cacheManager="cacheManager")
	@GetMapping("/gethomeorderimg/{homeorderimg}")
	public List<HomeOrderDto> gethomeorder(@PathVariable String homeorderimg) {
		return uiService.getHomeOrder();
	}
	
}
