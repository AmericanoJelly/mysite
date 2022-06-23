package com.douzone.mysite.repository;

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
		return sqlSession.insert("board.update", vo) == 1;
	}

	public boolean delete(Long no) {
		return sqlSession.delete("board.delete", no) == 1 ;
	}

	public boolean insert(BoardVo vo) {
		boolean result = false;
		if (vo.getG_no() != 0) {
			result = sqlSession.update("board.updateOno", vo) == 1;
			System.out.println(vo);
		}
		result = sqlSession.insert("board.insert", vo) == 1;
		return result;
	}
	
	public BoardVo findView(Long no) {
		return sqlSession.selectOne("board.findView", no);
	}
	
	public boolean updateHit(Long no) {
		return sqlSession.update("board.updateHit", no) == 1;
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


}
