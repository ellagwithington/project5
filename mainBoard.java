 
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
import javafx.scene.text.Font;
import javafx.scene.control.*;
public class mainBoard extends Application {

     
    private int row = 5;
    private int column = 6;
    private final int FONT_SIZE = 20;
     
    public static void main(String[] args) {
        launch(args);
    }
     
    public void start(Stage primaryStage) {
         
        final BorderPane root = new BorderPane();
        final GridPane gridpane = new GridPane();
        primaryStage.setTitle("Connect Four");
        
         
        Scene scene = new Scene(root, 800, 600, true);
        scene.setFill(Color.WHITE);
        
        gridpane.setAlignment(Pos.CENTER);
         
        
        createBoard(gridpane);
       // dropPiece();
        root.setCenter(gridpane);
        primaryStage.setScene(scene);
        
        primaryStage.show();
    }
     

    private void createBoard(final GridPane gridpane){
        final Circle gamePiece = new Circle(40);
        gamePiece.setFill(Color.RED);
        for(int r = 1;r < row +1; r++){
            for(int c = 0; c < column; c++){
                 //need a t/f array to check where all of the circles are
//only need a column to drop
//number the columns
            Label userLabel2 = new Label(String.valueOf(c));
	        userLabel2.setFont(new Font(FONT_SIZE));
	        gridpane.add(userLabel2, c, 0);
            Rectangle rect = new Rectangle(100,100);
            Circle circ = new Circle(47);
            circ.centerXProperty().set(50);
            circ.centerYProperty().set(50);
            Shape cell = Path.subtract(rect, circ);
            cell.setFill(Color.BLUE);
            cell.setStroke(Color.BLUE);
            
          
            StackPane stack = new StackPane();
             
            stack.getChildren().addAll(gamePiece, cell);
             
            gridpane.add(stack, c, r); 
             
              
            }
             
        }
        
        
    }
    
    private void dropPiece(int column){
        final Circle diskPreview = new Circle(40);
        diskPreview.setFill(Color.RED);
        
    }
} 
