package kr.or.ddit.prod.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import kr.or.ddit.prod.vo.ProdVO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
class ProdMapperTest {
	
	ProdMapper dao = new ProdMapperImpl();
	
	@Test
	void testSelectProdList() {
		assertDoesNotThrow(()->{
			List<ProdVO> prodList = dao.selectProdList(paging);
			for(ProdVO p : prodList) {
				log.info("lprod.lprodNm: {}, buyer.buyerName: {}",
						p.getLprod().getLprodNm(),p.getBuyer().getBuyerName());
			}
		});
	}
	@Test
	void testSelectProd(){
		//정상적으로 동작하면
		assertDoesNotThrow(()->{
			ProdVO prod = dao.selectProd("P101000001");
			assertNotNull(prod);
			
		});
		
		
	}
}
