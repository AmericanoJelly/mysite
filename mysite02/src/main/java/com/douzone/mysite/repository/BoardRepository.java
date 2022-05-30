package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.mysite.vo.BoardVo;


public class BoardRepository {

	public List<BoardVo> findAll() {
		List<BoardVo> result = new ArrayList<BoardVo>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			connection = getConnection();
		
			String sql =
					  " select a.g_no, a.title, b.name, a.hit, a.reg_date "
					+ " from board a, user b " 
					+ " where a.user_no = b.no "
					+ " order by a.g_no desc, a.o_no asc ";
			pstmt = connection.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				long g_no = rs.getLong(1);
				String title = rs.getString(2);
				String name = rs.getString(3);
				long hit = rs.getLong(4);
				String reg_date = rs.getString(5);
				
				BoardVo vo = new BoardVo();
				vo.setG_no(g_no);
				vo.setTitle(title);
				vo.setUser_name(name);
				vo.setHit(hit);
				vo.setReg_date(reg_date);
				
				result.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;		
	}
	
	
	
	private Connection getConnection() throws SQLException {
		Connection connection = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mysql://192.168.10.38:3306/webdb?charset=utf8";
			connection = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}
		
		return connection;
	}	
}
