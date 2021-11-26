package com.mycompany.ite5bemember.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.ite5bemember.dto.Cart;
import com.mycompany.ite5bemember.dto.CartedProduct;
import com.mycompany.ite5bemember.security.JWTUtil;
import com.mycompany.ite5bemember.service.CartService;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/member/cart")
@Slf4j

public class CartController {
	
	@Resource
	CartService cartService;
	
	
	// **장바구니리스트 조회**
	@PostMapping("/cartlist")
	public List<CartedProduct> searchlike(HttpServletRequest request){
		String jwt = request.getHeader("Authorization").substring(7);
	 	Claims claims = JWTUtil.validateToken(jwt);
		String mid = JWTUtil.getMid(claims);
		
		List<Cart> cartList = cartService.getCartList(mid);
		if(cartList.size()==0) {
			return new ArrayList<CartedProduct>();
		}
		
		List<CartedProduct> cartedList = cartService.cartProductByCart(cartList);
		for(CartedProduct cartedProduct : cartedList) {
			for(Cart cart :cartList) {
				if(cart.getPsid().equals(cartedProduct.getPsid())) {
					cartedProduct.setPquantity(cart.getPquantity());
					break;
				}
			}
		}
		
		return cartedList;
	}
	
	// **장바구니에서 상품 삭제**
	@DeleteMapping("/deletecart")
	public Map<String,String> deletecart(HttpServletRequest request, @RequestBody String json) {
		String jwt = request.getHeader("Authorization").substring(7);
		Claims claims = JWTUtil.validateToken(jwt);
		String mid = JWTUtil.getMid(claims);
		
		JSONObject jsonObject = new JSONObject(json);
		String psid = jsonObject.getString("psid");
		
		Cart cart = new Cart();
		cart.setMid(mid);
		cart.setPsid(psid);
		
		int result = cartService.deleteCart(cart);
		
		Map<String,String> map = new HashMap<String, String>();
		
		if(result == 0) {
			map.put("result", "fail");
		}else {
			map.put("result", "success");
		}
		return map;
	}
	
	// **장바구니에서 상품 "변경"버튼 누를때 => 색상,사이즈 정보보여주어야한다.
	@PostMapping("/getcolorsize")
	public Map<String,Object> getcolorsize(@RequestBody String json) {
		
		JSONObject jsonObject = new JSONObject(json);
		String pid = jsonObject.getString("pid");
		
		Map colorsize = cartService.getColorSize(pid);
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("colorsize", colorsize);
		return map;
	}
	
	// **장바구니에 담기**
	@PostMapping("/addcart")
	public Map<String,String> addcart(HttpServletRequest request, @RequestBody Cart cart){
		String jwt = request.getHeader("Authorization").substring(7);
		Claims claims = JWTUtil.validateToken(jwt);
		String mid = JWTUtil.getMid(claims);
		cart.setMid(mid);
		
		//기존에 카트에 상품이 담겨있는지 확인
		Cart searchedCart = cartService.searchCart(cart);
		
		int result = 0;
		//전에 장바구니에 담긴적이 없을때 새롭게 추가
		if(searchedCart == null) {
			result = cartService.addCart(cart);
		//전에 장바구니에 담긴적이 있는경우 수량만 변경
		}else {
			searchedCart.setPquantity(searchedCart.getPquantity()+cart.getPquantity());
			result = cartService.updateCart(searchedCart);
		}
		
		Map<String,String> map = new HashMap<String, String>();
		if(result == 0) {
			map.put("result", "fail");
		}else {
			map.put("result", "success");
		}
		return map;
	}
	
	//장바구니에서 색상,사이즈,수량을 바꾸고 "저장 버튼을 누른 경우"
	@PostMapping("/changecart")
	public Map<String,String> changecart(HttpServletRequest request, @RequestBody String json){
		String jwt = request.getHeader("Authorization").substring(7);
		Claims claims = JWTUtil.validateToken(jwt);
		String mid = JWTUtil.getMid(claims);
		
		JSONObject jsonObject = new JSONObject(json);
		String oldpsid = jsonObject.getString("oldpsid");
		String newpsid = jsonObject.getString("newpsid");
		int pquantity = jsonObject.getInt("pquantity");
		
		int result = 0;
		Cart cart = new Cart();
		cart.setMid(mid);
		cart.setPquantity(pquantity);
		cart.setPsid(newpsid);
		
		//사이즈,색상 같고 수량만 다른 경우 - 동일한 psid에서 pquantity만 update
		if(oldpsid.equals(newpsid)) {
			result = cartService.updateCart(cart);
		//사이즈,색상 모두 변경된 경우, 수량은 상관없음
		}else if(!oldpsid.equals(newpsid)) {
			
			//기존에 변경하고하자는 psid가 있는지 확인
			Cart searchedCart = cartService.searchCart(cart);
			
			//기존에 변경하고자하는 psid가 있는경우
			if(searchedCart != null) {
				cart.setPsid(oldpsid);
				int result1 = cartService.deleteCart(cart);
				cart.setPsid(newpsid);
				cart.setPquantity(searchedCart.getPquantity()+cart.getPquantity());
				int result2 = cartService.updateCart(cart);
				if(result1 != 0 && result2 != 0) {
					result = 1;
				}
			//기존에 변경하고자하는 psid가 없는경우
			}else {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("oldpsid", oldpsid);
				map.put("newpsid", newpsid);
				map.put("pquantity", pquantity);
				map.put("mid",mid);
				result = cartService.updateCart(map);
			}
		}
		//추가하는 과정에서 장바구니에 전에 담긴게 있는지 확인 필요
		//있다면 psid 지우고 기존 psid를 update
		//없다면 psid를 지우고 새로운 psid를 추가
		Map<String,String> map = new HashMap<String, String>();
		if(result == 0) {
			map.put("result", "fail");
		}else {
			map.put("result", "success");
		}
		return map;
	}
}
