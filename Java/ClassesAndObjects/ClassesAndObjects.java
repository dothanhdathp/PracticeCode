public class ClassAndObjects
{
    // Create local value in class
    int local_value = 0;
    final float PI = 3.14f;

    // constructor for class.
    ClassAndObjects()
    {
        local_value = 20;
    }

    ClassAndObjects(int value)
    {
        local_value = value;
    }

    public static void updateLocalValue(int new_value)
    {
        System.out.println(" -> Previous value is: "+local_value);
        System.out.println(" -> Update to value  : "+local_value);
        local_value = new_value;
    }

    public static void main(String[] args)
    {
        ClassAndObjects newObj1 = new ClassAndObjects();
        ClassAndObjects newObj2 = new ClassAndObjects(200);
        updateLocalValue(1996);
        System.out.println(newObj1.local_value);
        System.out.println(newObj2.local_value);
    } 
}