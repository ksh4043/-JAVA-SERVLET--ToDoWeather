<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String contextPath = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<div class="container">
		<section class="login_section">
			<article>
				<div class="login_form">
					<div class="email_form">
						<label>이메일</label>
						<input type="email" class="email" placeholder="이메일을 입력해 주세요">
					</div>
					
					<div class="password_form">
						<label>비밀번호</label>
						<input type="password" class="password" placeholder="비밀번호를 입력해 주세요">
					</div>
					
					<div class="submit_form">
						<button type="button" onclick="login()">로그인</button>
					</div>
				</div>
			</article>
		</section>
	</div>
</body>
</html>