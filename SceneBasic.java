// Author: Chris Fietkiewicz, adapted by Ella Withington
import java.io.PrintWriter;
import java.net.Socket;

import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import javafx.application.Application; // For launch()

public abstract class SceneBasic {
	private Stage stage;
	private Scene scene;
	protected VBox root = new VBox();
	protected Button chatButton = new Button("Chat");

	public SceneBasic(String titleText) {
        Label message = new Label(titleText);
        message.setFont(new Font(40));
        root.getChildren().addAll(message);
        root.setAlignment(Pos.TOP_CENTER);
        //scene = new Scene(root, 450, 300);
		 scene = new Scene(root, 900, 700);
       
	}
	

	// Return reference to this scene
	public Scene getScene() {
        return scene;
	}
	
	// Log out
	
}
