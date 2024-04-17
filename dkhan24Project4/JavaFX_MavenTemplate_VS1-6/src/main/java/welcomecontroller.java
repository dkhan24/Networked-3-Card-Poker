import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.awt.*;
import java.util.HashMap;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.io.*;



public class welcomecontroller {
    @FXML
    private Button startbutton;


    @FXML
    private TextField portTextField;

    Server serverConnection;

    @FXML
    private void initialize() {
        // TODO: Add initialization logic here

    }

    @FXML
    private void onStartServerClicked() throws Exception {

        try {
            serverConnection = new Server(data -> {
                Platform.runLater(() -> {
                    System.out.println(data);

                });
            });
            System.out.println("Server has been created.");

            Stage stage = (Stage) startbutton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("turnoff.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();

        }
            System.out.println("Start server button clicked");

        }
    }
