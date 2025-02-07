package kr.or.ddit.prod.dao;

import java.util.List;

import kr.or.ddit.prod.vo.LprodVO;

public interface LprodMapper {
	public LprodVO selectLprod(String lprodGu);
	
	public List<LprodVO> selectLprodList();
}
