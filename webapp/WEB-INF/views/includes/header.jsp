<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


    	<div id="header">
			<h1>MySite</h1>
			<ul>
				<c:choose>
					<c:when test="${empty authUser }">
						<!-- 로그인 전 -->
						<li><a href="${pageContext.request.contextPath }/user/loginform">로그인</a></li>
						<li><a href="${pageContext.request.contextPath }/user/joinform">회원가입</a></li>
					</c:when>
					<c:otherwise>
						<!-- 로그인 후 -->
						<li><a href="${pageContext.request.contextPath }/user/modifyform">회원정보수정</a></li>
						<li><a href="${pageContext.request.contextPath }/user/logout">로그아웃</a></li> 
						<li>${authUser.name}님 안녕하세요^^;</li>
					</c:otherwise>
				</c:choose>
							
			</ul>
		</div><!-- /header -->