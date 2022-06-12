package com.douzone.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;

@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepository;
	
	public List<BoardVo> getMessageList(int page, String kwd){
		System.out.println("");
		List<BoardVo> vo = boardRepository.findAll(page,kwd);
		System.out.println(vo);
		return boardRepository.findAll(page,kwd);
	}

	public int count(String kwd) {
		return boardRepository.count(kwd);
	}
	
	public Map<String, Integer> getPage(int page, int lastPage) {
		Map<String, Integer> map = new HashMap<>();

		int startPage = 0, endPage = 0;
		if (page < 4 || lastPage <= 5) {
			startPage = 1;
			endPage = 5;
		} else if ((lastPage - page) > 1) {
			startPage = page - 2;
			endPage = page + 2;
		} else {
			endPage = lastPage;
			startPage = endPage - 4;
		}

		map.put("startPage", startPage);
		map.put("endPage", endPage);
		return map;
	}
	
	public BoardVo getContents(Long no) {
		return boardRepository.findView(no);
	}

	public boolean write(BoardVo vo) {
		return boardRepository.insert(vo);
	}
	
	public boolean Update(BoardVo vo) {
		return boardRepository.update(vo);
	}

	public boolean delete(Long no) {
		return boardRepository.delete(no);
	}

	public boolean updateHit(Long no) {
		return boardRepository.updateHit(no);
	}

}


