import java.util.*;
class MyCode
{
public static void main(String...args)
{
Scanner sc = new Scanner(System.in);
while(sc.hasNextLine())
{
System.out.println(sc.nextLine());
}
System.out.println("Heydasdasad There Coder");
}
}

class inputTaker{public static void main(String...args) throws Exception{
try{System.setIn(new java.io.FileInputStream("inputFile.txt"));}catch(Exception e){}
MyCode.main(null);
}}