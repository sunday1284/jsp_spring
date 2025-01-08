package kr.or.ddit;

public class HelloMaven {

	public static void main(String[] args) {
//		jvm option으로 message 옵션을 받고, 콘솔에 출력
		
		String message = System.getProperty("message");
		
		System.out.println(message);
	}

}
