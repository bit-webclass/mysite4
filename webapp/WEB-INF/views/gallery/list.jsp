<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath }/assets/css/gallery.css" rel="stylesheet" type="text/css">
		
	<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.min.js"></script>
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
				<div id="gallery">
					<h2>갤러리</h2>
					
					<c:if test="${sessionScope.authUser ne null }">					
						<input id="btnImgPop" class="btn btn-primary" type="button" value="이미지등록">
					</c:if>	
					
					<ul>
						<c:forEach items="${galleryList}" var="galleryVo" >
							<li>
								<div class="view" data-no="${galleryVo.no}">
									<img src ="${pageContext.request.contextPath }/upload/${galleryVo.saveName}">
								</div>
							</li>
						</c:forEach>
					</ul>
				</div><!-- /gallery -->
			</div><!-- /c_box -->
		</div><!-- /content -->
			
		<!-- footer -->	
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		
	</div><!-- /container -->
	
	
	
	
	<!-- 이미지등록 팝업(모달)창 -->
	<div class="modal fade" id="delPop">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">이미지등록</h4>
				</div>
				<form method="post" action="${pageContext.request.contextPath }/ga/upload" enctype="multipart/form-data">
					<div class="modal-body">
						<div class="formgroup">
							<label>코멘트작성</label><br>
							<input type="text" name="comments" id="comment"><br>	
						</div>
						<div class="formgroup">
							<label>이미지선택</label><br>	
							<input type="file" name="file" value="" id="file"> <br>	
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
						<button type="submit" class="btn btn-primary" id="btnImgAdd">등록</button>
					</div>
				</form>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	
	
	<!-- 이미지보기 팝업(모달)창 -->
	<div class="modal fade" id="viewPop">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">이미지보기</h4>
				</div>
				<div class="modal-body">
					
					<div class="formgroup" >
						<img id="imgArea" src ="${pageContext.request.contextPath }/assets/sample/image-4.jpg" width="500px">
					</div>
					
					<div class="formgroup">
						<label>코멘트</label><br>
						<p id="comments"></p>
					</div>
					
					
				</div>
				<form method="post" action="${pageContext.request.contextPath }/ga/remove">
					<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
					<button type="submit" class="btn btn-danger" id="btnDel">삭제</button>
				</div>
				<input type="text" name="no" value="" id="delNo">
				</form>
				
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	
</body>

<script type="text/javascript">

/* 이미지등록 팝업(모달)창 띄우기*/ 
$("#btnImgPop").on("click", function() {
    $("#delPop").modal();
    
});

/* 이미지보기 팝업(모달)창 띄우기*/ 
$(".view").on("click", function() {
	console.log("aaa");
	var no = $(this).data("no");
	$("#delNo").val(no);
	
	
	$.ajax({
		url : "${pageContext.request.contextPath }/ga/view",		
		type : "post",
		/* contentType : "application/json", */
		data : {no: no},
		dataType : "json",
		success : function(galleryVo) {
			console.log(galleryVo);
			var imgurl = "${pageContext.request.contextPath }/upload/"+galleryVo.saveName;
			
			//이미지 출력
			$("#imgArea").attr("src", imgurl);
			
			//코멘트 출력
			$("#comments").html(galleryVo.comments);
			
			//버튼 출력
			if("${sessionScope.authUser.no}" == galleryVo.user_no){
				$("#btnDel").show();
			}else {
				$("#btnDel").hide();
			}
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	}); 
          
	$("#viewPop").modal();
});



</script>







</html>