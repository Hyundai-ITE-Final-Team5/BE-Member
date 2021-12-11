package com.mycompany.ite5bemember.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.ite5bemember.dto.HomeOrder;
import com.mycompany.ite5bemember.dto.HomeOrderDto;
import com.mycompany.ite5bemember.memberdao.HomeOrderDao;

@Service
public class UiService {
	
	@Resource
	private HomeOrderDao homeOrderDao;
	
	public Map<String,Object> getHomeOrder() {
		Map<String,Object> outermap = new HashMap<String, Object>();
		List<HomeOrderDto> homeOrderList = homeOrderDao.selectHomeOrder();
		for(HomeOrderDto homeOrderDto : homeOrderList) {
			outermap.put(homeOrderDto.getHoname(), homeOrderDto);
		}
		return outermap;
	}
}
