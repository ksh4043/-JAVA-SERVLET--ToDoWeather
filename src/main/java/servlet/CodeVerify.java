package servlet;

import java.io.IOException;
import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/auth/code")
public class CodeVerify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CodeVerify() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 받아온 Json 데이터 읽기
		 * Session에 있는 암호 가져오기
		 * Json으로 받은 암호 코드와 Session에서 가져온 암호 코드 대조하기
		 */
		HttpSession session = request.getSession(false);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		JsonObject resJson;
		
		if(session == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			resJson = Json.createObjectBuilder().add("success", false).build();
			response.getWriter().write(resJson.toString());
			return;
		}
		
		String email = "";
		String authCode = "";
		try {
			JsonObject reqJson = Json.createReader(request.getReader()).readObject();
			email = reqJson.getString("email");
			authCode = reqJson.getString("authcode");
		}catch (Exception e){
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			resJson = Json.createObjectBuilder().add("success", false).build();
			response.getWriter().write(resJson.toString());
			return;
		}
		
		String sesAuthCode = (String)session.getAttribute("authcode");
		String sesEmail = (String)session.getAttribute("email");
		
		if (!(email.equals(sesEmail) && authCode.equals(sesAuthCode))) {
			resJson = Json.createObjectBuilder().add("success", false).build();
			response.getWriter().print(resJson.toString());
			return;
		}else {
			resJson = Json.createObjectBuilder().add("success", true).build();
			response.getWriter().print(resJson.toString());
		}
		System.out.println(resJson);
		
	}

}
