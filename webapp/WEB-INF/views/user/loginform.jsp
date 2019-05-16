<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
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
				<div id="user">
					<h2>로그인</h2>

					<form class="form-box" method="post" action="${pageContext.request.contextPath }/user/login">

						<div class="form-group">
							<label class="block-label" for="email">이메일</label> 
							<input id="email" name="email" type="text" value="">
						</div>

						<div class="form-group">
							<label class="block-label">패스워드</label> 
							<input name="password" type="password" value="">
						</div>
						<c:if test="${param.result eq 'fail' }">
							<P>
								로그인이 실패했습니다.<br>다시입력해주세요
							</P>
						</c:if>
						
						<input type="submit" value="로그인">
					</form>

				</div>
				<!-- /user -->
			</div>
			<!-- /c_box -->
		</div>
		<!-- /content -->


		<!-- footer -->	
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		
	</div>
	<!-- /container -->
</body>
</html>

