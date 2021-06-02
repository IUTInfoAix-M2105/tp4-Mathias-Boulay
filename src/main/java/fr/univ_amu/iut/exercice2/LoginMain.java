package fr.univ_amu.iut.exercice2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginMain extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        // Create the Login custom control
        GridPane root = new LoginControl();
        Scene scene = new Scene(root);
        setupStyle(scene);
        stage.setScene(scene);
        stage.setTitle("FXMl Custom Control");
        stage.show();
    }

    private void setupStyle(Scene scene){
        scene.getStylesheets().add("/Login.css");
    }

}
