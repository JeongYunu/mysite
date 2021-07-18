<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="com.javaex.vo.UserVo" %>
<%
	UserVo authUser = (UserVo)session.getAttribute("authUser"); //(UserVo) 형변환
	System.out.println("세션확인: " + authUser);
%>

<div id="header" class="clearfix">
<h1>
	<a href="/mysite/main">MySite</a>
</h1>

<% if(authUser != null) { //로그인 성공일때%>
	<ul>
		<li><%=authUser.getName() %> 님 안녕하세요^^</li>
		<li><a href="/mysite/user?action=logout" class="btn_s">로그아웃</a></li>
		<li><a href="/mysite/user?action=modifyForm" class="btn_s">회원정보수정</a></li>
	</ul>
<% }else {  //로그인 아닐때 %>
	<ul>
		<li><a href="/mysite/user?action=loginForm" class="btn_s">로그인</a></li>
		<li><a href="/mysite/user?action=joinForm" class="btn_s">회원가입</a></li>
	</ul>
<% } %>

</div>
<!-- //header -->

<div id="nav">
	<ul class="clearfix">
		<li><a href="">입사지원서</a></li>
		<li><a href="/mysite/board?action=list">게시판</a></li>
		<li><a href="">갤러리</a></li>
		<li><a href="/mysite/gbc?action=addlist">방명록</a></li>
	</ul>
</div>
<!-- //nav -->