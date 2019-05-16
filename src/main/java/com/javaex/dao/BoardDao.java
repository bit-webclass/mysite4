package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;
	
	/* 글전체리스트 가져오기 기본*/
	/*
	public List<BoardVo> selectAll(){
		return sqlSession.selectList("board.selectAll");
	}
	*/
	
	/* 리스트 페이징 */
	public List<BoardVo> selectAll(int startRnum, int endRnum, String kwd){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startRnum", startRnum );
		map.put("endRnum", endRnum );
		map.put("kwd", kwd );
		
		return sqlSession.selectList("board.selectAll", map );
	}
	
	/* 전체글갯수 */
	public int totalCount(String kwd) {
		return sqlSession.selectOne("board.totalCount", kwd);
	}
	
	
	
	
	/* 글저장 */
	public int insert(BoardVo boardVo) {
		return sqlSession.insert("board.insert", boardVo);
	}
	
	/* no로 글가져오기 */
	public BoardVo selectBoard(int no) {
		return sqlSession.selectOne("board.selcetBoard", no);
	}
	
	/* 조회수 증가 */
	public int updateHit(int no) {
		return sqlSession.update("board.updateHit", no);
	}
	
	/* 글 수정 */
	public int update(BoardVo boardVo) {
		System.out.println(boardVo.toString());
		return sqlSession.update("board.update", boardVo);
	}

	/* 글 삭제 */
	public int delete(BoardVo boardVo) {
		return sqlSession.delete("board.delete", boardVo);
	}
	
}
