package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.EmailSender;
import service.RandAuthCode;

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
		BufferedReader reader = request.getReader();
		StringBuilder sb = new StringBuilder();
		String line;
		HttpSession session = request.getSession();
		
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
				
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
		
		// 이메일 인증 > 인증 코드 보냄 > 인증 코드 확인
		String authcode = RandAuthCode.authRandCode();
		session.setAttribute("authcode", authcode);
		session.setAttribute("email", email);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		if (EmailSender.sendVerificationEmail(email, authcode)) {
			out.write("{\"success\": " + true + "}");
		    out.flush();
		}else {
			out.write("{\"success\": " + false + "}");
		    out.flush();
		}
	}
	

}
