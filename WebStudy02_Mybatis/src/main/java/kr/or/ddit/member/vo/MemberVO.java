package kr.or.ddit.member.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 회원관리를 위한 domain layer 
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"memPass","memRegno1","memRegno2"})
@EqualsAndHashCode(of = "memId")
@Data
public class MemberVO implements Serializable{
	//일련번호 
	private int rnum;
	
	private String memId;
	private transient String memPass;
	private String memName;
	private transient String memRegno1;
	private transient String memRegno2;
	private String memBir;
	private String memZip;
	private String memAdd1;
	private String memAdd2;
	private String memHometel;
	private String memComtel;
	private String memHp;
	private String memMail;
	private String memJob;
	private String memLike;
	private String memMemorial;
	private String memMemorialday;
	private Long memMileage;
	private boolean memDelete;
}
