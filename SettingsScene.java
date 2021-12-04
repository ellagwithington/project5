import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane; 
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;

public class SettingsScene{
    private static Stage stage;
    private Scene scene;
    protected VBox root = new VBox();
	private Label you = new Label("Color of your Disk");
    private TextField color1;
	private Label opponent = new Label("Color of opponent's Disk");
	private TextField color2;
	private Button saveButton = new Button("Save");
	private Button cancelButton = new Button("Cancel");
	private Label errorMessage = new Label();


	public SettingsScene() {
        Label message = new Label("Settings");
        message.setFont(new Font(40));
        root.getChildren().addAll(message);
        root.setAlignment(Pos.TOP_CENTER);
        scene = new Scene(root, 450, 250);

        //Reading XML file
            try {
                BufferedReader reader = new BufferedReader(new FileReader("Settings.xml")); // For reading the file.
                String line = reader.readLine();

                // Read lines until end of file.
                while (line != null) {
                    // Check if line equals color1.
                    if (line.trim().equals("<color1>")) {
                        color1 = new TextField(reader.readLine().trim());
                    }

                    // Check if line equals username.
                    else if (line.trim().equals("<color2>")) {
                        color2 = new TextField(reader.readLine().trim());
                    }
                    line = reader.readLine();
                }

                // Close reader.
                reader.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        //Creating Grid Pane 
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(300, 200);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.add(you, 0, 0);
        gridPane.add(color1, 1, 0);
        gridPane.add(opponent, 0,1);
        gridPane.add(color2, 1, 1);
        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(saveButton, cancelButton);
        gridPane.add(buttonBox, 1, 2);
        errorMessage.setTextFill(Color.RED);
        gridPane.add(errorMessage, 1, 3);
        gridPane.setAlignment(Pos.TOP_CENTER);
        root.getChildren().addAll(gridPane);
	}

    public Scene getScene() {
        return scene;
    }

    public void setStage(Stage stage) {
        SettingsScene.stage = stage;
    }
}
