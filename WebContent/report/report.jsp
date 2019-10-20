<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>report/report.jsp</title>
</head>
<body>
<div class="out" style="width: 95%; text-align: center; border: 1px solid black; padding: 20px; margin: 15px">
<div class="in" style="display: inline-block; padding: 20px; margin: 15px">
<h1>신고하기</h1>
<form action="${cp }/report2" method="post">
	
	<input type="hidden" value=${vo.write_num } name="write_num">
	<br>
	
	<div style="float: left">신고할 아이디</div>
	<input type="text" value="${vo.id }" name="reportId" readonly="readonly" style="width: 300px;">
	<br><br>
	
	
	<div style="float: left">신고할 내용</div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<textarea rows="5" cols="40" name="report_content"></textarea>
	<input type="submit" value="신고하기">
	<input type="button" value="취소" onclick="location.href='${cp}/board/community'"><br>
	
</form>
</div>
</div>
</body>
</html>