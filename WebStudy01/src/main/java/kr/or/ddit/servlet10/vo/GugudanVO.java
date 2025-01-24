package kr.or.ddit.servlet10.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Getter
//@Setter
//@EqualsAndHashCode
//@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GugudanVO implements Serializable{
	private int minDan;
	private int maxDan;
	
	
}
