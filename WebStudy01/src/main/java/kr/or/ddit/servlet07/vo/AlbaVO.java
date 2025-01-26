package kr.or.ddit.servlet07.vo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

/**
 * 	java bean 규약에 따라 정의되어야 함.
 * 
 * 	VO(Value Object) : 레이어사이에서 데이터를 전달할 때, 하나의 객체 단위로 전달하기 위한 묶음 객체.
 *  DTO(Data Transfer Object) 
 *  Model
 *  Bean 
 */
public class AlbaVO implements Serializable{
	private String id;
	//직렬화 제외 ->transient
	private transient String password;
	private String name;
	private int age;
	private String birth;
	private String address;
	private String hp;
	private String gender;
	private String career;
	private String[] hobby;
	private String grade;
	private String email;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	public String[] getHobby() {
		return hobby;
	}
	public void setHobby(String[] hobby) {
		this.hobby = hobby;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlbaVO other = (AlbaVO) obj;
		return Objects.equals(id, other.id);
	}
	
	@Override
	public String toString() {
		return "AlbaVO [id=" + id + ", name=" + name + ", age=" + age + ", birth=" + birth + ", address=" + address
				+ ", hp=" + hp + ", gender=" + gender + ", career=" + career + ", hobby=" + Arrays.toString(hobby)
				+ ", grade=" + grade + ", email=" + email + "]";
	}
	
	
	
}
