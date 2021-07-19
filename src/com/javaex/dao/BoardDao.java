package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.BoardVo;
import com.javaex.vo.GuestBookVo;

public class BoardDao {

	// 0. import java.sql.*;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";

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

	// 게시판 출력
	public List<BoardVo> getBoardList() {
		List<BoardVo> boardList = new ArrayList<>();

		this.getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행 --> 완성된 sql문을 가져와서 작성할것
			String query = "";
			query += "select b.no no, ";
			query += "       b.title title, ";
			query += "       u.name name, ";
			query += "       b.hit hit, ";
			query += "       to_char(reg_date, 'yyyy-mm-dd') regdate, ";
			query += "       b.user_no fkuserNo ";
			query += "from users u, board b ";
			query += "where u.no = b.user_no ";
			query += "order by no desc ";

			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				int no = rs.getInt("no");
				String title = rs.getString("title");
				String name = rs.getString("name");
				int hit = rs.getInt("hit");
				String regDate = rs.getString("regdate");
				int userNo = rs.getInt("fkuserNo");

				BoardVo boardVo = new BoardVo();
				boardVo.setNo(no);
				boardVo.setTitle(title);
				boardVo.setName(name);
				boardVo.setHit(hit);
				boardVo.setRegDate(regDate);
				boardVo.setUserNo(userNo);

				boardList.add(boardVo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return boardList;
	}

	// 게시글 등록
	public int boardInsert(BoardVo boardVo) {

		int count = -1;

		this.getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행 --> 완성된 sql문을 가져와서 작성할것
			String query = "";
			query += "insert into board ";
			query += "values(seq_board_no.nextval, ?, ?, ?, sysdate, ?)";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, boardVo.getTitle());
			pstmt.setString(2, boardVo.getContent());
			pstmt.setInt(3, boardVo.getHit());
			pstmt.setInt(4, boardVo.getNo());

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 등록");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return count;

	}

	// read출력
	public BoardVo getRead(int boardNo) {
		BoardVo boardVo = null;

		this.getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행 --> 완성된 sql문을 가져와서 작성할것
			String query = "";
			query += "select b.no no, ";
			query += "       b.title title, ";
			query += "       b.content content, ";
			query += "       u.name name, ";
			query += "       b.hit hit, ";
			query += "       to_char(reg_date, 'yyyy-mm-dd') regdate, ";
			query += "       u.no userNo ";
			query += "from users u, board b ";
			query += "where u.no = b.user_no ";
			query += "and b.no = ? ";
			query += "order by b.no desc ";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);

			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				int no = rs.getInt("no");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String name = rs.getString("name");
				int hit = rs.getInt("hit");
				String regDate = rs.getString("regdate");
				int userNo = rs.getInt("userNo");

				boardVo = new BoardVo(no, title, content, name, hit, regDate, userNo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return boardVo;
	}

	// 수정폼 출력
	public BoardVo getContent(int boardNo) {

		BoardVo boardVo = null;

		this.getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행 --> 완성된 sql문을 가져와서 작성할것
			String query = "";
			query += "select b.title title, ";
			query += "       b.content content, ";
			query += "       u.name name, ";
			query += "       b.hit hit, ";
			query += "       to_char(reg_date, 'yyyy-mm-dd') regdate ";
			query += "from users u, board b ";
			query += "where u.no = b.user_no ";
			query += "and b.no = ? ";
			query += "order by b.no desc ";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);

			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				String title = rs.getString("title");
				String content = rs.getString("content");
				String name = rs.getString("name");
				int hit = rs.getInt("hit");
				String regDate = rs.getString("regdate");
				int userNo = rs.getInt("userNo");

				boardVo = new BoardVo(title, content, name, hit, regDate, userNo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return boardVo;
	}

	// db업데이트
	public int boardUpdate(String title, String content, int boardNo) {

		int count = -1;

		this.getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행 --> 완성된 sql문을 가져와서 작성할것
			String query = "";
			query += "update board ";
			query += "set title = ?, ";
			query += "    content = ? ";
			query += "where no = ?";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, boardNo);

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 수정");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return count;
	}
	
	// 게시글 삭제
	public int listDelete(int boardNo) {
		int count = -1;
		
		this.getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " delete from board ";
			query += " where no = ? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, boardNo);

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 삭제");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return count;
	}

}
