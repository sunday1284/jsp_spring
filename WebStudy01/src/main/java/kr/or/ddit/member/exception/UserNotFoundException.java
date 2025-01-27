package kr.or.ddit.member.exception;

/**
 * PK 로 검색하고 있는 회원이 존재하지 않을 경우 발생할 예외 
 */
public class UserNotFoundException extends RuntimeException{

	public UserNotFoundException(String memId) {
		super(String.format("%s 회원이 존재하지 않습니다.", memId));
	}
	
}
