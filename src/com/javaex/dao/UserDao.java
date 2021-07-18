package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.GuestBookVo;
import com.javaex.vo.UserVo;

public class UserDao {

	// 필드
	// 0. import java.sql.*;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";

	// 생성자

	// 게세

	// 일반
	private void getConnection() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
			// System.out.println("접속성공");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	public void close() {
		// 5. 자원정리
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}
	
	// 유저저장
	public int userInsert(UserVo userVo) {

		int count = -1;

		try {
			
			this.getConnection();
			
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += "insert into users ";
			query += "values(seq_user_no.nextval, ?, ?, ?, ?) ";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userVo.getId());
			pstmt.setString(2, userVo.getPw());
			pstmt.setString(3, userVo.getName());
			pstmt.setString(4, userVo.getGender());

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 저장되었습니다.");
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		this.close();
		
		return count;
	}

	// 유저1명 정보 가져오기
	public UserVo getUser(String id, String password) {

		UserVo userVo = null;

		try {

			this.getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += "select no, ";
			query += "       name ";
			query += "from users ";
			query += "where id = ? ";
			query += "and password = ? ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			
			rs = pstmt.executeQuery();

			// 4.결과처리
			while(rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				
				//생성자가 없는경우 Setter사용가능
				userVo = new UserVo();
				userVo.setNo(no);
				userVo.setName(name);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		this.close();

		return userVo;

	}
	
	// 유저수정전
	public UserVo getUserInfo(int no) {

		UserVo userVo = null;
		
		this.getConnection();

		try {


			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += "select no, ";
			query += "       id, ";
			query += "       name, ";
			query += "       password, ";
			query += "       gender ";
			query += "from users ";
			query += "where no = ? ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();

			// 4.결과처리
			while(rs.next()) {
				int No = rs.getInt("no");
				String id = rs.getString("id");
				String pw = rs.getString("password");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				
				userVo = new UserVo(no, id, pw, name, gender);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		this.close();

		return userVo;

	}
	
	// 유저수정
		public int getUpdate(String pass, String name, String gender) {

			int count = -1;
			
			this.getConnection();

			try {

				// 3. SQL문 준비 / 바인딩 / 실행
				String query = "";
				query += "update users ";
				query += "set password = ?, ";
				query += "    name = ?, ";
				query += "    gender = ? ";
				query += "where no = ? ";
				
				
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, pass);
				pstmt.setString(2, name);
				pstmt.setString(3, gender);
				
				pstmt.executeUpdate();

				// 4.결과처리
				System.out.println(count + "건이 수정되었습니다.");
					
			} catch (SQLException e) {
				System.out.println("error:" + e);
			} 
			
			this.close();

			return count;

		}

}
