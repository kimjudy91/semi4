<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!-- html에 삽입될꺼니까 다 없앰 -->

<div class="out" style="width: 95%; text-align: center; border: 1px solid black; padding: 20px; margin: 15px">
<div class="in" style="display: inline-block; padding: 20px; margin: 15px">
<h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;글등록하기</h1><br>

<form method="post" action="${cp }/board/insert">
	<div style="width: 500px; height: 100px">
	작성자&nbsp;
	<input type="text" name="id" value="${id }" readonly="readonly" >
	<br><br>
	
	제목&nbsp;&nbsp;&nbsp;
	<input type="text" name="p_title" >
	<br><br>
	
	장르&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<label><input type="radio" name="trans" value="rnb">알앤비</label>
	<label><input type="radio" name="trans" value="pop">팝</label>
	<label><input type="radio" name="trans" value="oldsong">가요</label>
	<br><br>

	<div style="float: left"> 내용</div>
	<textarea rows="5" cols="60" name="contents"></textarea><br>
	<input type="submit" value="등록">	
	</div>
	
</form>
</div>
</div>