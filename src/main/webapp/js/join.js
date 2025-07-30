const emailRegex = /^[a-zA-Z0-9]{7,15}@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
const passwordRegex = /^(?=[A-Z])(?=.*[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?])(?=.*[a-zA-Z])(?=.*\d)[A-Za-z\d!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]{8,20}$/;
const nicknameRegex = /^[가-힣a-zA-Z0-9]{3,12}$/;

const contextPath = document.querySelector('meta[name="context-path"]').content;

let emailInput, passwordInput, nickInput, validateCode, isEmailVerife, inputAuthCode;

document.addEventListener("DOMContentLoaded", () => {
	emailInput = document.querySelector(`input[name="email"]`);
	passwordInput = document.querySelector(`input[name="password"]`);
	nickInput = document.querySelector(`input[name="nickname"]`);
	validateCode = document.querySelector(`div[class="form-group validateCode"]`);
	
    isEmailVerifed = false;
});

function submitJoin() {
	if (!validateForm) {
		return false;
	}
	
	fetch(`${contextPath}/join`, {
		method: "POST",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify({
			email: emailInput.value,
			password: passwordInput.value,
			nickname: nickInput.value,
		})
	})
	.then(res => {
		if (!res.ok){
			throw Error("Failed Register");
		}
		return res.json();
	})
	.then(data => {
		if (!data.success){
			alert("Invalid Data");
		} else {
			alert("회원 가입에 성공했습니다!");
		}
	})
	.catch(error => {
		console.log("에러 발생: " + error);
	});
}

function validateForm(){
	if (!isEmailVerife) {
		alert("이메일 인증을 완료해주세요.");
		return false;
	}
		
	if (!emailRegex.test(emailInput.value)){
		alert("이메일 형식이 올바르지 않아용");
		return false;
	}
		
	if (!passwordRegex.test(passwordInput.value)){
		alert("비밀번호는 대소문자, 숫자, 특수문자를 포함하여 8~20자");
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
			throw new Error("이메일 확인 응답 에러");
		}
		return res.json();
	})
	.then(data => {
		if (data.success) {
			validateCode.style.display = 'inline-block';
		} else{
			alert("이메일이 올바르지 않습니다.");
		}
	})
	.catch(error => {
		console.log("에러 발생:", error);
	});
};

function isEqualsCode(){
	inputAuthCode = document.querySelector(`input[name="authcode"]`);
	fetch(`${contextPath}/auth/code`, {
		method:"POST",
		headers:{
			"Content-Type":"application/json"
		},
		body:JSON.stringify({
			authcode: inputAuthCode.value,
			email: emailInput.value
		})
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
			isEmailVerife = true;
			alert("인증이 완료되었습니다!");
		}else{
			alert("인증 코드가 일치하지 않습니다.");
		}
	})
	.catch(error => {
		console.log("에러 발생: ", error);
	});
}