import inboxclass.*;

class RefenceOrShadow
{
    static int x = 0;
    static int y = 1;

    void changeValue(int oldValue, int newValue) {
        oldValue = newValue;
    };

    public static void main(String[] args) {
        InBoxClass mInBoxClass = new InBoxClass();
        System.out.println("InBoxClass-Name: "+mInBoxClass.GetName());
    }
}