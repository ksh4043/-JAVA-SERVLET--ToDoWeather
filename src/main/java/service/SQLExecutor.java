package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

public class SQLExecutor {
	public boolean execute(String sql, HashMap<String, Object> params, Connection con) {
		String keyword = sql.trim().split(" ")[0].toUpperCase();
		switch (keyword) {
		case "SELECT":
			if (!handleSelect(sql, params, con)) {
				return false;
			}
			break;
		case "INSERT":
			if (!handleInsert(sql, params, con)) {
				return false;
			}
			break;
		case "DELETE":
			if (!handleDelete(sql, params, con)) {
				return false;
			}
			break;
		case "UPDATE":
			if (!handleUpdate(sql, params, con)) {
				return false;
			}
			break;
		default:
			System.out.println("잘못된 SQL 구문");
			return false;
		}
		return true;
	}

	private boolean handleUpdate(String sql, HashMap<String, Object> params, Connection con){
		// TODO Auto-generated method stub
		return false;
	}

	private boolean handleDelete(String sql, HashMap<String, Object> params, Connection con){
		// TODO Auto-generated method stub
		return false;
	}

	private boolean handleInsert(String sql, HashMap<String, Object> params, Connection con){
		try (PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, params.get("email").toString());
			pstmt.setString(2, params.get("password").toString());
			pstmt.setInt(3, 0);
			pstmt.setString(4, params.get("nickname").toString());
			
			int rows = pstmt.executeUpdate();
			return rows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Insert Error!");
			return false;
		}
	}

	private boolean handleSelect(String sql, HashMap<String, Object> params, Connection con){
		// TODO Auto-generated method stub
		return false;
	}

}
