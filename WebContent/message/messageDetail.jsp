<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
	window.onload=function(){
		getMsg('${rid}');
	}
	var ixhr=null;
	function sendMsg(rid){
		ixhr=new XMLHttpRequest();
		ixhr.onreadystatechange=success;
		var contents=document.getElementById("contents");
		ixhr.open('get','messageinsert.jsp?contents='+contents.value+'&rid='+rid,true);
		ixhr.send();
	}
	function success(){
		if(ixhr.readyState==4 && ixhr.status==200){
			var contents=document.getElementById("contents");
			contents.value="";
			deleteMsg();
			}
		}
	
	var xhr=null;
	function getMsg(rid){
		xhr=new XMLHttpRequest();
		xhr.onreadystatechange=successs;
		var contents=document.getElementById("contents");
		xhr.open('get','message.jsp?contents='+contents.value+'&rid='+rid,true);
		xhr.send();
	}
	function successs(){
		if(xhr.readyState==4 && xhr.status==200){
			var data=xhr.responseText;
			var msgs=JSON.parse(data)[0];
			var msgList=document.getElementById("msgList");
			for(var i=0;i<msgs.length;i++){
				var div=document.createElement("div");
				div.innerHTML=msgs[i].contents;
				div.style.border="1px solid blue";
				msgList.appendChild(div);
			}
		}
	}
	function deleteMsg(){
		var msgList=document.getElementById("msgList");
		var msglc=msgList.childNodes;
		for (var i = msglc.length-1; i >=0; i--) {
			var msg=msglc.item(i);
			msgList.removeChild(msg);
		}
		getMsg('${rid}');
	}
</script>
<div id="msgList">


</div>

<input type="text" width="30" id="contents"><input type="button" value="보내기" onclick="sendMsg('${rid}')">
</body>
</html>