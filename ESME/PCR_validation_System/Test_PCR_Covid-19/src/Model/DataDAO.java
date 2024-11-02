package Model;

import java.util.ArrayList;

// Déclaration de l'interface DataDAO
public interface DataDAO {
    // Méthode pour récupérer toutes les données de tests de la base de données
    public ArrayList<Data> getData();

    // Méthode pour récupérer les données d'un test par son ID
    public Data getId(int testId);

    // Méthode pour récupérer les données d'un test par son numéro de test
    public Data getTest(int numeroTest);
}
