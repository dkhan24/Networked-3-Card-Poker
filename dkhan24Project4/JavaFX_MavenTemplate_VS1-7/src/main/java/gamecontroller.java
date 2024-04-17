import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import static java.lang.System.exit;

public class gamecontroller {

    static boolean wagersubmitted = false;

    public AnchorPane anchorPane;

    static String aWager;
    static String pPlusWager;
    static String pWager;

    static String pCard1;
    static String pCard2;
    static String pCard3;

    static String dCard1;
    static String dCard2;
    static String dCard3;


    static boolean choiceMade = false;
    static String choice = "";

    @FXML
    private ListView<String> Info;

    @FXML
    public TextField AnteWager;

    @FXML
    public TextField PairPlusWager;

    @FXML
    public TextField PlayWager;

    @FXML
    private MenuBar option;

    @FXML
    private MenuItem NewLook;

    @FXML
    private MenuItem FreshStart;

    @FXML
    private MenuItem exit;

    @FXML
    private Button play;

    @FXML
    private Button fold;

    @FXML
    public Label DealerCard1;

    @FXML
    public Label DealerCard2;

    @FXML
    public Label DealerCard3;

    @FXML
    public Label PlayerCard1;

    @FXML
    public Label PlayerCard2;

    @FXML
    public Label PlayerCard3;

    @FXML
    public void getNewLook() {
        // TODO: Implement new look option
        anchorPane.setStyle("-fx-background-image: url('/newBackground.jpg')");
        //Change image

    }

    @FXML
    public void reset() {
        // TODO: Implement fresh start option
        try {
            Stage stage = (Stage) play.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("welcome1.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public void submitWager() {

        //System.out.println("Submit Wager button clicked.");
        gamecontroller.wagersubmitted = true;

        System.out.println("Value of wagersubmitted: " + gamecontroller.wagersubmitted);
    }

    @FXML
    public void exitGame() {
        // TODO: Implement exit option
        exit(0);
    }

    @FXML
    public void play() {
        if (choice == "") {
            choice = "play";
            choiceMade = true;
        }

    }


    @FXML
    public void updateAnteWager () {
        System.out.println(AnteWager.getText());
        aWager = AnteWager.getText();
    }

    public void revealPlayerCardPressed () {
        PlayerCard1.setText(pCard1);
        PlayerCard2.setText(pCard2);
        PlayerCard3.setText(pCard3);
    }

    public void revealDealerCardPressed() {
        if (choice != "" && choiceMade != false) {
            DealerCard1.setText(dCard1);
            DealerCard2.setText(dCard2);
            DealerCard3.setText(dCard3);
        }
    }

    public void updatePlayWager () {
        System.out.println(PlayWager.getText());
        pWager = PlayWager.getText();
    }

    @FXML
    public void updatePairPlus() {
        System.out.println(PairPlusWager.getText());

        pPlusWager = PairPlusWager.getText();
    }

    @FXML
    public void onProceedClick() {
        if (choice != "" && choiceMade != false) {
            try {
                Stage stage = (Stage) play.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("gameover.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }


    @FXML
    public void fold() {
        if (choice == "") {
            choice = "fold";
            choiceMade = true;
        }
    }
}
