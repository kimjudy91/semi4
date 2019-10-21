<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>nquire.detail.jsp</title>
</head>
<script type="text/javascript">
	function showComm(){
		var comm=document.getElementById("comm");
		comm.style.display="block";
	}
</script>
<body>
<link rel="stylesheet" type="text/css" href="../css.min/detailStyle.css">
<div id="nd">
<h1>나의 문의글</h1>
<hr>
</div>

<c:set var="cp" value="${pageContext.request.contextPath }"/>
<input type="hidden" value="${vo.id }" name="id">
<table>
	<tr><th>아이디</th><td>${vo.id}</td></tr>
	<tr><th>제목</th><td>${vo.title}</td></tr>
	<tr><th>내용</th><td><textarea cols='50' rows='8' name="contents" readonly="readonly">${vo.contents}</textarea></td></tr>
</table>
<c:choose>
<c:when test="${vo.comments!=null }">
<table>
			<tr><th>답변</th><td><textarea cols='50' rows='8' name="comments" disabled="disabled">${vo.comments }</textarea></td></tr>
</table>
</c:when>
<c:otherwise>
<c:if test="${sessionScope.id eq 'admin' }">
	<input type="button" value="댓글달기" onclick="showComm()">
</c:if>
<form method="post" action="${cp }/nquire/comm" id="comm" style="display: none;">
<input type="hidden" value=${vo.nquire_num } name="nquire_num">
<table>
			<tr><th>답변</th><td><textarea cols='50' rows='8' name="comments" ></textarea></td></tr>
</table>
	<input type="submit" value="저장"><br>
</form>
</c:otherwise>
</c:choose>
<div id="nlist"><a href="${cp }/nquire/list">목록으로 돌아가기</a></div>
</body>
</html>