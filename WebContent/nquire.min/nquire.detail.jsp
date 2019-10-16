<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>nquire.detail.jsp</title>
</head>
<body>
<link rel="stylesheet" type="text/css" href="../css.min/detailStyle.css">
<h1>문의하기</h1>
<hr>
<c:set var="cp" value="${pageContext.request.contextPath }"/>
<input type="hidden" value="${vo.id }" name="id">
<table>
	<tr><th>아이디</th><td>${vo.id}</td></tr>
	<tr><th>문의유형</th><td>${vo.id}</td></tr>
	<tr><th>제목</th><td>${vo.id}</td></tr>
	<tr><th>내용</th><td><textarea cols='50' rows='15' name="contents">${vo.contents}</textarea></td></tr>
</table>
<form method="post" action="${cp }/nquire/comm">
<table>
	<c:if test="${vo.id!='admin'}"><tr><th><input type="submit" value="답글달기" style="visibility:hidden;"></th></c:if>
	<c:if test="${vo.id=='admin'}"><tr><th><input type="submit" value="답글달기" style="visibility:visible;"></th></tr></c:if>
	<c:choose>
		<c:when test="${vo.id=='admin'}">
			<tr><th>답변</th><td><textarea cols='50' rows='15' name="contents">${vo.contents}</textarea></td></tr>
		</c:when>
		<c:otherwise>
			<tr><th>답변</th><td><textarea cols='50' rows='15' name="contents">${vo.contents}</textarea></td></tr>
		</c:otherwise>
	</c:choose>
</table>
</form>
<a href="${cp }/nquire/list">목록으로 돌아가기</a>
</body>
</html>