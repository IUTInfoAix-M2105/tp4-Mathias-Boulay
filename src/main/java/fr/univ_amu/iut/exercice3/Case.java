package fr.univ_amu.iut.exercice3;


import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

class Case extends Button {
    private int ligne;
    private int colonne;
    private Joueur possesseur = Joueur.PERSONNE;
    private ImageView imageView;

    public Case(int ligne, int colonne) {

        this.ligne = ligne;
        this.colonne = colonne;
        setPrefSize(50,50);
        setWidth(50);
        setHeight(50);
        setupImageView();
    }

    private void setupImageView(){
        imageView = new ImageView(possesseur.getImage());
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setPreserveRatio(true);
        setGraphic(imageView);
    }

    public Joueur getPossesseur() {
        return possesseur;
    }

    public void setPossesseur(Joueur possesseur) {
        this.possesseur = possesseur;
        setImage(possesseur.getImage());
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
