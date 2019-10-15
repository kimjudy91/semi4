<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>report/reportlist.jsp</title>
</head>
<body>
<table border="1" width="500" >
	<tr>
		<th>신고한 아이디</th><th>답글달기</th><th>답여부</th>
	</tr>
	<c:forEach var="report" items="${listReport2 }">
	<tr>
			<td>${report.id }</td><td><a href="${cp }/report/comments?write_num=${report.write_num}&rnum=${report.rnum}">답글달기</a></td><c:if test="${report.comments!=null }"><td>V</td></c:if><c:if test="${report.comments==null }"><td>X</td></c:if>
	</tr>
	</c:forEach>
</table>
</body>
</html>