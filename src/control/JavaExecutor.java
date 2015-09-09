package control;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class JavaExecutor {
	
	public static final int MAX_LEN = 255;
	
	public static void main(String[] args) throws Exception{
		 
		
		Process p = Runtime.getRuntime().exec(new String[]{"bash", "-c", "export LD_LIBRARY_PATH=/usr/local/lib/ && export JAVA_LIBRARY_PATH=/usr/local/lib/ && cd /home/johnson/git/T2TermReport/src/ && java -cp /usr/local/share/java/zmq.jar:/usr/local/lib:. input/InputUI"});
		Process q = Runtime.getRuntime().exec(new String[]{"bash", "-c", "export LD_LIBRARY_PATH=/usr/local/lib/ && export JAVA_LIBRARY_PATH=/usr/local/lib/ && cd /home/johnson/git/T2TermReport/src/ && java -cp /usr/local/share/java/zmq.jar:/usr/local/lib:. businessLogic/BusinessLogic"});
		Process r = Runtime.getRuntime().exec(new String[]{"bash", "-c", "export LD_LIBRARY_PATH=/usr/local/lib/ && export JAVA_LIBRARY_PATH=/usr/local/lib/ && cd /home/johnson/git/T2TermReport/src/ && java -cp /usr/local/share/java/zmq.jar:/usr/local/lib:. output/OutputUI"});
		
		
		
		
		String command = "";
		int len = 1;
		while (!command.equals("kill")){
	
		System.out.println("Java Calculator Components Executed. To terminate processes, type in 'kill' and press [Enter]");
		System.out.print("> ");
		byte cmd[] = new byte[MAX_LEN];
		len = System.in.read(cmd);
		
		if (len <= 0)
			break;
		command = new String(cmd);
		command = command.trim();
	    command = command.toLowerCase();
		if (command.equals("kill")){
			p.destroy();
			q.destroy();
			r.destroy();
		} else{
			System.out.println("Invalid Command");
		}
		
		}
		
		
		 }
		 
		 
		
	}
	
	
	
	

