<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/board/detail.jsp</title>
</head>
<script type="text/javascript">
		windows.onload=function(){
			getList();
		};
		var xhrList=null;
		function getList(){
			xhrList=new XMLHttprequest();
			xhrList.onreadystatechange=listOk;
			xhrList.open('get','comments?write_num=${vo.write_num}&cmd=list',true);
			xhrList.send();
		}
		function listOk(){
			if(xhrList.readyState==4 && xhrList.status==200){
				var data=xhrList.responseText;
				var list=JSON.parse(data)[0];
				var commList=document.getElementById("commList");
				for(var i=0;i<list.length;i++){
					var str="아이디:" + list[i].id +"<br>" +
			        "내용:" + list[i].comments +"<br>" +
			        "<a href='javascript:delComm(" + list[i].num +")'>삭제</a>";
			var div=document.createElement("div");
			div.innerHTML=str;
			div.className="comm";
			commList.appendChild(div);
		}			
	}		
}
		
		var ixhr=null;
		function insertComm(){
			ixhr=new XMLHttpRequest();
			ixhr.onreadystatechange=insertOk;
			ixhr.open('post','comments?cmd=insert',true);
			ixhr.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
			var id=document.getElementById("id").value;
			var comments=document.getElementById("comments").value;
			var param="write_num=${vo.write_num}&id="+ id +"&comments_contents="+ comments_contents;
			ixhr.send(param);
		}
		function insertOk(){
			if(ixhr.readyState==4 && ixhr.status==200){
				var data=ixhr.responseText;
				var json=JSON.parse(data);
				if(json.code=='success'){
					getList();
				}else{
					alert("댓글등록실패!");
				}
			}
		}	

</script>
<body>
<c:set var="cp" value="${pageContext.request.contextPath }"/>
<h1>상세글보기</h1>

<div style="text-align: center;">

<table border="1" width="600">

	<tr>
		<th>글번호</th>
		<td>${vo.write_num }</td>
		<th>조회수</th>
		<td>${vo.views }</td>
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
	
	<tr>
		<td colspan="4" class="text-center">
			
			<input type="submit" class="btn-modify" value="수정하기" onclick="location.href='${cp}/board/update?write_num=${vo.write_num}'">
			<input type="submit" class="btn-delete" value="삭제하기" onclick="location.href='${cp}/board/delete?write_num=${vo.write_num}'">
		
			<input type="submit" class="btn-list" value="목록보기" onclick="location.href='${cp}/board/community'">
		</td>
	</tr>
	<tr>
		<td>
			<textarea rows="5" cols="60" id="comments" ></textarea><Br>
			<input type="submit" class="btn-comments" value="답글등록" onclick="insertComm()">
		</td>
	</tr>
</table>

</div>
</body>
</html>