package com.mycompany.ite5bemember.memberdao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.ite5bemember.dto.Likes;
import com.mycompany.ite5bemember.dto.Member;


@Mapper
public interface MemberDao {
	public int insert(Member member);	
	public Member selectByMid(String mid);
	public int updateMember(Member member);

	
}
