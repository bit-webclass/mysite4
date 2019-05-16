<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
	
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
				<div id="guestbook">
					<h2>방명록</h2>

					<div>
						<table>
							<tr>
								<td>이름</td>
								<td><input type="text" name="name"></td>
								<td>비밀번호</td>
								<td><input type="password" name="password"></td>
							</tr>
							<tr>
								<td colspan=4><textarea name="content" cols="75" rows="8"></textarea></td>
							</tr>
							<tr>
								<td colspan=4 align=right><input id="btnAdd" type="button" VALUE=" 확인 "></td>
							</tr>
						</table>
					</div>
					<br>
					
					<div id="guestbookList">
					
					
					</div>



					<%-- <c:forEach items="${list }" var="vo">
						<table width=510 border=1>
							<tr>
								<td>${vo.no }</td>
								<td>${vo.name }</td>
								<td>${vo.regDate }</td>
								<td><a href="${pageContext.request.contextPath }/gb/deleteform?no=${vo.no }">삭제</a></td>
							</tr>
							<tr>
								<td colspan=4>${vo.content }</td>
							</tr>
						</table>
						<br>
					</c:forEach> --%>
				</div>
				<!-- /guestbook -->
			</div>
			<!-- /c_box -->
		</div>
		<!-- /content -->

			
		<!-- footer -->	
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		
	</div><!-- /container -->


	<!-- 삭제팝업(모달)창 -->
	<div class="modal fade" id="delPop">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">방명록삭제</h4>
				</div>
				<div class="modal-body">
					<label>비밀번호</label>
					<input type="password" name="modalPassword" id="modalPassword"><br>	
					<input type="text" name="modalPassword" value="" id="modalNo"> <br>	
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
					<button type="button" class="btn btn-danger" id="btnDel">삭제</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->


</body>



<script type="text/javascript">

var lastNo = 0; //제일 하단 글번호-->하단에 글이 추가될때마다 업데이트됨

//페이지가 준비될때
$(document).ready(function() {
	//리스트 출력
	fetchList();
});	


//저장버튼을 클릭했을때
$("#btnAdd").on("click", function() {
	event.preventDefault();
	
	guestbookVo ={
  		name: $("input[name='name']").val(),
  		password: $("input[name='password']").val(),
  		content: $("textarea[name='content']").val()
  	}
	
	$.ajax({
		url : "${pageContext.request.contextPath }/api/gb/add",		
		type : "post",
		/* contentType : "application/json", */
		data : guestbookVo,
		dataType : "json",
		success : function(guestbookVo) {
			console.log(guestbookVo);
			render(guestbookVo, "up");
			$("input[name='name']").val("");
	  		$("input[name='password']").val("");
	  		$("textarea[name='content']").val("");
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	}); 
              
	
	
});


/* 삭제팝업창 띄우기*/ 
$("#guestbookList").on("click", ".btnDelModal", function() {
	var no = $(this).data("delno");
	
	$("#modalNo").val(no);
    $("#delPop").modal();
    
});



/* 삭제 */
$("#btnDel").on("click", function() {
	event.preventDefault();
	var no = $("#modalNo").val();
   	var password = $("#modalPassword").val();
   	
	
	$.ajax({
		url : "${pageContext.request.contextPath }/api/gb/delete",	
		type : "post",
		/* contentType : "application/json", */
		data :{no: no, password: password},
		dataType : "json",
		success : function(count) {
			if(count > 0){
				$("#div"+no).remove();
			}
			$("#delPop").modal("hide")
			$("#modalPassword").val("");
			$("#modalNo").val("");	
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
	
});


//스크롤이 화면 제일 아래에 왔을때
$(window).scroll(function() {
    if ($(window).scrollTop() == $(document).height() - $(window).height()) {
    	fetchList();
    }
});

	


/* 게시물을 10개 가져와 화면에 출력합니다. */
function fetchList() {

	$.ajax({
		url : "${pageContext.request.contextPath }/api/gb/list",
		type : "post",
		/* contentType : "application/json", */
		data : {lastNo: lastNo},
		dataType : "json",
		success : function(guestbookList) {
			console.log(guestbookList);
			for(var i=0; i<guestbookList.length; i++){
				render(guestbookList[i], "down");
			}
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});

}	


/* 게시물을 화면에 표현합니다. */
function render(guestbookVo, updown) {
	
	var str = "";
	str += "<div id='div"+guestbookVo.no+"'>";
	str += "	<table>";
	str += "		<tr>";
	str += "			<td>[" + guestbookVo.no + "]</td>";
	str += "			<td>" + guestbookVo.name + "</td>";
	str += "			<td>" + guestbookVo.regDate + "</td>";
	str += "			<td><input class='btnDelModal' type='button' value='삭제' data-delno='" + guestbookVo.no + "' ></td>";
	str += "		</tr>";
	str += "		<tr>";
	str += "			<td colspan=4>"+ guestbookVo.content.replace(/\n/gi,'<br>') + "</td>";
	str += "		</tr>";
	str += "	</table>";
	str += "	<br>";
	str += "</div>";
		

	if(updown == "up"){
		$("#guestbookList").prepend(str); 
	}else if(updown == "down"){
		$("#guestbookList").append(str); 
		lastNo = guestbookVo.no;  //마지막번호 업데이트
	}else{
		console.log("updown 오류")
	}
}	
	
	
</script>


</html>

