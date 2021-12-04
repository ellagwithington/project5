import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
    private SettingsScene settings;


    public void start(Stage stage) {
        settings = new SettingsScene();
        settings.setStage(stage);
        stage.setScene(settings.getScene());
        stage.setTitle("Alpha");
        stage.show();
    } // end start();

    public static void main(String[] args) {
        launch(args);  // Run this Application.
    } // end main();

} // end Store
