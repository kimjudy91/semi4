<%@page import="loginIds.dao.joo.LoginIdsDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String id=(String)request.getAttribute("id");
	LoginIdsDao.getDao().insertLogins(id);
	request.getSession().invalidate();
	System.out.println("sss");
%>