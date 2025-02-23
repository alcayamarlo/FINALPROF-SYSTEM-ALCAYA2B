-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 23, 2025 at 09:05 AM
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
-- Database: `hospital_billing`
--

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE `patient` (
  `p_id` int(20) NOT NULL,
  `fn` varchar(50) NOT NULL,
  `city_address` varchar(50) NOT NULL,
  `dateofBirth` varchar(50) NOT NULL,
  `email` varchar(60) NOT NULL,
  `contactNo` varchar(50) NOT NULL,
  `username` varchar(60) NOT NULL,
  `password` varchar(100) NOT NULL,
  `confirmpass` varchar(100) NOT NULL,
  `status` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`p_id`, `fn`, `city_address`, `dateofBirth`, `email`, `contactNo`, `username`, `password`, `confirmpass`, `status`) VALUES
(1, 'Marlo Alcaya', 'Bojo Aloguinsan Cebu', '10/13/2005', 'alcayamarlo12@gmail.com', '09151391914', 'alcayamarlo', 'alcayamarlo!', 'alcayamarlo!', 'Pending'),
(2, 'Alcaya Marlo', 'Aloguinsan', '10/13/2005', 'alcayamarlo12@gmail.com', '09151391914', 'marlo1', 'marloalcaya', 'marloalcaya', 'Pending'),
(3, 'asdasd', '123123', 'qweqwe', 'alcayamarlo12@gmail.com', '09151391914', 'alcaya1', '!2345678', '!2345678', 'Pending'),
(4, 'Gwapo', 'asda', 'asdasd', 'alcayamarlo12@gmail.com', '09151391914', 'gwapo123', '12345678', '12345678', 'Pending');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`p_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `patient`
--
ALTER TABLE `patient`
  MODIFY `p_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
