<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board/community.jsp</title>
<style type="text/css">
	table{width: 100%; text-align: center;}
	   #divPaging {clear:both;  margin:0 auto; width:220px; height:50px;}
	   #divPaging > div {float:left; width: 30px; margin:0 auto; margin-top: 30px;text-align:center;}
       #divSearchOption {clear:both; }
       #divSearchOption > div {float:left; margin:0 auto; margin-top: 30px; width:auto; height:100px; text-align:center;}
</style>
</head>
<body>
<hr>
	
	<!-- 게시판 body 영역 -->
<table border="1" width="600">
	<tr>
		<th>글번호</th><th>제목</th><th>작성자</th><th>작성일</th><th>조회</th>
	</tr>

    <c:forEach var="vo" items="${list }">
	<tr>
		<td>${vo.write_num }</td>
		<td><a href="#">${vo.p_title }</a></td> 
		<td>${vo.id }</td>
		<td>${vo.r_date }</td>
		<td>${vo.views }</td>
		</tr>
	</c:forEach>
</table>

	<!-- 게시판 페이징 -->
     <div id="divPaging">
         <div>◀</div>
         <div><b>1</b></div>
         <div>2</div>
         <div>3</div>
         <div>4</div>
         <div>5</div>
         <div>▶</div>
         </div>
                
    <!-- 검색 폼 영역 --> 
    <div id="divSearchOption">
      <select id='SearchOption' >
         <option value='TC'>제목+내용</option>
         <option value='T'>제목</option>
         <option value='C'>내용</option>
      </select>
         <input id='KeyWord' />
         <input type='button' value='검색'/>
         <input type='button' value='글쓰기' onclick="window.location='/board/insert'"/>
    </div>
		
</body>
</html>
	