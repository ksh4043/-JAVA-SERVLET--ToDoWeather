package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.EmailSender;
import service.RandAuthCode;
import javax.json.Json;
import javax.json.JsonObject;

@WebServlet("/auth/email")
public class EamilAuthService extends HttpServlet {
    public EamilAuthService() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		JsonObject resJson;
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		String email = null;
		try {
			JsonObject reqJson = Json.createReader(request.getReader()).readObject();
			email = reqJson.getString("email");
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			resJson = Json.createObjectBuilder()
	                  .add("success", false)
	                  .build();
			response.getWriter().write(resJson.toString());
			return;
		}
		
		// 이메일 인증 > 인증 코드 보냄 > 인증 코드 확인
		String authcode = RandAuthCode.authRandCode();
		session.setAttribute("authcode", authcode);
		session.setAttribute("email", email);
		
		if (EmailSender.sendVerificationEmail(email, authcode)) {
		    resJson = Json.createObjectBuilder()
		                  .add("success", true)
		                  .build();
		} else {
		    resJson = Json.createObjectBuilder()
		                  .add("success", false)
		                  .build();
		}
		
		response.getWriter().write(resJson.toString());
	}
	

}