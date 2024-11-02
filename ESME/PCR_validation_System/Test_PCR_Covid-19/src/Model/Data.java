// Déclaration du package dans lequel se trouve la classe
package Model;

// Déclaration de la classe DataDAO
public class Data {

    // Propriétés de la classe représentant les données d'un test
    private int numeroTest;
    private String dateTest;
    private int resultatTest;

    // Constructeur par défaut
    public Data() {
    }

    // Constructeur avec paramètres
    public Data(int numeroTest, String dateTest, int resultatTest) {
        this.numeroTest = numeroTest;
        this.dateTest = dateTest;
        this.resultatTest = resultatTest;
    }

    // Méthode getter pour obtenir le numéro du test
    public int getNumeroTest() {
        return numeroTest;
    }

    // Méthode setter pour définir le numéro du test
    public void setNumeroTest(int numeroTest) {
        this.numeroTest = numeroTest;
    }

    // Méthode getter pour obtenir la date du test
    public String getDateTest() {
        return dateTest;
    }

    // Méthode setter pour définir la date du test
    public void setDateTest(String dateTest) {
        this.dateTest = dateTest;
    }

    // Méthode getter pour obtenir le résultat du test
    public int getResultatTest() {
        return resultatTest;
    }

    // Méthode setter pour définir le résultat du test
    public void setResultatTest(int resultatTest) {
        this.resultatTest = resultatTest;
    }
}
