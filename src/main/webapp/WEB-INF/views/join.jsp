<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String contextPath = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="context-path" content="<%= request.getContextPath() %>">
<script src="<%=contextPath %>/js/validate.js" defer></script>
</head>
<body>
	<h1>회원 가입 테스트 페이지</h1>
	<form action="<%=contextPath%>/join" method="post" onsubmit="return validateForm()">
	이메일 : <input type="text" name="email"> <button type="button" name="check" onclick="return checkEmail()">앙 아운띠</button> <br>
	비밀번호 : <input type="password" name="password"><br>
	닉네임 : <input type="text" name="nickname"><br>
	<input type="submit" value="회원가입">
	</form>
</body>
</html>