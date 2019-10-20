<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>message/friends.jsp</title>
<link rel="stylesheet" type="text/css" href="${cp }/message/friends.css">
</head>
<body>
<c:if test="${cmd !=null }">
<c:set var="cmd" value="${cmd }"/>
	<script type="text/javascript">
		function showcmd(){
			alert("${cmd}");
		}
		showcmd();
	</script>
</c:if>
<script type="text/javascript">
	function refri(){
		alert("success");
	}
	function sendfri(sid){
		var rid=document.getElementById("rid");
		if(rid.value==""){
			alert("추가할 아이디를 입력해 주세요");
			return;
		}
		if (confirm(rid.value+"님을 친구추가 하시겠습니까??") == true) { 
			location.href="/semi/insertFri?sid="+sid+"&rid="+rid.value;
			} else { 
				return;
			}
	}
</script>
<div id="f"><h1>친구목록</h1></div>
<div id="f1">
<input type="text" id="rid" width="40" ><input type="button" value="친구추가" onclick="sendfri('${sessionScope.id}')">
</div>

<div id="t1">
<table>
	<tr>
		<th>아이디</th><th>메세지</th>
	</tr>
	<c:forEach var="fri" items="${list }">
	<tr>
	<c:choose>
	<c:when test="${(fri.sid eq sessionScope.id ) and (fri.commit==1 )}">
		<td><a href="${cp }/messageList?rid=${fri.rid}">${fri.rid }</a></td><td><a href="${cp }/message/detail?rid=${fri.rid }">메세지</a></td>
	</c:when>
	<c:when test="${(fri.rid eq sessionScope.id ) and (fri.commit==1 )}">
		<td><a href="${cp }/messageList?rid=${fri.sid}">${fri.sid }</a></td><td><a href="${cp }/message/detail?rid=${fri.sid }">메세지</a></td>
	</c:when>
	</c:choose>	
	</tr>
	</c:forEach>
</table>
</div>

<h2>보낸목록</h2>
<div id="t2">
<table>
	<tr>
		<th>아이디</th><th>수락여부</th>
	</tr>
	<c:forEach var="fri" items="${list }">
	<tr>
	<c:if test="${fri.sid eq sessionScope.id  and fri.commit==0 }">
		<td>${fri.rid }</td><td>X</td>
	</c:if>	
	</tr>
	</c:forEach>
</table>
</div>

<h2>받은목록</h2>
<div id="t3">
<table>
	<tr>
		<th>아이디</th><th>수락</th>
	</tr>
	<c:forEach var="fri" items="${list }">
	<tr>
	<c:if test="${fri.rid eq sessionScope.id and fri.commit==0}">
		<td>${fri.sid }</td><td><a href="${cp }/acceptfri?sid=${fri.sid}&rid=${fri.rid}">수락</a></td>
	</c:if>
	</tr>
	</c:forEach>
</table>
</div>
</body>
</html>