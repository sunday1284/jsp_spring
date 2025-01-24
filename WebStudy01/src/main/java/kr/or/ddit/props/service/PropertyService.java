package kr.or.ddit.props.service;

import java.util.List;

import kr.or.ddit.props.vo.PropertyVO;

// vo에 관한 서비스 interface 생성
public interface PropertyService {
	
	public List<PropertyVO> readPropertyList();
	
	public PropertyVO readProperty(String propertyName);
}
