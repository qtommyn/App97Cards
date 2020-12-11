package com.tommy.nguyen.app97cards;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Partie {

    int nombreDeCartes = 97;
    boolean finDeLaPartie = false;
    List<Integer> listeDeCartes;

    public boolean isFinDeLaPartie() {
        return finDeLaPartie;
    }

    public void setFinDeLaPartie(boolean finDeLaPartie) {
        this.finDeLaPartie = finDeLaPartie;
    }

    List<Integer> listeCartesJoueur, listeCartesCroissantes, listeCartesDecroissantes;

    public Partie() {
        listeDeCartes = new ArrayList<>();
        listeCartesJoueur = new ArrayList<>();
        listeCartesCroissantes = new ArrayList<>();
        listeCartesDecroissantes = new ArrayList<>();

        for (int i = 1; i <= nombreDeCartes; i++)
            listeDeCartes.add(i);

        // https://docs.oracle.com/javase/6/docs/api/java/util/Collections.html
        Collections.shuffle(listeDeCartes);
    }

    // Au debut du jeu, placer une carte au hasard pour le joueur
    public void placerCarteAleatoire(LinearLayout carte) {
        ((TextView) carte.getChildAt(0)).setText(Integer.toString(listeDeCartes.remove(0)));
    }

    // Verifie si le joueur peut placer sa carte sur une des piles croissantes
    public boolean validerPileCroissante(int carteSelectionne, int carteSurLaPile) {
        boolean plusGrandQuePile = carteSelectionne > carteSurLaPile;
        boolean moinsDe10 = carteSelectionne == carteSurLaPile - 10;
        if (plusGrandQuePile || moinsDe10) {
            listeDeCartes.remove(Integer.valueOf(carteSelectionne));
            nombreDeCartes = nombreDeCartes - 1;
            return true;
        }
        else {
            return false;
        }
    }

    // Verifie si le joueur peut placer sa carte sur une des piles decroissantes
    public boolean validerPileDecroissante(int carteSelectionne, int carteSurLaPile) {
        boolean plusPetitQuePile = carteSelectionne < carteSurLaPile;
        boolean plusDe10 = carteSelectionne == carteSurLaPile + 10;
        if (plusPetitQuePile || plusDe10) {
            listeDeCartes.remove(Integer.valueOf(carteSelectionne));
            nombreDeCartes = nombreDeCartes - 1;
            return true;
        }
        else {
            return false;
        }
    }

    public void viderListesDeCartes() {
        listeCartesJoueur.clear();
        listeCartesCroissantes.clear();
        listeCartesDecroissantes.clear();
    }

    public void extraireValeurCarteJoueur (Context context, LinearLayout carte) {
        TextView textView = new TextView(context);
        textView = (TextView) carte.getChildAt(0);
        String valeurCarte = textView.getText().toString();
        listeCartesJoueur.add(Integer.parseInt(valeurCarte));
    }

    public void extraireValeurPileCroissante (Context context, LinearLayout carte) {
        TextView textView = new TextView(context);
        textView = (TextView) carte.getChildAt(0);
        String valeurCarte = textView.getText().toString();
        listeCartesCroissantes.add(Integer.parseInt(valeurCarte));
    }

    public void extraireValeurPileDecroissante (Context context, LinearLayout carte) {
        TextView textView = new TextView(context);
        textView = (TextView) carte.getChildAt(0);
        String valeurCarte = textView.getText().toString();
        listeCartesDecroissantes.add(Integer.parseInt(valeurCarte));
    }

    public boolean validerMouvementPossible() {
        boolean mouvementPossiblePileCroissante = false;
        boolean mouvementPossiblePileDecroissante = false;
        for (int i = 0; i < listeCartesJoueur.size(); i++) {
            for (int j = 0; j < listeCartesCroissantes.size(); j++)
            {
                boolean plusGrandQuePile = listeCartesJoueur.get(i) > listeCartesCroissantes.get(j);
                // a SURVEILLER
                boolean valeurPlusPetitDe10 = listeCartesJoueur.get(i) == listeCartesJoueur.get(i) - 10;
                if (plusGrandQuePile || valeurPlusPetitDe10) {
                    mouvementPossiblePileCroissante = true;
                }
            }
        }
        for (int i = 0; i < listeCartesJoueur.size(); i++) {
            for (int j = 0; j < listeCartesDecroissantes.size(); j++)
            {
                boolean plusPetitQuePile = listeCartesJoueur.get(i) < listeCartesDecroissantes.get(j);
                // a SURVEILLER AUSSI
                boolean valeurPlusGrandDe10 = listeCartesJoueur.get(i) == listeCartesJoueur.get(i) + 10;
                if (plusPetitQuePile || valeurPlusGrandDe10) {
                    mouvementPossiblePileDecroissante = true;
                }
            }
        }

        if (mouvementPossiblePileCroissante == true || mouvementPossiblePileDecroissante == true) {
            return false;
        }
        return true;

    }

    public int getNombreDeCartes() {
        return nombreDeCartes;
    }

    public List<Integer> getListeDeCartes() {
        return listeDeCartes;
    }

    public List<Integer> getListeCartesCroissantes() {
        return listeCartesCroissantes;
    }

    public List<Integer> getListeCartesDecroissantes() {
        return listeCartesDecroissantes;
    }

    public List<Integer> getListeCartesJoueur() {
        return listeCartesJoueur;
    }

}
