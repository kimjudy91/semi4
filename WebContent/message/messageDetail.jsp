<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style >
input[type=text]:focus {border:2px #FF0000 solid}

</style>
<body style="background-color: #b1b1b1;">
<script type="text/javascript">
	window.onload=function(){
		function focusMsg(){
			var con=document.getElementById("contents");
			con.focus();
		}
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
					var div=document.createElement("span");
					var divs=document.createElement("div");
					if(msgs[i].sid=='${rid}'){
						div.innerHTML=msgs[i].contents;
						divs.style.marginTop="15px";	
						div.style.backgroundImage="url('/semi/images/mess1.png')";
						div.style.backgroundRepeat="no-repeat";
						div.style.backgroundSize ="100%100%";	
						div.style.padding="5px";
						divs.style.textAlign="left";
				
					}else{
						div.innerHTML=msgs[i].contents;
						div.style.textAlign="right";	
						div.style.backgroundImage="url('/semi/images/mess.png')";
						div.style.backgroundRepeat="no-repeat";
						div.style.backgroundSize ="100%100%";
						div.style.padding="5px";
						div.style.color="#fbfaef";
						divs.style.marginTop="15px";	
						
						
					}
					divs.appendChild(div);
					msgList.appendChild(divs);
					msgList.scrollTop = msgList.scrollHeight;
					var con=document.getElementById("contents");
					con.focus();
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
				var div=document.createElement("span");
				var divs=document.createElement("div");
				if(msgs[i].sid=='${rid}'){
					div.innerHTML=msgs[i].contents;
					div.style.backgroundImage="url('/semi/images/mess1.png')";
					div.style.backgroundRepeat="no-repeat";
					div.style.backgroundSize ="100%100%";	
					div.style.padding="5px";
					divs.style.marginTop="15px";
					
				
					divs.style.textAlign="left";
				}else{
					div.innerHTML=msgs[i].contents;
					div.style.backgroundImage="url('/semi/images/mess.png')";
					div.style.backgroundRepeat="no-repeat";
					div.style.backgroundSize ="100%100%";
					div.style.textAlign="right";	
					div.style.padding="5px";
					divs.style.marginTop="15px";	
							
					
					div.style.color="#fbfaef";
					
				}
				divs.appendChild(div);
				msgList.appendChild(divs);
				msgList.scrollTop = msgList.scrollHeight;
				var con=document.getElementById("contents");
				con.focus();
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
	function showScroll(){
		var divs=document.getElementById("msgList");
		divs.style.overflow="auto";
	}	function hideScroll(e){
		var divs=document.getElementById("msgList");
		divs.style.overflow="hidden";
	}
</script>
<div>
<h3 style="text-align: left; display:inline-block;"><a href="${cp }/messageList" style="font-size: 25px;color:black;text-decoration: none;">＜<c:if test="${countMsgs>0}">${ countMsgs}</c:if></a></h3>
<h2 style="text-align: center; display:inline-block;margin-left: 250px;">${rid }</h2>
<div id="msgList" style="height: 450px;width:600px;overflow: hidden;text-align: right;" onmousewheel="showScroll()" onmouseout="hideScroll()">


</div>
</div>
<div>
<form action="javascript:sendMsg('${rid}')" style="display: inline-block;">
<input type="text"  id="contents" style=" width:500px; height:30px;display: inline-block;border: 1px solid silver;"> </form>

<input type="button" value="보내기" onclick="sendMsg('${rid}')" style="display: inline-block;width:95px; height:40px;border-radius: 10px;background-color: #c7c7c7;color:#fbfbfb;-webkit-text-stroke-width:medium;">
</div>
</body>
</html>