<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>report/report.jsp</title>
</head>
<body>
<h1>신고하기</h1>
<form action="${cp }/report2" method="post">
	<input type="hidden" value=${vo.write_num } name="write_num">
	신고할 아이디<br><input type="text" value="${vo.id }" name="reportId" readonly="readonly"><br>
	신고할 내용<br><textarea rows="5" cols="40" name="report_content"></textarea>
	<input type="submit" value="신고하기"><input type="button" value="취소" onclick="location.href='${cp}/board/community'">
</form>
</body>
</html>