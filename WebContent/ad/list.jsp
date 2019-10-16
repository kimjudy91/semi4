<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- ad/list.jsp -->
<div>
	<table border="1" width="400">
		<tr>
			<th>회사명</th><th>광고비</th><th>시작날짜</th><th>끝날짜</th>
		</tr>
		<c:forEach var="ad" items="${adList }">
			<tr>
				<td>${ad.ad_com }</td><td>${ad.ad_money }</td><td>${ad.ad_start_date }</td><td>${ad.ad_end_date}</td>
			</tr>
		</c:forEach>
	</table>
</div>
<a href="${cp }/ad/insert">광고추가</a>