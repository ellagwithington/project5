// Author: Chris Fietkiewicz. See description in P4 instructions. 
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.*;

public class StoreServer {

	private static int LISTENING_PORT = 32007;

    public static void main(String[] args) {
        if (args.length == 1)
        	LISTENING_PORT = Integer.parseInt(args[0]);
        else if (args.length > 1 )
            System.out.println("Usage:  java StoreServer <listening-port>");

        ServerSocket listener;  // Listens for incoming connections.
        try {
            listener = new ServerSocket(LISTENING_PORT);
            System.out.println("Listening on port " + LISTENING_PORT);
            // Client loop
            while (true) {
                // Accept next connection request and handle it.
                Socket client;      // For communication with the connecting program.
            	client = listener.accept(); 
                System.out.println("Connection from " + client.getInetAddress().toString() );
                System.out.println("Creating thread");
                StoreThread worker = new StoreThread(client);
                worker.start();
            }
        }
        catch (Exception e) {
            System.out.println("Sorry, the server has shut down.");
            System.out.println("Error:  " + e);
            return;
        }
    }  // end main()
} //end class
