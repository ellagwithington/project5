// Author: Chris Fietkiewicz. See description in P4 instructions. 
import java.net.*;
import java.io.*;
import java.util.*;

public class StoreThread extends Thread {
    private Socket client;      // For communication with the connecting program.
   
    private BufferedReader incoming;
    private PrintWriter outgoing;   // Stream for sending data.

    public StoreThread(Socket client) {
    	this.client = client;
    }
    
    public void run() {
    	
        System.out.println("Connection from client " + client);
        // Client loop
        try {
            incoming = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outgoing = new PrintWriter( client.getOutputStream() );
            System.out.println("Waiting for request...");
            String request = incoming.readLine();
            System.out.println("Request: " + request);
            while (!request.equals("QUIT")) {
                if (request.equals("START")){
                    
                     SceneManager.setMainBoardScene();
                }
                else if(Integer.parseInt(request) > 0 && Integer.parseInt(request) < 7 ){
                    System.out.println("Sucessful move");
                    sendMove(outgoing, "SUCCESS");
                
                    }
                else{
                    System.out.println("Invalid move");
                    sendMove(outgoing, "FAIL");
                    }
    }
                request = incoming.readLine();
                System.out.println("Request: " + request); // For debugging
            
            System.out.println("Quitting"); // For debugging
            client.close();
        }
        catch (Exception e) {
            System.out.println("Sorry, the server has shut down.");
            System.out.println("Error:  " + e);
            return;
        }
    }  // end run()
    
    

    // Send inventory list to customer
    public void sendMove(PrintWriter outgoing, String message) {
  
	        outgoing.println(message);
            System.out.println("Sending:" + message);
		
        outgoing.println("DONE");
        outgoing.flush();
    }

    
    
} //end class
