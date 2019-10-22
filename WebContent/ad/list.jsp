<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- ad/list.jsp -->
<div class="out" style="width: 95%; text-align: center; border: 1px solid black; padding: 20px; margin: 15px">
<div class="in" style="display: inline-block; padding: 20px; margin: 15px">
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
</div>
</div>
<a href="${cp }/ad/insert">광고추가</a>