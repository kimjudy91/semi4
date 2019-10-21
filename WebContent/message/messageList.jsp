<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>message/message.jsp</title>
<style type="text/css">
	.chat{height:50px;width:600px; background: #b1b1b1; margin-top: 20px; text-decoration: none; font-size: 30px; color: #e4e4e4;
	 }
</style>
</head>
<script type="text/javascript">
	function change1(e){
		var diva=e.target;
		diva.style.backgroundColor="#757272";
	}
	function change2(e){
		var diva=e.target;
		diva.style.backgroundColor="#b1b1b1";
	}
</script>
<body>
<h1>채팅</h1>
<div  style="height: 100%;width:600px;overflow: auto;" >
<c:forEach var="list" items="${newMsg }">
	<c:choose>
		<c:when test="${list.count>0 }">
			<div class="chat" onclick="location.href='${cp }/message/detail?rid=${list.id }'" onmouseover="change1(event)" onmouseout="change2(event)">
				${list.id }[${list.count }]
			</div>
		</c:when>
		<c:otherwise>
			<div class="chat" onclick="location.href='${cp }/message/detail?rid=${list.id }'" onmouseover="change1(event)" onmouseout="change2(event)">
			${list.id }
			</div>
		</c:otherwise>
	</c:choose>
</c:forEach>
</div>
</body>
</html>