package fr.univ_amu.iut.exercice3;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

import java.util.ArrayList;
import java.util.List;


public class Othellier extends GridPane {

    private static final Point2D[] directions = {
            new Point2D(1, 0),
            new Point2D(1, 1),
            new Point2D(0, 1),
            new Point2D(-1, 1),
            new Point2D(-1, 0),
            new Point2D(-1, -1),
            new Point2D(0, -1),
            new Point2D(1, -1)
    };
    private final EventHandler<ActionEvent> caseListener = event -> {
        throw new RuntimeException("Not yet implemented !");
    };
    private int taille;
    private Case[][] cases;
    private ObjectProperty<Joueur> joueurCourant = new SimpleObjectProperty<>(Joueur.NOIR);

    public Othellier(int taille) {
        this.taille = taille;
        cases = new Case[taille][taille];
        remplirOthelier(taille);
        adapterLesLignesEtColonnes();

        nouvellePartie();
    }

    public ObjectProperty<Joueur> joueurCourantProperty() {
        throw new RuntimeException("Not yet implemented !");
    }

    private void remplirOthelier(int taille) {
        for(int i=0; i<taille; ++i){
            for(int j=0; j<taille; ++j){
                cases[i][j] = new Case(i, j);
            }
        }
    }

    private void adapterLesLignesEtColonnes() {
        for (int i = 0; i < taille; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setHgrow(Priority.ALWAYS);
            getColumnConstraints().add(column);
        }

        for (int i = 0; i < taille; i++) {
            RowConstraints row = new RowConstraints();
            row.setVgrow(Priority.ALWAYS);
            getRowConstraints().add(row);
        }
    }

    private void positionnerPionsDebutPartie() {
        cases[3][3].setPossesseur(Joueur.BLANC);
        cases[4][4].setPossesseur(Joueur.BLANC);
        Joueur.BLANC.incrementerScore();
        Joueur.BLANC.incrementerScore();

        cases[3][4].setPossesseur(Joueur.NOIR);
        cases[4][3].setPossesseur(Joueur.NOIR);
        Joueur.NOIR.incrementerScore();
        Joueur.NOIR.incrementerScore();

    }


    public void nouvellePartie() {
        vider();
        joueurCourant.setValue(Joueur.NOIR);
        Joueur.initialiserScores();
        positionnerPionsDebutPartie();
    }

    private boolean peutJouer() {
        return casesJouables().size() !=0;
    }

    private List<Case> casesJouables() {
        ArrayList<Case> casesJouables = new ArrayList<>();
        for(Case[] row : cases) {
            for (Case cell : row) {
                if(estPositionJouable(cell)){
                    casesJouables.add(cell);
                }
            }
        }
        return casesJouables;
    }

    private List<Case> casesCapturable(Case caseSelectionnee) {
        List<Case> casesCapturable = new ArrayList<>();

        for (Point2D direction : directions) {
            casesCapturable.addAll(casesCapturable(caseSelectionnee, direction));
        }

        return casesCapturable;
    }

    private List<Case> casesCapturable(Case caseSelectionnee, Point2D direction) {
        List<Case> casesCapturable = new ArrayList<>();

        int indiceLigne = caseSelectionnee.getLigne() + (int) direction.getY();
        int indiceColonne = caseSelectionnee.getColonne() + (int) direction.getX();

        while (estIndicesValides(indiceLigne, indiceColonne)) {
            if (cases[indiceLigne][indiceColonne].getPossesseur() != joueurCourant.get().suivant())
                break;

            casesCapturable.add(cases[indiceLigne][indiceColonne]);

            indiceLigne += direction.getY();
            indiceColonne += direction.getX();
        }

        if (estIndicesValides(indiceLigne, indiceColonne) &&
                cases[indiceLigne][indiceColonne].getPossesseur() == joueurCourant.get())
            return casesCapturable;
        return new ArrayList<>();
    }

    private boolean estIndicesValides(int indiceLigne, int indiceColonne) {
        throw new RuntimeException("Not yet implemented !");
    }

    private boolean estIndiceValide(int indiceColonne) {
        throw new RuntimeException("Not yet implemented !");
    }

    private boolean estPositionJouable(Case caseSelectionnee) {
        return (caseSelectionnee.getPossesseur().equals(Joueur.PERSONNE)
                && casesCapturable(caseSelectionnee).size() != 0);
    }

    private void capturer(Case caseCapturee) {
        caseCapturee.setPossesseur(joueurCourant.get());
        joueurCourant.get().incrementerScore();
        joueurCourant.get().suivant().decrementerScore();

        for(Case cell : casesCapturable(caseCapturee)){
            capturer(cell);
        }
    }

    public Joueur getJoueurCourant() {
        return joueurCourant.get();
    }

    private void tourSuivant() {
        joueurCourant.set(joueurCourant.get().suivant());
        if(peutJouer()) return;
        joueurCourant.set(joueurCourant.get().suivant());
        if(peutJouer()) return;
        //Partie terminé
        joueurCourant.set(Joueur.PERSONNE);
    }

    private void vider() {
        for(int i=0; i < cases.length; ++i){
            for(int j=0; j < cases[i].length; ++j){
                cases[i][j].setPossesseur(Joueur.PERSONNE);
            }
        }
    }
}
