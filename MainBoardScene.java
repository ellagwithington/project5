 
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
public class MainBoardScene extends SceneBasic {
//need to extend scene basic
//possibly make this look like store
//have a constructor so that scene manager can call this 
    
    private int row = 5;
    private int column = 6;
    private final int FONT_SIZE = 20;
    private Label columnNum = new Label("Enter your column number:");
    private TextField columnField = new TextField();
    private Button button = new Button("Send");
    private Label errorMessage = new Label();
    final StackPane stack = new StackPane();
    final GridPane gridPane = new GridPane();
   
     
    public MainBoardScene(){
        super("Connect 4"); 
        final BorderPane root = new BorderPane();
        
        
      
        
         
        Scene scene = new Scene(root, 800, 600, true);
        scene.setFill(Color.WHITE);
        
        gridPane.setAlignment(Pos.CENTER);
         
        
        createBoard(gridPane);
        dropPiece(1);
        root.setCenter(gridPane);
       
    }
     

    private void createBoard(final GridPane gridPane){
        final Circle gamePiece = new Circle(40);
       
        gridPane.add(columnNum, 6, 5);
        gridPane.add(columnField, 6, 6);
        gridPane.add(button, 6, 7);
        //button.setOnAction(e -> send());
        gamePiece.setFill(Color.RED);
        for(int r = 1;r < row +1; r++){
            for(int c = 0; c < column; c++){
                 //need a t/f array to check where all of the circles are
//only need a column to drop
//number the columns
            Label userLabel2 = new Label(String.valueOf(c));
	        userLabel2.setFont(new Font(FONT_SIZE));
            gridPane.add(userLabel2, c, 0);
             errorMessage.setTextFill(Color.RED);
             gridPane.add(errorMessage, 5, 6);
            Rectangle rect = new Rectangle(100,100);
            Circle circ = new Circle(47);
            circ.centerXProperty().set(50);
            circ.centerYProperty().set(50);
            Shape cell = Path.subtract(rect, circ);
            cell.setFill(Color.BLUE);
            cell.setStroke(Color.BLUE);
            
          
           
             
            stack.getChildren().addAll(gamePiece, cell);
             
            gridPane.add(stack, c, r); 
             
              
            }
             
        }
        
        
    }
    
    private void dropPiece(int column){
        final Circle gamePiece = new Circle(40);
        gamePiece.setFill(Color.RED);
        stack.getChildren().addAll(gamePiece);
             
            gridPane.add(stack, column, 5); 
    }
     public void send(){
        try {
            Socket connection = SceneManager.getSocket(); // Server socket
			//Socket connection = SceneManager.getSocket(); // Server socket
	    	PrintWriter outgoing;   // Stream for sending data.
			outgoing = new PrintWriter( connection.getOutputStream() );
			
			String move = columnField.getText();
			outgoing.println(move);
			outgoing.flush();  // Make sure the data is actually sent!
            System.out.println("Sent move"); // For debugging
//            outgoing.close();

            BufferedReader incoming = new BufferedReader( 
                    new InputStreamReader(connection.getInputStream()) );
            System.out.println("Waiting for result...");
            String reply = incoming.readLine();
//            incoming.close();
            if (reply.equals("SUCESS")) {
            	errorMessage.setText("");
            	dropPiece(Integer.parseInt(move));
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
