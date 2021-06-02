package fr.univ_amu.iut.exercice3;


import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

class Case extends Button {
    private int ligne;
    private int colonne;
    private Joueur possesseur;
    private ImageView imageView;

    public Case(int ligne, int colonne) {
        setupImageView();
        this.ligne = ligne;
        this.colonne = colonne;
        setPrefSize(50,50);
    }

    private void setupImageView(){
        imageView = new ImageView();
        imageView.setFitWidth(50);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);
    }

    public Joueur getPossesseur() {
        return possesseur;
    }

    public void setPossesseur(Joueur possesseur) {
        this.possesseur = possesseur;
    }

    public void setImage(Image image){
        imageView.setImage(image);
    }

    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }
}
