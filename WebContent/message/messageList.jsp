<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>message/message.jsp</title>
<style type="text/css">
	.chat{height:50px;width:600px; background: #757272; margin-top: 20px; text-decoration: none; font-size: 30px; color: #e4e4e4; }
</style>
</head>
<body>
<h1>채팅</h1>
<div  style="height: 600px;width:600px;overflow: auto;" >
<c:forEach var="list" items="${newMsg }">
	<c:choose>
		<c:when test="${list.count>0 }">
			<div class="chat" onclick="location.href='${cp }/message/detail?rid=${list.id }'">
				${list.id }[${list.count }]
			</div>
		</c:when>
		<c:otherwise>
			<div class="chat" onclick="location.href='${cp }/message/detail?rid=${list.id }'">
			${list.id }
			</div>
		</c:otherwise>
	</c:choose>
</c:forEach>
</div>
</body>
</html>