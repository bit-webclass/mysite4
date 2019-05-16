package com.javaex.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.javaex.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception 	{
		System.out.println("++++++++++++++++++++++++++++++");
		System.out.println("AuthInterceptor");
		
		// 1. handler 종류 확인
		// 우리가 관심 있는 것은 Controller에 있는 메서드이므로 HandlerMethod 타입인지 체크
		if( handler instanceof HandlerMethod == false ) {
			System.out.println("controller 메소드 아님");
			// return true이면  Controller에 있는 메서드가 아니므로, 그대로 컨트롤러로 진행
			return true;
		}

		//2.@Auth 가 있는지?
		Auth auth = ( ( HandlerMethod ) handler ).getMethodAnnotation( Auth.class );
		
		if( auth == null ) { //없으면  접근제어를 판단할 필요없음
			System.out.println("@Auth없음");
		    return true;
		}
		
		//3.@Auth 가 있으면  session 있는지 체크
		HttpSession session = request.getSession();
		if( session == null ) { //없으면 로그인 폼
			System.out.println("@Auth있음 session없음");
			response.sendRedirect(request.getContextPath() + "/user/loginform" );
			return false;
		}

		//4.@Auth 가 있고 session 이 있고 session에 정보가 있는지?
		UserVo authUser = (UserVo)session.getAttribute( "authUser" );
		if( authUser == null ) { //없으면 로그인 폼으로
			System.out.println("@Auth있음 session있음 authUser없음");
			response.sendRedirect(request.getContextPath() + "/user/loginform" );
			return false;
		}

		//5.모든 조건을 만족한 사용자는 요청한 곳으로 보낸다.
		System.out.println("@Auth있음 session있음 authUser있음");
		return true;
	
	}
}
