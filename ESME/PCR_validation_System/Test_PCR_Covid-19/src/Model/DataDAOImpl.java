// Déclaration du package dans lequel se trouve la classe
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DataDAOImpl implements DataDAO {
    // Informations de connexion à la base de données MySQL
    private final String url = "jdbc:mysql://localhost:3306/PCRSystem";
    private final String user = "root";
    private final String password = "root";
    private Connection login = null;
    private Statement statement = null;

    // Méthode pour récupérer toutes les données de tests de la base de données
    @Override
    public ArrayList<Data> getData() {
        ArrayList<Data> listTest = new ArrayList<>();
        String sql = ("SELECT * FROM `TestPCR`");

        try {
            // Connexion à la base de données
            login = DriverManager.getConnection(url, user, password);
            statement = login.createStatement();
            ResultSet result = statement.executeQuery(sql);

            // Parcours des résultats et création d'objets Data correspondants
            while (result.next()) {
                Data dataTest = new Data();
                dataTest.setNumeroTest(result.getInt("IdTest"));
                dataTest.setDateTest(result.getString("DateTest"));
                dataTest.setResultatTest(result.getInt("ResultatTest"));

                listTest.add(dataTest);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermeture des ressources
            closeResources();
        }
        return listTest;
    }

    // Méthode pour récupérer les données d'un test par son ID
    @Override
    public Data getId(int testId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // Méthode pour récupérer les données d'un test par son numéro de test
    @Override
    public Data getTest(int numeroTest) {
        String dateTest;
        int resultatTest;
        String sql = "SELECT `DateTest`, `ResultatTest` FROM `TestPCR` WHERE `IdTest`=" + numeroTest;

        try {
            Data dataTest = new Data();
            // Connexion à la base de données
            login = DriverManager.getConnection(url, user, password);
            statement = login.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            // Récupération des données du test si trouvé
            if (rs.next()) {
                resultatTest = rs.getInt("ResultatTest");
                dateTest = rs.getString("DateTest");
                dataTest.setDateTest(dateTest);
                dataTest.setResultatTest(resultatTest);

                return dataTest;
            } else {
                System.out.println("Aucun résultat trouvé pour NumeroTest : " + numeroTest);
            }
        } catch (SQLException e) {
            System.out.println(sql);
            e.printStackTrace();
        } finally {
            // Fermeture des ressources
            closeResources();
        }
        return null;
    }

    // Méthode pour fermer les ressources de connexion et de statement
    private void closeResources() {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (login != null) {
            try {
                login.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
