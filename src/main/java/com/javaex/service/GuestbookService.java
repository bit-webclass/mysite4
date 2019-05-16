package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestbookService {
	
	@Autowired
	private GuestbookDao guestbookDao;

	public List<GuestbookVo> getList(){
		return guestbookDao.selectList();
	}

	public List<GuestbookVo> getList(int lastNo){
		return guestbookDao.selectList(lastNo);
	}
	
	public int add(GuestbookVo vo){
		return guestbookDao.insert(vo);
	}
	
	public int delete(GuestbookVo vo){
		return guestbookDao.delete(vo);
	}
	
	public GuestbookVo addSelectVo(GuestbookVo vo) {
		guestbookDao.insertSelectNo(vo);
		int no = vo.getNo();
		return guestbookDao.selectByNo(no);
	}
	
}
