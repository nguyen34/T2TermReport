package input;



//
//  Hello World client in Java
//  Connects REQ socket to tcp://localhost:5555
//  Sends "Hello" to server, expects "World" back
//

import org.zeromq.ZMQ;

public class HelloWorldClient {

    public static void main(String[] args) {
        ZMQ.Context context = ZMQ.context(1);

        //  Socket to talk to server
        System.out.println("Connecting to hello world server…");

        ZMQ.Socket publisher = context.socket(ZMQ.PUB);
        publisher.bind("tcp://*:5555");

        for (int requestNbr = 0; requestNbr != 10; requestNbr++) {
            String request = "Hello";
            System.out.println("Sending Hello");
            publisher.send(request.getBytes(), 0);
            
            request = "My name is Johnson";
            System.out.println("Sending Hello");
            publisher.send(request.getBytes(), 0);
            
            request = "How are you?";
            System.out.println("Sending Hello");
            publisher.send(request.getBytes(), 0);
         //   byte[] reply = requester.recv(0);
          //  System.out.println("Received " + new String(reply) + " " + requestNbr);
        }
        publisher.close();
        context.term();
    }
}

