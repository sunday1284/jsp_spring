package kr.or.ddit.commons;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.jupiter.api.Test;

import kr.or.ddit.servlet07.vo.AlbaVO;

class BeanUtilsTest {

	@Test
	void test() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
		Map<String, String[]> parameterMap = new HashMap<>();
		parameterMap.put("id", new String[]{"a001"});
		parameterMap.put("age", new String[]{"23"});
		parameterMap.put("hobby", new String[]{"coding", "programming"});
		
		AlbaVO vo = new AlbaVO();
		BeanUtils.populate(vo, parameterMap);
		
		System.out.println(vo);
		
		AlbaVO clone = (AlbaVO) BeanUtils.cloneBean(vo);
		
		System.out.println(clone);
		
		AlbaVO dest = new AlbaVO();
		
		BeanUtils.copyProperties(dest, clone);
		
		System.out.println(dest);
	}

}
