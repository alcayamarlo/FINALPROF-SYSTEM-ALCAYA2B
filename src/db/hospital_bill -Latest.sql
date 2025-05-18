-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 18, 2025 at 02:56 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hospital_bill`
--

-- --------------------------------------------------------

--
-- Table structure for table `billings`
--

CREATE TABLE `billings` (
  `b_id` int(50) NOT NULL,
  `p_id` int(50) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `treatmentType` varchar(250) NOT NULL,
  `TotalAmount` varchar(60) NOT NULL,
  `AmountPaid` varchar(50) NOT NULL,
  `Date` varchar(60) NOT NULL,
  `BillingStatus` varchar(100) NOT NULL,
  `Description` varchar(255) NOT NULL,
  `rate` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `returnAmount` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `billings`
--

INSERT INTO `billings` (`b_id`, `p_id`, `Name`, `treatmentType`, `TotalAmount`, `AmountPaid`, `Date`, `BillingStatus`, `Description`, `rate`, `quantity`, `returnAmount`) VALUES
(2, 63, 'asfasfjak', 'Agay', '5000.0', '3000.0', '2025-20-20', 'Unpaid', 'haahhhaa', 2500, 2, '-2000.0'),
(3, 64, 'asdasdasd', 'HAHAHAH', '2500.0', '2500.0', '2025-20-20', 'Paid', 'asdasdas', 2500, 1, '0.0'),
(4, 62, 'kansdhao', 'Nakaigit sa purol', '4000.0', '3000.0', '2025-10-10', 'Paid', 'Salamat', 4000, 1, '-1000.0'),
(5, 61, 'Marlo Alcaya', 'Unta mao nani', '5000000.0', '500000.0', '2025-20-20', 'Pending', 'nakaigit', 2500, 2000, '-4500000.0');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_log`
--

CREATE TABLE `tbl_log` (
  `log_id` int(11) NOT NULL,
  `p_id` int(11) NOT NULL,
  `login_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `u_type` varchar(250) NOT NULL,
  `log_status` varchar(250) NOT NULL,
  `logout_time` timestamp NOT NULL DEFAULT current_timestamp(),
  `u_username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_log`
--

INSERT INTO `tbl_log` (`log_id`, `p_id`, `login_time`, `u_type`, `log_status`, `logout_time`, `u_username`) VALUES
(1, 47, '2025-05-01 00:47:25', 'Failed - Inactive Account', 'Approved', '2025-05-01 00:47:25', 'marlo123'),
(2, 47, '2025-05-01 06:53:16', 'Success - User Login', 'Active', '2025-05-01 06:53:16', 'marlo123'),
(3, 47, '2025-05-01 06:53:16', 'Success - User Login', 'Active', '2025-05-01 06:53:16', 'marlo123'),
(4, 47, '2025-05-01 06:55:26', 'Failed - Inactive Account', 'Active', '2025-05-01 06:55:26', 'marlo123'),
(5, 47, '2025-05-01 06:55:37', 'Failed - Inactive Account', 'Active', '2025-05-01 06:55:37', 'marlo123'),
(6, 0, '2025-05-01 07:20:00', 'Success - User Action', 'Active', '2025-05-01 07:20:00', 'andre123'),
(7, 52, '2025-05-01 07:20:13', 'Failed - Inactive Account', 'Active', '2025-05-01 07:20:13', 'andre123'),
(8, 52, '2025-05-01 07:21:03', 'Failed - Inactive Account', 'Active', '2025-05-01 07:21:03', 'andre123'),
(9, 0, '2025-05-01 07:22:09', 'Success - User Action', 'Active', '2025-05-01 07:22:09', 'gwapo012'),
(10, 0, '2025-05-01 07:28:51', 'Success - User Action', 'Active', '2025-05-01 07:28:51', 'marlogwapo'),
(11, -1, '2025-05-01 07:29:05', 'Failed - Account Not Approved', 'Active', '2025-05-01 07:29:05', 'marlogwapo'),
(12, -1, '2025-05-01 07:29:38', 'Failed - Account Not Approved', 'Active', '2025-05-01 07:29:38', 'marlogwapo'),
(13, 0, '2025-05-01 07:35:07', 'Success - User Action', 'Active', '2025-05-01 07:35:07', 'hahaha123'),
(14, -1, '2025-05-01 07:35:57', 'Failed - Account Not Approved', 'Active', '2025-05-01 07:35:57', 'hahaha'),
(15, -1, '2025-05-01 07:36:34', 'Failed - Account Not Approved', 'Active', '2025-05-01 07:36:34', 'hahaha'),
(16, -1, '2025-05-01 07:37:23', 'Failed - Account Not Approved', 'Active', '2025-05-01 07:37:23', 'hahaha'),
(17, 22, '2025-05-01 07:47:19', 'Failed - Inactive Account', 'Active', '2025-05-01 07:47:19', 'hahaha'),
(18, 0, '2025-05-01 07:48:35', 'Success - User Action', 'Active', '2025-05-01 07:48:35', 'ataymar123'),
(19, 0, '2025-05-01 07:56:39', 'Success - User Action', 'Active', '2025-05-01 07:56:39', 'mikemikemike'),
(20, 0, '2025-05-01 08:00:02', 'Success - User Action', 'Active', '2025-05-01 08:00:02', 'kagwapo321'),
(21, 0, '2025-05-01 08:04:03', 'Success - User Action', 'Active', '2025-05-01 08:04:03', 'aldous12'),
(22, 59, '2025-05-01 08:05:00', 'Success - User Login', 'Active', '2025-05-01 08:05:00', 'aldous12'),
(23, 59, '2025-05-01 08:06:31', 'Success - User Login', 'Active', '2025-05-01 08:06:31', 'aldous12'),
(24, 0, '2025-05-02 02:04:53', 'Success - User Action', 'Active', '2025-05-02 02:04:53', 'adolf1234'),
(25, 60, '2025-05-02 02:05:26', 'Success - Admin Login', 'Active', '2025-05-02 02:05:26', 'adolf1234'),
(26, 0, '2025-05-02 10:43:00', 'Success - User Action', 'Active', '2025-05-02 10:43:00', 'marloalcaya0'),
(27, -1, '2025-05-02 10:48:34', 'Failed - Invalid Login', 'Active', '2025-05-02 10:48:34', 'marloalcaya0'),
(28, 61, '2025-05-02 10:49:39', 'Success - User Login', 'Active', '2025-05-02 10:49:39', 'marloalcaya0'),
(29, 0, '2025-05-02 11:20:57', 'Success - User Action', 'Active', '2025-05-02 11:20:57', 'maykel1'),
(30, 62, '2025-05-02 11:23:35', 'Success - User Login', 'Active', '2025-05-02 11:23:35', 'maykel1'),
(31, 0, '2025-05-02 13:38:54', 'Success - User Action', 'Active', '2025-05-02 13:38:54', 'gwapohon'),
(32, 63, '2025-05-02 13:40:59', 'Failed - Inactive Account', 'Active', '2025-05-02 13:40:59', 'gwapohon'),
(33, 0, '2025-05-02 13:42:24', 'Success - User Action', 'Active', '2025-05-02 13:42:24', 'bibi1234'),
(34, 64, '2025-05-02 13:43:13', 'Success - User Login', 'Active', '2025-05-02 13:43:13', 'bibi1234'),
(35, 62, '2025-05-02 15:17:40', 'Success - User Action', 'Active', '2025-05-02 15:17:40', 'maykel1'),
(36, -1, '2025-05-02 15:18:25', 'Failed - Invalid Login', 'Active', '2025-05-02 15:18:25', 'maykel1'),
(37, -1, '2025-05-02 15:18:30', 'Failed - Invalid Login', 'Active', '2025-05-02 15:18:30', 'maykel1'),
(38, 62, '2025-05-02 15:18:48', 'Success - User Action', 'Active', '2025-05-02 15:18:48', 'maykel1'),
(39, 62, '2025-05-02 15:18:58', 'Success - User Login', 'Active', '2025-05-02 15:18:58', 'maykel1'),
(40, 62, '2025-05-02 15:23:39', 'Success - User Login', 'Active', '2025-05-02 15:23:39', 'maykel1'),
(41, 62, '2025-05-02 15:25:10', 'Success - User Login', 'Active', '2025-05-02 15:25:10', 'maykel1'),
(42, 62, '2025-05-02 15:26:14', 'Success - User Login', 'Active', '2025-05-02 15:26:14', 'maykel1'),
(43, 62, '2025-05-02 15:26:44', 'Patient', 'Active', '2025-05-02 15:26:44', 'maykel1'),
(44, 62, '2025-05-02 15:26:48', 'Patient', 'Active', '2025-05-02 15:26:48', 'maykel1'),
(45, 62, '2025-05-02 15:52:43', 'Success - User Login', 'Active', '2025-05-02 15:52:43', 'maykel1'),
(46, 62, '2025-05-02 15:53:29', 'Success - User Login', 'Active', '2025-05-02 15:53:29', 'maykel1'),
(47, 62, '2025-05-02 15:54:22', 'Patient', 'Active', '2025-05-02 15:54:22', 'maykel1'),
(48, 62, '2025-05-02 15:54:25', 'Patient', 'Active', '2025-05-02 15:54:25', 'maykel1'),
(49, 62, '2025-05-02 15:54:29', 'Patient', 'Active', '2025-05-02 15:54:29', 'maykel1'),
(50, 62, '2025-05-02 15:56:23', 'Success - User Login', 'Active', '2025-05-02 15:56:23', 'maykel1'),
(51, 62, '2025-05-02 15:56:48', 'Patient', 'Active', '2025-05-02 15:56:48', 'maykel1'),
(52, 62, '2025-05-02 15:56:53', 'Patient', 'Active', '2025-05-02 15:56:53', 'maykel1'),
(53, 62, '2025-05-02 16:05:32', 'Success - User Login', 'Active', '2025-05-02 16:05:32', 'maykel1'),
(54, 62, '2025-05-02 16:05:50', 'Patient', 'Active', '2025-05-02 16:05:50', 'maykel1'),
(55, 0, '2025-05-18 11:36:13', 'Success - User Action', 'Active', '2025-05-18 11:36:13', 'gwapomarlo'),
(56, -1, '2025-05-18 11:36:24', 'Failed - Invalid Login', 'Active', '2025-05-18 11:36:24', 'marloalcaya'),
(57, -1, '2025-05-18 11:51:13', 'Failed - Invalid Login', 'Active', '2025-05-18 11:51:13', 'marloalcaya'),
(58, 65, '2025-05-18 11:52:53', 'Success - Admin Login', 'Active', '2025-05-18 11:52:53', 'gwapomarlo'),
(59, 65, '2025-05-18 11:54:39', 'Success - Admin Login', 'Active', '2025-05-18 11:54:39', 'gwapomarlo'),
(60, 65, '2025-05-18 11:56:20', 'Success - Admin Login', 'Active', '2025-05-18 11:56:20', 'gwapomarlo'),
(61, 65, '2025-05-18 12:00:44', 'Success - Admin Login', 'Active', '2025-05-18 12:00:44', 'gwapomarlo'),
(62, -1, '2025-05-18 12:05:02', 'Failed - Invalid Login', 'Active', '2025-05-18 12:05:02', 'marloalcaya'),
(63, -1, '2025-05-18 12:05:10', 'Failed - Invalid Login', 'Active', '2025-05-18 12:05:10', 'gwapo'),
(64, -1, '2025-05-18 12:05:15', 'Failed - Invalid Login', 'Active', '2025-05-18 12:05:15', 'gwapomarlo'),
(65, 65, '2025-05-18 12:05:23', 'Success - Admin Login', 'Active', '2025-05-18 12:05:23', 'gwapomarlo'),
(66, 65, '2025-05-18 12:11:02', 'Success - Admin Login', 'Active', '2025-05-18 12:11:02', 'gwapomarlo'),
(67, 65, '2025-05-18 12:25:24', 'Success - Admin Login', 'Active', '2025-05-18 12:25:24', 'gwapomarlo'),
(68, 65, '2025-05-18 12:30:33', 'Success - Admin Login', 'Active', '2025-05-18 12:30:33', 'gwapomarlo'),
(69, 65, '2025-05-18 12:35:42', 'Success - Admin Login', 'Active', '2025-05-18 12:35:42', 'gwapomarlo'),
(70, 0, '2025-05-18 12:45:16', 'Success - User Action', 'Active', '2025-05-18 12:45:16', 'marlo1234'),
(71, 66, '2025-05-18 12:45:24', 'Success - Admin Login', 'Active', '2025-05-18 12:45:24', 'marlo1234');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `p_id` int(50) NOT NULL,
  `fn` varchar(50) NOT NULL,
  `cityAddress` varchar(50) NOT NULL,
  `email` varchar(250) NOT NULL,
  `contactNo` varchar(250) NOT NULL,
  `username` varchar(250) NOT NULL,
  `password` varchar(150) NOT NULL,
  `usertype` varchar(100) NOT NULL,
  `status` varchar(100) NOT NULL,
  `securityQ` varchar(255) NOT NULL,
  `answer` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`p_id`, `fn`, `cityAddress`, `email`, `contactNo`, `username`, `password`, `usertype`, `status`, `securityQ`, `answer`, `image`) VALUES
(61, 'Marlo Alcaya', 'Aloguinsan', 'marlo32@gmail.com', '09151391914', 'marloalcaya0', 'H77GOYsTfDSOgfwiN/PJVq6o1LXgZPTaG+3hZNr5IMA=', 'Patient', 'Approved', 'What is your favourite food?', 'melon', ''),
(62, 'kansdhao', 'oiasjoiasj', 'ijsaod@gmail.com', '09151391914', 'maykel1', 't2KI2ga5PH7olRxcEL4bEeO5LHCHaHB93KYqSOtl6CQ=', 'Patient', 'Approved', 'What is your favourite food?', 'apple', ''),
(63, 'asfasfjak', 'asknfjasf', 'slfosnf@gmail.com', '09151391914', 'gwapohon', 'ma0nXRZUOp1eXi/wBIfE1QigEyut3xR7+Bs82IOUUWQ=', 'Patient', 'Pending', 'What is your favourite food?', 'apple', 'NULL'),
(64, 'asdasdasd', 'asdfasfs', 'asfasmfn@gmail.com', '09151391914', 'bibi1234', '1P9aeGbxWshgkN8WGPKyJNvn9M825DBq2B87n07ImOU=', 'Patient', 'Approved', 'What is your Nickname?', 'bibi', 'NULL'),
(65, 'Marlo Alcaya', 'Aloguinsan Cebu', 'alcayamarlo02@gmail.com', '09151391914', 'gwapomarlo', 'CYNRIzG32dq7xxyFMuvxi4IDSJ51qJvKv8uGdrYsy98=', 'Admin', 'Approved', 'What is your favourite movie?', 'tarzan', 'NULL'),
(66, 'asdas', 'asdads', 'asdaskfjam@gmail.com', '09151391914', 'marlo1234', 'KiWwxNIhGi5JgEmhGFpfNtCU5I459eyVqDM8a7Dgug0=', 'Admin', 'Pending', 'What is your Nickname?', 'gwapo', 'NULL');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `billings`
--
ALTER TABLE `billings`
  ADD PRIMARY KEY (`b_id`),
  ADD KEY `p_id` (`p_id`);

--
-- Indexes for table `tbl_log`
--
ALTER TABLE `tbl_log`
  ADD PRIMARY KEY (`log_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`p_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `billings`
--
ALTER TABLE `billings`
  MODIFY `b_id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `tbl_log`
--
ALTER TABLE `tbl_log`
  MODIFY `log_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=72;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `p_id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=67;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `billings`
--
ALTER TABLE `billings`
  ADD CONSTRAINT `p_id` FOREIGN KEY (`p_id`) REFERENCES `users` (`p_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
