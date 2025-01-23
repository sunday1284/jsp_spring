package kr.or.ddit.props.service;

import java.util.List;

import kr.or.ddit.props.dao.PropertyDAO;
import kr.or.ddit.props.dao.PropertyDAOImpl;
import kr.or.ddit.props.vo.PropertyVO;

public class PropertyServiceImpl implements PropertyService {
	private PropertyDAO dao = new PropertyDAOImpl();
	
	
	
	
	public List<PropertyVO> readPropertyList(){
		return dao.selectPropertyList();
	}

	@Override
	public PropertyVO readProperty(String propertyName) {
		PropertyVO propVO = dao.selectProperty(propertyName);
		return propVO;
	};
}
