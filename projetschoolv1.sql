-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  Dim 15 avr. 2018 à 21:24
-- Version du serveur :  10.1.30-MariaDB
-- Version de PHP :  7.2.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `projetschoolv1`
--

-- --------------------------------------------------------

--
-- Structure de la table `administrateur`
--

CREATE TABLE `administrateur` (
  `idadmin` int(10) NOT NULL,
  `nomadmin` varchar(16) NOT NULL,
  `prenom` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `authentification`
--

CREATE TABLE `authentification` (
  `user` int(10) NOT NULL,
  `password` varchar(50) NOT NULL,
  `level` set('SECRETAIRE','ADMINISTRATEUR','COMPTABLE','ELEVE','ENSEIGNANT') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `authentification`
--

INSERT INTO `authentification` (`user`, `password`, `level`) VALUES
(1, 'JJ', 'ELEVE'),
(2, 'JJ', 'ELEVE'),
(3, 'JJ', 'ELEVE'),
(4, 'JJ', 'ELEVE'),
(5, 'JJ', 'ELEVE'),
(6, 'PASSWORD', 'ELEVE'),
(9, 'SS', 'SECRETAIRE');

-- --------------------------------------------------------

--
-- Structure de la table `enseignant`
--

CREATE TABLE `enseignant` (
  `idenseignant` int(10) NOT NULL,
  `nomenseignant` varchar(16) NOT NULL,
  `prenomenseignant` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `enseignant`
--

INSERT INTO `enseignant` (`idenseignant`, `nomenseignant`, `prenomenseignant`) VALUES
(2018000, 'Mat', 'émathique'),
(2018001, 'Claude', 'francoise'),
(2018002, 'Max', 'IMUM');

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

CREATE TABLE `etudiant` (
  `idetudiant` int(10) NOT NULL,
  `nometudiant` varchar(16) NOT NULL,
  `prenometudiant` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `etudiant`
--

INSERT INTO `etudiant` (`idetudiant`, `nometudiant`, `prenometudiant`) VALUES
(1, 'bo', 'ti'),
(2, 'BLONDE', 'SEBASTIENNE'),
(3, 'LeChat', 'Albert'),
(4, 'JOJO', 'jojo'),
(5, 'JIJI', 'GOATDEFROID'),
(6, 'Jean', 'Pierre');

-- --------------------------------------------------------

--
-- Structure de la table `groupe`
--

CREATE TABLE `groupe` (
  `idgroupe` int(10) NOT NULL,
  `matiere_idmatiere` int(10) NOT NULL,
  `nomgroupe` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `groupe`
--

INSERT INTO `groupe` (`idgroupe`, `matiere_idmatiere`, `nomgroupe`) VALUES
(4, 2, 'JPP');

-- --------------------------------------------------------

--
-- Structure de la table `groupe_has_inscription`
--

CREATE TABLE `groupe_has_inscription` (
  `groupe_idgroupe` int(10) NOT NULL,
  `groupe_matiere_idmatiere` int(10) NOT NULL,
  `inscription_etudiant_idetudiant` int(10) NOT NULL,
  `inscription_idinscription` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `groupe_has_inscription`
--

INSERT INTO `groupe_has_inscription` (`groupe_idgroupe`, `groupe_matiere_idmatiere`, `inscription_etudiant_idetudiant`, `inscription_idinscription`) VALUES
(4, 2, 1, 2),
(4, 2, 2, 1),
(4, 2, 3, 4);

-- --------------------------------------------------------

--
-- Structure de la table `inscription`
--

CREATE TABLE `inscription` (
  `idinscription` int(10) NOT NULL,
  `etudiant_idetudiant` int(10) NOT NULL,
  `dateinscription` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `inscription`
--

INSERT INTO `inscription` (`idinscription`, `etudiant_idetudiant`, `dateinscription`) VALUES
(1, 2, '2018-04-15'),
(2, 1, '2018-04-15'),
(3, 2, '2018-04-15'),
(4, 3, '2018-04-02'),
(5, 5, '2018-04-18'),
(6, 6, '2018-04-24');

-- --------------------------------------------------------

--
-- Structure de la table `matiere`
--

CREATE TABLE `matiere` (
  `idmatiere` int(10) NOT NULL,
  `nommatiere` varchar(32) NOT NULL,
  `libellematiere` varchar(10) NOT NULL,
  `nivmatiere` varchar(10) NOT NULL,
  `enseignant_idenseignant` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `matiere`
--

INSERT INTO `matiere` (`idmatiere`, `nommatiere`, `libellematiere`, `nivmatiere`, `enseignant_idenseignant`) VALUES
(1, 'Mathématique philosophique', 'EC-MATHS', 'TRIVIAL', 2018000),
(2, 'Sciences politiques', 'EC-USELESS', 'MATERNELLE', 2018001);

-- --------------------------------------------------------

--
-- Structure de la table `salle`
--

CREATE TABLE `salle` (
  `idsalle` int(10) NOT NULL,
  `nomsalle` varchar(16) NOT NULL,
  `capacitesalle` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `salle`
--

INSERT INTO `salle` (`idsalle`, `nomsalle`, `capacitesalle`) VALUES
(10, 'Info-10', 40),
(11, 'Info-11', 40),
(100, 'Info-PetitAmphi', 150),
(101, 'Info-GrandAmphi', 300);

-- --------------------------------------------------------

--
-- Structure de la table `secretaire`
--

CREATE TABLE `secretaire` (
  `idSecretaire` int(10) NOT NULL,
  `nomsecretaire` varchar(16) NOT NULL,
  `prenomsecretaire` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `session`
--

CREATE TABLE `session` (
  `idsession` int(10) NOT NULL,
  `groupe_idGroupe` int(10) NOT NULL,
  `salle_idsalle` int(10) NOT NULL,
  `anneesession` int(10) NOT NULL,
  `nomsessions` varchar(16) NOT NULL,
  `datedebutsession` date NOT NULL,
  `datefinsession` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `session`
--

INSERT INTO `session` (`idsession`, `groupe_idGroupe`, `salle_idsalle`, `anneesession`, `nomsessions`, `datedebutsession`, `datefinsession`) VALUES
(4, 4, 100, 2018, 'JJP-Session-5', '2018-04-11', '2018-04-11'),
(5, 4, 101, 2018, 'JJPREALLY', '2018-04-19', '2018-04-19'),
(6, 4, 10, 2018, 'JJPREALLY', '2018-04-19', '2018-04-19'),
(7, 4, 100, 2018, 'HLL', '2018-04-03', '2018-04-03'),
(8, 4, 100, 2018, 'HLL', '2018-04-03', '2018-04-03');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `administrateur`
--
ALTER TABLE `administrateur`
  ADD PRIMARY KEY (`idadmin`);

--
-- Index pour la table `authentification`
--
ALTER TABLE `authentification`
  ADD KEY `FK3` (`user`);

--
-- Index pour la table `enseignant`
--
ALTER TABLE `enseignant`
  ADD PRIMARY KEY (`idenseignant`);

--
-- Index pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD PRIMARY KEY (`idetudiant`);

--
-- Index pour la table `groupe`
--
ALTER TABLE `groupe`
  ADD PRIMARY KEY (`idgroupe`),
  ADD KEY `groupe_FKIndex4` (`matiere_idmatiere`);

--
-- Index pour la table `groupe_has_inscription`
--
ALTER TABLE `groupe_has_inscription`
  ADD KEY `groupe_idgroupe` (`groupe_idgroupe`,`groupe_matiere_idmatiere`,`inscription_etudiant_idetudiant`,`inscription_idinscription`),
  ADD KEY `groupe_matiere_idmatiere` (`groupe_matiere_idmatiere`),
  ADD KEY `inscription_idinscription` (`inscription_idinscription`),
  ADD KEY `inscription_etudiant_idetudiant` (`inscription_etudiant_idetudiant`);

--
-- Index pour la table `inscription`
--
ALTER TABLE `inscription`
  ADD PRIMARY KEY (`idinscription`),
  ADD KEY `inscription_FKIndex1` (`etudiant_idetudiant`);

--
-- Index pour la table `matiere`
--
ALTER TABLE `matiere`
  ADD PRIMARY KEY (`idmatiere`),
  ADD KEY `enseignant_idenseignant` (`enseignant_idenseignant`);

--
-- Index pour la table `salle`
--
ALTER TABLE `salle`
  ADD PRIMARY KEY (`idsalle`);

--
-- Index pour la table `secretaire`
--
ALTER TABLE `secretaire`
  ADD PRIMARY KEY (`idSecretaire`);

--
-- Index pour la table `session`
--
ALTER TABLE `session`
  ADD PRIMARY KEY (`idsession`),
  ADD KEY `groupe_idGroupe` (`groupe_idGroupe`),
  ADD KEY `session_FK` (`salle_idsalle`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `administrateur`
--
ALTER TABLE `administrateur`
  MODIFY `idadmin` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `enseignant`
--
ALTER TABLE `enseignant`
  MODIFY `idenseignant` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2018003;

--
-- AUTO_INCREMENT pour la table `etudiant`
--
ALTER TABLE `etudiant`
  MODIFY `idetudiant` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `groupe`
--
ALTER TABLE `groupe`
  MODIFY `idgroupe` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `inscription`
--
ALTER TABLE `inscription`
  MODIFY `idinscription` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `matiere`
--
ALTER TABLE `matiere`
  MODIFY `idmatiere` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `secretaire`
--
ALTER TABLE `secretaire`
  MODIFY `idSecretaire` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `session`
--
ALTER TABLE `session`
  MODIFY `idsession` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `groupe`
--
ALTER TABLE `groupe`
  ADD CONSTRAINT `groupe_ibfk_2` FOREIGN KEY (`matiere_idmatiere`) REFERENCES `matiere` (`idmatiere`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `groupe_has_inscription`
--
ALTER TABLE `groupe_has_inscription`
  ADD CONSTRAINT `groupe_has_inscription_ibfk_6` FOREIGN KEY (`inscription_idinscription`) REFERENCES `inscription` (`idinscription`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `groupe_has_inscription_ibfk_7` FOREIGN KEY (`inscription_etudiant_idetudiant`) REFERENCES `inscription` (`etudiant_idetudiant`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `groupe_has_inscription_ibfk_8` FOREIGN KEY (`groupe_idgroupe`) REFERENCES `groupe` (`idgroupe`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `groupe_has_inscription_ibfk_9` FOREIGN KEY (`groupe_matiere_idmatiere`) REFERENCES `groupe` (`matiere_idmatiere`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `inscription`
--
ALTER TABLE `inscription`
  ADD CONSTRAINT `inscription_ibfk_1` FOREIGN KEY (`etudiant_idetudiant`) REFERENCES `etudiant` (`idetudiant`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `matiere`
--
ALTER TABLE `matiere`
  ADD CONSTRAINT `matiere_ibfk_1` FOREIGN KEY (`enseignant_idenseignant`) REFERENCES `enseignant` (`idenseignant`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `session`
--
ALTER TABLE `session`
  ADD CONSTRAINT `session_FK` FOREIGN KEY (`salle_idsalle`) REFERENCES `salle` (`idsalle`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `session_ibfk_1` FOREIGN KEY (`groupe_idGroupe`) REFERENCES `groupe` (`idgroupe`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
