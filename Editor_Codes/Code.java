import java.util.*;
class MyCode
{
public static void main(String...args)
{
Scanner sc = new Scanner(System.in);
System.out.println("Hey There " + sc.next());
}
}

class inputTaker{public static void main(String...args) throws Exception{
try{System.setIn(new java.io.FileInputStream("inputFile.txt"));}catch(Exception e){}
MyCode.main(null);
}}