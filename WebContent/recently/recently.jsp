<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>recently/recently.jsp</title>
</head>
<body>
<body>

<h1 style="font-size: 1px;">최신글TOP5(업로드커뮤니티)</h1>


<table border="1" width="300" style="font-size: 1px;">
	<tr>
		<th>글번호</th><th>제목</th><th>아이디</th><th>작성일</th>

	</tr>	
<c:forEach var="vo" items="${recently }">
	<tr>
		<td>${vo.write_num }</td><td>${vo.p_title }</td><td>${vo.id }</td><td>${vo.r_date }</td>
	</tr>
</c:forEach>
</table>
</body>
</body>
</html>