package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.DBConnection;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    public LoginServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		JsonObject reqJson = Json.createReader(request.getReader()).readObject();
		String email = reqJson.getString("email");
		String password = reqJson.getString("password");
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
		
		// SELECT
	}

}
