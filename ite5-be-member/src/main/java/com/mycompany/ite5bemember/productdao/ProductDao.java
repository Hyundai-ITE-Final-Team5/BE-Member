package com.mycompany.ite5bemember.productdao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.ite5bemember.dto.Cart;
import com.mycompany.ite5bemember.dto.CartedProduct;
import com.mycompany.ite5bemember.dto.LikedProduct;
import com.mycompany.ite5bemember.dto.Likes;

@Mapper
public interface ProductDao {
	public List<LikedProduct> selectBylikes(List<Likes> likes);
	public List<CartedProduct> selectByCart(List<Cart> cart);
	public Map selectColorNSizeByPid(String pid);
}
