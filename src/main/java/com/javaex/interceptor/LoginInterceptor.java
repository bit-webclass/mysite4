package com.javaex.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	UserService userService;
	
	@Override
	public boolean preHandle(
		HttpServletRequest request,
		HttpServletResponse response,
		Object handler/*HandlerMethod*/)
		throws Exception {
		
		String email = request.getParameter( "email" );
		String password = request.getParameter( "password" );
		
		// 데이터베이스에서 해당 UserVo 받아오기 
		UserVo userVo = userService.login(email, password);
		
		// 이메일과 패쓰워드가 일치하지 않는 경우
		if( userVo == null ) {
			response.sendRedirect( 
				request.getContextPath() + "/user/loginform?result=fail" );
			return false;
		}
		
		// 인증 처리
		HttpSession session = request.getSession( true );
		session.setAttribute( "authUser", userVo );
		response.sendRedirect( request.getContextPath()+"/main" );
		
		return false;
	}

}
