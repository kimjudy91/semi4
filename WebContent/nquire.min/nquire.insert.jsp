<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form method="post" action="${cp }/board/nquire">
<table border='1' width=500px>
	<tr><th>아이디</th><td>${vo.id}</td></tr>
	<tr><th>제목</th><td><input type="text" name="title"></td></tr>
	<tr><th>내용</th><td><textarea cols='50' rows='15' name="contents"></textarea></td></tr>
	<tr><th colspan="2"><input type="submit" value="저장">&nbsp&nbsp&nbsp
	<input type="reset" value="취소"></th></tr>
</table>
</form>