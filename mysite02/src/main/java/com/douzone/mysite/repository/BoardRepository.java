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
			//hit
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


	public List<BoardVo> findAll(int page, String kwd) {
		List<BoardVo> result = new ArrayList<BoardVo>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connection = getConnection();

			if (kwd == null) {
				// 3. SQL 준비
				String sql = "select a.no, a.g_no, a.title, b.name, a.hit, date_format(a.reg_date, '%Y-%m-%d %r'), a.user_no, a.dept, a.o_no "
						+ "	from board a, user b " 
						+ " where a.user_no = b.no "
						+ "	order by g_no desc, o_no asc limit ?, 5 ";
				pstmt = connection.prepareStatement(sql);

				// 4. Parameter Mapping
				pstmt.setInt(1, (page - 1) * 5);
			}else {
				String sql =
						" select a.no, a.g_no, a.title, b.name, a.hit, date_format(a.reg_date, '%Y-%m-%d %r'), a.user_no, a.dept, a.o_no "
								+ " from board a, user b " 
								+ " where a.user_no = b.no "
								+ "	and (a.title like concat('%', ?, '%'))"
								+ " order by a.g_no desc, a.o_no asc limit ?, 5 ";
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, kwd);
				pstmt.setLong(2, (page - 1) * 5);
			}

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
				long o_no = rs.getLong(9);

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setUser_name(name);
				vo.setHit(hit);
				vo.setReg_date(reg_date);
				vo.setG_no(g_no);
				vo.setO_no(o_no);
				vo.setDept(dept);
				vo.setUser_no(user_no);

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

	public int count(String kwd) {
		int result = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = getConnection();

			if (kwd == null) {
				// 3. SQL 준비
				String sql = "select count(*) from board";
				pstmt = connection.prepareStatement(sql);

				// 4. Parameter Mapping
			} else {
				// 3. SQL 준비
				String sql = "select count(*)" + "	from board a, user b" 
						+ "    where a.user_no = b.no"
						+ "    and (b.name like concat('%', ?, '%')" 
						+ "	or a.title like concat('%', ?, '%'))";
				pstmt = connection.prepareStatement(sql);

				// 4. Parameter Mapping
				pstmt.setString(1, kwd);
				pstmt.setString(2, kwd);
			}

			// 5. SQL 실행
			rs = pstmt.executeQuery();

			// 6. 결과처리
			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (SQLException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
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
