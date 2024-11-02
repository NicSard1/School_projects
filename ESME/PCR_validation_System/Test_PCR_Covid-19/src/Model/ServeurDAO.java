// Déclaration du package dans lequel se trouve la classe
package Model;

// Déclaration de la classe ServeurDAO
public class ServeurDAO {

    // Propriété de la classe représentant la durée de validité d'un test
    private int validiteTest;

    // Méthode getter pour obtenir la durée de validité d'un test
    public int getValiditeTest() {
        return validiteTest;
    }

    // Méthode setter pour définir la durée de validité d'un test
    public void setValiditeTest(int validiteTest) {
        this.validiteTest = validiteTest;
    }
}
