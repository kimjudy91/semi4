<%@page import="loginIds.dao.joo.LoginIdsDao"%>
<%@page import="loginIds.vo.joo.LoginIdVO"%>
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
<script type="text/javascript">


</script>
<link rel="stylesheet" type="text/css" href="${cp }/index/indexxx.css">
<style>
</style>
</head>
<body>
<div id="main">
<div id="header">
<a href="${cp }/index" style="color: white; text-decoration: none"><h1>Music'sss Guest</h1></a>
<c:if test="${sessionScope.grade==0}">
	<c:redirect url="/nouse"/>
</c:if>
<c:if test="${warning>0 }">
<c:set var="warning" value="${warning }"/>
<c:set var="sessionScope.id" value="${sessionScope.id }"/>
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
					<input type="button" value="신고[${report2Count }]" onclick="location.href='${cp}/reportList'" >
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
			<li id="nus1">음악게시판<br></li>
			<c:choose>
				<c:when test="${sessionScope.id!=null }">
					<c:choose>
						<c:when test="${sessionScope.grade<2  }">
							<li><br><a href="javascript:gradepl()">Music<br></a></li>
						</c:when>
						<c:otherwise>
						<li><br><a href="${cp }/fileboard/community">Music<br></a></li>	
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<li><br><a href="javascript:loginpl()">Music<br></a></li>
				</c:otherwise>
			</c:choose>
			
			<li><br></li>
			<li id="nus1">문의게시판<br></li>
			<li><br><a href="${cp }/nquire/list">문의하기<br></a></li>
			<li><br></li>
		</ul>
		<div id="users">		
		접속자명단<br>
		<hr id="uhr" >
		<div id="uss" style="overflow:scroll;">
		<%
		ArrayList<LoginIdVO> userIds=LoginIdsDao.getDao().list();
		for(int i=0;i<userIds.size();i++){
			if(!(userIds.get(i).getIds()).equals("admin")){
			%>
			<a href="javascript:showsel('<%=userIds.get(i)%>','<%=i%>')"><%=userIds.get(i).getIds() %></a>
			<select id="sss<%=i %>"  style="display: none;width:100%;" size="2" onchange="showMsg2('<%=userIds.get(i).getIds()%>',this.value)" onblur="hidesel('<%=userIds.get(i)%>','<%=i%>')">
				<option value="1">친구추가</option>
				<option value="2">글보기</option>	
			</select>
			<br>
			<%
			}
			}
		%>
		</div>
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
window.onbeforeunload = function() {
	
	var dxhr=null;
	function deleteIds(){
		dxhr=new XMLHttpRequest();
		dxhr.onreadystatechange=suc;
		dxhr.open("get",'${cp}/index/deleteIds.jsp?id=${sessionScope.id}',true);
		dxhr.send();
	}
	function suc(){
		if(dxhr.readyState==4 && dxhr.status==200){
		var data=xhr.responseText;
		var json=JSON.parse(data);
		deleteIds();
		}
		
	}
}


	function loginpl(){
		if (confirm("로그인후 이용하실수 있습니다.") == true) { 
			location.href="${cp}/logins";
			} else { 
				return;
			}
	}
	function gradepl(){
		alert("등급이 실버 이상만 들어갈 수 있습니다.");
	}
	function showMsg(cp,id){
		window.open(cp,"메세지",'width=660, height=620, toolbar=yes, menubar=yes, scrollbars=no, resizable=yes');
	}
	function showsel(e,n){
		var sel=document.getElementById("sss"+n);
		sel.style.display="inline";
		sel.focus();
	}function hidesel(e,n){
		var sel=document.getElementById("sss"+n);
		sel.style.display="none";
	}
	function showMsg2(id,s){
		
		if(s==1){
			if (confirm(id+"님을 친구추가 하시겠습니까?") == true) { 
				window.open("/semi/insertFri?sid='${sessionScope.id}'&rid="+id,"메세지",'width=660, height=620, toolbar=yes, menubar=yes, scrollbars=no, resizable=yes');
			}
				 else { 
					return;
				}
		}else if(s==2){
			location.href="/semi/board/community?field=id&keyword="+id;
		}
	}
</script>
</body>
</html>