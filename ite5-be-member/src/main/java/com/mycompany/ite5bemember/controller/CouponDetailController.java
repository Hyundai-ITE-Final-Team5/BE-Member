package com.mycompany.ite5bemember.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.ite5bemember.dto.CouponDetail;
import com.mycompany.ite5bemember.dto.DownloadedCoupon;
import com.mycompany.ite5bemember.security.JWTUtil;
import com.mycompany.ite5bemember.service.CouponDetailService;
import com.mycompany.ite5bemember.service.EventService;
import com.mycompany.ite5bemember.service.EventService.EventCheckResult;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/member/coupon")
@Slf4j
public class CouponDetailController {
	
	@Resource
	private EventService eventService;
	
	@Resource
	private CouponDetailService couponDetailService;
	
	// ** 쿠폰목록조회 **
	@GetMapping("/couponlist")
	public List<DownloadedCoupon> couponlist(HttpServletRequest request){
		String jwt = request.getHeader("Authorization").substring(7);
	 	Claims claims = JWTUtil.validateToken(jwt);
		String mid = JWTUtil.getMid(claims);
		
		List<DownloadedCoupon> couponList = couponDetailService.getCouponList(mid);
		if(couponList.size()==0) {
			return new ArrayList<DownloadedCoupon>();
		}
		return couponList;
	}
		
	// ** 쿠폰다운로드 **
	@GetMapping("/download")
	public Map<String, String> download(HttpServletRequest request, int eno){
		Map<String, String> map = new HashMap<>();
		
		//토큰의 값이 null인 경우
		if(request.getHeader("Authorization") == null) {
			map.put("result", "needlogin");
			return map;
		}
		
		//토큰의 값이 ""인 경우
		if(request.getHeader("Authorization").equals("")) {
			map.put("result", "needlogin");
			return map;
		} else {
			
			String jwt = request.getHeader("Authorization").substring(7);
		 	Claims claims = JWTUtil.validateToken(jwt);
			String mid = JWTUtil.getMid(claims);
			
			
			//해당 이벤트의 상태확인
			EventCheckResult eventCheckResult =  eventService.getEventStatus(eno);

			if(eventCheckResult == eventCheckResult.DOWNLOAD_POSSIBLE) {
				//이전에 쿠폰다운로드를 한 이력이 있는지 확인
				CouponDetail coupon = couponDetailService.searchCoupon(eno,mid);
				if(coupon != null) {
					map.put("result", "already");
				} else {
					couponDetailService.addCoupon(eno,mid);
					map.put("result", "success");
				}
			}else if(eventCheckResult == eventCheckResult.COUPON_EXHAUSTED) {
				map.put("result", "exhausted");
			}else if(eventCheckResult == eventCheckResult.EXPIRED_DATE) {
				map.put("result", "expired");
			}else if(eventCheckResult == eventCheckResult.ENO_NONE) {
				map.put("result", "enoerror");
			}
			return map;
		}
	}
}
