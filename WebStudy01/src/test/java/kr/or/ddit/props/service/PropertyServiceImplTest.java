package kr.or.ddit.props.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import kr.or.ddit.props.vo.PropertyVO;

class PropertyServiceImplTest {
	
	PropertyService service = new PropertyServiceImpl();
	
	@Test
	void testReadPropertyList() {
		assertDoesNotThrow(()->{
			service.readPropertyList();		
		});
	}

	@Test
	void testReadProperty() {
		PropertyVO prop =  service.readProperty("EXPORT_VIEWS_VERSION");
		assertNotNull(prop);
	}

}
