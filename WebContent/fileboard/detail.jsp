<%@page import="members.vo.min.MembersVo"%>
<%@page import="members.dao.min.MembersDao"%>
<%@page import="board.filecontroller.dao.FileBoardCommentsDao"%>
<%@page import="board.filecontroller.dao.FileBoardDao"%>
<%@page import="board.filecontroller.vo.FileUpLoadVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	FileBoardCommentsDao bcdDao=FileBoardCommentsDao.getCommentsDao();
	MembersDao dao=MembersDao.getDao();
	String id="";
	int grade=0;
	int ref=0;
	int cnt=0;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/board/detail.jsp</title>
<style type="text/css">
	.comm{width: 100%; height: 100%; border: 1px solid #aaa; margin-bottom: 5px; margin-top: 5px;}
</style>
</head>
<script type="text/javascript">

</script>
<body>
<c:set var="cp" value="${pageContext.request.contextPath }"/>
<h1>상세글보기</h1>
<table border="1" width="600" style="text-align: center";>

	<tr>
		<th>글번호</th>
		<td>${vo.write_num }</td>
		<th>조회수</th>
		<td>${vo.views }</td>
			<th>좋아요</th>
		<td>${vo.likes }</td>
			
			<td>
		<form method="post" action="${cp}/fileboard/likes"  >
	<input type="hidden" value="${vo.likes }" name="likes">
	<input type="hidden" value="${vo.write_num }" name="write_num">
	<input type="submit" value="좋아요">
	</form>
		</td>
	</tr>
	<tr>
		<th>아이디</th>
		<td>${vo.id }</td>
	</tr>
	<tr>
		<th>제목</th>
		<td>${vo.p_title }</td>
	</tr>
	<tr>
		<th>글 내용</th>
		<td>${vo.contents }</td>
	</tr>
	
	<%
		String music=(String)request.getAttribute("music");
	%>
	
	<tr>
		<th>다운로드 파일</th>
		<td>
	<a href="${cp}/fileboard.download?f_num=${vo.f_num} ">다운로드</a>
		</td>
	</tr>
		<tr>
		<th>음악파일재생</th>
		<td>
		<form action="${cp }/fileboard/play?f_num=${vo.f_num}&write_num=${vo.write_num}" method="post"  >
		<input type="submit" value="실행">
		<audio src=<%=music %> controls="controls"  autoplay="autoplay" ></audio>
		</form>
		</td>
	</tr>


	<tr>
		<td colspan="4" class="text-center">
			<input type="submit" class="btn-modify" value="수정하기" onclick="location.href='${cp}/fileboard/update?write_num=${vo.write_num}'">
			<input type="submit" class="btn-delete" value="삭제하기" onclick="location.href='${cp}/fileboard/delete?write_num=${vo.write_num}&f_num=${vo.f_num}'">
			<input type="button" class="btn-report" value="신고하기" onclick="location.href='${cp}/report2?write_num=${vo.write_num}'">
			<input type="submit" class="btn-list" value="목록보기" onclick="location.href='${cp}/fileboard/community'">
		</td>
	</tr>
</table>



<div>
<form action="${cp }/fileboard/comments" method="post"  >
<input type="hidden" value="${sessionScope.id }" name="id">
<input type="hidden" value="insert" name="cmd">
<input type="hidden" value="${vo.write_num }" name="write_num">
			댓글내용<br><textarea rows="5" cols="50" name="comments_contents"></textarea>
<input type="submit" value="저장">
</form>
</div>
<div id="commList">
	<c:forEach var="comLi" items="${commList }">
			<div id="cw${comLi.comments_num }">
			<div style="border:1px solid red;" id="c${comLi.comments_num }" >
				<c:set var="id" value="${comLi.id }"/>
				<c:set var="ref" value="${comLi.comments_num }"/>
				<%
					 id=(String)pageContext.getAttribute("id");
					MembersVo memVo=dao.search(id);
					grade=memVo.getGrade();
					ref=(Integer)pageContext.getAttribute("ref");
					cnt=bcdDao.getCommCount(ref)-1;
					pageContext.setAttribute("grade",grade);
					pageContext.setAttribute("cnt",cnt);
				%>
				<c:choose>
					<c:when test="${grade==1 }">
						<img  src="${cp }/images/bronze.png">
					</c:when>
					<c:when test="${grade==2 }">
						<img  src="${cp }/images/silver.png">
					</c:when>
					<c:when test="${grade==3 }">
						<img  src="${cp }/images/gold.png">
					</c:when>
				</c:choose>
				${comLi.id }<br>
				
				${comLi.comments_contents }
				<a href="">삭제</a>
				<br>
				<c:choose>
					<c:when test="${cnt!=0 }">
						<input type="button" value="댓글보기(${cnt })" onclick="showSr(${comLi.comments_num}), showComm('${comLi.write_num }','${comLi.comments_num}') " id="b1${comLi.comments_num }">
						<input type="button" value="댓글보기(${cnt })" onclick="hideSr(${comLi.comments_num}), hideComm('${comLi.write_num }','${comLi.comments_num}')" id="b2${comLi.comments_num }" style="display: none">
					</c:when>
					<c:otherwise>	
						<input type="button" value="댓글보기" onclick="showSr(${comLi.comments_num}), showComm('${comLi.write_num }','${comLi.comments_num}') " id="b1${comLi.comments_num }">
						<input type="button" value="댓글보기" onclick="hideSr(${comLi.comments_num}), hideComm('${comLi.write_num }','${comLi.comments_num}')" id="b2${comLi.comments_num }" style="display: none">
					</c:otherwise>
				</c:choose>
				<br>
			</div>
						<form action="${cp }/fileboard/comments" method="post"  id="sr${comLi.comments_num }" style="display: none;">
					<input type="hidden" value="${sessionScope.id }" name="id">
					<input type="hidden" value="insertCom" name="cmd">
					<input type="hidden" value="${comLi.write_num }" name="write_num">
					<input type="hidden" value="${comLi.ref }" name="ref">
					<input type="hidden" value="${comLi.lev }" name="lev">
					<input type="hidden" value="${comLi.step }" name="step">
					댓글내용<br><textarea rows="5" cols="50" name="comments_contents"></textarea>
					<input type="submit" value="저장"><br>
			</form>
			</div>
	</c:forEach>
</div>


</body>
<script type="text/javascript">
	var xhr=null;
	function showComm(w,c){
		xhr=new XMLHttpRequest();
		xhr.onreadystatechange=success;
		xhr.open('get','comments.jsp?ref='+c+'&write_num='+w,true);
		xhr.send();
	}
	function success(){
		if(xhr.readyState==4 && xhr.status==200){
			var data=xhr.responseText;
			var comm=JSON.parse(data)[0];
			var com=document.getElementById('c'+comm[0].ref);
			var b1=document.getElementById('b1'+comm[0].ref);
			b1.style.display="none";
			var b2=document.getElementById('b2'+comm[0].ref);
			b2.style.display="inline";
			for(var i=0;i<comm.length;i++){
				var div=document.createElement("div");
				var id=comm[0].ref+""+ i;
				div.innerHTML=comm[i].id+"<br>"+comm[i].comments_contents;
				
				//여기 댓글 div css
				div.style.marginLeft=50*comm[i].lev+"px";
				div.style.border="1px solid blue";
				div.className="cl"+comm[i].ref;
				div.id=id;
				com.appendChild(div);
			}
		}
	}
	function hideComm(w,c){
		var divs=document.getElementsByClassName("cl"+c);
		var b1=document.getElementById('b1'+c);
		b1.style.display="inline";
		var b2=document.getElementById('b2'+c);
		b2.style.display="none";
		var com=document.getElementById('c'+c);
		var n=divs.length;
		for (var i = 0; i <n ; i++) {
			com.removeChild(com.lastChild);
		}
	}
	function showSr(c){
		var sr=document.getElementById("sr"+c);
		sr.style.display="inline";
	}
	function hideSr(c){
		var sr=document.getElementById("sr"+c);
		sr.style.display="none";
	}
	


</script>
</html>


