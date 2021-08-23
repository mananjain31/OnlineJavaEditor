import java.io.*;
class delete
{
public static void main(String...args)
{
File folder = new File("C:\\Users\\pc\\Documents\\NetBeansProjects\\OnlineIDE\\IdeCodes");
for(File file: folder.listFiles())
{
if(file.getName().endsWith(".class") && !file.getName().equals("delete.class"))
{
file.delete();
}
}
}
}