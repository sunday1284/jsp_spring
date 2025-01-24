package kr.or.ddit.props.service;

import java.util.List;

import kr.or.ddit.props.dao.PropertyDAO;
import kr.or.ddit.props.dao.PropertyDAOImpl;
import kr.or.ddit.props.vo.PropertyVO;

//서비스에서 인터페이스로 받아와 Dao 관련 작업을 리턴시킨다.
public class PropertyServiceImpl implements PropertyService {
	private PropertyDAO dao = new PropertyDAOImpl();
	
		
	/**
	 * 전체 리스트를 보여준다.
	 */
	public List<PropertyVO> readPropertyList(){
		return dao.selectPropertyList();
	}
	
	/**
	 * propertyName값을 파라미터로 받아서 보여줌
	 */
	@Override
	public PropertyVO readProperty(String propertyName) {
		PropertyVO propVO = dao.selectProperty(propertyName);
		return propVO;
	};
}
