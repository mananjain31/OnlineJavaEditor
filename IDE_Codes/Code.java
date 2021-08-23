class FirstProgram 
{
	public static void main(String...args)
	{
		System.out.print("This is my first Java Program");
	}
}

class inputTaker{public static void main(String...args) throws Exception{
try{System.setIn(new java.io.FileInputStream("inputFile.txt"));}catch(Exception e){}
FirstProgram.main(null);
}}