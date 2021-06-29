import java.util.*;

class Code
{
public static void main(String...args)
{
Scanner sc = new Scanner(System.in);
System.out.println(sc.nextInt());
System.out.println("Hey Coder");
}
}
class inputTaker{public static void main(String...args) throws Exception{
try{System.setIn(new java.io.FileInputStream("inputFile.txt"));}catch(Exception e){}
Code.main(null);
}}