 
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
    private StackPane stack = new StackPane();
    private GridPane gridPane = new GridPane();
    private  boolean[][] piece = new boolean[6][7];; // 2D boolean array
    private boolean turn;
    private Label userLabel2;
   private String hostName = "127.0.0.1";
	private int LISTENING_PORT = 32007;
    private Socket connection;
    private Canvas canvas = new Canvas(800,700);
    private GraphicsContext g = canvas.getGraphicsContext2D();
     
    public MainBoardScene(){
        
        super("Connect 4");
        //GridPane gridPane = new GridPane();
        gridPane.setMinSize(400, 200); 
              
        
        
        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(button);
        gridPane.add(buttonBox, 1, 2);
        errorMessage.setTextFill(Color.RED);
        gridPane.add(errorMessage, 1, 7);
        gridPane.setAlignment(Pos.TOP_CENTER);
        root.getChildren().addAll(gridPane);
        final BorderPane root = new BorderPane();
//        
//      
//        
        gridPane.setAlignment(Pos.CENTER);
//         
//        
        final Circle gamePiece = new Circle(40);
//       
        gridPane.add(columnNum, 6, 5);
        gridPane.add(columnField, 6, 6);
        gridPane.add(button, 6, 7);
        button.setOnAction(e -> send());

            errorMessage.setTextFill(Color.RED);
        gamePiece.setFill(Color.RED);

    final Circle diskPreview = new Circle(40);
        diskPreview.setFill(Color.RED);
        for(int r = 0;r < row; r++){
            for(int c = 0; c < column; c++){
                 //need a t/f array to check where all of the circles are
//only need a column to drop
//number the columns
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


            //StackPane stack = new StackPane();

           // stack.getChildren().addAll(diskPreview, cell);
  g.setStroke(Color.RED);
             g.fillOval( 40, 40, 1, 1 );
            gridPane.add(cell, c, r); 
  // dropPiece(3);

            }

        }
           // dropPiece(1);  
            }
             
      
    
    private void dropPiece(int column){
       //StackPane stack = new StackPane();
        final Circle gamePiece = new Circle(40);
        gamePiece.setFill(Color.RED);
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
        gamePiece.setFill(Color.YELLOW);
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
    
    public void myTurn(){
      //method to ask server if its my turn yet
      //maybe literally take the button away or remove its functionality if its not my turn
      // set server response to turn boolean
    }
     public void send(){
      
      //TO DO:
      //clean this up and make the buffered reader and print writer part of params
         try {
			if (connection == null) { // If no socket has been created...
				connection = new Socket(hostName, LISTENING_PORT);
				SceneManager.setSocket(connection); // Client socket
			}
		}
	    catch (Exception e) {
	        System.out.println("Error:  " + e);
	    }
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

System.out.println(reply);
            if (reply.equals("SUCCESS")) {
            	errorMessage.setText("");
            	dropPiece(Integer.parseInt(move));
              opponentTurn(1);
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
