// Author: Chris Fietkiewicz. See description in P4 instructions. 
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

public class Store extends Application {
	private SceneManager sceneManager;
		private Socket connection;
//	private String hostName = "localhost";
	

    public void start(Stage stage) {
		
    	sceneManager = new SceneManager();
    	sceneManager.setStage(stage);
		SceneManager.setStartScene();
        stage.setTitle("Store Application");
        stage.show();
    } // end start();
    
    // Notifies server that GUI is terminating. Executed when user quits.
    public void stop() {
    	try {
            System.out.println("Quitting");
        	Socket connection = sceneManager.getSocket();
        	if (connection == null)
    			connection = new Socket("localhost", 32007);
	    	PrintWriter outgoing;   // Stream for sending data.
			outgoing = new PrintWriter( connection.getOutputStream() );
		
			outgoing.println("QUIT");
			outgoing.flush();
			connection.close();
    	}
        catch (Exception e) {
            System.out.println("Error:  " + e);
        }
    }

    public static void main(String[] args) {
        launch(args);  // Run this Application.
    }
}