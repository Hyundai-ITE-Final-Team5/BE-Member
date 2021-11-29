package com.mycompany.ite5bemember.orderdao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.ite5bemember.dto.Member;
import com.mycompany.ite5bemember.dto.Orders;

@Mapper
public interface OrdersDao {
	public int selectTotalOrder(String mid);
	public List<Orders> selectOrders(String mid);
	public List<Member> select1grade();
	public List<Member> select2grade();
	public List<Member> select3grade();
	public List<Member> select4grade();
	public List<Member> select5grade();
	public List<Member> select6grade();
}
