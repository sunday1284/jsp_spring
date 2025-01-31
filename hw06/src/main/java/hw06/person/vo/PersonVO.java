package hw06.person.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Data
public class PersonVO implements Serializable{
	private transient String id;
	private String name;
	//성별 F or M
	private boolean gender;
	private int age;
	private String address;
}
