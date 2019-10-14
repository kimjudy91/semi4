<%@page import="java.sql.Connection"%>
<%@page import="jdbc.JdbcUtil"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.io.PrintWriter"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String phone = request.getParameter("phone");
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	boolean check = false;
	try {
		con = JdbcUtil.getConn();
		String sql = "select * from members where phone=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, phone);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			check = true;
		}

	} catch (SQLException se) {
		System.out.println(se.getMessage());
	} finally {
		JdbcUtil.close(con, pstmt, rs);
	}
	response.setContentType("text/xml; charset=utf-8");
	PrintWriter pw = response.getWriter();
	pw.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
	pw.print("<data>");
	pw.print("<check>" + check + "</check>");
	pw.print("</data>");
%>