package control;

public class JavaExecutor {
	public static String cmd1 = "java -cp /usr/local/share/java/zmq.jar:/usr/local/lib:. input/InputUI";

	public static void main(String[] args) throws Exception{
		
		
		Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"cd /home/johnson/git/T2TermReport/src/\"");
		
		
	}
	
	
	
	
}
