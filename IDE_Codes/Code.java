class Code
{
public static void main(String...args)
{
System.out.println("Hey Coder, hi");
}
}
class inputTaker{public static void main(String...args) throws Exception{
try{System.setIn(new java.io.FileInputStream("inputFile.txt"));}catch(Exception e){}
Code.main(null);
}}