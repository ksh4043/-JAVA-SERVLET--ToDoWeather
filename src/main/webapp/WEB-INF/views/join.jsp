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
<link rel="stylesheet" href="<%=contextPath %>/css/join.css" />
</head>
<body>
	<div class="joinContainer">
	
		<header class="join-header">
			<h2>SIGN IN</h2>
		</header>
		
		<section class="join-form">

			<div class="form-container">

				<div class="form-group">
					<label for="email">이메일</label> <input type="email" id="email"
						name="email">
					<button type="button" onclick="checkEmail()">이메일 확인</button>
				</div>

				<div class="form-group validateCode">
					<label for="authcode">인증 코드</label> <input type="text"
						id="authcode" name="authcode">
					<button type="button" onclick="isEqualsCode()">인증 코드 확인</button>
				</div>

				<div class="form-group">
					<label for="password">비밀번호</label> <input type="password"
						id="password" name="password">
				</div>

				<div class="form-group">
					<label for="nickname">닉네임</label> <input type="text" id="nickname"
						name="nickname">
				</div>

				<div class="form-group">
					<button type="button" onclick="return submitJoin()">회원가입</button>
				</div>

			</div>

		</section>
	</div>
</body>
</html>