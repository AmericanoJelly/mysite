package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVo;


@Repository
public class BoardRepository {
	@Autowired
	private SqlSession sqlSession;

	public boolean update(BoardVo vo) {
		boolean result =  sqlSession.insert("board.update", vo) == 1;
		return result;
	}

	public boolean delete(BoardVo vo) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = getConnection();

			String sql = "delete from board where no = ? ";
			pstmt = connection.prepareStatement(sql);

			pstmt.setLong(1, vo.getNo());

			int count = pstmt.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}


	public boolean insert(BoardVo vo) {
		boolean result =  sqlSession.insert("board.insert", vo) == 1;
		System.out.println("인서트인서트"+ vo);
		return result;
	}

	//comment update
	public boolean c_update(Long no) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = getConnection();


			String sql = "update board set MAX(o_no)+1 where g_no = ?";
			pstmt = connection.prepareStatement(sql); 

			pstmt.setLong(1, no);

			int count = pstmt.executeUpdate();
			result = count == 1;
		} catch (SQLException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {

			}
		}
		return result;
	}

	public BoardVo findView(Long no) {
		return sqlSession.selectOne("board.findView", no);
	}


	public List<BoardVo> findAll(int page, String kwd) {
		page = (page - 1) * 5;

		Map<String, Object> map = new HashMap<>();
		map.put("page", page);
		map.put("kwd", kwd);

		return sqlSession.selectList("board.findAll", map);
	}

	public int count(String kwd) {
		return sqlSession.selectOne("board.count", kwd);
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
