package com.mycompany.ite5bemember.memberdao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.ite5bemember.dto.Cart;

@Mapper
public interface CartDao {
	public List<Cart> selectCartByMid(String mid);
	public int deleteCart(Cart cart);
	public int insertCart(Cart cart);
}
