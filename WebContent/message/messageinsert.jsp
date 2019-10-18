<%@page import="java.io.PrintWriter"%>
<%@page import="org.json.JSONArray"%>
<%@page import="message.dao.joo.MessageDao"%>
<%@page import="message.vo.joo.MessageVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String sid=(String)request.getSession().getAttribute("id");
	System.out.println(sid);
	String rid=request.getParameter("rid");
	String contents=request.getParameter("contents");
	MessageDao.getDao().insertMsg(sid, rid, contents);
%>