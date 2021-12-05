
// Author: Chris Fietkiewicz. For Project #3.
// Description: Manages changes from one scene to another scene.
import java.net.Socket;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Pos;

import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane; 
import javafx.geometry.Insets; 
import java.util.*;
import javafx.scene.paint.Color;
import java.net.*;
import java.io.*;

public class SceneManager {
    private static Socket connection; // Socket connection to server
	private static Stage stage; // Stage used for all scenes
	

    private static SettingsScene settingsScene; // Allows user to change Socket host and port number
	private static MainBoardScene mainBoardScene; // Allows user to play the game
	private static StartScene startScene;
//	
//	
//	
//
// Constructor
	public SceneManager() {
		mainBoardScene = new MainBoardScene(); 
		settingsScene = new SettingsScene();
		startScene = new StartScene();
		
	}
	
	// Set socket connection to server
	public static void setSocket(Socket setConnection) {
		connection = setConnection;
	}

	// Get socket connection to server
	public static Socket getSocket() {
		return connection;
	}

	// Set initial stage to be used by all scenes
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	// Change view to the main board
	public static void setStartScene() {
		stage.setScene(startScene.getScene());
	}
	
	
	// Change view to the main board
	public static void setMainBoardScene() {
		stage.setScene(mainBoardScene.getScene());
	}
	
	// Change view to SettingsScene
	public static void setSettingsScene() {
		stage.setScene(settingsScene.getScene());
	}
	
}

