package waddles.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import waddles.Waddles;
import waddles.WaddlesResult;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserProfile.png"));
    private final Image waddlesImage = new Image(this.getClass().getResourceAsStream("/images/WaddlesProfile.png"));
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Waddles waddles;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Waddles instance
     */
    public void setWaddles(Waddles waddles) {
        this.waddles = waddles;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Waddles's reply and then appends
     * them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        WaddlesResult response = waddles.getResponse(input);
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage),
                DialogBox.getWaddlesDialog(response.getMessage(), waddlesImage));
        userInput.clear();

        if (response.hasEnded()) {
            Platform.exit();
        }
    }
}
