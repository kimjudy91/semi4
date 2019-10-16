<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/board/detail.jsp</title>
</head>
<body>
<c:set var="cp" value="${pageContext.request.contextPath }"/>
<h1>상세글보기</h1>
<script type="text/javascript">
	window.onload=function(){
		getList();
	};
	var xhrList=null;

	function getList() {
		xhrList = new XMLHttpRequest();
		xhrList.onreadystatechange = listOk;
		xhrList.open('get', 'board/comments?write_num=${vo.write_num}&cmd=list', true);
		xhrList.send();
	}

	function listOk() {
		if (xhrList.readyState == 4 && xhrList.status == 200) {
			removeComm();
			var data = xhrList.responseText;
			var list = JSON.parse(data)[0];
			var commList = document.getElementById("commList");
			for (var i = 0; i < list.length; i++) {
				var str = list[i].num + "<br>" + list[i].id + "<br>"
						+ list[i].comments + "<br>"
						+ "<a href='javascript:delComm(" + list[i].num
						+ ")'>삭제</a>";
				var div = document.createElement("div");
				div.innerHTML = str;
				div.className = "comm";
				commList.appendChild(div);
			}
		}
	}

	function showComm() {
		var comm = document.getElementById("comm");
		comm.style.display = "inline";

	}
</script>
<div style="text-align: center;">

<table border="1" width="600">
	<tr>
		<th>글번호</th>
		<td>${vo.write_num }</td>
		<th>조회수</th>
		<td>${vo.views }</td>
	</tr>
	<tr>
		<th>아이디</th>
		<td>${vo.id }</td>
	</tr>
		<tr>
		<th>장르</th>
		<c:choose>
			<c:when test="${vo.genre_num==1 }">
				<td>가요</td>
			</c:when>
			<c:when test="${vo.genre_num==2 }">
				<td>R&B</td>
			</c:when>
			<c:when test="${vo.genre_num==3 }">
				<td>PoP</td>
			</c:when>
		</c:choose>
	</tr>
	<tr>
		<th>제목</th>
		<td>${vo.p_title }</td>
	</tr>
	<tr>
		<th>글 내용</th>
		<td>${vo.contents }</td>
	</tr>
	
	<tr>
		<td colspan="4" class="text-center">
			<input type="button" class="btn-update" value="답글쓰기" onclick="showComm()">
			<input type="submit" class="btn-modify" value="수정하기" onclick="location.href='${cp}/board/update?write_num=${vo.write_num}'">
			<input type="submit" class="btn-delete" value="삭제하기" onclick="location.href='${cp}/board/delete?write_num=${vo.write_num}'">
			<input type="button" class="btn-report" value="신고하기" onclick="location.href='${cp}/report2?write_num=${vo.write_num}'">
			<input type="submit" class="btn-list" value="목록보기" onclick="location.href='${cp}/board/community'">
		</td>
	</tr>	
</table>

<div>
<form action="${cp }/board/comments" method="post" id="comm" style="display: none;" >
<input type="hidden" value="${sessionScope.id }" name="id">
<input type="hidden" value="insert" name="cmd">
<input type="hidden" value="${vo.write_num }" name="write_num">
내용<br><textarea rows="10" cols="30" name="comments_contents"></textarea>
<input type="submit" value="저장">
</form>
</div>
</div>
</body>
</html>
















