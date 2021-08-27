class MyCode
{
public static void main(String...args)
{

if(true)
{
int i=2077;
System.out.printf("Hello");
}

}
}

class inputTaker{public static void main(String...args) throws Exception{
try{System.setIn(new java.io.FileInputStream("inputFile.txt"));}catch(Exception e){}
MyCode.main(null);
}}