package com.mycompany.ite5bemember.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.ite5bemember.dto.HomeOrderDto;
import com.mycompany.ite5bemember.memberdao.HomeOrderDao;

@Service
public class UiService {
	
	@Resource
	private HomeOrderDao homeOrderDao;
	
	public List<HomeOrderDto> getHomeOrder() {
		List<HomeOrderDto> homeorderList = homeOrderDao.selectHomeOrder();
		return homeorderList;
	}
}
