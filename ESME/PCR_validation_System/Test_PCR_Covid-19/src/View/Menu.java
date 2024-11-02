package View;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import Model.Serveur;
import Model.ServeurDAO;
import Model.TestDataGenerator;

// Déclaration de la classe Menu
public class Menu {

    // Déclaration d'un formateur de date statique pour obtenir la date actuelle
    private static DateTimeFormatter todaysDate = DateTimeFormatter.ofPattern("dd MMMM yyyy");

    // Déclaration d'un objet Scanner pour la saisie utilisateur
    private static Scanner scanner = new Scanner(System.in);

    private TestDataGenerator generator;

    // Propriété de la classe représentant les données du serveur
    private ServeurDAO donneServeur;

    // Constructeur avec un paramètre pour initialiser les données du serveur
    public Menu(ServeurDAO donneServeur) {
        this.donneServeur = donneServeur;
        this.generator = new TestDataGenerator();
    }

    // Constructeur par défaut
    public Menu() {
    }

    // Méthode pour afficher le menu principal
    public void afficherMenuPrincipal(Serveur serveurDonnees) {
        // Affichage du titre du menu principal
        System.out.println("--------------------------------------------------");
        System.out.println("Menu principal - Covid19");
        System.out.println("1. Vérifier un test PCR\n2. Afficher le nombre de tests\n3. Générer des tests PCR\n4. Quitter");
        System.out.print("\nVotre choix : ");
        // Récupération du choix de l'utilisateur
        int idMenu = scanner.nextInt();

        // Structure switch pour traiter le choix de l'utilisateur
        switch (idMenu) {
            case 1:
                // Appel de la méthode pour vérifier un test PCR
                menuCheckTest(serveurDonnees);
                break;
            case 2:
                // Appel de la méthode pour afficher le nombre de tests
                serveurDonnees.displayNumberOfTests();
                break;
            case 3:
                 // Option pour générer de nouvelles données de test PCR
                 generator.generator();
                 break;
            case 4:
                // Affichage d'un message de sortie et fermeture du programme
                System.out.println("\nAu revoir !");
                System.exit(0);
                break;
            default:
                // Affichage d'un message en cas de choix invalide
                System.out.println("\nChoix invalide.");
        }
    }

    // Méthode pour le menu de vérification d'un test PCR
    public void menuCheckTest(Serveur serveurDonnees) {
        // Initialisation d'un indicateur de test trouvé à false
        boolean testTrouve = false;
        do {
            // Affichage du message pour entrer le numéro de test
            System.out.println("--------------------------------------------------");
            System.out.print("Entrez un numéro de test PCR : ");
            // Récupération du numéro de test saisi par l'utilisateur
            int numeroTestRecherche = scanner.nextInt();
            // Appel de la méthode pour vérifier le test
            testTrouve = serveurDonnees.checkTest(numeroTestRecherche, testTrouve);
        } while (!testTrouve);
    }

    // Méthode pour le menu de choix du pays
    public int menuPays(ServeurDAO donneeServeur) {
        // Affichage des options de pays
        System.out.println("--------------------------------------------------");
        System.out.println("Choisissez votre pays destinataire :");
        System.out.println("1. France\n2. Espagne\n3. Italie\n4. Allemagne");
        System.out.println("5. Royaume-Uni\n6. Canada\n7. Japon\n8. Australie\n9. Brésil\n10. Afrique du Sud");
        System.out.println("0. Annuler");

        System.out.print("\nVotre choix : ");
        // Récupération du choix de l'utilisateur
        int choixPays = scanner.nextInt();

        // Initialisation de la durée de validité à 0
        int dureeValidite = 0;

        // Structure switch pour déterminer la durée de validité en fonction du pays
        // choisi
        switch (choixPays) {
            case 1:
            case 2:
                dureeValidite = 7;
                break;
            case 3:
            case 4:
                dureeValidite = 2;
                break;
            case 5:
            case 6:
            case 7:
                dureeValidite = 4;
                break;
            case 8:
            case 9:
            case 10:
                dureeValidite = 3;
                break;
            case 0:
                // Affichage d'un message en cas d'annulation
                System.out.println("\nOpération annulée.");
                dureeValidite = 0;
                break;
            default:
                // Affichage d'un message en cas de choix invalide
                System.out.println("\nChoix invalide.");
                return 0;
        }

        // Si la durée de validité est 0, retourner la valeur
        if (dureeValidite == 0) {
            return dureeValidite;
        }

        // Définition de la validité du test dans le serveur
        donneServeur.setValiditeTest(dureeValidite);
        // Affichage du message avec la durée de validité choisie et la date actuelle
        System.out.print("\nVous avez choisi un pays avec " + donneServeur.getValiditeTest()
                + " jours de validité et nous sommes le "
                + todaysDate.format(LocalDateTime.now()) + ".\n");
        // Retour de la durée de validité
        return dureeValidite;
    }
}
