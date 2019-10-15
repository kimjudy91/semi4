<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>report/reportComments.jsp</title>
</head>
<body>
<form action="${cp }/report/comments" method="post">
<input type="hidden" value="${vo.rnum }" name="rnum">
신고자:${vo.id}<br>
피신고자:${bvo.id}<br>
신고내용:${vo.report_content}<br>
신고글:${bvo.contents}<br>
답글<br>
<textarea rows="5" cols="50" name="comments"></textarea>
<br>
<input type="submit" value="등록">
</form>
</body>
</html>