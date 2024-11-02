// Déclaration du package dans lequel se trouve la classe
package Controller;

import java.io.IOException;

import Model.*;
import View.*;

// Déclaration de la classe principale du projet
public class Test_PCRProjet {

    // Instance statique pour charger des données de test du fichier Data.txt
    //static public Data loadTestData;

    // Méthode principale du programme
    public static void main(String[] args) throws NumberFormatException, IOException {

        // Initialisation d'une instance de la classe Serveur
        Serveur serveur = new Serveur();

        // Affichage du titre du projet
        System.out.println("\nSystème de validation de tests PCR Covid-19 - Projet Java ESME 2023");
        System.out.println("Groupe : A1S - Sandric BRETECHER et Clément CHANVALON");

        // Utilisation d'une boucle infinie pour le menu principal. On sort de la boucle
        // lorsque l'utilisateur sélectionne l'option "quitter".
        while (true) {
            // Initialisation d'une instance du menu avec le DAO du serveur
            Menu menu = new Menu(serveur.getServeurDAO());
            menu.afficherMenuPrincipal(serveur);
        }

    }

}
