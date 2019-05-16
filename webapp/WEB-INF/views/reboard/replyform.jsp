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
					<h2>계층형 게시판-댓글등록</h2>
					
					<form class="board-form" method="post" action="${pageContext.request.contextPath }/reboard/write">
						<table class="tbl-ex">
							<tr>
								<th colspan="2">글쓰기</th>
							</tr>
							<tr>
								<td class="label">제목</td>
								<td><input type="text" name="title" value=""></td>
							</tr>
							<tr>
								<td class="label">내용</td>
								<td>
									<textarea id="content" name="content" ></textarea>
								</td>
							</tr>
						</table>
						<!-- group_no --><input type="text" name="groupNo" value="${reboardVo.groupNo}"> <br/>
						<!-- order_no --><input type="text" name="orderNo" value="${reboardVo.orderNo}"><br/>
						<!-- depth --><input type="text" name="depth" value="${reboardVo.depth}"><br>
						<!-- crtPage --><input type="text" name="crtPage" value="${param.crtPage }" >
						<!-- kwd --><input type="text" name="kwd" value="${param.kwd }" >
						<div class="bottom">
							<a href="${pageContext.request.contextPath }/reboard/list?&crtPage=${param.crtPage }&kwd=${param.kwd}">취소</a>
							<input type="submit" value="등록">
						</div>
					</form>	
					
				</div><!-- /board -->
			</div><!-- /c_box -->
		</div><!-- /content -->
			
			
			
		<!-- footer -->	
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		
	</div><!-- /container -->
</body>
</html>