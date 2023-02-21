public class TypeCasting {
    public static void main(String[] args) // Array input
    {
        String  var_Str = "This is string!";
        int     var_Int = 2147483000;
        float   var_Flo = 200.255f;
        char    var_Chr = 'x';
        boolean var_Bol = true;
        double  var_Dou = 200.255;

        // Int to another
        float   var_IntToFlo = (float)var_Int;
        char    var_IntToChr = (char)var_Int;
        //boolean var_IntToBol = (boolean)var_Int; // int cannot be converted to boolean
        double  var_IntToDou = (double)var_Int;
        System.out.println("var_IntToFlo = "+var_IntToFlo);
        System.out.println("var_IntToChr = "+var_IntToChr);
        // System.out.println("var_IntToBol = "+var_IntToBol);
        System.out.println("var_IntToDou = "+var_IntToDou);

        // Float to another
        int     var_FloToInt = (int)var_Flo;
        char    var_FloToChr = (char)var_Flo;
        // boolean var_FloToBol = (boolean)var_Flo; // float cannot be converted to boolean
        double  var_FloToDou = (double)var_Flo;
        System.out.println("var_FloToInt = "+var_FloToInt);
        System.out.println("var_FloToInt = "+var_FloToChr);
        // System.out.println("var_FloToChr = "+var_FloToBol);
        System.out.println("var_FloToChr = "+var_FloToDou);

        // Char to another
        int     var_ChrToInt = (int)var_Chr;
        float   var_ChrToFlo = (float)var_Chr;
        // boolean var_ChrToBol = (boolean)var_Chr; char cannot be converted to boolean
        double  var_ChrToDou = (double)var_Chr;
        System.out.println("var_ChrToInt = "+var_ChrToInt);
        System.out.println("var_ChrToFlo = "+var_ChrToFlo);
        // System.out.println("var_ChrToBol = "+var_ChrToBol);
        System.out.println("var_ChrToDou = "+var_ChrToDou);

        // Double to another
        int     var_DouToInt = (int)var_Dou;
        float   var_DouToFlo = (float)var_Dou;
        // boolean var_DouToBol = (boolean)var_Dou; // double cannot be converted to boolean
        char    var_DouToChr = (char)var_Dou;
        System.out.println("var_DouToInt = "+var_DouToInt);
        System.out.println("var_DouToFlo = "+var_DouToFlo);
        // System.out.println("var_DouToBol = "+var_DouToBol);
        System.out.println("var_DouToChr = "+var_DouToChr);
    }
}