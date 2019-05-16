package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;
	
	/* 리스트 기본 */
	/*
	public List<BoardVo> getList(){
		List<BoardVo> boardList = boardDao.selectAll(); 
		return boardList;
		
	}
	*/
	
	/* 리스트 페이징 */
	public Map<String, Object> getList(int crtPage, String kwd){
		////////////////////////////////////////
		//리스트 가져오기  
		/////////////////////////////////////////
		
		//페이지당 글갯수
		int listCnt = 10;
		
		//현재페이지
		crtPage = (crtPage > 0) ? crtPage : (crtPage=1); // crtPage가 0보다 작으면 1page로
		
		//시작글 번호
		int startRnum = (crtPage -1)*listCnt;  //1page --> 0
		
		//끝글 번호
		int endRnum = startRnum + listCnt;  //1page --> 10
		
		List<BoardVo> boardList = boardDao.selectAll(startRnum, endRnum, kwd);
		
		
		////////////////////////////////////////
		//페이징 계산
		/////////////////////////////////////////
		
		//전체글갯수
		int totalCount = boardDao.totalCount(kwd) ;
		System.out.println(totalCount);
		//페이지당 버튼 갯수
		int pageBtnCount = 5;
		
		//마지막버튼번호
		//1  --> 1~5
		//2  --> 1~5
		//3  --> 1~5
		//6  --> 6~10
		//7  --> 6~10
		//10  --> 6~10
		int endPageBtnNo = (int)Math.ceil(crtPage/(double)pageBtnCount)*pageBtnCount;
		
		//시작버튼번호
		int startPageBtnNo = endPageBtnNo - (pageBtnCount-1) ;
		
		//다음 화살표 유무
		boolean next = false ;
		if(endPageBtnNo*listCnt < totalCount) {  //10*10 < 107
			next = true;
		}else { 
			endPageBtnNo = (int)Math.ceil(totalCount/(double)listCnt);
		}
		
		//이전 화살표 유무
		boolean prev = false;
		if(startPageBtnNo != 1) {
			prev = true;
		}
		
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("prev", prev);
		pMap.put("startPageBtnNo", startPageBtnNo);
		pMap.put("endPageBtnNo", endPageBtnNo);
		pMap.put("next", next);
		pMap.put("boardList", boardList);
		
		System.out.println(pMap.toString());
		
		return pMap;
		
	}
	
	
	/* 글저장 */
	public int write(BoardVo boardVo) {
		
		return boardDao.insert(boardVo);
	}
	
	/* 글읽기 */
	public BoardVo read(int no) {
		boardDao.updateHit(no);
		BoardVo boardVo = boardDao.selectBoard(no);
		return boardVo;
	}
	
	/* 글 수정폼 */
	public BoardVo modifyform(int no) {
		BoardVo boardVo = boardDao.selectBoard(no);
		return boardVo;
	}
	
	/* 글 수정 */
	public int modify(BoardVo boardVo) {
		return boardDao.update(boardVo);
	}
	
	/* 글 삭제 */
	public int remove(BoardVo boardVo) {
		return boardDao.delete(boardVo);
	}
}
