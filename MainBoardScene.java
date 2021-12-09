
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
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.control.*;
import javafx.scene.canvas.GraphicsContext;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

public class MainBoardScene extends SceneBasic {
   
    private int row = 5;
    private int column = 6;
    private final int FONT_SIZE = 20;
    private Label columnNum = new Label("Enter your column number:");
    private TextField columnField = new TextField();
    private Button button = new Button("Send");
    private Button quitButton = new Button("Quit");
    private Label errorMessage = new Label();
    private StackPane stack = new StackPane();
    private GridPane gridPane = new GridPane();
    private  boolean[][] piece = new boolean[6][7];; // 2D boolean array
    private Label userLabel2;
    private String hostName = "127.0.0.1";
	 private int LISTENING_PORT = 32007;
    private Socket connection;
    private Color myColor = Color.RED;
    private Color oppColor = Color.YELLOW;
     
    public MainBoardScene(){
        
        super("Connect 4");
        gridPane.setMinSize(400, 200); 
        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(button, quitButton);
        gridPane.add(buttonBox, 1, 2);
        errorMessage.setTextFill(Color.RED);
        gridPane.add(errorMessage, 1, 7);
        gridPane.setAlignment(Pos.TOP_CENTER);
        root.getChildren().addAll(gridPane);
        final BorderPane root = new BorderPane();    
        gridPane.setAlignment(Pos.CENTER);  
        gridPane.add(columnNum, 6, 5);
        gridPane.add(columnField, 6, 6);
        gridPane.add(button, 6, 7);
        gridPane.add(quitButton, 7, 8);
        button.setOnAction(e -> send());
        quitButton.setOnAction(e -> quit());
        errorMessage.setTextFill(Color.RED);
        
        for(int r = 0;r < row; r++){
            for(int c = 0; c < column; c++){
               
            Label userLabel2 = new Label(String.valueOf(c+1));
	        userLabel2.setFont(new Font(FONT_SIZE));
            gridPane.add(userLabel2, c, 7);
            Rectangle rect = new Rectangle(100,100);
            Circle circ = new Circle(47);
            circ.centerXProperty().set(50);
            circ.centerYProperty().set(50);
            Shape cell = Path.subtract(rect, circ);
            cell.setFill(Color.BLUE);
            cell.setStroke(Color.BLUE);

            gridPane.add(cell, c, r); 
 
            }

        }
             
            }
             
      
    
    private void dropPiece(int column){
       //StackPane stack = new StackPane();
        final Circle gamePiece = new Circle(40);
        gamePiece.setFill(myColor);
        int r = 0;
        while(r < 5){
        if (!piece[r][column]){
         gridPane.add(gamePiece, column -1, 4-r);
         gridPane.getChildren().remove(button);
         piece[r][column] = true;
         
         r = 5;
         }
         else{
          r++;  
         }
         
        }
    }
            
     private void opponentTurn(int column){
        final Circle gamePiece = new Circle(40);
        gamePiece.setFill(oppColor);
        int r = 0;
        while(r < 5){
        if (!piece[r][column]){
         gridPane.add(gamePiece, column -1, 4-r);
         gridPane.add(button, 6, 7);
         piece[r][column] = true;
        
         r = 5;
         }
         else{
          r++;  
         }
         
        } 
      }
      
      public void quit(){
       try {
			
           PrintWriter outgoing = new PrintWriter( SceneManager.getSocket().getOutputStream() );
            System.out.println("Sending Quit");
            outgoing.println("QUIT");
            outgoing.flush();
            SceneManager.setStartScene();
			}
		
	    catch (Exception e) {
	        System.out.println("Error:  " + e);
	    }
      }
      
      public void getColor(){
       
        try {
           
            Socket connection = SceneManager.getSocket(); // Server socket
	    	PrintWriter outgoing;   // Stream for sending data.
			outgoing = new PrintWriter( connection.getOutputStream() );
        // outgoing.println("MYCOLOR"); //UNCOMMENT to work with the server
       //  outgoing.flush();
          BufferedReader incoming = new BufferedReader( 
                    new InputStreamReader(connection.getInputStream()) );
            System.out.println("Waiting for result...");
            String reply;
           //  reply = incoming.readLine(); //UNCOMMENT to work with server
            reply = "BLUE"; // fake server response
             if (reply.equals("RED")){
               myColor = Color.RED;
            }
            if (reply.equals("BLUE")){
               myColor = Color.RED;
            }
            if (reply.equals("YELLOW")){
               myColor = Color.RED;
            }
            if (reply.equals("BLACK")){
               myColor = Color.RED;
            }
            if (reply.equals("GREEN")){
               myColor = Color.RED;
            }
           // outgoing.println("OPPCOLOR"); //UNCOMMENT to work with the server
           // outgoing.flush();
            String reply2;
            // reply2 = incoming.readLine(); //UNCOMMENT to work with server
            reply2 = "GREEN"; // fake server response 
             if (reply2.equals("RED")){
               oppColor = Color.RED;
            }
            if (reply2.equals("BLUE")){
               oppColor = Color.BLUE;
            }
            if (reply2.equals("YELLOW")){
               oppColor = Color.YELLOW;
            }
            if (reply2.equals("BLACK")){
               oppColor = Color.BLACK;
            }
            if (reply2.equals("GREEN")){
               oppColor = Color.GREEN;
            }
             
            
    }
    catch (Exception e) {
            System.out.println("Error:  " + e);
        }
    
      }
    
    
     public void send(){
      getColor();
      
        try {
           
            Socket connection = SceneManager.getSocket(); // Server socket
			
	    	PrintWriter outgoing;   // Stream for sending data.
			outgoing = new PrintWriter( connection.getOutputStream() );
			
			String move = columnField.getText();
			outgoing.println(move);
			outgoing.flush();  // Make sure the data is actually sent!
            System.out.println("Sent move"); // For debugging


            BufferedReader incoming = new BufferedReader( 
                    new InputStreamReader(connection.getInputStream()) );
            System.out.println("Waiting for result...");
            String reply = incoming.readLine();

            System.out.println(reply);
            if (reply.equals("SUCCESS")) {
            	errorMessage.setText("");
            	dropPiece(Integer.parseInt(move));
              opponentTurn((int)(Math.random() * 6) +1); //randomize move to simulate server (other player)
            }
            else if (reply.equals("FAIL")) {
            	errorMessage.setText("Please choose another move");
            }
            else
            	errorMessage.setText(reply);
		}
        catch (Exception e) {
            System.out.println("Error:  " + e);
        }
	}

} 