<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="FILE\HEAD.jsp"%>

<%@include file="FILE\header.jsp"%>
<div id="primary">
	<div id="content">
		<div id="frameError">
			<div id="titleLogin">Lỗi</div>
			<div id="tb">
				<p><%=request.getAttribute("errorMessage")%></p>
			</div>
		</div>
	</div>
</div>
