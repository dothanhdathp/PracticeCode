class JavaClass
{

    public static void main(String[] args) {
        MyClass myclass = new MyClass();
    	myclass.setList(1000);
        String[] list = myclass.getList();
        for(String i : list) {
            System.out.print(i+", ");
        }
        System.out.println("");
    }
}
