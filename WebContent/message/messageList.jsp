<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>message/message.jsp</title>
</head>
<body>
<c:forEach var="list" items="${newMsg }">
	<c:choose>
		<c:when test="${list.count>0 }">
			<a href="${cp }/message/detail?rid=${list.id }">${list.id }[${list.count }]</a><br>
		</c:when>
		<c:otherwise>
			<a href="${cp }/message/detail?rid=${list.id }">${list.id }</a><br>
		</c:otherwise>
	</c:choose>
	
</c:forEach>
</body>
</html>