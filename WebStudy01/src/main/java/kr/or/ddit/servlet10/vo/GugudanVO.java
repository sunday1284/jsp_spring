package kr.or.ddit.servlet10.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//@Getter
//@Setter
//@EqualsAndHashCode
//@ToString
@Data
public class GugudanVO implements Serializable{
	private int minDan;
	private int maxDan;
	private int propNum;
	
	
}
