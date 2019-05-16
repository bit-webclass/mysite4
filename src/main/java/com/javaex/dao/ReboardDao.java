package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.ReboardVo;

@Repository
public class ReboardDao {
	
	@Autowired
	private SqlSession sqlSession;

	/* 리스트 */
	public List<ReboardVo> selectList(int startRnum, int endRnum, String kwd) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startRnum", startRnum );
		map.put("endRnum", endRnum );
		map.put("kwd", kwd );
		return sqlSession.selectList("reboard.selectList", map);
	}
	
	/* 전체글갯수 */
	public int totalCount(String kwd) {
		return sqlSession.selectOne("reboard.totalCount", kwd);
	}
	
	
	/* 글저장 */
	public int insert(ReboardVo reboardVo) {
		return sqlSession.insert("reboard.insert", reboardVo);
	}
	
	// orderNo증가시키지
	public int increaseOrderNo(int groupNo, int orderNo) {
		Map<String, Integer> imap = new HashMap<String, Integer>();
		imap.put("groupNo", groupNo);
		imap.put("orderNo", orderNo);
		return sqlSession.update("reboard.increaseOrderNo", imap);
	}
	
	
	
	/* 글 가져오기 */
	public ReboardVo selectReboard(int no) {
		return sqlSession.selectOne("reboard.selectReboard", no);
	}
	
	/* 조회수 증가 */
	public int updateHit(int no) {
		return sqlSession.update("reboard.updateHit", no);
	}
	
	
	/* 글 수정 */
	public int updateReboard(ReboardVo reboardVo) {
		return sqlSession.update("reboard.updateReboard", reboardVo);
	}
	
	/* 글삭제 */
	public int deleteReboard(ReboardVo reboardVo) {
		return sqlSession.update("reboard.deleteReboard", reboardVo);
	}
}
