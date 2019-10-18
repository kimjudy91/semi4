<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:forEach var="list" items="${list }">
	<c:choose>
		<c:when test="${list.sid eq sessionScope.id }">
			${list.contents }<br>
		</c:when>
		<c:otherwise>
			<c:forEach var="i" begin="1" end="20">
				&nbsp;
			</c:forEach>
			${list.contents }<br>
		</c:otherwise>
	</c:choose>
</c:forEach>
<input type="text" width="30" name="contents"><input type="button" value="보내기">
</body>
</html>