package com.mycompany.ite5bemember.scheduler;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mycompany.ite5bemember.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Scheduler {
	
	@Resource 
	private MemberService memberService;
	
	@Scheduled(cron ="00 00 2 * * *")
	public void scheduleMemberGrade() {
		log.info("회원등급 Update Scheduler 작업시작");
		memberService.updateGrade();
		log.info("회원등급 Update Scheduler 작업끝");
	}
}
