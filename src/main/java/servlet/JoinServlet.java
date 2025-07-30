package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.DBConnection;
import service.SQLExecutor;

@WebServlet("/join")
public class JoinServlet extends HttpServlet {
    public JoinServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		JsonObject formDataJson = Json.createReader(request.getReader()).readObject();
		String email = formDataJson.getString("email");
		String password = formDataJson.getString("password");
		String nickname = formDataJson.getString("nickname");
		Connection con = null;
		DBConnection dbc = new DBConnection();
		
		try {
			con = dbc.connectDB();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("MySQL Driver is not found");
			return;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Loading Properties is failed");
			return;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB Connection is failed");
			return;
		}
		
		// Join to User
		String sql = "INSERT INTO user (email, password, certification, nickname) values (?, ?, ?, ?)";
		SQLExecutor sqlExc = new SQLExecutor();
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("email", email);
		params.put("password", password);
		params.put("nickname", nickname);
		boolean result = sqlExc.execute(sql, params, con);
		
		// Json Response
		JsonObject resJson;
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		if (!result) {
			resJson = Json.createObjectBuilder().add("success", false).build();
			response.sendRedirect("WEB-INF/views/join.jsp");
		}else { 
			resJson = Json.createObjectBuilder().add("success", true).build();
			RequestDispatcher toLogin = request.getRequestDispatcher("WEB-INF/views/login.jsp");
			toLogin.forward(request, response);
		}
		
		response.getWriter().write(resJson.toString());
	}

}