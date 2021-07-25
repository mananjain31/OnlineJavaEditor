class Hello
{
public static void main(String...args)
{
System.out.println("Manan JAin Hello");
}
}

class inputTaker{public static void main(String...args) throws Exception{
try{System.setIn(new java.io.FileInputStream("inputFile.txt"));}catch(Exception e){}
Hello.main(null);
}}