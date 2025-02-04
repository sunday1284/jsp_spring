package kr.or.ddit.person.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
//rest에서 응답 컨텐츠 표현을 위한 직렬화
public class PersonVO implements Serializable{
	private String id;
	private String name;
	private String gender;
	private int age;
	private String address;
}
