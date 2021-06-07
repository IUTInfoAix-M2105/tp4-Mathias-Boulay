package fr.univ_amu.iut.exercice3;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;


public class OthelloController {
    @FXML
    private StatusBar statusBar;

    @FXML
    private Othellier othellier;

    @FXML
    void initialize() {
        othellier.joueurCourantProperty().addListener((observableValue, joueur, t1) -> {
            if(t1.equals(Joueur.PERSONNE)){
                afficheDialogFinDePartie();
            }
        });

        statusBar.joueurCourantProperty().bind(othellier.joueurCourantProperty());
    }

    void setStageAndSetupListeners(Stage stage) {
        stage.setOnCloseRequest(windowEvent -> {
            actionMenuJeuQuitter();
            windowEvent.consume();
        });
    }

    private void afficheDialogFinDePartie() {
        Alert alertDialog = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.OK);
        alertDialog.showAndWait();
    }

    @FXML
    public void actionMenuJeuQuitter() {
        Alert alertDialog = new Alert(Alert.AlertType.CONFIRMATION, "Voulez vous vraiment quitter ?", ButtonType.OK);
        Optional<ButtonType> buttonPressed = alertDialog.showAndWait();

        if (buttonPressed.isPresent() && buttonPressed.get() == ButtonType.OK){
            Platform.exit();
        }
    }

    @FXML
    public void actionMenuJeuNouveau() {
        Alert alertDialog = new Alert(Alert.AlertType.CONFIRMATION, "Voulez vous vraiment commencer une nouvelle partie ?", ButtonType.OK);
        Optional<ButtonType> buttonPressed = alertDialog.showAndWait();

        if (buttonPressed.isPresent() && buttonPressed.get() == ButtonType.OK){
            othellier.nouvellePartie();
        }
    }
}
