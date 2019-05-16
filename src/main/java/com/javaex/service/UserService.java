package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	/* 회원가입*/
	public int join(UserVo userVo) {
		return userDao.insert(userVo);
	}
	
	/* 로그인 */
	public UserVo login(String email, String password) {
		return userDao.select(email, password);
	}
	
	/* 수정폼 */
	public UserVo modifyform(int no) {
		return userDao.select(no);
	}
	
	/* 수정 */
	public int modify(UserVo userVo) {
		return userDao.update(userVo);
	}
}
