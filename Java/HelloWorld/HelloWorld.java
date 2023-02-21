public class HelloWorld {
  public static int putNumber(int i)
  {
    System.out.println("You in put: " + i);
    return i/2;
  }

  public static String putText(String str)
  {
    System.out.println("You in put: " + str);
    return str;
  }

  public static void main(String[] args) {
    int x = putNumber(50);
    String y = putText("Oh, Magaa!");
    System.out.println("Hello World! "+x+''+y);
  }
}