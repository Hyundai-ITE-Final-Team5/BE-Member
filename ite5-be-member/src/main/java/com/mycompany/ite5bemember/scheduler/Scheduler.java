package com.mycompany.ite5bemember.scheduler;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mycompany.ite5bemember.service.EventService;
import com.mycompany.ite5bemember.service.MemberService;
import com.mycompany.ite5bemember.service.TodayService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Scheduler {
	
	@Resource 
	private MemberService memberService;

	@Resource 
	private TodayService todayService;
	
	@Resource
	private EventService eventService;
	
	@Scheduled(cron ="00 00 2 * * *")
	public void scheduleMemberGrade() {
		log.info("현재시간 : "+ new Date().toLocaleString());
		log.info("--회원등급 Update Scheduler 작업시작--");
		memberService.updateGrade();
		log.info("--회원등급 Update Scheduler 작업끝--");
	}
	
	@Scheduled(cron = "00 00 0 * * *")
	public void scheduleEventStatus() {
		log.info("현재시간 : "+ new Date().toLocaleString());
		log.info("--이벤트 Status Update Scheduler 작업시작--");
		eventService.updateStatus();
		log.info("--이벤트 Status Update Scheduler 작업끝--");
	}
	
	@Scheduled(cron = "00 00 3 * * *")
	public void scheduleTodayInsert() {
		log.info("현재시간 : "+ new Date().toLocaleString());
		log.info("--Today Insert Scheduler 작업시작--");
		todayService.insertVisitor();
		log.info("--Today Insert Scheduler Scheduler 작업끝--");
	}
}
