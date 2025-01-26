package kr.or.ddit.props.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@NoArgsConstructor	// 인자가 없는 생성자 생성 -> ex) new PropertyVO() 구조 
@AllArgsConstructor // 모든 인자로 받는 생성자를 생성 -> ex new PropertyVO(String arg1 String arg2..) 구조  
//property 하나만 비교할때 ->ex)propertyName
@EqualsAndHashCode(of = "propertyName") // -> 이 값만 HashCode.equls로 설정한다.
@ToString(exclude = "description") // -> toString에서 배제시켜 제외한다.
@Data
public class PropertyVO implements Serializable{
//	PROPERTY_NAME     VARCHAR2(128)  
	private String propertyName;
//	PROPERTY_VALUE    VARCHAR2(4000) 
	private String propertyValue;
//	DESCRIPTION       VARCHAR2(4000) 
	private String description;
}
