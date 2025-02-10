package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.or.ddit.paging.PaginationInfo;
import kr.or.ddit.prod.vo.ProdVO;

public interface ProdMapper {
	/**
	 * 페이징 처리를 위한 전체 레코드수 조회
	 * @return
	 */
	public int selectTotalRecord(PaginationInfo<ProdVO> paging);
	
	/**
	 * 페이징 처리된 데이터 목록 조회
	 * @param paging
	 * @return
	 */
	public List<ProdVO> selectProdList(PaginationInfo<ProdVO> paging);
	/**
	 * @param prodId
	 * @return 상품이 없는 경우, null반환
	 */
	public ProdVO selectProd(@Param("prodId") String prodId);
	
	/**
	 * 상품 등록 
	 * @param prod
	 * @return
	 */
	public int insertProd(ProdVO prod);
	
	
	/**
	 * @param prod
	 * @return
	 */
	public int updateProd(ProdVO prod);
}
