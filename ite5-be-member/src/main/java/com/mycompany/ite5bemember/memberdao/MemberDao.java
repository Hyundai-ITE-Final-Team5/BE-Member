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
	public int update1Grade(List<Member> member);
	public int update2Grade(List<Member> member);
	public int update3Grade(List<Member> member);
	public int update4Grade(List<Member> member);
	public int update5Grade(List<Member> member);
	public int update6Grade(List<Member> member);
	
}
