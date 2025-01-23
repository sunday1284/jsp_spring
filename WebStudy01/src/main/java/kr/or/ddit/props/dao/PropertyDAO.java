package kr.or.ddit.props.dao;

import java.util.List;

import kr.or.ddit.props.vo.PropertyVO;

public interface PropertyDAO {
	public List<PropertyVO> selectPropertyList(); 
	public PropertyVO selectProperty(String propertyName);
}
