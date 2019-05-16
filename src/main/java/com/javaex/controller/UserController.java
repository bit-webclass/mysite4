package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/joinform", method=RequestMethod.GET)
	public String joinform() {
		
		return "user/joinform";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println(userVo.toString());
		int count = userService.join(userVo);
		return "user/joinsuccess";
	}
	
	@RequestMapping(value="/loginform", method=RequestMethod.GET)
	public String loginform() {
		return "user/loginform";
	}
	
	/*//인터셉터로 대체
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@RequestParam("email") String email, 
						@RequestParam("password") String password,
						HttpSession session) {
		UserVo authUser = userService.login(email, password);
		
		if(authUser != null) {//로그인 성공이면 로그인 처리 후 메인
			session.setAttribute("authUser", authUser);
			return "redirect:/main";
		}else {//로그인 실패
			return "redirect:/user/loginform?result=fail";
		}
		
	}
	*/

	
	/* //인터셉터로 대체
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("authUser");
		session.invalidate();
		return "redirect:/main";
	}
	*/
	
	@RequestMapping(value = "/modifyform", method = RequestMethod.GET)
	public String modifyform(HttpSession session, Model model) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		int no = authUser.getNo();

		UserVo userVo = userService.modifyform(no);
		model.addAttribute("userVo", userVo);
		return "user/modifyform";
	}

	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(@ModelAttribute UserVo userVo, HttpSession session) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		int no = authUser.getNo();
		userVo.setNo(no);

		userService.modify(userVo);
		authUser.setName(userVo.getName());

		return "redirect:/main";
	}
}
