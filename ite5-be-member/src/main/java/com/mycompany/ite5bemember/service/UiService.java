package com.mycompany.ite5bemember.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.ite5bemember.dto.HomeOrder;
import com.mycompany.ite5bemember.dto.HomeOrderDto;
import com.mycompany.ite5bemember.memberdao.HomeOrderDao;

@Service
public class UiService {
	
	@Resource
	private HomeOrderDao homeOrderDao;
	
	public HomeOrderDto getHomeOrder() {
		HomeOrderDto homeOrderDto = new HomeOrderDto();
		List<HomeOrder> homeOrderList = homeOrderDao.selectHomeOrder();
		for(HomeOrder homeOrder : homeOrderList) {
			if(homeOrder.getHname().equals("newproduct")) {
				homeOrderDto.setNewproduct(homeOrder.getHorder());
			}else if(homeOrder.getHname().equals("bestproduct")) {
				homeOrderDto.setBestproduct(homeOrder.getHorder());
			}else if(homeOrder.getHname().equals("event")) {
				homeOrderDto.setEvent(homeOrder.getHorder());
			}
		}
		return homeOrderDto;
	}
}
