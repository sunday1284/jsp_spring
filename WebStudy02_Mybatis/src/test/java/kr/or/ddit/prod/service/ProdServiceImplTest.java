package kr.or.ddit.prod.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import kr.or.ddit.prod.exception.ProdNotExistException;

class ProdServiceImplTest {
	
	ProdService service = new ProdServiceImpl();
	@Test
	void testReadProd() {
		assertThrows(ProdNotExistException.class, ()-> service.readProd("asdfasd"));
		assertDoesNotThrow(()-> service.readProd("P101000001"));
	}
	

}
