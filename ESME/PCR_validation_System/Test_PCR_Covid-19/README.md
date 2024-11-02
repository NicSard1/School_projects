# Projet PCR Covid-19 - Java ESME 2023

Bienvenue dans le projet PCR Covid-19 développé en Java pour le cours de POO Java de la Majeure Systèmes Embarqués & Transports Intelligents.

## Introduction

Ce projet consiste en un système de validation de tests PCR Covid-19, permettant de vérifier la validité des tests, d'afficher le nombre total de tests, et de choisir le pays destinataire pour déterminer la durée de validité.

## Structure du Projet

Le projet est divisé en plusieurs classes, chacune ayant une responsabilité spécifique :

- `Serveur`: Gère les données des tests, leur chargement depuis un fichier, la vérification de leur validité, etc.
- `DataDAO`: Représente les données d'un test PCR.
- `ServeurDAO`: Stocke la durée de validité des tests.
- `Menu`: Gère l'interaction avec l'utilisateur, affiche les menus, et collecte les entrées.
- `TestDataGenerator`: Génère des données de test PCR pour enrichir la base de données.

## Commentaires du Code

- [Serveur.java](Model/Serveur.java): Gère la logique métier du projet, chargement des tests, vérification de la validité, etc.
  
- [DataDAO.java](Model/DataDAO.java): Classe représentant les données d'un test PCR, avec des méthodes d'accès (getters et setters).
  
- [ServeurDAO.java](Model/ServeurDAO.java): Classe stockant la durée de validité des tests.
  
- [TestDataGenerator.java](Model/TestDataGenerator.java): Classe générant des données de test PCR pour enrichir la base de données.
  
- [Test_PCRProjet.java](Controller/Test_PCRProjet.java): Classe principale du projet, initialise l'instance du serveur et affiche le menu principal.
  
- [Menu.java](View/Menu.java): Gère l'interface utilisateur, affiche les menus, et collecte les entrées utilisateur.

## Utilisation

1. Exécutez la classe `Test_PCRProjet.java` pour démarrer le programme.
2. Choisissez les options du menu principal pour interagir avec le système.

## Dépendances

- Java 8 ou version ultérieure.

## Auteurs

- Sandric BRETECHER
- Clément CHANVALON

- Groupe A1S - Promo 2026
