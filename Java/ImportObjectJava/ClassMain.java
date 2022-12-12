// import java.ClassFirst;
// import java.ClassSecond;

class testClass {
	public static void printfStr(string args) {
		System.out.println(args);
	}
}
class MainWindownClass {
	public static void main(String[] args) {
		testClass tester = new testClass();
		tester.printfStr("Hello");
	}
}