package kr.or.ddit.streaming.vo;

import java.io.Serializable;
import java.util.Objects;

/**
 *  하나의 파일에 대한 경로와 특정 위치를 기준으로 한 상대경로를 가진 객체 
 *  VO (DTO, Model, Bean)
 *  JavaBean 규약 
 *  1. 값을 저장할 수 있는 프로퍼티 정의 
 *  2. 프러퍼티에 대한 캡슐화 
 *  3. 캡슐화된 프로퍼티에 접근 가능한 인터페이스 메소드 정의(getter/setter)
 *  	get[set]프로퍼티명 -> camel 표기법 
 *  4. 상태를 비교할 수 있는 메소드 정의(equals)
 *  5. 상태를 확인할 수 있는 메소드 정의(toString)
 *  6. 직렬화
 */
public class FileVO implements Serializable{
	private String realtivePath;
	private String filename;
	private String mine;
	
	public String getRealtivePath() {
		return realtivePath;
	}
	public void setRealtivePath(String realtivePath) {
		this.realtivePath = realtivePath;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getMine() {
		return mine;
	}
	public void setMine(String mine) {
		this.mine = mine;
	}
	@Override
	public int hashCode() {
		return Objects.hash(realtivePath);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileVO other = (FileVO) obj;
		return Objects.equals(realtivePath, other.realtivePath);
	}
	
	@Override
	public String toString() {
		return "FileVO [realtivePath=" + realtivePath + ", filename=" + filename + ", mine=" + mine + "]";
	}
	
	
	
	
	
}
