package com.mycompany.ite5bemember.memberdao;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.ite5bemember.dto.Today;

@Mapper
public interface TodayDao {
	public Today selectToday(String today);
	public int insertToday(int count);
	public int updateToday(String today);
}
