package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class TestDataGenerator {
    // Informations de connexion à la base de données MySQL
    private static final String url = "jdbc:mysql://localhost:3306/PCRSystem";
    private static final String user = "root";
    private static final String password = "root";

    // Méthode principale pour générer des tests PCR de manière aléatoire
    public void generator() {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            // Génération d'un nombre aléatoire de tests
            int randomTestNumber = generateRandomTestNumber();
            
            // Insertion des données générées dans la base de données
            insertTestData(connection, randomTestNumber);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour insérer les données générées dans la base de données
    private static void insertTestData(Connection connection, int numberOfTests) throws SQLException {
        // Requête pour obtenir l'ID maximum existant dans la table TestPCR
        String getLastIdQuery = "SELECT MAX(IdTest) FROM TestPCR";
        int lastId = 0;

        // Récupération de l'ID maximum existant
        try (PreparedStatement lastIdStatement = connection.prepareStatement(getLastIdQuery);
                ResultSet resultSet = lastIdStatement.executeQuery()) {

            if (resultSet.next()) {
                lastId = resultSet.getInt(1);
            }
        }

        // Requête pour insérer les données dans la table TestPCR
        String insertQuery = "INSERT INTO `TestPCR`(`IdTest`, `DateTest`, `ResultatTest`) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            Random random = new Random();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            // Génération et insertion des données pour le nombre spécifié de tests
            for (int i = 0; i < numberOfTests; i++) {
                // Génération de l'ID du test à partir de lastId + 1
                int idTest = lastId + 1 + i;

                // Génération de la date du test dans un intervalle de 10 jours avant aujourd'hui
                Date currentDate = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(currentDate);
                int daysBeforeToday = random.nextInt(10);
                calendar.add(Calendar.DAY_OF_YEAR, -daysBeforeToday);
                Date dateTest = calendar.getTime();

                // Génération du résultat du test avec 78% de probabilité d'être 0
                int resultatTest = (random.nextDouble() < 0.78) ? 0 : 1;

                // Attribution des valeurs dans la requête préparée
                preparedStatement.setInt(1, idTest);
                preparedStatement.setString(2, dateFormat.format(dateTest));
                preparedStatement.setInt(3, resultatTest);

                // Exécution de la requête d'insertion
                preparedStatement.executeUpdate();
            }

            System.out.println(numberOfTests + " tests inserted successfully.");
        }
    }

    // Méthode pour générer un nombre aléatoire de tests
    public static int generateRandomTestNumber() {
        Random random = new Random();
        return random.nextInt(101); // Génère un nombre aléatoire entre 0 (inclus) et 101 (exclus)
    }
}
