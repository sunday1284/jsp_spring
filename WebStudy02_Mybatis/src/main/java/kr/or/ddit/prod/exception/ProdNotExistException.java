package kr.or.ddit.prod.exception;

public class ProdNotExistException extends RuntimeException{

	public ProdNotExistException(String prodId) {
		super(String.format("%s 상품코드의 상품의 없음.", prodId));
	}
	
	
}
