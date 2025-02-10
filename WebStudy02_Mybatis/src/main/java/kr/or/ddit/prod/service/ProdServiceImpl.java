package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.paging.PaginationInfo;
import kr.or.ddit.prod.dao.ProdMapper;
import kr.or.ddit.prod.dao.ProdMapperImpl;
import kr.or.ddit.prod.exception.ProdNotExistException;
import kr.or.ddit.prod.vo.ProdVO;

public class ProdServiceImpl implements ProdService {

	private ProdMapper dao = new ProdMapperImpl();
	@Override
	public List<ProdVO> readProdList(PaginationInfo<ProdVO> paging) {
		int totalRecord = dao.selectTotalRecord(paging);
		// 총 레코드 개수 셋팅
		paging.setTotalRecord(totalRecord);
		return dao.selectProdList(paging);
	}
	
	
	public ProdVO readProd(String prodId) throws ProdNotExistException{
		ProdVO prod = dao.selectProd(prodId);
		if(prod==null) {
			//prodId값이 존재하지 않으면 예외 발생 
			throw new ProdNotExistException(prodId);
		}
		return prod;
		
	}


	@Override
	public boolean createProd(ProdVO prod) {
		return dao.insertProd(prod) > 0;
	}


	@Override
	public boolean modifyProd(ProdVO prod) {
		return dao.updateProd(prod) > 0;
	}
	
}
