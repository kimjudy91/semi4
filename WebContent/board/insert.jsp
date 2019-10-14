<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- html에 삽입될꺼니까 다 없앰 -->
<div>
<h1>글등록하기</h1>
<form method="post" action="${cp }/board/insert">
	작성자<br>
	<input type="text" name="id"><br>
	제목<br>
	<input type="text" name="p_title"><br>
	내용<br>
	<textarea rows="5" cols="60" name="contents"></textarea><Br>
	<input type="submit" value="등록">	
</form>
</div>