<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String contextPath = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<meta name="context-path" content="<%= request.getContextPath() %>">
<script src="<%=contextPath %>/js/join.js" defer></script>
<link rel="stylesheet" href="<%=contextPath %>/css/join.css"/>
</head>
<body>
	<div class="joinContainer">
		<main>
			<h2>SIGN IN</h2>
			<form action="<%=contextPath%>/join" method="post" onsubmit="return validateForm()">
			이메일 : <input type="text" name="email"> <button type="button" name="check" onclick="return checkEmail()">이메일 확인</button> <br>
			<div class="validateCode">
				<input type="text" name="authcode">
				<button type="button" name="codeCheck" onclick="return isEqualsCode()">인증 코드 확인</button>
			</div>
			비밀번호 : <input type="password" name="password"><br>
			닉네임 : <input type="text" name="nickname"><br>
			<input type="submit" value="회원가입">
			</form>
		</main>
	</div>
</body>
</html>