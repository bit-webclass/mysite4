package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.ReboardDao;
import com.javaex.vo.BoardVo;
import com.javaex.vo.ReboardVo;

@Service
public class ReboardService {

	@Autowired
	private ReboardDao reboardDao;
	
	/* 리스트 */
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
		
		List<ReboardVo> reboardList = reboardDao.selectList(startRnum, endRnum, kwd);
		
		
		////////////////////////////////////////
		//페이징 계산
		/////////////////////////////////////////
		
		//전체글갯수
		int totalCount = reboardDao.totalCount(kwd) ;
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
		pMap.put("reboardList", reboardList);
		
		System.out.println(pMap.toString());
		
		return pMap;

	}
	
	/* 글쓰기 */
	public int write(ReboardVo reboardVo) {
		
		int count ;
		int groupNo = reboardVo.getGroupNo();
		
		
		if(groupNo != 0) { //댓글일때  orderNo, depth 증가시키기
			System.out.println("댓글");
			int orderNo = reboardVo.getOrderNo();
			int depth = reboardVo.getDepth();
			
			//자신보다 orderNo가 큰 글 +1시키기
			reboardDao.increaseOrderNo(groupNo, orderNo);
			
			//orderNo, depth 부모값에서 증가시키기
			reboardVo.setOrderNo(orderNo+1);
			reboardVo.setDepth(depth+1);
			count = reboardDao.insert(reboardVo);
		}else { //새글일때
			count = reboardDao.insert(reboardVo);
		}
		
		return count;
	}
	
	/* 글읽기 */
	public ReboardVo read(int no) {
		ReboardVo reboardVo = reboardDao.selectReboard(no);
		reboardDao.updateHit(no);
		return reboardVo;
	}
	
	/* 글수정 */
	public int modifyReboard(ReboardVo reboardVo) {
		return reboardDao.updateReboard(reboardVo);
	}
	
	/* 글삭제 */
	public int remove(ReboardVo reboardVo) {
		return reboardDao.deleteReboard(reboardVo);
	}
}
