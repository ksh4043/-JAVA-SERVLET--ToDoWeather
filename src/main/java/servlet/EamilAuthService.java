package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
<<<<<<< HEAD
import javax.servlet.http.HttpSession;

import service.EmailSender;
import service.RandAuthCode;

=======
import jakarta.mail.Session;
import jakarta.mail.Message;
import jakarta.mail.Transport;
import jakarta.mail.internet.MimeMessage;
import jakarta.activation.DataHandler;
import jakarta.activation.FileDataSource;
>>>>>>> 1f3125001ee5c35c039d56a0f99a03118ce05cad
import java.io.BufferedReader;
import javax.json.Json;
import javax.json.JsonObject;

@WebServlet("/auth/email")
public class EamilAuthService extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EamilAuthService() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
<<<<<<< HEAD
		BufferedReader reader = request.getReader();
		StringBuilder sb = new StringBuilder();
		String line;
		HttpSession session = request.getSession();
=======
		System.out.println("요청 경로 이동 성공");
		BufferedReader reader = request.getReader();
		StringBuilder sb = new StringBuilder();
		String line;
>>>>>>> 1f3125001ee5c35c039d56a0f99a03118ce05cad
		
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
<<<<<<< HEAD
				
=======
		
		
>>>>>>> 1f3125001ee5c35c039d56a0f99a03118ce05cad
		String json = sb.toString();
		System.out.println("Json Data : " + json);
		
		String email = null;
		try {
			JsonObject jsonObject = Json.createReader(new StringReader(json)).readObject();
			email = jsonObject.getString("email");
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().write("{\"success\": false, \"message\": \"Invalid JSON\"}");
			return;
		}
		
<<<<<<< HEAD
		// 이메일 인증 > 인증 코드 보냄 > 인증 코드 확인
		String authcode = RandAuthCode.authRandCode();
		session.setAttribute("authcode", authcode);
		session.setAttribute("email", email);
=======
		boolean success = email != null && email.contains("@");
>>>>>>> 1f3125001ee5c35c039d56a0f99a03118ce05cad
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
<<<<<<< HEAD
		
		if (EmailSender.sendVerificationEmail(email, authcode)) {
			out.write("{\"success\": " + true + "}");
		    out.flush();
		}else {
			out.write("{\"success\": " + false + "}");
		    out.flush();
=======
	    out.write("{\"success\": " + success + "}");
	    out.flush();
		
		if(success) {
			System.out.println("이메일 JSON 데이터 검사 성공");
		}else {
			System.out.println("잘못된 데이터");
>>>>>>> 1f3125001ee5c35c039d56a0f99a03118ce05cad
		}
	}
	

}
