package waddles.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import waddles.Waddles;

/**
 * Main application class for Waddle's GUI.
 */
public class Main extends Application {
    private final Waddles waddles = new Waddles();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane pane = fxmlLoader.load();
            Scene scene = new Scene(pane);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setWaddles(waddles); // inject the Waddles instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
