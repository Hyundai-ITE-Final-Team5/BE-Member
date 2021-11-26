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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.ite5bemember.dto.CartedProduct;
import com.mycompany.ite5bemember.dto.LikedProduct;
import com.mycompany.ite5bemember.dto.Likes;
import com.mycompany.ite5bemember.security.JWTUtil;
import com.mycompany.ite5bemember.service.LikesService;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/member/likes")
@Slf4j
public class LikesController {
	@Resource
	private LikesService likesService;
	
	// **찜목록 조회**
	@PostMapping("/likelist")
	public List<LikedProduct> searchlike(HttpServletRequest request){
		String jwt = request.getHeader("Authorization").substring(7);
		Claims claims = JWTUtil.validateToken(jwt);
		String mid = JWTUtil.getMid(claims);
		
		List<Likes> likeList = likesService.getLikeList(mid);
		if(likeList.size()==0) {
			return new ArrayList<LikedProduct>();
		}
		List<LikedProduct> likedProductList= likesService.getProductbyLike(likeList);
		return likedProductList;
	}
	
	// **찜목록 삭제**
	@DeleteMapping("/deletelike")
	public Map<String,String> deletelike(HttpServletRequest request, @RequestBody String json) {
		String jwt = request.getHeader("Authorization").substring(7);
		Claims claims = JWTUtil.validateToken(jwt);
		String mid = JWTUtil.getMid(claims);
		
		JSONObject jsonObject = new JSONObject(json);
		String pid = jsonObject.getString("pid");
		
		Likes likes = new Likes();
		likes.setMid(mid);
		likes.setPid(pid);
		
		int result = likesService.deleteLike(likes);
		
		Map<String,String> map = new HashMap<String, String>();
		
		if(result == 0) {
			map.put("result", "fail");
		}else {
			map.put("result", "success");
		}
		return map;
	}
	
	// **찜목록에 추가**
	@PostMapping("/addlike")
	public Map<String,String> addlike(HttpServletRequest request, @RequestBody String json){
		String jwt = request.getHeader("Authorization").substring(7);
		Claims claims = JWTUtil.validateToken(jwt);
		String mid = JWTUtil.getMid(claims);
		
		JSONObject jsonObject = new JSONObject(json);
		String pid = jsonObject.getString("pid");
		
		Likes likes = new Likes();
		likes.setMid(mid);
		likes.setPid(pid);
		
		int result = likesService.addLike(likes);
		
		Map<String,String> map = new HashMap<String, String>();
		if(result == 0) {
			map.put("result", "fail");
		}else {
			map.put("result", "success");
		}
		return map;
	}
}
