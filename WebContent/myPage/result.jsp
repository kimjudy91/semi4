<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/myPage/result.jsp</title>
</head>
<body>
<c:choose>
	<c:when test="${code=='success' }">
		<h1>회원정보 수정성공</h1>
		<a href="${cp }/index">메인으로 가기</a>
	</c:when>
	<c:otherwise>
		<h1>정보수정에 실패하였습니다.</h1>
		<a href="${cp }/index">메인으로 가기</a>
	</c:otherwise>
</c:choose>


</body>
</html>