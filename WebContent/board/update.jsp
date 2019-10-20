<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board/update.jsp</title>
</head>
<body>
<div class="out" style="width: 95%; text-align: center; border: 1px solid black; padding: 20px; margin: 15px">
<div class="in" style="display: inline-block; padding: 20px; margin: 15px">
<c:set var="cp" value="${pageContext.request.contextPath }"/>
<div>
<h1>&nbsp;&nbsp;&nbsp; 글수정하기</h1><br>
<form method="post" action="${cp }/board/update">
	글번호 <input type="text" name="write_num" value="${vo.write_num}" readonly="readonly">
	<br><br>
	조회수 <input type="text" name="views" value="${vo.views }" readonly="readonly">
	<br><br>
	작성자
	<input type="text" name="id" value="${vo.id}" readonly="readonly"><br><br>
	제목 &nbsp;
	<input type="text" name="p_title" value="${vo.p_title}"><br><br>
	
	<div>&nbsp;&nbsp;
	<div style="float: left">내용<br></div>
	<textarea rows="5" cols="60" name="contents" >${vo.contents}</textarea><Br>
	<input type="submit" value="등록"></div>
</form>
</div>
</div>
</div>
</body>
</html>