document.addEventListener("DOMContentLoaded", () => {
	let email = document.querySelector(`input[type="email"]`);
	let password = document.querySelector(`input[type="password"]`);
	let submit = document.querySelector(`button[type="button"]`);
})

function login() {
	if (email.value == null || email.value == "") {
		alert("이메일을 입력해 주세요");
		return;
	}

	if (password.value == null || password.value == "") {
		alert("비밀번호를 입력해 주세요");
		return;
	}
	
	fetch(`${contextPath}/login`, {
		method: "POST",
		headers: {"Content-Type" : "application/json"},
		body: Json.stringify({
			email: email.value,
			password: password.value
		})
	})
	.then(res => {
		if (!res.ok) {
			throw Error("서버와 통신 실패");
		}
		return res.json;
	})
	.then(data => {
		if (data.success) {
			alert("로그인 성공");
		} else {
			alert("아이디 또는 비밀번호가 일치하지 않습니다");
		}
	})
	.then(error => {
		console.log(error);
	});
}