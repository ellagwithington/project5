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
                else if(Integer.parseInt(request) > 0 && Integer.parseInt(request) < 6 ){
                    System.out.println("Sucessful move");
                    sendMove(outgoing, "SUCESS");
                
                    }
                else{
                    System.out.println("Invalid move");
                    sendMove(outgoing, "FAIL");
                    }
    
                request = incoming.readLine();
            }
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
    
    public void sendPosition(PrintWriter outgoing,int num) {
    	if(num<0 || num >6) {
    		System.out.println("the column that you enter dont exist"); 
    	}else {
    		outgoing.println(num); 
    		outgoing.flush(); 
    	}
    	
   }
    
    public void sendColor(PrintWriter outgoing, String clientMessage) {
    	File file = new File("Settings.xml"); 
    	Scanner inp = null; 
    	String userColor = ""; 
    	String opponentColor = ""; 
    	
    	try {
    		inp = new Scanner(file); 
    	}catch(Exception e) {
    		System.out.println("Couldnt find file"); 
    	}
    	
    	while(inp.hasNextLine()) {
    		String message =""; 
    		message = inp.nextLine().trim();
       	 
        	if(message.startsWith("<color1>")) {
        		userColor= inp.next(); 
        	}
        	if(message.startsWith("<color2>")) {
        		opponentColor = inp.next();
        	}

    	}
    	
    	if(clientMessage.equals("Color")){
    	outgoing.println("Your color is:" + userColor); 
    	outgoing.println("Your opponentColor is:"+ opponentColor);
    	outgoing.flush();
    	}
    	
    	
    }
    
    public void printMove(PrintWriter outgoing, BufferedReader in) {
 	   try {
 		   in = new BufferedReader(new InputStreamReader(client.getInputStream())); 
 		   outgoing = new PrintWriter(client.getOutputStream()); 
 		   int column = in.read();
 		 
 		   while(column > 0 && column <= 6) {
 			  outgoing.println("Putting piece in column:" + column);
 		 }
 		   
 	   }catch(Exception e) {
 		   System.out.println("Error ... Couldnt connect"); 
 	   }
 	   
    }

    
    
} //end class
