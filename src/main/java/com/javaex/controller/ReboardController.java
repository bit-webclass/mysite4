package com.javaex.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.ReboardService;
import com.javaex.vo.ReboardVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/reboard")
public class ReboardController {
	
	@Autowired
	private ReboardService reboardService;
	
	/* 리스트 */
	@RequestMapping(value= {"","/list"}, method=RequestMethod.GET )
	public String list(@RequestParam(value="crtPage", required=false, defaultValue="1" ) int crtPage, 
			   			@RequestParam( value="kwd", required=false, defaultValue="") String kwd,
			   			Model model) {
		System.out.println("리스트"+ crtPage + kwd);
		Map<String, Object> pMap = reboardService.getList(crtPage, kwd);
		model.addAttribute("pMap", pMap);
		return "reboard/list";
	}
	
	/* 새글쓰기폼 */
	@RequestMapping(value="/writeform", method=RequestMethod.GET)
	public String writeform() {
		return "reboard/writeform";
	}
	
	/* 글쓰기 */
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(@ModelAttribute ReboardVo reboardVo,  
			 @RequestParam(value="crtPage", required=false, defaultValue="1" ) int crtPage, 
			 @RequestParam( value="kwd", required=false, defaultValue="") String kwd,
			 HttpSession session) throws UnsupportedEncodingException {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		reboardVo.setUserNo(authUser.getNo());
		System.out.println(reboardVo.toString());
		reboardService.write(reboardVo);
		
		System.out.println("crtPage  "+crtPage);
		
		kwd= URLEncoder.encode(kwd, "UTF-8");
		return "redirect:/reboard/list?crtPage="+ crtPage + "&kwd=" + kwd;
	}
	
	
	/* 글읽기 */
	@RequestMapping(value="/read/{no}", method=RequestMethod.GET)
	public String write(@PathVariable("no") int no, Model model) {
		
		ReboardVo reboardVo = reboardService.read(no);
		model.addAttribute("reboardVo", reboardVo);
		return "reboard/read";
	}
	
	/* 글 수정 폼 */
	@RequestMapping(value="/modifyform", method=RequestMethod.GET)
	public String modifyform(@RequestParam("no") int no, Model model) {
		
		ReboardVo reboardVo = reboardService.read(no);
		model.addAttribute("reboardVo", reboardVo);
		return "reboard/modifyform";
	}
	
	/* 글 수정 */
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(@ModelAttribute ReboardVo reboardVo,  
			 			 @RequestParam(value="crtPage", required=false, defaultValue="1" ) int crtPage, 
			 			 @RequestParam( value="kwd", required=false, defaultValue="") String kwd,
			 			 HttpSession session) throws UnsupportedEncodingException {
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		int userNo = authUser.getNo();
		reboardVo.setUserNo(userNo);
		
		reboardService.modifyReboard(reboardVo);
		
		kwd= URLEncoder.encode(kwd, "UTF-8");
		return "redirect:/reboard/read/"+reboardVo.getNo()+"?crtPage="+ crtPage + "&kwd=" + kwd;
	}
	
	
	/* 댓글쓰기폼 */
	@RequestMapping(value="/replyform/{no}", method=RequestMethod.GET)
	public String replyform(@PathVariable("no") int no, Model model) {
		ReboardVo reboardVo = reboardService.read(no);
		model.addAttribute("reboardVo", reboardVo);
		
		return "reboard/replyform";
	}
	
	/* 글삭제 */
	@RequestMapping(value="/remove", method=RequestMethod.GET)
	public String remove(@ModelAttribute ReboardVo reboardVo,  
						 @RequestParam(value="crtPage", required=false, defaultValue="1" ) int crtPage, 
						 @RequestParam( value="kwd", required=false, defaultValue="") String kwd,
						 HttpSession session) throws UnsupportedEncodingException {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		int userNo = authUser.getNo();
		reboardVo.setUserNo(userNo);
		
		reboardService.remove(reboardVo);
		
		kwd= URLEncoder.encode(kwd, "UTF-8");
		return "redirect:/reboard/list?crtPage="+ crtPage + "&kwd=" + kwd;
	}
}
