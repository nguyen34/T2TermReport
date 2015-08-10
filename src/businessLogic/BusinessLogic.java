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
	            byte[] integer1 = receiver.recv(0);
	            String string1 = new String(integer1);
	            
	            System.out.println("Integer 1 Received: " + string1);
	            
	            byte[] integer2 = receiver.recv(0);
	            String string2 = new String(integer2);
	            
	            System.out.println("Integer 2 Received: " + string2);
	            
	            
	            byte[] op = receiver.recv(0);
	            String opString = new String(op);
	            
	            System.out.println("Operation Sign Received: " + opString);
	            
	            
	            int i1 = Integer.parseInt(string1);
	            int i2 = Integer.parseInt(string2);
	            
	            int result = 0;
	            if(opString.equals("+")){
	            	result = i1 + i2;
	            }
	            else if(opString.equals("-")){
	            	result = i1 - i2;
	            }
	            else if(opString.equals("*")){
	            	result = i1 * i2;
	            }
	            else if(opString.equals("/")){
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
