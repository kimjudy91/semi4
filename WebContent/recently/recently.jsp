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
	<c:choose>
				<c:when test="${sessionScope.id!=null }">
					<c:choose>
						<c:when test="${sessionScope.grade<2  }">
							<td><a href="javascript:gradepl()">${vo.write_num }</a></td>
						</c:when>
						<c:otherwise>
						<td><a href="${cp }/fileboard/detail?write_num=${vo.write_num}">${vo.write_num }</a></td>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
						<td><a href="javascript:loginpl();">${vo.write_num }</a></td>
				</c:otherwise>
			</c:choose>
		<td>${vo.p_title }</td><td>${vo.id }</td><td>${vo.r_date }</td>
	</tr>
</c:forEach>
</table>

</body>
</html>