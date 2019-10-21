<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<link rel="stylesheet" type="text/css" href="${cp }/main/main.css">
<!-- banner -->
<div id="bann"><c:import url="/ad"/></div>

<div id="mwrap">
<div id="d1">
	<div id="mlikes"><c:import url="/likes"/></div>
	<div id="mrecently"><c:import url="/recently"/></div>
	<div id="mrecently_music"><c:import url="/recently_music"/></div>
</div>
<div id="d2">
	<div id="mRock"><c:import url="/recommended"/></div>
	<div id="mFolk"><c:import url="/recommended_folk"/></div>
	<div id="mRB"><c:import url="/recommended_RB"/></div>
</div>
</div>