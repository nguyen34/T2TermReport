package control;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class JavaExecutor {
	public static void main(String[] args) throws Exception{
		 
		
		Process p = Runtime.getRuntime().exec(new String[]{"bash", "-c", "export LD_LIBRARY_PATH=/usr/local/lib/ && export JAVA_LIBRARY_PATH=/usr/local/lib/ && cd /home/johnson/git/T2TermReport/src/ && java -cp /usr/local/share/java/zmq.jar:/usr/local/lib:. input/InputUI"});
		Process q = Runtime.getRuntime().exec(new String[]{"bash", "-c", "export LD_LIBRARY_PATH=/usr/local/lib/ && export JAVA_LIBRARY_PATH=/usr/local/lib/ && cd /home/johnson/git/T2TermReport/src/ && java -cp /usr/local/share/java/zmq.jar:/usr/local/lib:. businessLogic/BusinessLogic"});
		Process r = Runtime.getRuntime().exec(new String[]{"bash", "-c", "export LD_LIBRARY_PATH=/usr/local/lib/ && export JAVA_LIBRARY_PATH=/usr/local/lib/ && cd /home/johnson/git/T2TermReport/src/ && java -cp /usr/local/share/java/zmq.jar:/usr/local/lib:. output/OutputUI"});
		
		while (!Thread.currentThread().isInterrupted()) {
		BufferedReader pReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line = "";
		while ((line = pReader.readLine()) != null){
			System.out.println(line);	
		}
		
		BufferedReader qReader = new BufferedReader(new InputStreamReader(q.getInputStream()));
		line = "";
		while ((line = qReader.readLine()) != null){
			System.out.println(line);
		}
		BufferedReader rReader = new BufferedReader(new InputStreamReader(r.getInputStream()));
		line = "";
		while ((line = rReader.readLine()) != null){
			System.out.println(line);
		}
		
		
		 }
		 
		 
		
	}
	
	
	
	
}
