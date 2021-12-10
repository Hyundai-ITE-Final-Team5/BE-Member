package com.mycompany.ite5bemember.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.ite5bemember.dto.Event;
import com.mycompany.ite5bemember.dto.Pager;
import com.mycompany.ite5bemember.memberdao.EventDao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EventService {
	
	public enum EventCheckResult {
		DOWNLOAD_POSSIBLE,
		COUPON_EXHAUSTED,
		EXPIRED_DATE,
		ENO_NONE
	}
	
	@Resource
	private EventDao eventDao;
	
	public List<Event> getEvent(Pager pager){
		return eventDao.selectEventList(pager);
	}
	
	public int getTotalEventNum(){
		return eventDao.totalEventCount();
	}
	
	public Event getEventDetail(int eno) {
		return eventDao.selectEvent(eno);
	}
	
	public EventCheckResult getEventStatus(int eno) {
		 Event event = eventDao.selectEvent(eno);
		 //select과정에서 쿠폰이 다 발행되었는지 확인
		 if(event.getEcount() >= event.getElimitcount()) {
			 eventDao.updateToExhausted(eno);
			 //쿠폰소진으로 상태변경
			 event.setEstatus(2);
		 }
			 
		 EventCheckResult result = null;
		 if(event == null) {
			 return  EventCheckResult.ENO_NONE;
		 }
		 int estatus = event.getEstatus();
		 
		 if(estatus == 0) {
			 result = EventCheckResult.EXPIRED_DATE;
		 }else if(estatus == 1) {
			 result = EventCheckResult.DOWNLOAD_POSSIBLE;			 
		 }else if(estatus == 2) {
			 result = EventCheckResult.COUPON_EXHAUSTED;
		 }
		 return result;
		 
	}
	
	// 날짜가 지난 Event status 처리
	public void updateStatus() {
		List<Event> eventList = eventDao.selectExpiredEvent();
		if(eventList.size() != 0) {
			eventDao.updateExpiredEvent(eventList);
		}
	}

}
