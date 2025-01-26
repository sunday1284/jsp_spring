package kr.or.ddit.Console;

public class PrintJVMOptionAndArgument {

	public static void main(String[] args) {
		String name = System.getProperty("username");
		String pass = System.getProperty("password");
		System.out.println("-----print in main method---");
		System.out.println(args[0]);
		System.out.println("---print in printJVMOption method-------");
		System.out.println("username : "+name);
		System.out.println("password : " +pass);
	}

}
