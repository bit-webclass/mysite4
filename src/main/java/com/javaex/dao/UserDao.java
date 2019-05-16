package com.javaex.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	/* 회원정저장 */
	public int insert(UserVo userVo) {
		return sqlSession.insert("user.insert", userVo);
	}
	
	/* 회원정보 가져오기(email, password) */
	public UserVo select(String email, String password) {
		Map<String, Object> userMap = new HashMap<String, Object>();
		userMap.put("email", email);
		userMap.put("password", password);
		return sqlSession.selectOne("user.select", userMap);
	}
	
	/* 회원정보 가져오기(no) */	
	public UserVo select(int no) {
		return sqlSession.selectOne("user.selectByNo", no);
	}
	
	
	/* 회원정보 update */	
	public int update(UserVo userVo) {
		return sqlSession.update("user.update", userVo);
	}
}
