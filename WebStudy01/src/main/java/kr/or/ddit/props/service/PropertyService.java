package kr.or.ddit.props.service;

import java.util.List;

import kr.or.ddit.props.vo.PropertyVO;

public interface PropertyService {
	public List<PropertyVO> readPropertyList();
	public PropertyVO readProperty(String propertyName);
}
