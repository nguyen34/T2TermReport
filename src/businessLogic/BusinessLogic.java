package businessLogic;

import org.zeromq.ZMQ;


public class BusinessLogic {
	private static String inputAddress = "ipc://input";
	private static String outputAddress = "ipc://output"; 
	
	
	
	   public static void main(String[] args) throws Exception {
	   ZMQ.Context context = ZMQ.context(2);

	        //  Socket to pull from input
	        ZMQ.Socket receiver = context.socket(ZMQ.PULL);
	        receiver.bind(inputAddress);
	        
	        // Socket to push to output
	        ZMQ.Socket sender = context.socket(ZMQ.PUB);
	        sender.bind(outputAddress);

	        while (!Thread.currentThread().isInterrupted()) {
	            // Wait for next request from the input
	            byte[] mathProblem = receiver.recv(0);
	            String mathProblemStr = new String(mathProblem);
	            
	            System.out.println("Equation Received: " + mathProblemStr);
	            
	            String[] equationArray = mathProblemStr.split(" ");
	            
	            String integer1 = equationArray[0];
	            String integer2 = equationArray[1];
	            String op = equationArray[2];
	            
	            
	            System.out.println("Integer 1 is: " + integer1);
	            System.out.println("Integer 2 is: " + integer2);
	            System.out.println("Operation is: " + op);
	            
	            int i1 = Integer.parseInt(integer1);
	            int i2 = Integer.parseInt(integer2);
	            
	            int result = 0;
	            if(op.equals("+")){
	            	result = i1 + i2;
	            }
	            else if(op.equals("-")){
	            	result = i1 - i2;
	            }
	            else if(op.equals("*")){
	            	result = i1 * i2;
	            }
	            else if(op.equals("/")){
	            	result = i1 / i2;
	            }
	            
	            System.out.println("Result: " + result);
	            
	            
	            String resultString = Integer.toString(result);
	           //  Thread.sleep(1000);
	          
	            //send string from input to output
	            System.out.println("Sending to Output: " + resultString);
	          sender.send(resultString, 0);
	           
	        }
	        receiver.close();
	        sender.close();
	        context.term();
	    }
	
	
	
	
	
	
	

}
