-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 13, 2024 at 11:01 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `computershop`
--

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id_product` int(11) NOT NULL,
  `name` varchar(88) NOT NULL,
  `price` bigint(20) NOT NULL,
  `type` varchar(64) NOT NULL,
  `stock_quantity` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id_product`, `name`, `price`, `type`, `stock_quantity`) VALUES
(10, 'Laptop', 1000, 'Electronics', 20),
(12, 'Kompjuter', 12000, 'Electronics', 20);

-- --------------------------------------------------------

--
-- Table structure for table `purchase`
--

CREATE TABLE `purchase` (
  `id_purchase` int(11) NOT NULL,
  `fk_user` int(11) NOT NULL,
  `fk_product` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `purchase`
--

INSERT INTO `purchase` (`id_purchase`, `fk_user`, `fk_product`) VALUES
(10, 10, 10),
(12, 16, 12);

-- --------------------------------------------------------

--
-- Table structure for table `search`
--

CREATE TABLE `search` (
  `id_search` int(11) NOT NULL,
  `fk_user` int(11) NOT NULL,
  `fk_search_settings` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `search`
--

INSERT INTO `search` (`id_search`, `fk_user`, `fk_search_settings`) VALUES
(6, 10, 10),
(8, 16, 12);

-- --------------------------------------------------------

--
-- Table structure for table `search_settings`
--

CREATE TABLE `search_settings` (
  `id_search_settings` int(11) NOT NULL,
  `min_price` bigint(20) NOT NULL,
  `max_price` bigint(20) NOT NULL,
  `type` varchar(64) NOT NULL,
  `keyword` varchar(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `search_settings`
--

INSERT INTO `search_settings` (`id_search_settings`, `min_price`, `max_price`, `type`, `keyword`) VALUES
(10, 500, 2000, 'Laptop', 'Gaming'),
(12, 1000, 2000, 'Kompjuter', 'Strimovanje');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `full_name` varchar(64) NOT NULL,
  `username` varchar(45) NOT NULL,
  `email` varchar(64) NOT NULL,
  `birth_date` varchar(45) NOT NULL,
  `account_balance` bigint(20) NOT NULL,
  `spent_amount` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `full_name`, `username`, `email`, `birth_date`, `account_balance`, `spent_amount`) VALUES
(10, 'Janko Jankovic', 'janko', 'janko@example.com', '1990-01-01', 5500, 10000),
(12, 'Jovana Peric', 'jovana', 'jovana@example.com', '1990-01-01', 8500, 3500),
(14, 'Katarina Jankovic', 'katarina', 'katarina@example.com', '1990-01-01', 8500, 12000),
(16, 'Ana Ivanovic', 'ana', 'ana@example.com', '1990-01-01', 9000, 17000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id_product`);

--
-- Indexes for table `purchase`
--
ALTER TABLE `purchase`
  ADD PRIMARY KEY (`id_purchase`),
  ADD KEY `fk_user` (`fk_user`),
  ADD KEY `fk_product` (`fk_product`);

--
-- Indexes for table `search`
--
ALTER TABLE `search`
  ADD PRIMARY KEY (`id_search`),
  ADD KEY `fk_user` (`fk_user`),
  ADD KEY `fk_search_settings` (`fk_search_settings`);

--
-- Indexes for table `search_settings`
--
ALTER TABLE `search_settings`
  ADD PRIMARY KEY (`id_search_settings`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id_product` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `purchase`
--
ALTER TABLE `purchase`
  MODIFY `id_purchase` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `search`
--
ALTER TABLE `search`
  MODIFY `id_search` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `search_settings`
--
ALTER TABLE `search_settings`
  MODIFY `id_search_settings` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `purchase`
--
ALTER TABLE `purchase`
  ADD CONSTRAINT `purchase_ibfk_1` FOREIGN KEY (`fk_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE,
  ADD CONSTRAINT `purchase_ibfk_2` FOREIGN KEY (`fk_product`) REFERENCES `product` (`id_product`) ON DELETE CASCADE;

--
-- Constraints for table `search`
--
ALTER TABLE `search`
  ADD CONSTRAINT `search_ibfk_1` FOREIGN KEY (`fk_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE,
  ADD CONSTRAINT `search_ibfk_2` FOREIGN KEY (`fk_search_settings`) REFERENCES `search_settings` (`id_search_settings`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
