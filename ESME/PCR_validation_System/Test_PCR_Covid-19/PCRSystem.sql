-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:8889
-- Généré le : lun. 20 nov. 2023 à 16:36
-- Version du serveur : 5.7.39
-- Version de PHP : 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `PCRSystem`
--

-- --------------------------------------------------------

--
-- Structure de la table `TestPCR`
--

CREATE TABLE `TestPCR` (
  `IdTest` int(11) NOT NULL,
  `DateTest` tinytext,
  `ResultatTest` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `TestPCR`
--

INSERT INTO `TestPCR` (`IdTest`, `DateTest`, `ResultatTest`) VALUES
(1, '2023-11-18', 0),
(2, '2023-11-17', 0),
(3, '2023-11-14', 0),
(4, '2023-11-12', 0),
(5, '2023-11-18', 1),
(6, '2023-11-11', 0),
(7, '2023-11-12', 0),
(8, '2023-11-10', 0),
(9, '2023-11-15', 0),
(10, '2023-11-14', 0),
(11, '2023-11-11', 1),
(12, '2023-11-16', 0),
(13, '2023-11-13', 1),
(14, '2023-11-18', 0),
(15, '2023-11-17', 1),
(16, '2023-11-18', 0),
(17, '2023-11-10', 0),
(18, '2023-11-13', 0),
(19, '2023-11-18', 0),
(20, '2023-11-17', 0),
(21, '2023-11-13', 0),
(22, '2023-11-15', 0),
(23, '2023-11-15', 0),
(24, '2023-11-10', 0),
(25, '2023-11-12', 0),
(26, '2023-11-12', 0),
(27, '2023-11-17', 0),
(28, '2023-11-17', 1),
(29, '2023-11-11', 0),
(30, '2023-11-14', 1),
(31, '2023-11-12', 0),
(32, '2023-11-15', 0),
(33, '2023-11-14', 0),
(34, '2023-11-15', 0),
(35, '2023-11-17', 0),
(36, '2023-11-10', 0),
(37, '2023-11-11', 1),
(38, '2023-11-18', 0),
(39, '2023-11-15', 0),
(40, '2023-11-17', 0),
(41, '2023-11-12', 0),
(42, '2023-11-16', 0),
(43, '2023-11-16', 0),
(44, '2023-11-16', 0),
(45, '2023-11-19', 0),
(46, '2023-11-19', 0),
(47, '2023-11-10', 1),
(48, '2023-11-11', 0),
(49, '2023-11-15', 0),
(50, '2023-11-12', 0),
(51, '2023-11-16', 0),
(52, '2023-11-16', 0),
(53, '2023-11-10', 0),
(54, '2023-11-12', 0),
(55, '2023-11-19', 0),
(56, '2023-11-14', 1),
(57, '2023-11-15', 0),
(58, '2023-11-13', 0),
(59, '2023-11-11', 0),
(60, '2023-11-13', 0),
(61, '2023-11-17', 0),
(62, '2023-11-11', 0),
(63, '2023-11-18', 1),
(64, '2023-11-17', 0),
(65, '2023-11-19', 0),
(66, '2023-11-12', 0),
(67, '2023-11-16', 0),
(68, '2023-11-12', 0),
(69, '2023-11-10', 0),
(70, '2023-11-13', 0),
(71, '2023-11-13', 0),
(72, '2023-11-14', 0),
(73, '2023-11-12', 0),
(74, '2023-11-19', 0),
(75, '2023-11-17', 0),
(76, '2023-11-12', 0),
(77, '2023-11-13', 1),
(78, '2023-11-18', 0),
(79, '2023-11-18', 0),
(80, '2023-11-10', 0),
(81, '2023-11-18', 0),
(82, '2023-11-12', 1),
(83, '2023-11-17', 0),
(84, '2023-11-17', 0),
(85, '2023-11-17', 1),
(86, '2023-11-16', 0),
(87, '2023-11-14', 0),
(88, '2023-11-15', 1),
(89, '2023-11-19', 0),
(90, '2023-11-19', 1),
(91, '2023-11-19', 0),
(92, '2023-11-17', 0),
(93, '2023-11-16', 0),
(94, '2023-11-19', 0),
(95, '2023-11-17', 0),
(96, '2023-11-19', 1),
(97, '2023-11-13', 0),
(98, '2023-11-10', 0),
(99, '2023-11-19', 0),
(100, '2023-11-19', 0),
(101, '2023-11-16', 0),
(102, '2023-11-18', 0),
(103, '2023-11-13', 0),
(104, '2023-11-17', 1),
(105, '2023-11-14', 1),
(106, '2023-11-19', 1),
(107, '2023-11-15', 0),
(108, '2023-11-18', 1),
(109, '2023-11-17', 0),
(110, '2023-11-17', 0),
(111, '2023-11-14', 0),
(112, '2023-11-10', 0),
(113, '2023-11-12', 0),
(114, '2023-11-12', 1),
(115, '2023-11-17', 1),
(116, '2023-11-19', 1),
(117, '2023-11-11', 0),
(118, '2023-11-14', 0),
(119, '2023-11-17', 0),
(120, '2023-11-15', 1),
(121, '2023-11-12', 0),
(122, '2023-11-13', 0),
(123, '2023-11-12', 1),
(124, '2023-11-14', 0),
(125, '2023-11-17', 0),
(126, '2023-11-10', 0),
(127, '2023-11-19', 0),
(128, '2023-11-11', 1),
(129, '2023-11-19', 1),
(130, '2023-11-12', 0);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `TestPCR`
--
ALTER TABLE `TestPCR`
  ADD PRIMARY KEY (`IdTest`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `TestPCR`
--
ALTER TABLE `TestPCR`
  MODIFY `IdTest` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=131;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;