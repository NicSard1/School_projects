// Déclaration du package dans lequel se trouve la classe
package Model;

// Importation des classes nécessaires
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

// Importation de la classe Menu du package View
import View.Menu;

// Déclaration de la classe Serveur
public class Serveur {
    // Déclaration d'une instance de ServeurDAO
    private ServeurDAO serveurDAO;
    private DataDAO dataDAO;

    // Tableau de DataDAO pour stocker les tests
    // private Data[] tests;

    // Chemin du fichier de données
    // private static final String fileName = "src/Sources/Data.txt";

    // Constructeur de la classe Serveur
    public Serveur() throws IOException {
        // Initialisation de l'instance de ServeurDAO
        this.serveurDAO = new ServeurDAO();
        this.dataDAO = new DataDAOImpl(); // Vous pouvez changer l'implémentation si nécessaire

        // Chargement des données de test à partir du fichier Data.txt
        // loadTestData();
    }

    // Méthode pour récupérer l'instance de ServeurDAO
    public ServeurDAO getServeurDAO() {
        return serveurDAO;
    }

    // Méthode privée pour charger les données de test depuis le fichier Data.txt
    /*
     * private void loadTestData() throws IOException {
     * // Création d'un objet File à partir du chemin du fichier
     * File dataFile = new File(fileName);
     * 
     * try (BufferedReader br = new BufferedReader(new FileReader(dataFile))) {
     * String line;
     * // Liste temporaire pour stocker les objets Data
     * ArrayList<Data> testsList = new ArrayList<>();
     * 
     * // Lecture du fichier ligne par ligne
     * while ((line = br.readLine()) != null) {
     * // Séparation de la ligne en parties en utilisant l'espace comme délimiteur
     * String[] parts = line.split(" ");
     * int numeroTest = Integer.parseInt(parts[0]);
     * String dateTest = parts[1];
     * int resultatTest = Integer.parseInt(parts[2]);
     * 
     * // Création d'un objet Data et initialisation de ses propriétés
     * Data test = new Data();
     * test.setNumeroTest(numeroTest);
     * test.setDateTest(dateTest);
     * test.setResultatTest(resultatTest);
     * 
     * // Ajout de l'objet Data à la liste
     * testsList.add(test);
     * }
     * 
     * // Conversion de la liste en tableau de DataDAO
     * tests = testsList.toArray(new Data[0]);
     * 
     * } catch (IOException e) {
     * e.printStackTrace();
     * throw e;
     * }
     * }
     */

    // Méthode pour récupérer un test par son numéro
    /*
     * public Data getTestById(int numeroTestRecherche) {
     * for (Data test : tests) {
     * if (test != null && test.getNumeroTest() == numeroTestRecherche) {
     * return test;
     * }
     * }
     * return null;
     * }
     */

    // Méthode pour vérifier la validité d'un test en fonction de sa date
    public void verifyTestValidity(Data test, int dureeTest) {
        // Formatage de la date en utilisant le format "yyyy-MM-dd"
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // Conversion de la date du test en objet Date
            Date dateTest = dateFormat.parse(test.getDateTest());
            // Calcul de la différence en jours entre la date actuelle et la date du test
            long differenceEnMillis = new Date().getTime() - dateTest.getTime();
            long differenceEnJours = differenceEnMillis / (24 * 60 * 60 * 1000);

            // Vérification de la validité du test en fonction de la durée spécifiée
            if (differenceEnJours <= dureeTest) {
                System.out.print("Le test est valide.\n");
            } else {
                System.out.print("Le test est invalide.\n");
            }
        } catch (ParseException e) {
            System.out.println("Date du test erronée.\n");
        }
    }

    // Méthode pour afficher le nombre total de tests dans la base de données
    public void displayNumberOfTests() {
        // Utilisation de la méthode getData de DataDAOImpl pour obtenir la liste des
        // tests
        ArrayList<Data> testsList = dataDAO.getData();

        // Calcul du nombre de tests dans la base de données
        long nombreDeTests = testsList.size();
        System.out.println("\nNombre total de tests dans la base de données : " + nombreDeTests);
    }

    // Méthode pour vérifier l'existence d'un test et afficher ses détails
    public boolean checkTest(int numeroTestRecherche, boolean testTrouve) {
        // Création d'une instance de Menu pour interagir avec l'utilisateur
        Menu Terminal = new Menu(serveurDAO);
        // Récupération du test par son numéro
        Data test = dataDAO.getTest(numeroTestRecherche);

        if (test != null) {
            testTrouve = true;
            System.out.println("Date du test (yyyy-MM-dd) : " + test.getDateTest());
            if (test.getResultatTest()==0){
                System.out.println("Résultat du test : négatif");
            }else{
                System.out.println("Résultat du test : positif");
            }

            // Vérification de la validité du test si le résultat est "negatif"
            if (test.getResultatTest() == 0) {
                Terminal.menuPays(serveurDAO);
                int dureeTest = serveurDAO.getValiditeTest();
                if (dureeTest != 0) {
                    verifyTestValidity(test, dureeTest);
                }
            } else {
                System.out.println("--------------------------------------------------");
                System.out.println("Le test est positif donc invalide.");
            }
        } else {
            System.out.println("Test non trouvé. Veuillez réessayer.");
        }
        return testTrouve;
    }
}
