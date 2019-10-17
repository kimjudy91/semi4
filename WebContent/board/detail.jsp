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
	function showComm(){
		var a=document.getElementsByClassName("a");
		var showComm=document.getElementById("showComm");
		showComm.style.display="none";
		var hideComm=document.getElementById("hideComm");
		hideComm.style.display="inline";
		for (var i = 0; i < a.length; i++) {
			a[i].style.display="block";
		}
	}
	function hideComm(){
		var a=document.getElementsByClassName("a");
		var showComm=document.getElementById("showComm");
		showComm.style.display="inline";
		var hideComm=document.getElementById("hideComm");
		hideComm.style.display="none";
		for (var i = 0; i < a.length; i++) {
			a[i].style.display="none";
		}
	}
</script>
<div>

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
			<input type="button" class="btn-modify" value="수정하기" onclick="location.href='${cp}/board/update?write_num=${vo.write_num}'">
			<input type="button" class="btn-delete" value="삭제하기" onclick="location.href='${cp}/board/delete?write_num=${vo.write_num}'">
			<input type="button" class="btn-report" value="신고하기" onclick="location.href='${cp}/report2?write_num=${vo.write_num}'">
			<input type="button" class="btn-list" value="목록보기" onclick="location.href='${cp}/board/community'">
		</td>
	</tr>	
</table>
<div id="commList">
	<c:forEach var="comLi" items="${commList }">
			<div style="border:1px solid red;" id="c${comLi.comments_num }">
				${comLi.comments_contents }
				<input type="button" value="댓글" onclick="showComm('${comLi.write_num }','${comLi.comments_num}')" id="b1${comLi.comments_num }">
				<input type="button" value="댓글숨기기" onclick="hideComm('${comLi.write_num }','${comLi.comments_num}')" id="b2${comLi.comments_num }" style="display: none">
			</div>
	</c:forEach>
</div>
<div>
<form action="${cp }/board/comments" method="post"  >
<input type="hidden" value="${sessionScope.id }" name="id">
<input type="hidden" value="insert" name="cmd">
<input type="hidden" value="${vo.write_num }" name="write_num">
댓글달기<br><textarea rows="10" cols="30" name="comments_contents"></textarea>
<input type="submit" value="저장">
</form>
</div>
</div>
</body>
<script type="text/javascript">
	var xhr=null;
	function showComm(w,c){
		xhr=new XMLHttpRequest();
		xhr.onreadystatechange=success;
		xhr.open('get','comments.jsp?ref='+c+'&write_num='+w,true);
		xhr.send();
	}
	function success(){
		if(xhr.readyState==4 && xhr.status==200){
			var data=xhr.responseText;
			var comm=JSON.parse(data)[0];
			var com=document.getElementById('c'+comm[0].ref);
			var b1=document.getElementById('b1'+comm[0].ref);
			b1.style.display="none";
			var b2=document.getElementById('b2'+comm[0].ref);
			b2.style.display="inline";
			for(var i=0;i<comm.length;i++){
				var div=document.createElement("div");
				div.innerHTML=comm[i].comments_contents;
				div.style.marginLeft=50*comm[i].lev+"px";
				div.style.border="1px solid blue";
				div.className="cl"+comm[0].ref;
				com.appendChild(div);
			}
		}
	}
	function hideComm(w,c){
		var divs=document.getElementsByClassName("cl"+c);
		var b1=document.getElementById('b1'+c);
		b1.style.display="inline";
		var b2=document.getElementById('b2'+c);
		b2.style.display="none";
		var com=document.getElementById('c'+c);
		var n=divs.length;
		for (var i = 0; i <n ; i++) {
			com.removeChild(com.lastChild);
		}
	}
</script>
</html>
















