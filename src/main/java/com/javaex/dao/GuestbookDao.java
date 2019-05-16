package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<GuestbookVo> selectList() {
		return sqlSession.selectList("guestbook.selectList");
	}

	public int insert(GuestbookVo vo) {
		return sqlSession.insert("guestbook.insert", vo);
	}

	public int delete(GuestbookVo vo) {
		return sqlSession.delete("guestbook.delete", vo);
	}
	
	//ajax방명록 리스트
	public List<GuestbookVo> selectList(int lastNo){
		return sqlSession.selectList("guestbook.selectListBylastNo", lastNo);
	}
	
	/* ajax등록 */
	public int insertSelectNo(GuestbookVo vo) {
		return sqlSession.insert("guestbook.insertSelectNo", vo);
	}
	
	/* ajax selectVo */
	public GuestbookVo selectByNo(int no) {
		return sqlSession.selectOne("guestbook.selectByNo", no);
	}
}
