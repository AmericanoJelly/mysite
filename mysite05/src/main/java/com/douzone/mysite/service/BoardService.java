package com.douzone.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;

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
	
	
	private List<BoardVo> findAll(int page, String kwd) {
		return boardRepository.findAll(page,kwd);
	}
	
	public Map<String, Object> getPageAndList(int page, String kwd) {
		Map<String, Object> map = new HashMap<>();

		int count = count(kwd) - (5 * (page - 1));
		int lastPage = (count(kwd) - 1) / 5 + 1;
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

		map.put("currentPage", page);
		map.put("kwd", kwd);
		map.put("list", findAll(page, kwd));
		map.put("count", count);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("lastPage", lastPage);
		return map;
	}


	public BoardVo getContents(Long no) {
		return boardRepository.findView(no);
	}

	public boolean write(BoardVo vo) {
		if (vo.getNo() != 0) {
			BoardVo vo1 = boardRepository.findView((vo.getNo()));
			vo.setG_no(vo1.getG_no());
			vo.setO_no(vo1.getO_no() + 1);
			vo.setDept(vo1.getDept() + 1);
		} 
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


