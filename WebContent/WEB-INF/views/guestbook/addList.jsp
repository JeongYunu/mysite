  
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="com.javaex.vo.GuestBookVo" %>
<%@ page import="java.util.List" %>

<%
	List<GuestBookVo> guestBookList = (List<GuestBookVo>)request.getAttribute("guestBookList");
	System.out.println(guestBookList);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="./gbc" method="get">
		<table border="1" width="500px">
			<tr>
				<td>이름</td>
				<td><input type="text" name="name"></td>
				<td>비밀번호</td>
				<td><input type="password" name="pass"></td>
			</tr>
			<tr>
				<td colspan="4"><textarea name="content" cols=60 rows=5></textarea></td>
			</tr>
			<tr>
				<td colspan="4"><button type="submit">확인</button></td>
			</tr>
		</table>
		<input type="hidden" name="action" value="add" >
	</form>
	<br/>

	<% 
		for(int i=0; i<guestBookList.size(); i++){
	%>
			<table border="1"  width="500px">
				<tr>
					<td>[<%=guestBookList.get(i).getNo() %>]</td>
					<td><%=guestBookList.get(i).getName() %></td>
					<td><%=guestBookList.get(i).getRegDate() %></td>
					<td><a href="./gbc?action=dForm&no=<%=guestBookList.get(i).getNo() %>">삭제</a></td>
				</tr>
				<tr>
					<td colspan=4><%=guestBookList.get(i).getContent() %></td>
				</tr>
			</table>
		    <br/>
	<% 
		}
	%>
</body>
</html>