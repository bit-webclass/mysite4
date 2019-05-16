<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath }/assets/css/main.css" rel="stylesheet" type="text/css">
	<title>Mysite</title>
</head>
<body>
	<div id="container">
		<!-- header -->
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
	
		<!-- navigation -->
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
		
		<div id="content">
			<div id="c_box">
				<div id="main">
					<h2>Welcome</h2>
					
					<img id="profile" src="${pageContext.request.contextPath }/assets/images/profile.png" width="150px">
					<p id="intro">
						안녕하세요.<br> 
						홍길동의 mysite에 방문하신 것을<br/> 환영합니다.
					</p>	
					
					<p>	
						이 사이트는 웹 프로그램밍 실습과제 예제 사이트입니다.<br>
						<br>
						메뉴는 사이트 소개, 방명록, 게시판이 있구요. JAVA 수업 + 데이터베이스 수업 + 웹프로그래밍 수업
						배운 거 있는거 없는 거 다 합쳐서 만들어 놓은 사이트 입니다.<br>
					</p>
					
					<div id="goGuestbook">
						<a  href="">방명록</a>에 글 남기기
					</div>
				</div><!-- /main -->
			</div><!-- /c_box -->
		</div><!-- /content -->
			
		<!-- footer -->	
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		
	</div><!-- /container -->
</body>
</html>