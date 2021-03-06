package com.mycompany.ite5bemember.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.mycompany.ite5bemember.memberdao.TodayDao;

@Service
public class TodayService {
	
	@Resource
	private RedisTemplate<String, String> redisTemplate;
	
	@Resource TodayDao todayDao;
	
	public Map<String,String> visit() {
	ValueOperations<String, String> vop = redisTemplate.opsForValue();
	Date nowDate  = new Date();
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy/MM/dd"); 
	String today = simpleDateFormat.format(nowDate);
	vop.increment(today, 1);
	//유효시간 설정 2일
	//vop.getAndExpire(today, 2, TimeUnit.DAYS);
		
	Map<String,String> map = new HashMap<String, String>();
	map.put("result", "success");
	return map;
	}
	
	//어제 방문자 수를 insert하는 메서드
	public void insertVisitor() {
		ValueOperations<String, String> vop = redisTemplate.opsForValue();
		
		Calendar cal = Calendar.getInstance();
	    cal.setTime(new Date());
	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy/MM/dd"); 
	    
	    //하루 빼기
	    cal.add(Calendar.DATE, -1);
		
		String yesterday = simpleDateFormat.format(cal.getTime());
		int count = Integer.valueOf(vop.get(yesterday));
		
		todayDao.insertToday(count);
	}
	
	//방문자 수를 return하는 메서드
	public int getTodayCount() {
		ValueOperations<String, String> vop = redisTemplate.opsForValue();
		Date nowDate  = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy/MM/dd"); 
		String today = simpleDateFormat.format(nowDate);
		int count = Integer.valueOf(vop.get(today));
		return count;
	}
}
