package businessLogic;

import org.zeromq.ZMQ;


public class BusinessLogic {
	private static String inputResponsePort = "tcp://*:5555";
	private static String outputPublishPort = "tcp://*:5556"; 
	
	
	
	   public static void main(String[] args) throws Exception {
	   ZMQ.Context context = ZMQ.context(2);

	        //  Socket to talk to input
	        ZMQ.Socket responder = context.socket(ZMQ.REP);
	        responder.bind(inputResponsePort);
	        
	        // Socket to publish to output
	        ZMQ.Socket publisher = context.socket(ZMQ.PUB);
	        publisher.bind(outputPublishPort);

	        while (!Thread.currentThread().isInterrupted()) {
	            // Wait for next request from the input
	            byte[] request = responder.recv(0);
	            String requestString = new String(request);
	            System.out.println("Received: " + requestString);
	           //  Thread.sleep(1000);
	          
	            //send string from input to output
	            System.out.println("Sending to Output: " + requestString);
	            publisher.send(requestString);
	          
	            //re-send string to input to tell ui it's been sent *Not needed but necessary for REQ-REP sockets*
	            
	            System.out.println("Re-sending to input" + requestString);
	            
	            String reply = requestString + " Sent.";
	            responder.send(reply.getBytes(), 0);
	            // Do some 'work'
	           
	        }
	        responder.close();
	        publisher.close();
	        context.term();
	    }
	
	
	
	
	
	
	

}
