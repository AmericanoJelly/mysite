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
	

	public boolean update(BoardVo vo) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = getConnection();
			
			String sql =
					 " update board set title = ?, contents=? "
					+" where no = ? ";
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getNo());
			
			int count = pstmt.executeUpdate();
			result = count == 1;
		} catch (SQLException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} finally {
			try {
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
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = getConnection();
			if (vo.getG_no() == 0) { 
			String sql =
					 " insert into board values "
				   + " ( null, ?, ?, 0, now(), (select max(g_no)from board a)+1, 1, 1, ?)";
				pstmt = connection.prepareStatement(sql);
				
				pstmt.setString(1, vo.getTitle());
				pstmt.setString(2, vo.getContents());
				pstmt.setLong(3, vo.getUser_no());
			
			}else { 
				String sql = "insert into board values(null, ?, ?, 0, now(), ?, ?, ?, ?)";
				pstmt = connection.prepareStatement(sql);

				
				pstmt.setString(1, vo.getTitle());
				pstmt.setString(2, vo.getContents());
				pstmt.setLong(3, vo.getG_no());
				pstmt.setLong(4, vo.getO_no() + 1);
				pstmt.setLong(5, vo.getDept() + 1);
				pstmt.setLong(6, vo.getUser_no());

			}
			
			
			int count = pstmt.executeUpdate();
			result = count == 1;
		} catch (SQLException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} finally {
			try {
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
	
	public boolean c_update(Long no) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = getConnection();

			// 3. SQL 준비
			
			String sql = "update board set o_no= o_no+1 where g_no = ?";
			pstmt = connection.prepareStatement(sql); 

			// 4. Mapping(bind)
			pstmt.setLong(1, no);
			
			// 4. SQL 실행
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
	
	public BoardVo findView(long num) {
		BoardVo result = null;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connection = getConnection();

			String sql =
					" select title, contents, hit, user_no, g_no ,dept ,o_no from board where no = ? ";
			pstmt = connection.prepareStatement(sql);

			pstmt.setLong(1, num);
			rs = pstmt.executeQuery();
			long hit=0;
			if(rs.next()) {
				String title = rs.getString(1);
				String contents = rs.getString(2);
				hit = rs.getLong(3);
				long user_no = rs.getLong(4);
				long g_no = rs.getLong(5);
				long dept = rs.getLong(6);
				long o_no = rs.getLong(7);

				result = new BoardVo();
				result.setTitle(title);
				result.setContents(contents);
				result.setHit(hit);
				result.setUser_no(user_no);
				result.setG_no(g_no);
				result.setDept(dept);
				result.setO_no(o_no);
				
			}
			
			String sql2 =
					" update board set hit=? where no= ? ";
			pstmt = connection.prepareStatement(sql2);
			pstmt.setLong(1, hit+1);
			pstmt.setLong(2, num);
			
			pstmt.executeUpdate();
			
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


	public List<BoardVo> findAll() {
		List<BoardVo> result = new ArrayList<BoardVo>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connection = getConnection();

			String sql =
					" select a.no, a.g_no, a.title, b.name, a.hit, a.reg_date, a.user_no, a.dept "
							+ " from board a, user b " 
							+ " where a.user_no = b.no "
							+ " order by a.g_no desc, a.o_no asc ";
			pstmt = connection.prepareStatement(sql);
			
			rs = pstmt.executeQuery();

			while(rs.next()) {
				long no = rs.getLong(1);
				long g_no = rs.getLong(2);
				String title = rs.getString(3);
				String name = rs.getString(4);
				long hit = rs.getLong(5);
				String reg_date = rs.getString(6);
				long user_no = rs.getLong(7);
				long dept = rs.getLong(8);

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setG_no(g_no);
				vo.setTitle(title);
				vo.setUser_name(name);
				vo.setHit(hit);
				vo.setReg_date(reg_date);
				vo.setUser_no(user_no);
				vo.setDept(dept);

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
			String url = "jdbc:mysql://localhost/webdb?charset=utf8";
			connection = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}

		return connection;
	}



}
