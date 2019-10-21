<%@page import="java.util.ArrayList"%>
<%@page import="sessionListener.joo.SessionIdListener"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>tset10_css.html</title>
<c:set var="cp" value="${pageContext.request.contextPath }"/>
<link rel="stylesheet" type="text/css" href="${cp }/index/indexx.css">
<style>
</style>
</head>
<body>
<div id="main">
<div id="header">
<a href="${cp }/index" style="color: white; text-decoration: none"><h1>Music'sss Guest</h1></a>
<c:if test="${grade==0}">
	<c:redirect url="/nouse"/>
</c:if>
<c:if test="${warning>0 }">
<c:set var="warning" value="${warning }"/>
	<script type="text/javascript">
		function showWarning(){
			alert("경고"+${warning}+"번을 받았습니다. 조심하세요");
		}
		showWarning();
	</script>
</c:if>
</div>
	<div id="menu">
		<div id="logins">
		<c:choose>
			<c:when test="${sessionScope.id==null }">
				<input type="button" value="로그인" onclick="location.href='${cp}/logins'">
				<input type="button" value="회원가입" onclick="location.href='${cp}/join/insert'">
			</c:when>
			<c:when test="${sessionScope.id eq 'admin' }">
				<c:choose>
				<c:when test="${report2Count==-1||report2Count==0||report2Count==null }">
					<input type="button" value="신고" onclick="location.href='${cp}/reportList'">
				</c:when>
				<c:otherwise>
					<input type="button" value="신고[${report2Count }]" onclick="location.href='${cp}/reportList'" size="10"><br>
				</c:otherwise>
				</c:choose>
				<input type="button" value="광고" onclick="location.href='${cp}/ad/list'">
				<input type="button" value="회원관리" onclick="location.href='${cp}/members_management'">
				<input type="button" value="로그아웃" onclick="location.href='${cp}/logout'">
			</c:when>
			<c:when test="${sessionScope.id!=null }">
				<c:choose>
				<c:when test="${newrf==-1||newrf==0||newrf==null }">
					<input type="button" value="친구" onclick="showMsg('${cp }/friends','${sessionScope.id}')">
				</c:when>
				<c:otherwise>
					<input type="button" value="친구[${newrf }]" onclick="showMsg('${cp }/friends','${sessionScope.id}')">
				</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${countMsgs>0 }">
						<input type="button" value="메세지[${countMsgs }]" onclick="showMsg('${cp }/messageList','${sessionScope.id}')">
					</c:when>
					<c:otherwise>
						<input type="button" value="메세지" onclick="showMsg('${cp }/messageList','${sessionScope.id}')">
					</c:otherwise>
				</c:choose>
				<input type="button" value="마이페이지" onclick="location.href='${cp}/myPage'">
				<input type="button" value="로그아웃" onclick="location.href='${cp}/logout'">
			</c:when>
		</c:choose>
		</div>
		<ul id="menus">
			<li id="nus1">커뮤니티게시판<br></li>
			<li><br><a href="${cp }/board/community">Community</a></li>
			<li><br></li>
			<li id="nus1">문의게시판<br></li>
			<li><br><a href="${cp }/nquire/list">문의하기<br></a></li>
			<li><br></li>
			<li id="nus1">음악게시판<br></li>
			<li><br><a href="">Rock<br></a></li>
			<li><br><a href="">folk<br></a></li>
			<li><br><a href="">R&B<br></a></li>
			<li><br></li>
			<li id="nus1">기타<br></li>
			<li><br><a href="">문의하기<br></a></li>
		</ul>
		<div id="users">		
		접속자명단<br>
		<hr id="uhr">
		<%
		ArrayList<String> userIds=SessionIdListener.getUserId();
		for(String a:userIds){
			%>
			<%=a %><br>
			<%
		}
		%>
		</div>
	</div>
	<div id="home">
			<c:import url="${page }"/>
	</div>	
</div>
<div id="footer">
풋터
</div>
<script type="text/javascript">
	function showMsg(cp,id){
		window.open(cp,"메세지",'width=660, height=620, toolbar=yes, menubar=yes, scrollbars=no, resizable=yes');
	}
	
</script>
</body>
</html>