package kr.or.ddit.props.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import kr.or.ddit.props.vo.PropertyVO;

class PropertyDAOImplTest {
	
	PropertyDAO dao = new PropertyDAOImpl();

	@Test
	void testSelectPropertyList() {
		List<PropertyVO> propList = dao.selectPropertyList();
		assertNotNull(propList);
		assertNotEquals(0, propList.size());
	}

	@Test
	void testSelectProperty() {
		PropertyVO prop = dao.selectProperty("EXPORT_VIEWS_VERSION");
		assertNotNull(prop);
	}

}
