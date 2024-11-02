-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:8889
-- Généré le : ven. 01 nov. 2024 à 17:56
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
-- Base de données : `Cinema`
--

-- --------------------------------------------------------

--
-- Structure de la table `customer`
--

CREATE TABLE `customer` (
  `Name` varchar(20) DEFAULT NULL,
  `Surname` varchar(30) DEFAULT NULL,
  `Age` int(2) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `idCustomer` int(11) NOT NULL,
  `discount` double DEFAULT '0',
  `password` varchar(20) DEFAULT NULL,
  `payment_infos` int(11) DEFAULT NULL,
  `loyalty_points` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `employee`
--

CREATE TABLE `employee` (
  `Name` varchar(20) DEFAULT NULL,
  `Surname` varchar(20) DEFAULT NULL,
  `Age` int(2) DEFAULT NULL,
  `idEmployee` int(11) NOT NULL,
  `Password` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `employee`
--

INSERT INTO `employee` (`Name`, `Surname`, `Age`, `idEmployee`, `Password`) VALUES
('Tom', 'Hanks', 65, 25, 'forest'),
('Tom', 'Cruise', 59, 26, 'missionIMPOSSIBLE'),
('Tom', 'Holland', 26, 27, 'spiderman'),
('Tom', 'Hardy', 44, 28, 'venom');

-- --------------------------------------------------------

--
-- Structure de la table `movie`
--

CREATE TABLE `movie` (
  `id` int(11) NOT NULL,
  `title` varchar(50) DEFAULT NULL,
  `duration` int(11) DEFAULT NULL,
  `productor` varchar(30) NOT NULL,
  `type` varchar(15) NOT NULL,
  `summary` varchar(500) NOT NULL,
  `idMovieShow` int(11) NOT NULL,
  `affiche` varchar(50) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `movie`
--

INSERT INTO `movie` (`id`, `title`, `duration`, `productor`, `type`, `summary`, `idMovieShow`, `affiche`) VALUES
(1, 'Doctor Strange in the Multiverse of Madness', 185, 'Sam Raimi', 'Fantastic', 'Dr. Stephen Strange continues his research on the Time Stone. However, an old friend turned enemy attempts to destroy all wizards on Earth, disrupting Strange s plan.', 0, '/sources/drStrange.jpeg'),
(134, 'Top Gun Maverick', 180, 'Joseph Kosinski', 'Action', 'After more than 30 years of service as one of the best airmen in the Navy, Pete Maverick Mitchell is in his place, pushing the limits as a courageous test pilot and dodging the rank advancement that would bring him to the ground.', 0, '/sources/topgun.jpeg'),
(135, 'Jurassic World Dominion', 135, 'Colin Trevorrow', 'SF', 'Isla Nublar and her park have been destroyed, but the problems are not over. While dinosaurs have spread around the world, tense relations between the present and the past fuel new conflicts. The fragile balance is being tested and the future is facing a new direction.', 0, '/sources/jurassic.jpeg'),
(136, 'Avatar: The Way of Water', 195, 'James Cameron', 'SF', 'Jake Sully and Neytiri have formed a family and are doing everything to stay as close together as possible. However, they are forced to leave their homes and explore the different still mysterious regions of Pandora. When an old threat resurfaces, Jake will have to wage a difficult war against humans.', 0, '/sources/avatar2.jpeg'),
(137, 'BadGuys', 110, 'Pierre Perifel', 'Animation', 'After a lifetime of legendary robberies, the notorious criminals Mr. Wolf, Mr. Snake, Mr. Piranha, Mr. Shark and Mrs. Tarantula are finally captured. To avoid a prison sentence, outlaw animals must succeed in their biggest scam: becoming model citizens. Under the tutelage of their mentor, Professor Marmalade, the gang undertakes to make the world believe that it is becoming honest.', 0, '/sources/badguy.jpeg'),
(138, 'Sonic 2, The Movie', 115, 'Jeff Fowler', 'Animation', 'Well settled in the small town of Green Hills, Sonic now wants to prove that he has the stuff of a real hero. A major challenge presents itself to him when Dr. Robotnik reappears. Accompanied by his new accomplice Knuckles, they are looking for an emerald whose power would destroy all humanity. To ensure that the emerald does not fall into the wrong hands, Sonic teams up with Tails. Then begins a journey around the world.', 0, '/sources/sonic.jpeg'),
(139, 'Men', 135, 'Alex Garland', 'Horror', 'In the aftermath of a personal tragedy, Harper retired alone to the beautiful English countryside, hoping to have found a place to heal. However, someone or something in the surrounding woods seems to track her down.', 0, '/sources/men.jpeg'),
(140, 'Elvis', 145, 'Baz Luhrmann', 'Drama', 'From his rise to glory to his life as an unprecedented superstar, rock n roll icon Elvis Presley had a complicated relationship with his enigmatic manager, Colonel Tom Parker, for 20 years.', 0, '/sources/elvis.jpeg');

-- --------------------------------------------------------

--
-- Structure de la table `movieshow`
--

CREATE TABLE `movieshow` (
  `idShow` int(11) NOT NULL,
  `availablePlaces` int(11) DEFAULT NULL,
  `seatsSold` int(11) DEFAULT NULL,
  `date` varchar(11) DEFAULT NULL,
  `hour` varchar(11) DEFAULT NULL,
  `roomNumber` int(11) DEFAULT NULL,
  `movieId` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `movieshow`
--

INSERT INTO `movieshow` (`idShow`, `availablePlaces`, `seatsSold`, `date`, `hour`, `roomNumber`, `movieId`) VALUES
(19829, 100, 0, '2022-07-02', '21:00', 3, 134),
(19827, 100, 0, '2022-07-02', '15:15', 3, 134),
(19828, 100, 0, '2022-07-02', '18:05', 3, 134),
(19825, 25, 0, '2022-07-02', '23:00', 2, 1),
(19826, 25, 0, '2022-07-02', '15:00', 2, 1),
(19830, 100, 0, '2022-07-02', '12:45', 3, 134),
(19831, 100, 0, '2022-07-02', '13:30', 1, 135),
(19832, 100, 0, '2022-07-02', '15:45', 1, 135),
(19833, 100, 0, '2022-07-02', '18:00', 1, 135),
(19834, 100, 0, '2022-07-02', '21:15', 1, 135),
(19835, 250, 0, '2022-07-02', '13:00', 5, 136),
(19836, 250, 0, '2022-07-02', '15:55', 5, 136),
(19837, 250, 0, '2022-07-02', '19:40', 5, 136),
(19838, 250, 0, '2022-07-02', '22:30', 5, 136),
(19839, 50, 0, '2022-07-02', '14:00', 6, 137),
(19840, 50, 0, '2022-07-02', '16:45', 6, 137),
(19841, 50, 0, '2022-07-02', '20:10', 6, 137),
(19842, 50, 0, '2022-07-02', '22:30', 6, 137),
(19843, 75, 0, '2022-07-02', '11:00', 7, 138),
(19844, 75, 0, '2022-07-02', '14:05', 7, 138),
(19845, 75, 0, '2022-07-02', '16:20', 7, 138),
(19846, 75, 0, '2022-07-02', '18:45', 7, 138),
(19847, 75, 0, '2022-07-02', '21:00', 7, 138),
(19848, 110, 0, '2022-07-02', '13:30', 8, 139),
(19849, 110, 0, '2022-07-02', '15:15', 8, 139),
(19850, 110, 0, '2022-07-02', '17:45', 7, 139),
(19851, 110, 0, '2022-07-02', '20:25', 7, 139),
(19852, 110, 0, '2022-07-02', '17:55', 8, 139),
(19853, 110, 0, '2022-07-02', '19:45', 8, 139),
(19854, 110, 0, '2022-07-02', '22:05', 8, 139);

-- --------------------------------------------------------

--
-- Structure de la table `purchase`
--

CREATE TABLE `purchase` (
  `purchaseId` int(11) NOT NULL,
  `customerId` int(11) NOT NULL,
  `total_price` double NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `ticket`
--

CREATE TABLE `ticket` (
  `idTicket` int(11) NOT NULL,
  `idCustomer` int(11) DEFAULT NULL,
  `idMovie` int(11) DEFAULT NULL,
  `purchaseId` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`idCustomer`);

--
-- Index pour la table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`idEmployee`);

--
-- Index pour la table `movie`
--
ALTER TABLE `movie`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `movieshow`
--
ALTER TABLE `movieshow`
  ADD PRIMARY KEY (`idShow`),
  ADD KEY `movieId` (`movieId`);

--
-- Index pour la table `purchase`
--
ALTER TABLE `purchase`
  ADD PRIMARY KEY (`purchaseId`),
  ADD KEY `customerId` (`customerId`);

--
-- Index pour la table `ticket`
--
ALTER TABLE `ticket`
  ADD PRIMARY KEY (`idTicket`),
  ADD KEY `idCustomer` (`idCustomer`),
  ADD KEY `idMovie` (`idMovie`),
  ADD KEY `purchaseId` (`purchaseId`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `customer`
--
ALTER TABLE `customer`
  MODIFY `idCustomer` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT pour la table `employee`
--
ALTER TABLE `employee`
  MODIFY `idEmployee` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT pour la table `movie`
--
ALTER TABLE `movie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=142;

--
-- AUTO_INCREMENT pour la table `movieshow`
--
ALTER TABLE `movieshow`
  MODIFY `idShow` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19855;

--
-- AUTO_INCREMENT pour la table `purchase`
--
ALTER TABLE `purchase`
  MODIFY `purchaseId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT pour la table `ticket`
--
ALTER TABLE `ticket`
  MODIFY `idTicket` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=187;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
