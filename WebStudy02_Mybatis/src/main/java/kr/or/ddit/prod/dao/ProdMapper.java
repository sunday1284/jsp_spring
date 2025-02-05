package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.or.ddit.prod.vo.ProdVO;

public interface ProdMapper {
	//전체 조회 
	public List<ProdVO> selectProdList();
	/**
	 * @param prodId
	 * @return 상품이 없는 경우, null반환
	 */
	public ProdVO selectProd(@Param("prodId") String prodId);
}
