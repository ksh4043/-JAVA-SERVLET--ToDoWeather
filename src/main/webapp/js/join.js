const emailRegex = /^[a-zA-Z0-9]{7,15}@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
const passwordRegex = /^(?=[A-Z])(?=.*[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?])(?=.*[a-zA-Z])(?=.*\d)[A-Za-z\d!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]{8,20}$/;
const nicknameRegex = /^[가-힣a-zA-Z0-9]{3,12}$/;

const contextPath = document.querySelector('meta[name="context-path"]').content;

let emailInput, passwordInput, nickInput;

document.addEventListener("DOMContentLoaded", () => {
	emailInput = document.querySelector(`input[name="email"]`);
	passwordInput = document.querySelector(`input[name="password"]`);
	nickInput = document.querySelector(`input[name="nickname"]`);
});

function validateForm(){
	if (!emailRegex.test(emailInput.value)){
		alert("이메일 형식이 올바르지 않아용");
		return false;
	}
		
	if (!passwordRegex.test(passwordInput.value)){
		alert("비밀번호 형식이 올바르지 않아용");
		return false;
	}
	
	if (!nicknameRegex.test(nickInput.value)){
		alert("닉네임 형식이 올바르지 않아용");
		return false;
	}
	return true;
}

function checkEmail() {
	fetch(`${contextPath}/auth/email`, {
		method : "POST",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify({email: emailInput.value})
	})
	.then(res => {
		if(!res.ok) {
			console.log(res);
			throw new Error("이메일 확인 응답 에러");
		}
		return res.json();
	})
	.then(data => {
		if (data.success) {
			alert("정상 이메일 확인");
		} else{
			// 로직 처리
			alert("안됨ㅋㅋ");
		}
	})
	.catch(error => {
		console.log("에러 발생:", error);
	});
};

function isEqualsCode(){
	let inputAuthCode = document.querySelector(`input[name="authcode"]`).value;

	fetch(`${contextPath}/auth/code`, {
		method:"POST",
		headers:{
			"Content-Type":"application/json"
		},
		body:JSON.stringify({authcode: inputAuthCode.value})
	})
	.then(res => {
		if(!res.ok){
			console.log(res);
			throw new Error("인증 코드 확인 응답 에러");
		}
		return res.json();
	})
	.then(data => {
		if (data.success) {
			alert("data:", data);
		}else{
			alert("응 안됨");
		}
	})
}