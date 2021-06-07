package fr.univ_amu.iut.exercice3;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.When;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class StatusBar extends BorderPane implements Initializable {
    private static final String MESSAGE_TOUR_NOIR = "Noir joue !";
    private static final String MESSAGE_TOUR_BLANC = "Blanc joue !";
    private static final String SCORE_NOIR = "Noir : ";
    private static final String SCORE_BLANC = "Blanc : ";
    private static final String MESSAGE_TOUR_FIN_PARTIE = "Partie Terminée";

    @FXML
    private Label messageScoreNoir;

    @FXML
    private Label messageScoreBlanc;

    @FXML
    private Label messageTourDeJeu;

    private ObjectProperty<Joueur> joueurCourant = new SimpleObjectProperty<>(Joueur.NOIR);

    public StatusBar() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/fr/univ_amu/iut/exercice3/StatusBarView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        createBinding();
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
    }

    private void createBinding() {
        messageScoreNoir.textProperty().bind(Bindings.format(SCORE_NOIR + "%d", Joueur.NOIR.scoreProperty()));
        messageScoreBlanc.textProperty().bind(Bindings.format(SCORE_BLANC + "%d", Joueur.BLANC.scoreProperty()));
        messageTourDeJeu.textProperty().bind(Bindings.when(Bindings.equal(Joueur.BLANC, joueurCourant)).then(MESSAGE_TOUR_BLANC).otherwise(MESSAGE_TOUR_NOIR));
    }

    public Joueur getJoueurCourant() {
        return joueurCourant.get();
    }

    public void setJoueurCourant(Joueur joueurCourant) {
        this.joueurCourant.setValue(joueurCourant);
    }

    public ObjectProperty<Joueur> joueurCourantProperty() {
        return joueurCourant;
    }


}
