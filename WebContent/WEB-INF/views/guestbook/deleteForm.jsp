<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

 
<%
	String no = request.getParameter("no");
	System.out.println(no);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="./gbc" method="get" >
		<table>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="password"></td>
				<td><input type="submit" value="확인"></td>
			</tr>
		</table>
		<a href="./gbc?action=addList">메인으로 돌아가기</a>
		<br>
		<input type='hidden' name="no" value="<%=no%>">
		<input type='hidden' name="action" value="delete">
	</form>
	
	
</body>
</html>