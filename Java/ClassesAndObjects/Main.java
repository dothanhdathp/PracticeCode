public class Main {
    public static void main(String args[]) {
        ClassAndObjects1 obj1 = new ClassAndObjects1();
        ClassAndObjects1 obj2 = obj1;

        obj1.local_value = 100;
        obj2.local_value = 200;

        System.out.println("obj1.local_value = " + obj1.local_value);
        System.out.println("obj2.local_value = " + obj2.local_value);
    }
}