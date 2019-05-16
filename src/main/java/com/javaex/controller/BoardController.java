package com.javaex.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.interceptor.Auth;
import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	/* 리스트 페이징 */
	@RequestMapping(value= {"","/list"}, method=RequestMethod.GET)
	public String list(@RequestParam(value="crtPage", required=false, defaultValue="1" ) int crtPage, 
					   @RequestParam( value="kwd", required=false, defaultValue="") String kwd,
					   Model model) {
		Map<String, Object> pMap = boardService.getList(crtPage, kwd);
		model.addAttribute("pMap", pMap);
		return "board/list";
	}
	
	
	/* 리스트 기본 */
	/*
	@RequestMapping(value= {"","/list"}, method=RequestMethod.GET)
	public String list(Model model) {
		List<BoardVo> boardList = boardService.getList();
		model.addAttribute("boardList", boardList);
		return "board/list";
	}
	*/
	
	
	/* 글쓰기폼 */
	@Auth
	@RequestMapping(value="/writeform", method=RequestMethod.GET)
	public String writeform(HttpSession session, HttpServletRequest request, Model model) {
		/*
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if( authUser != null) { //로그인 사용자이면 글쓰기폼으로
			return "board/writeform";
		}else { //로그인X 사용자이면 로그인폼
			System.out.println("비로그인  글쓰기폼");
			
			return "redirect:/user/loginform";
			
		}
		*/
		return "board/writeform";
	}
	
	/* 글저장 */
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(@ModelAttribute BoardVo boardVo, HttpSession session) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		boardVo.setUserNo(authUser.getNo());
		boardService.write(boardVo);		
		
		return "redirect:/board/list";
	}
	
	/* 글읽기 */
	@RequestMapping(value="/read/{no}", method=RequestMethod.GET)
	public String read(@PathVariable("no") int no, Model model) {
		BoardVo boardVo = boardService.read(no);
		model.addAttribute("boardVo", boardVo);
		return "board/read";
	}
	
	/* 글 수정폼 */
	@RequestMapping(value="/modifyform", method=RequestMethod.GET)
	public String modifyform(@RequestParam int no, HttpSession session, Model model) {
		BoardVo boardVo = boardService.modifyform(no);
		UserVo authUser =  (UserVo)session.getAttribute("authUser");
		if(boardVo.getUserNo() == authUser.getNo()) {
			model.addAttribute("boardVo", boardVo);
			return "board/modifyform";
		}else {
			return "redirect:/board/list";
		}
		
	}
	
	/* 글수정 */
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modifyform(@ModelAttribute BoardVo boardVo,
							 @RequestParam(value="crtPage", required=false, defaultValue="1" ) int crtPage, 
							 @RequestParam( value="kwd", required=false, defaultValue="") String kwd,
			                 HttpSession session
			                 ) throws UnsupportedEncodingException {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		int userNo = authUser.getNo();
		
		if(boardVo.getUserNo() == userNo) {
			boardService.modify(boardVo);
			System.out.println(crtPage);
			System.out.println(kwd);
			
			//한글키워드 인코딩
			kwd= URLEncoder.encode(kwd, "UTF-8"); 
			return "redirect:/board/read/"+boardVo.getNo()+"?crtPage="+ crtPage + "&kwd=" + kwd;
		}else {
			return "redirect:/board/list";
		}
		
	}
	
	/* 글삭제 */
	@RequestMapping(value="/remove", method=RequestMethod.GET)
	public String remove(@ModelAttribute BoardVo boardVo, HttpSession session) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		boardVo.setUserNo(authUser.getNo());
		boardService.remove(boardVo);	
		return "redirect:/board/list";
	}
	
	
}
