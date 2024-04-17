import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.ArrayList;

import static java.lang.System.exit;

public class turnoffcontroller {

    public static ObservableList<String> logs;

    @FXML
    private ListView<String> listView;

    @FXML
    private Button turnOffButton;

    @FXML
    private void initialize() {
        // TODO: Add initialization logic here
        listView = new ListView<>();
        listView.setPlaceholder(new Label("Server has been connected."));
        logs = FXCollections.observableArrayList();
        System.out.println("Initialize called for turnoff controller.");
    }

    @FXML
    private void onTurnOffClicked() {
        // TODO: Add logic to turn off here
        System.out.println("Turn off button clicked");

        exit(0);

    }

    @FXML
    public void displayLogsPressed() {
        new Thread(() -> {
            while (true) {
                System.out.println(logs);
                if (logs.size() > 0) {
                    System.out.println("There is an item for display.");
                    System.out.println(logs.get(0));
                    listView.getItems().add(logs.get(0));
                    //listView.refresh();
                }
                System.out.println("Thread Pulse.");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    break;
                }
            }
        }).start();
    }

    @FXML
    private void listViewEdit() {

    }
}
