package kr.or.ddit.prod.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import kr.or.ddit.prod.vo.LprodVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class LprodMapperTest {
	
	LprodMapper dao = new LprodMapperImpl();
	@Test
	void testSelectLprod() {
		LprodVO lprod = dao.selectLprod("P101");
		log.info("prodList : {}", lprod.getProdList());
	}
	
	@Test
	void testSelectLprodList() {
		dao.selectLprodList()
				.forEach(lp->log.info("NM, {},buyerList : {}",lp.getLprodNm(), lp.getBuyerList()));
	}

}
