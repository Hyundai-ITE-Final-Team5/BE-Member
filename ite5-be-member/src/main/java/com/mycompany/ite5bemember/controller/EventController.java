package com.mycompany.ite5bemember.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.ite5bemember.dto.Event;
import com.mycompany.ite5bemember.dto.Pager;
import com.mycompany.ite5bemember.service.EventService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/event")
@Slf4j
public class EventController {
	
	@Resource
	EventService eventService;
	
	
	// **이벤트 리스트 조회 5개씩**
	@GetMapping("/eventlist")
	public List<Event> eventlist(@RequestParam(defaultValue = "1") int pageNo){
		int totalRows = eventService.getTotalEventNum();
		Pager pager = new Pager(5, 5, totalRows, pageNo);
		List<Event> eventList = eventService.getEvent(pager);
		return eventList;
	}
	
	// ** 이벤트 상세페이지 ** 
	@GetMapping("/eventdetail")
	public Event eventdetail(int eno) {
		return eventService.getEventDetail(eno);
	}
}
