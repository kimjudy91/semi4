<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>myPage.jsp</title>
</head>
<body>
<h1>마이페이지</h1>
이메일
전화번호
주소
<c:choose>
	<c:when test="">
	<img  src="../images/bronze.png">
	</c:when>
	<c:when test="">
	<img  src="../images/silver.png">
	</c:when>
	<c:when test="">
	<img  src="../images/gold.png">
	</c:when>
</c:choose>
</body>
</html>