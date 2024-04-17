import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import static java.lang.Integer.parseInt;

public class welcome1controller {

    @FXML
    private Label titleLabel;

    @FXML
    private TextField ipTextField;

    @FXML
    private TextField portTextField;

    @FXML
    private Button connectButton;

    public void initialize() {
    }

    @FXML
    public void onConnectButtonClicked() {
        // This method is called when the Connect button is clicked
        // You can put your logic to connect to the server here

        try {
            client clientConnection = new client(message -> System.out.println(message), ipTextField.getText(),
                    parseInt(portTextField.getText()));

            Stage stage = (Stage) connectButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("gameboard.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            clientConnection.start();

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

}