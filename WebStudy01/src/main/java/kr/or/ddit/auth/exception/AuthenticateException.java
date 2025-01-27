package kr.or.ddit.auth.exception;


/**
 * 예외 : 예상하지 못한 모든 비정상적인 상황.(Throwable)
 *  Error : 시스템 내부에서 발생한 치명적인 에러로 논리적인 로직으로 해결할 수 없는 오류(직접 처리 불가능). 
 *  Exception : 발생한 에러를 로직으로 해결할 수 있는 형태인 경우.
 *  	checked exception : 발생 가능한 코드가 있을 때, 예외 처리를 하지 않은 경우 컴파일 에러 발생.
 *  		ex) IOException, SQLException	
 *  	unChecked exception(RuntimeException) : 묵시적으로 모든 메소드에 throws 구문에 포함된 예외.
 *  		ex) NullPointerException, ArrayIndexOutOfBoundException
 *  	
 *  예외 처리 방법
 *  1.throws : 발생한 예외를 직접 처리하지 않고, 호출자에게 다시 던지는(throw) 구조.
 *  2.try~catch~finally 
 *  	try{
 *  		예외가 발생할 가능성이 있는 구문.
 *  	}catch(처리할 예외 명시){
 *  		예외 처리 구문.
 *  		1) 예외 전환 : 발생한 예외를 전혀 다른 형태의 예외로 바꿔서 다시 던지는 방식
 *  					checked exception 을 unchecked exception으로 전환할 때 활용.
 *  					ex) thorw new RuntimeException(e);
 *  		2) 예외 복구 
 *  	}finally{
 *  		예외 발생 여부에 무관하게 실행할 구문.
 *  	}
 *  
 *  커스텀 예외 정의
 *  1. 정의할 예외의 특성에 따라 Exception(Checked)/RuntimeException(UnChecked) 상속
 *  2. throw new 생성자 의 형태로 예외를 직접 호출자에게 throw(투과)함.
 */
public class AuthenticateException extends RuntimeException{

	public AuthenticateException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AuthenticateException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public AuthenticateException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public AuthenticateException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public AuthenticateException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
