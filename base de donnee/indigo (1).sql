-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Jeu 15 Février 2018 à 20:58
-- Version du serveur :  5.7.14
-- Version de PHP :  5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `indigo`
--

-- --------------------------------------------------------

--
-- Structure de la table `articles`
--

CREATE TABLE `articles` (
  `code` varchar(6) NOT NULL,
  `code_categorie` char(3) NOT NULL,
  `designation` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `quantite` int(11) DEFAULT NULL,
  `prix_unitaire` double DEFAULT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `articles`
--

INSERT INTO `articles` (`code`, `code_categorie`, `designation`, `quantite`, `prix_unitaire`, `date`) VALUES
('XENO25', 'POR', 'Xenon 25', 15, 850, '2018-02-11'),
('ZENI77', 'TAB', 'Zenith 77', 9, 500, '2018-02-11');

-- --------------------------------------------------------

--
-- Structure de la table `categories`
--

CREATE TABLE `categories` (
  `code` varchar(3) NOT NULL,
  `designation` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `categories`
--

INSERT INTO `categories` (`code`, `designation`) VALUES
('ECR', 'ecran'),
('OHP', 'ORDINATEUR HP'),
('POR', 'portable'),
('TAB', 'tablette');

-- --------------------------------------------------------

--
-- Structure de la table `clients`
--

CREATE TABLE `clients` (
  `code` varchar(6) NOT NULL,
  `nom` varchar(15) DEFAULT NULL,
  `prenom` varchar(15) DEFAULT NULL,
  `carte_fidele` tinyint(1) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `adresse` varchar(45) DEFAULT NULL,
  `code_postal` varchar(45) DEFAULT NULL,
  `ville` varchar(45) DEFAULT NULL,
  `tel_fixe` varchar(45) DEFAULT NULL,
  `mobilis` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `remarques` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `clients`
--

INSERT INTO `clients` (`code`, `nom`, `prenom`, `carte_fidele`, `date`, `adresse`, `code_postal`, `ville`, `tel_fixe`, `mobilis`, `email`, `remarques`) VALUES
('CL0001', 'tru', 'fhu', 1, '2018-02-15', 'ffh', 'guui', 'vv', 'ghjk', ' ', 'ghi', ''),
('CL0002', 'jhjhvhv', '', 0, '2018-02-15', 'bhv hvh', '', '', 'h vhvhv h', ' ', '', ''),
('CL0003', 'vvyv', 'hv', 0, '2018-02-15', 'hvf', '', '', '', ' ', '', '');

-- --------------------------------------------------------

--
-- Structure de la table `commandes`
--

CREATE TABLE `commandes` (
  `code` varchar(15) NOT NULL,
  `total_ttc` double DEFAULT NULL,
  `date` date DEFAULT NULL,
  `code_client` varchar(6) NOT NULL,
  `code_mode_reglements` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `commandes`
--

INSERT INTO `commandes` (`code`, `total_ttc`, `date`, `code_client`, `code_mode_reglements`) VALUES
('ZENI77', 500, '2018-02-15', 'CL0001', 2);

-- --------------------------------------------------------

--
-- Structure de la table `lignes_commandes`
--

CREATE TABLE `lignes_commandes` (
  `code_commande` varchar(15) NOT NULL,
  `code_article` varchar(6) NOT NULL,
  `quantite` int(15) NOT NULL,
  `prix_unitaire` double DEFAULT NULL,
  `total` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `mode_reglements`
--

CREATE TABLE `mode_reglements` (
  `code` int(1) NOT NULL,
  `type` varchar(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `mode_reglements`
--

INSERT INTO `mode_reglements` (`code`, `type`) VALUES
(1, 'espece'),
(2, 'cheque'),
(3, 'carte');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `articles`
--
ALTER TABLE `articles`
  ADD PRIMARY KEY (`code`,`code_categorie`),
  ADD KEY `fk_articles_categories1_idx` (`code_categorie`),
  ADD KEY `index_designation` (`designation`);

--
-- Index pour la table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`code`),
  ADD KEY `index_designation` (`designation`);

--
-- Index pour la table `clients`
--
ALTER TABLE `clients`
  ADD PRIMARY KEY (`code`);

--
-- Index pour la table `commandes`
--
ALTER TABLE `commandes`
  ADD PRIMARY KEY (`code`,`code_client`,`code_mode_reglements`),
  ADD KEY `fk_commandes_clients1_idx` (`code_client`),
  ADD KEY `fk_commandes_mode_reglements1_idx` (`code_mode_reglements`);

--
-- Index pour la table `lignes_commandes`
--
ALTER TABLE `lignes_commandes`
  ADD PRIMARY KEY (`code_commande`,`code_article`),
  ADD KEY `fk_commandes_has_articles_articles1_idx` (`code_article`),
  ADD KEY `fk_commandes_has_articles_commandes_idx` (`code_commande`);

--
-- Index pour la table `mode_reglements`
--
ALTER TABLE `mode_reglements`
  ADD PRIMARY KEY (`code`);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `articles`
--
ALTER TABLE `articles`
  ADD CONSTRAINT `fk_articles_categories1` FOREIGN KEY (`code_categorie`) REFERENCES `categories` (`code`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `commandes`
--
ALTER TABLE `commandes`
  ADD CONSTRAINT `fk_commandes_clients1` FOREIGN KEY (`code_client`) REFERENCES `clients` (`code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_commandes_mode_reglements1` FOREIGN KEY (`code_mode_reglements`) REFERENCES `mode_reglements` (`code`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `lignes_commandes`
--
ALTER TABLE `lignes_commandes`
  ADD CONSTRAINT `fk_commandes_has_articles_articles1` FOREIGN KEY (`code_article`) REFERENCES `articles` (`code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_commandes_has_articles_commandes` FOREIGN KEY (`code_commande`) REFERENCES `commandes` (`code`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
