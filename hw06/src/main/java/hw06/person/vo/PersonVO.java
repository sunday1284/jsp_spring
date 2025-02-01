package hw06.person.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/*비동기로 처리할 때 주의 사항 -> transient로 직렬화를 제외시키면 
 * 해당 값이 파싱될때 파싱된 데이터에서 해당 값이 안뜨는 현상이 발생해서
 * 비동기 처리시에는 transient를 사용하지 않아야 한다.. -> 이걸로 삽질함 
*/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonVO implements Serializable{
	private String id;
	private String name;
	//성별 F or M
	private String gender;
	private int age;
	private String address;
}
