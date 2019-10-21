<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>recently/recently.jsp</title>
<style type="text/css">
	table{
		text-align: center; 
		font-size: 20px;
		width: 500px;
		
	}
	#TCOMM{
		font-size: 13px;
		padding-bottom: 15px;
    	border-bottom: inherit;
	}


</style>
</head>
<body>
<div id="TCOMM">
<h1>최신글TOP5(업로드커뮤니티)</h1>
</div>

<table>
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
</html>