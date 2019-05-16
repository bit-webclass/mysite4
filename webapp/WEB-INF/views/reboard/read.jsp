<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
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
				<div id="board" class="board-form">
					<h2>계층형 게시판-보기</h2>
					
					<table class="tbl-ex">
						<tr>
							<th colspan="2">글보기</th>
						</tr>
						<tr>
							<td class="label">제목</td>
							<td>${reboardVo.title }</td>
						</tr>
						<tr>
							<td class="label">내용</td>
							<td>
								<div class="view-content">
									<span style="white-space: pre-line;">${reboardVo.content }</span>
								</div>
							</td>
						</tr>
					</table>
					<div class="bottom">
						<a href="${pageContext.request.contextPath }/reboard/list?crtPage=${param.crtPage}&kwd=${param.kwd}">글목록</a>
						
						<c:if test="${sessionScope.authUser.no == reboardVo.userNo }">	
							<a href="${pageContext.request.contextPath }/reboard/modifyform?no=${reboardVo.no }&crtPage=${param.crtPage}&kwd=${param.kwd}">글수정</a>
						</c:if>
						
						<c:if test="${sessionScope.authUser ne null }">		
							<a href="${pageContext.request.contextPath }/reboard/replyform/${reboardVo.no }?crtPage=${param.crtPage }&kwd=${param.kwd}">댓글쓰기</a>
						</c:if>
						
					</div>
					
				</div><!-- /board -->
			</div><!-- /c_box -->
		</div><!-- /content -->
			
			
		<!-- footer -->	
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		
	</div><!-- /container -->
</body>
</html>