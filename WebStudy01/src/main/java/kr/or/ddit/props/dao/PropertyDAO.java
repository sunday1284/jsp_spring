package kr.or.ddit.props.dao;

import java.util.List;

import kr.or.ddit.props.vo.PropertyVO;

//다오임플에서 쓸 인터페이스를 정의함 
public interface PropertyDAO {
	public List<PropertyVO> selectPropertyList(); 
	public PropertyVO selectProperty(String propertyName);
}
