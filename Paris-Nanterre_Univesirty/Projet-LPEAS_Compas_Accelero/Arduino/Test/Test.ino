#include <Adafruit_LSM303DLH_Mag.h>  // Bibliothèque pour la lecture du capteur magnétique LSM303DLH
#include <Adafruit_LSM303_Accel.h>   // Bibliothèque pour la lecture de l'accéléromètre LSM303
#include <Adafruit_Sensor.h>         // Bibliothèque pour la lecture des capteurs Adafruit
#include <Wire.h>                    // Bibliothèque pour la communication I2C
#include <SD.h>                      // Bibliothèque pour l'utilisation de la carte SD
#include "Arduino.h"                 // Bibliothèque standard d'Arduino
#include "RTClib.h"                  // Bibliothèque pour la lecture de l'horloge RTC

#define ENTETE "$23EAS"              // Entete pour identifier le début de la trame
#define LABEL_CAP '9'                // Label pour le CAP en °
#define LABEL_ACCELERO 'A'           // Label pour l'accelero avec X/Y/Z en m/s
#define LABEL_V1 '0'                 // Label pour identifié la version V1 de la trame
#define CHECKSUM 0                   // Checksum pour remplir les trames jusqu'à 32 octets si la trame est inférieur à 32 octets

File myFile;                         // Fichier sur la carte SD
RTC_DS1307 rtc;                      // Horloge RTC

const int pinSD = 9;                 // Définition de la broche CS pour la carte SD
String namefile;

Adafruit_LSM303DLH_Mag_Unified mag = Adafruit_LSM303DLH_Mag_Unified(12345);  // Objet capteur magnétique
Adafruit_LSM303_Accel_Unified accel = Adafruit_LSM303_Accel_Unified(54321);  // Objet accéléromètre

/*
   Définition des strucutre pour remplire les trames avec une taille de 32 octets

   Taille de la trame en octets = 32 octets
   Taille int : 2 octets par int
   Taille Char : 1 octet par char
   Taille String : 1 octets par char présent dans le String, soit 6 octets pour l'entete
   Taille float : 1 octet par float

*/
struct MagnetoStruct { // structure pour la trame concernant le cap
  String entete;
  char label, version;
  int second, minute, hour, jour, month, annee;
  float heading ;
  int checksum1, checksum2, checksum3, checksum4 ;
};
struct AcceleroStruct { // structure pour la trame concernant l'accelero
  String entete;
  char label, version;
  int second, minute, hour, jour, month, annee;
  float accelX, accelY, accelZ;
};

/*Fonctions setup pour les différents équipements*/
void Accelero_setup() {
  Serial.println("Accelerometer Test");  // Affiche un message sur la console série
  if (!accel.begin()) {                  // Si l'initialisation de l'objet accel a échoué
    Serial.println("Erreur ! LSM303 non détecté !");  // Affiche un message d'erreur sur la console série
    while (1);                           // Boucle infinie pour bloquer le programme
  }
  accel.setRange(LSM303_RANGE_4G);       // Configure la plage de mesure de l'accéléromètre à ±4g
  accel.setMode(LSM303_MODE_NORMAL);     // Configure le mode de mesure de l'accéléromètre en mode normal
}

void Compas_setup() {
  Serial.println("Magnetometer Test");   // Affiche le message "Magnetometer Test" sur la console série
  if (!mag.begin()) {                    // Si la communication avec le capteur de champ magnétique n'est pas établie
    Serial.println("Erreur ! LSM303 non détecté !"); // Affiche un message d'erreur sur la console série
    while (1);                           // Boucle infinie pour bloquer le programme
  }
}

void Clock_setup() {
  // Vérifier si le module RTC fonctionne
  if (!rtc.begin()) {
    while (1) delay(10);
  }
  // Si le module RTC ne fonctionne pas, le programme reste bloqué dans cette boucle while
  // jusqu'à ce que l'Arduino soit redémarré manuellement
  if (!rtc.isrunning()) {
    // Si l'horloge RTC ne fonctionne pas, on la règle avec la date et l'heure du moment où
    // le programme est téléversé sur l'Arduino (grâce aux macros __DATE__ et __TIME__)
    rtc.adjust(DateTime(F(__DATE__), F(__TIME__)));
  }
}

void OpenFile() {
  //On obtient la date actuelle
  DateTime now = rtc.now();
  int i = 0;

  // Crée un nom de fichier unique en fonction de l'heure et de la date actuelles
  do {
    namefile =  String(now.year()) + String(now.month()) + String( now.day()) + "_" + i + ".txt";
    i++;
  } while (SD.exists(namefile));
  // Ouvrir le fichier en écriture
  return namefile;

  // Vérifier si le fichier a bien été créé
  if (!myFile) {
    Serial.println("Erreur : impossible d'ouvrir le fichier !");
    return;
  }
}

void CarteSD_setup() {
  Serial.begin(9600); // Initialisation de la communication série
  while (!Serial); // Attente de la connexion de l'ordinateur

  if (!SD.begin(pinSD)) { // Initialisation de la carte SD
    Serial.println("Carte SD déconnectée");
    while (1); // Boucle infinie en cas d'erreur
  }
  Serial.println("La carte SD est initialisée.");
}

void setup(void) {
  Clock_setup(); // Initialisation de l'horloge RTC
  delay(50);
  Accelero_setup(); // Initialisation de l'accéléromètre
  delay(50);
  Compas_setup(); // Initialisation du magnétomètre
  delay(50);
  CarteSD_setup(); // Initialisation de la carte SD
  delay(50);
  Serial.println();
  OpenFile();// On ouvre le fichier pour écrire les trames ultérieurements.
  myFile = SD.open(namefile, FILE_WRITE);
  if (myFile) { // si le fichier a été créé, on affiche son nom dans le moniteur série
    Serial.print("Le fichier");
    Serial.println(" a été créé avec succès !");
    Serial.println();
  }
  delay(500);
}


void loop() {
  // Obtenir la date et l'heure actuelles
  DateTime now = rtc.now();

  // On vérifie si la carte SD est présente à chaque boucle
  if (!SD.begin(pinSD)) {
    Serial.println("Carte SD déconnectée");
    while (1);
  }

  /*Ouverture du fichier d'écriture*/
  myFile = SD.open(namefile, FILE_WRITE);// Appel de la fonction pour écrire dans le fichier créer précédemment

  /*Accéléromètre*/
  // Acquisition des données de l'accéléromètre
  sensors_event_t event;
  accel.getEvent(&event);
  // On stocke les composantes d'accélération dans des variables floattantes pour avoir une bonne précision
  float X = event.acceleration.x;
  float Y = event.acceleration.y;
  float Z = event.acceleration.z;

  /*Magnétomètre*/
  // Acquisition des données du magnétomètre (CAP)
  mag.getEvent(&event); // récupère les données du magnétomètre dans la variable event
  // Calcul de la direction du nord magnétique (heading) à partir des données du magnétomètre
  float heading = atan2(event.magnetic.y, event.magnetic.x) * 180 / PI; // On calcule l'angle en radians à partir des données du magnétomètre, puis on le convertit en degrés
  heading += (heading < 0) ? 360 : 0; // On s'assure que l'angle est compris entre 0 et 360 degrés

  /*Ecriture des données acquises

      Pour écrire dans la trame en héxadécimal les données recues, on pourrait écrire :
            - myFile.print(String((int)trameMagneto.heading, HEX));
            - myFile.print(String((int)trameAccelero.accelX, HEX));
            - myFile.print(String((int)trameAccelero.accelY, HEX));
            - myFile.print(String((int)trameAccelero.accelZ, HEX));
  */

  //Stockées les données d'acqusition dans 2 structures différentes permet de réalsier 2 trames avec un format d'entête identque
  struct MagnetoStruct trameMagneto = {ENTETE, LABEL_CAP, LABEL_V1, now.second(), now.minute(), now.hour(), now.day(), now.month(), now.year(), heading, CHECKSUM, CHECKSUM, CHECKSUM, CHECKSUM}; // Tableau pour stocker les échantillons du CAP
  /*on vérifie que la taille de la trame en octets est =32*/
  //  Serial.print("La taille de la structure trameCap est : ");
  //Serial.println(sizeof(trameMagneto));

  struct AcceleroStruct trameAccelero = {ENTETE, LABEL_ACCELERO, LABEL_V1, now.second(), now.minute(), now.hour(), now.day(), now.month(), now.year(), X, Y, Z}; // Tableau pour stocker les échantillons de l'ACCELERO
  /*on vérifie que la taille de la trame en octets est =32*/
  //Serial.print("La taille de la structure trameCap est : ");
  //Serial.println(sizeof(trameAccelero));

  /*Stockage des données dans la carte SD*/
  // On ouvre le fichier pour écrire les données stockées dans les structures
  if (myFile) {
    Serial.println("Envoie des trame sur la carte SD...");

    //Envoie de la trame Cap sur le fichier de la carte SD
    myFile.print(trameMagneto.entete);
    myFile.print(';');
    myFile.print(trameMagneto.label);
    myFile.print(trameMagneto.version);
    myFile.print(';');
    myFile.print(trameMagneto.hour);
    myFile.print(':');
    myFile.print(trameMagneto.minute);
    myFile.print(':');
    myFile.print(trameMagneto.second);
    myFile.print(';');
    myFile.print(trameMagneto.jour);
    myFile.print('-');
    myFile.print(trameMagneto.month);
    myFile.print('-');
    myFile.print(trameMagneto.annee);
    myFile.print(';');
    myFile.print(trameMagneto.heading);
    myFile.print(';');
    myFile.print(trameMagneto.checksum1);
    myFile.print(';');
    myFile.print(trameMagneto.checksum2);
    myFile.print(';');
    myFile.print(trameMagneto.checksum3);
    myFile.print(';');
    myFile.println(trameMagneto.checksum4);

    //Envoie de la trame Accelero sur le fichier de la carte SD
    myFile.print(trameAccelero.entete);
    myFile.print(';');
    myFile.print(trameAccelero.label);
    myFile.print(trameAccelero.version);
    myFile.print(';');
    myFile.print(trameAccelero.hour);
    myFile.print(':');
    myFile.print(trameAccelero.minute);
    myFile.print(':');
    myFile.print(trameAccelero.second);
    myFile.print(';');
    myFile.print(trameAccelero.jour);
    myFile.print('-');
    myFile.print(trameAccelero.month);
    myFile.print('-');
    myFile.print(trameAccelero.annee);
    myFile.print(';');
    myFile.print(trameAccelero.accelX);
    myFile.print(';');
    myFile.print(trameAccelero.accelY);
    myFile.print(';');
    myFile.println(trameAccelero.accelZ);

    myFile.close();// Fermer le fichier
  } else {
    // Afficher un message d'erreur si le fichier ne peut pas être ouvert
    Serial.println("Erreur : impossible d'écrire dans le fichier");
  }
  delay(50); // Delais de 0,5s entre chaque loop d'acquisition. Plus le delay est petit, plus il y aura de précision en temps réel. Cependant, nous ne sommes pas réellement sur une équipements temps réel.
}
