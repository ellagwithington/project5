 
/**
 *Program by Ella Withington
 * 
 */

import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.control.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;
public class StartScene extends SceneBasic {
//need to extend scene basic
//possibly make this look like store
//have a constructor so that scene manager can call this 
    
  
    private Button startButton = new Button("Start");
    private Button settingsButton = new Button("Settings");
    private Label errorMessage = new Label();
    private StackPane stack = new StackPane();
    private GridPane gridPane = new GridPane();
   private String hostName = "127.0.0.1";
	private int LISTENING_PORT = 32007;
    private Socket connection;
     
    public StartScene() {
        
        super("Connect 4");
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(400, 200); 
       // gridPane.setPadding(new Insets(10, 10, 10, 10)); 
        gridPane.setVgap(5); 
        gridPane.setHgap(5);       
       // gridPane.add(columnField, 0, 0);
        
        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(startButton, settingsButton);
        gridPane.add(buttonBox, 1, 2);
        errorMessage.setTextFill(Color.RED);
        gridPane.add(errorMessage, 1, 3);
        gridPane.setAlignment(Pos.TOP_CENTER);
        root.getChildren().addAll(gridPane);
        startButton.setOnAction(e -> startGame());
        settingsButton.setOnAction(e -> SceneManager.setSettingsScene());
    }
     

    //private void createBoard(final GridPane gridPane){
    //    
    //    
    //    
    //}
    
  // Main login connects to socket, sends signal and login info, and expects an account type as a String
	private void startGame() {
		// Socket connection
		System.out.println("Connection = " + connection); // For debugging
		connection = SceneManager.getSocket(); // Get current socket, in case it was set using SettingsScene
		try {
			if (connection == null) { // If no socket has been created...
				connection = new Socket(hostName, LISTENING_PORT);
				SceneManager.setSocket(connection); // Client socket
            SceneManager.setMainBoardScene();
			}
		}
	    catch (Exception e) {
	        System.out.println("Error:  " + e);
	    }


}
}