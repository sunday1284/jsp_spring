package kr.or.ddit.prod.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.paging.PaginationInfo;
import kr.or.ddit.prod.vo.ProdVO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
class ProdMapperTest {
	
	ProdMapper dao = new ProdMapperImpl();
	
	@Test
	void testSelectProdList() {
		assertDoesNotThrow(()->{
			PaginationInfo<ProdVO> paging = new PaginationInfo<>();
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
			log.info("입고일 : {}", prod.getProdInsdate());
		});
		
		
	}
	@Test
	void testInsertProd() {
		ProdVO dummy = dao.selectProd("P101000001");
		dummy.setProdId(null);
		dummy.setProdName("신규상품");
		
		assertDoesNotThrow(()->{
			assertEquals(1, dao.insertProd(dummy));		
		});
	}
	
	@Test
	void testUpdateProd() {
		ProdVO dummy = dao.selectProd("P101000001");
		dummy.setProdCost(1000);
		dummy.setProdOutline("수정된 상품 내용");
		dummy.setProdImg("");

		assertDoesNotThrow(() -> {
			assertEquals(1, dao.updateProd(dummy));
		});
	}
}
