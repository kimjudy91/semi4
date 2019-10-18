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
${rid }
<script type="text/javascript">
	window.onload=function(){
		getMsg1();
	}
	var cnt1;
	var cnt2;
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
			}
		}
	
	var xhr=null;
	function getMsg(){
		xhr=new XMLHttpRequest();
		xhr.onreadystatechange=successs;
		var contents=document.getElementById("contents");
		xhr.open('get','message.jsp?contents='+contents.value+'&rid='+'${rid}',true);
		xhr.send();
	}
	function successs(){
		if(xhr.readyState==4 && xhr.status==200){
			var data=xhr.responseText;
			var msgs=JSON.parse(data)[0];
			var msgList=document.getElementById("msgList");
			cnt2=msgs.length;
			if(cnt1<cnt2){
				cnt1=cnt2;
				deleteMsg();
				for(var i=0;i<msgs.length;i++){
					var div=document.createElement("div");
					div.innerHTML=msgs[i].contents;
					if(msgs[i].rid=='${rid}'){
						div.style.border="1px solid blue";
					}else{
						div.style.border="1px solid red";
						div.style.textAlign="right";
					}
					msgList.appendChild(div);
					msgList.scrollTop = msgList.scrollHeight;
				}
			}else{
				return;
			}
		}
	}
	function getMsg1(){
		xhr=new XMLHttpRequest();
		xhr.onreadystatechange=successss;
		var contents=document.getElementById("contents");
		xhr.open('get','message.jsp?contents='+contents.value+'&rid='+'${rid}',true);
		xhr.send();
	}
	function successss(){
		if(xhr.readyState==4 && xhr.status==200){
			var data=xhr.responseText;
			var msgs=JSON.parse(data)[0];
			var msgList=document.getElementById("msgList");
			cnt1=msgs.length;
			cnt2=msgs.length;
			for(var i=0;i<msgs.length;i++){
				var div=document.createElement("div");
				div.innerHTML=msgs[i].contents;
				if(msgs[i].rid=='${rid}'){
					div.style.border="1px solid blue";
				}else{
					div.style.border="1px solid red";
					div.style.textAlign="right";
				}
				msgList.appendChild(div);
				msgList.scrollTop = msgList.scrollHeight;
			}
		}
		recmsg();
	}
	function deleteMsg(){
		var msgList=document.getElementById("msgList");
		var msglc=msgList.childNodes;
		for (var i = msglc.length-1; i >=0; i--) {
			var msg=msglc.item(i);
			msgList.removeChild(msg);
		}
	}
	function recmsg(){
		setInterval(rrr, 1000);
	}
	function rrr(){
		getMsg();	
	}
</script>
<div>
<div id="msgList" style="height: 600px;width:600px;overflow: auto;" >


</div>
</div>
<form action="javascript:sendMsg('${rid}')">
<input type="text" width="30" id="contents" > </form><input type="button" value="보내기" onclick="sendMsg('${rid}')">
</body>
</html>