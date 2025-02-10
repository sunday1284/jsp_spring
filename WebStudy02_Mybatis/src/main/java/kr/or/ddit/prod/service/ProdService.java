package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.paging.PaginationInfo;
import kr.or.ddit.prod.exception.ProdNotExistException;
import kr.or.ddit.prod.vo.ProdVO;

public interface ProdService {
	public List<ProdVO> readProdList(PaginationInfo<ProdVO> paging);
	
//	존재하지 않는 상품의 경우, kr.ddit.prod.exception.ProdNotExistException(Unchecked) 발생 
	/**
	 * @param prodId
	 * @return
	 * @throws ProdNotExistException 상품이 없는 경우 발생하는 예외
	 */
	public ProdVO readProd(String prodId) throws ProdNotExistException;
	
	/**
	 * 상품 정보 등록
	 * @param prod
	 * @return
	 */
	public boolean createProd(ProdVO prod);
	
	/**
	 * 상품 정보 수정
	 * @param prod
	 * @return
	 */
	public boolean modifyProd(ProdVO prod);
}
