<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/likes/likes.jsp</title>
</head>
<body>

<h1 style="font-size: 1px;">좋아요TOP10</h1>


<table border="1" width="300" style="font-size: 1px;">
	<tr>
		<th>글번호</th><th>제목</th><th>아이디</th><th>좋아요</th>

	</tr>	
<c:forEach var="vo" items="${likeslist }">
	<tr>
		<td>${vo.write_num }</td><td>${vo.p_title }</td><td>${vo.id }</td><td>${vo.likes }</td>

	</tr>
</c:forEach>
</table>

</body>
</html>