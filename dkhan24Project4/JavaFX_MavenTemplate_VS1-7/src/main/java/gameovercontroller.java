import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import static java.lang.System.console;
import static java.lang.System.exit;

public class gameovercontroller {

    private static String res;
    private static String resMon;

    @FXML
    public Label result;

    @FXML
    public Label resultmoney;

    @FXML
    private Button playagain;

    @FXML
    private Button exit;

    public void initialize() {
        // Initialize the controller here




    }


    public void playAgain() {
        // Initialize the controller here

        try {
            Stage stage = (Stage) playagain.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("welcome1.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public void exitGame() {
        // Initialize the controller here

        exit(0);

    }

    public static void setresult(String r) {


        res = r;


    }

    public static void setresultMoney(String rm) {


        resMon = rm;

    }
    public void revealResultPressed() {
        result.setText(res);
        resultmoney.setText(resMon);
    }


    // Define methods for handling button actions, if needed

}
