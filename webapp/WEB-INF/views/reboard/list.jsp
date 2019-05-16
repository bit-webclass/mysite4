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
				<div id="board">
					<h2>계층형 게시판-리스트</h2>
					
					<form action="${pageContext.request.contextPath }/reboard/list" method="get">
						<input type="text" id="kwd" name="kwd" value="">
						<input type="submit" value="찾기">
					</form>
					
					<table class="tbl-ex">
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>글쓴이</th>
							<th>조회수</th>
							<th>작성일</th>
							<th>&nbsp;</th>
						</tr>				
						
						<c:forEach items="${pMap.reboardList }" var="boardVo" >
							<tr>
								<td>${boardVo.no}</td>
								<td style="text-align:left">
									<c:forEach begin="1" end="${boardVo.depth }">
										&nbsp;&nbsp;&nbsp;&nbsp;
									</c:forEach>
									<c:if test="${boardVo.depth > 0}">
										<img src="${pageContext.request.contextPath }/assets/images/reply.png">
									</c:if>
									<c:if test="${boardVo.state eq 'del'}">
										삭제된 게시물 입니다.
									</c:if>
									<c:if test="${boardVo.state ne 'del'}">
										<a href="${pageContext.request.contextPath }/reboard/read/${boardVo.no }?crtPage=${param.crtPage }&kwd=${param.kwd}">${boardVo.title }</a>
									</c:if>
								</td>
								<td>${boardVo.userName }${boardVo.groupNo}/${boardVo.orderNo}/${boardVo.depth}</td>
								<td>${boardVo.hit }</td>
								<td>${boardVo.regDate }</td>
									
								<c:if test="${sessionScope.authUser.no == boardVo.userNo }">	
									<td><a href="${pageContext.request.contextPath }/reboard/remove?no=${boardVo.no }&crtPage=${param.crtPage }&kwd=${param.kwd}" class="del">삭제</a></td>
								</c:if>
								<c:if test="${sessionScope.authUser.no != boardVo.userNo }">	
									<td></td>
								</c:if>
							</tr>
						</c:forEach>
					</table>
					<div class="pager">
						<ul>
							<c:if test="${pMap.prev == true}">
								<li><a href="${pageContext.request.contextPath }/reboard/list?crtPage=${pMap.startPageBtnNo-1 }&kwd=${param.kwd}">◀</a></li>
							</c:if>
							
							<c:forEach begin="${pMap.startPageBtnNo }" end="${pMap.endPageBtnNo }" step="1" var="page">
								<c:choose>
									<c:when test="${param.crtPage eq page }">
										<li class="selected">
											<a href="${pageContext.request.contextPath }/reboard/list?crtPage=${page }&kwd=${param.kwd}">${page }</a>
										</li>
									</c:when>
									<c:otherwise>
										<li>
											<a href="${pageContext.request.contextPath }/reboard/list?crtPage=${page }&kwd=${param.kwd}">${page }</a>
										</li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							
							<c:if test="${pMap.next == true}">
								<li><a href="${pageContext.request.contextPath }/reboard/list?crtPage=${pMap.endPageBtnNo+1 }&kwd=${param.kwd}">▶</a></li>
							</c:if>
							
						</ul>
					</div>		
					<c:if test="${sessionScope.authUser ne null }">					
						<div class="bottom">
							<a href="${pageContext.request.contextPath }/reboard/writeform?kwd=${param.kwd}" id="new-book">글쓰기</a>
						</div>
					</c:if>	
					
				</div><!-- /board -->
			</div><!-- /c_box -->
		</div><!-- /content -->
			
			
		<!-- footer -->	
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		
	</div><!-- /container -->
</body>
</html>