import java.util.*;
class Code
{
public static void main(String...args)
{
System.out.println("hola amigos!");
}
}

class JimCode
{
public static void main(String...args)
{
Scanner sc = new Scanner(System.in);
System.out.println("hola amigosaa!");
while(sc.hasNext())
System.out.println(sc.nextInt()*100);
}
}

class inputTaker{public static void main(String...args) throws Exception{
try{System.setIn(new java.io.FileInputStream("inputFile.txt"));}catch(Exception e){}
JimCode.main(null);
}}