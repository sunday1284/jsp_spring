package kr.or.ddit.props.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
//property 하나만 비교할때 ->ex)propertyName
@EqualsAndHashCode(of = "propertyName")
@ToString(exclude = "description")
@Data
public class PropertyVO implements Serializable{
//	PROPERTY_NAME     VARCHAR2(128)  
	private String propertyName;
//	PROPERTY_VALUE    VARCHAR2(4000) 
	private String propertyValue;
//	DESCRIPTION       VARCHAR2(4000) 
	private String description;
}
