package com.douzone.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.GuestBookVo;

@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepository;
	
//	public  BoardVo getContents(long num){
//		return boardRepository.findView(num);
//	}
	
	public List<BoardVo> getMessageList(int page, String kwd){
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
		BoardVo result = null;
		if (no != null) {
			result = boardRepository.findView(no);
		}
		return result;
	}



}
