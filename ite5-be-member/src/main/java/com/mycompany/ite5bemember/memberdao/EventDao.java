package com.mycompany.ite5bemember.memberdao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.ite5bemember.dto.Event;
import com.mycompany.ite5bemember.dto.Pager;

@Mapper
public interface EventDao {
	public int totalEventCount();
	public List<Event> selectEventList(Pager pager);
	public Event selectEvent(int eno);
	public int updateToExhausted(int eno);
	public int updateEcount(Map<String, Object> map);
	public List<Event> selectExpiredEvent();
	public int updateExpiredEvent(List<Event> event);
}
