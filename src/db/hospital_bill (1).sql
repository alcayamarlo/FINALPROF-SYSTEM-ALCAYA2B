-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 02, 2025 at 06:07 PM
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
  `BillingStatus` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
(54, 62, '2025-05-02 16:05:50', 'Patient', 'Active', '2025-05-02 16:05:50', 'maykel1');

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
(2, 'marlo', 'test', 'test@gmail.com', '09151391914', 'test', 'testtest1', 'Admin', 'Approved', '', '', ''),
(3, 'Mike Bus', 'Aloguinsan', 'mike@gmail.com', '09362263271', 'mike', 'yMc4xJPvpAFTlrXDBbMHuRVBXn6jdh1xeZ8fWuioAG8=', 'Patient', 'Approved', '', '', ''),
(4, 'anton', 'anton', 'anton21@gmail.com', '09332622492', 'anton12', 'yx+bYoBmKSkN+xJwlgt9l1anASwwH1IabCOk27ozkQQ=', 'Patient', 'Pending', '', '', ''),
(6, 'alcaya', 'alcaya', 'alcaya0@gmail.com', '09332522485', 'alcaya321', 'xRAm6cYqmwq/QRziumgwODEKu0tZjvV8QhM3xG0BWls=', 'Patient', 'Approved', '', '', ''),
(7, 'marlo', 'marlo', 'marlo21@gmail.com', '09372172421', 'marlo21', 'A62f8GI73OScnYuUnGVd3rLPrvxNmj0gJf6+onO4xz4=', 'Admin', 'Approved', '', '', ''),
(8, 'marloalcaya', 'alcaya', 'alcaya@gmail.com', '09332522382', 'alcaya3211', 'JO6JdVsw7fAqyh3em5n2rvQ9MK6zg8XD+xTXHNx+fDg=', 'Patient', 'Approved', '', '', ''),
(9, 'Adolf Borjassss', 'Tungkop City', 'adolf@gmail.com', '09151391914', 'adolf', 'DNPZqMo10kNNVXdENYxfQft5bsZLUTguRef5I2b8BS4=', 'Patient', 'Approved', '', '', ''),
(12, 'Marlo Alcaya', 'Aloguinsan Cebu', 'alcaya122@gmail.com', '09914410936', 'alcayamarlo12', 'yXtvcftQlpWmz188WU748rpwgnSQqybhm9HVGLAmwos=', 'Admin', 'Approved', '', '', ''),
(13, 'Alcaya Marlo', 'Aloguinsan Cebu', 'alcayamarlo2@gmail.com', '09511108871', 'alcayamarlo1', 'uGvdWf63o9o8/Wmb+LYtUZci2UCMhTvKnbh27LGX/Mw=', 'Doctor', 'Pending', '', '', ''),
(14, 'Bustamante  Mike', 'Aloguinsan', 'mikes@gmail.com', '09332622495', 'mikey', 'BJqTnZ7YU1OfE2LrAe8lpnNxGWHc9iSuOSCoKcwmIbw=', 'Doctor', 'Pending', '', '', ''),
(15, 'Mikey Bustamante', 'Aloguinsan  Cebu', 'mikee12@gmail.com', '09151391914', 'mikey1', 'jph89IXd/RqQRWy6OZvJuX8whxjvBU+d3xvqHgS2Vno=', 'Doctor', 'Pending', '', '', ''),
(16, 'test', 'test', 'test12@gmail.com', '09332622485', 'test321', 'k36NX7tIvUlJU2zWW401xCa4DS+DDFwwjizexCKuIkQ=', 'Doctor', 'Pending', '', '', ''),
(17, 'testtest', 'testtest', 'testtest@gmail.com', '09332522486', 'testtest1', 'XxqSkx8Ucu9jA+5rbzIN1MezvPLMPD6DlTcv8UU4uqI=', 'Doctor', 'Pending', '', '', ''),
(18, 'testtesttest', 'testtesttest', 'testtesttest@gmail.com', '09332622485', 'testtesttest', 'osltUY8QmaO2r+KeRDNA+fX98SiYU/wDSQhETyvLiYI=', 'Doctor', 'Pending', '', '', ''),
(19, 'huhay', 'huhayh', 'huhay@gmail.com', '09332522485', 'huhay', 'A2Vl8vmtP6ic/zVUYcTRvIfwB4VeFgwygpa6kpEHXAU=', 'Doctor', 'Pending', '', '', ''),
(20, 'wqewe', 'weqwe', 'qweqwe@gmail.com', '09332522843', 'testtest', 'NyaDNd1pMQRb3N+SYj/4GaZCRLU9DnRtQ4eXNJ1NpXg=', 'Doctor', 'Pending', '', '', ''),
(21, 'asda', 'asdas', 'asda@gmail.com', '09161491912', 'alcaya', 'KiWwxNIhGi5JgEmhGFpfNtCU5I459eyVqDM8a7Dgug0=', 'Doctor', 'Pending', '', '', ''),
(22, 'asds', 'asdads', 'asdas@gmail.com', '09151391914', 'hahaha', 'gFUD5pPECuelDZ1XQ0X2jNi8oSuX+fIPdPJsFXVmLBQ=', 'Doctor', 'Pending', '', '', ''),
(23, 'alogdelima', 'tungkop', 'delima@yahoo.com', '09876987681', 'alogdelima', '73l8gRjwLftklgfdXT+MdiMEjJwGPVMsyVxe16iYpk8=', 'Admin', 'Approved', '', '', ''),
(24, 'Jeswel Aliganga', 'Aloguinsan', 'jeswelgwapo12@gmail.com', '09332522485', 'jeswel', 'mAapqMuhnjpGoZcMA9Y0/i+fvqycUo0SBF38/yRLs/M=', 'Patient', 'Pending', '', '', ''),
(25, 'Anton Alforque', 'Aloguinsan', 'antonal4k@gmail.com', '09332622485', 'anton14', 'yx+bYoBmKSkN+xJwlgt9l1anASwwH1IabCOk27ozkQQ=', 'Patient', 'Pending', '', '', ''),
(26, 'asda', 'asda', 'asd@gmail.com', '09332522485', 'anton2', '3e3i4+6KiEhz7p014l7iTT4T8G/nUZwJThZ1AmLSzck=', 'Patient', 'Pending', '', '', ''),
(28, 'Alcaya Marlo V.', 'Aloguinsan Cebu', 'alcaya231@gmail.com', '09914410936', 'alcayamarlo123', '/388rxJjHNL7EbZAhnN/KfAMpy0lwZWgMGHAlBGmCWs=', 'Patient', 'Approved', '', '', ''),
(29, 'asdasd', 'asdasd', 'alcayakupal@gmail.com', '09151391914', 'alcayahaha', 'alcaya1234', 'Patient', 'Pending', '', '', ''),
(30, 'Marlo V. Alcaya', 'Aloguinsan Cebu', 'marlo@gmail.com', '09151391914', 'marloalcaya', 'YoubV4Pm4YD6gYzqzdEHd/0WYbdHRQXEF5zur2vpcMw=', 'Patient', 'Approved', '', '', ''),
(31, 'Marlo Alcaya', 'gwapoko', 'ashda@gmail.com', '09151391914', 'gwapo', 'gwapo123', 'Patient', 'Pending', '', '', ''),
(33, 'mike', 'mike', 'mike1@gmail.com', '09151391914', 'mikes', 'yMc4xJPvpAFTlrXDBbMHuRVBXn6jdh1xeZ8fWuioAG8=', 'Admin', 'Approved', '', '', ''),
(35, 'Clara Jane', 'Aloguinsan', 'clara@gmail.com', '09151391914', 'clara123', 'x7QY7OmZH45RguJX6OYJyNKnpJ09PxQGkqUqt1sZcDA=', 'Patient', 'Approved', '', '', ''),
(37, 'Marlo', 'asd', 'aadscad@gmail.com', '09151391914', 'marlo', 'JOkR7IU3r5kELW6wb+CF0DGhcPtOfDeuO6ilMekpQGw=', 'Patient', 'Pending', 'What is your favourite food?', 'bingka', ''),
(38, 'Marlo Alcaya', 'Aloguinsan Cebu', 'alcayahoy@gmail.com', '09332622485', 'alcayamarlo21', 'alcayamarlo21', 'Patient', 'Approved', 'What is your favourite movie?', 'Vivamax', ''),
(40, 'gwapoko', 'aloguinsan', 'alcay21312@gmail.com', '09151391914', 'gwapo1234', 'AJmgKSPgo3f5LRJYOjXB22ijvKc7lGrkppF+A+3fIJg=', 'Admin', 'Approved', 'What is your favourite food?', 'manggo', ''),
(42, 'Mayk', 'Aloguinsan', 'mayk@gmail.com', '09151391914', 'mikemike', 'yMc4xJPvpAFTlrXDBbMHuRVBXn6jdh1xeZ8fWuioAG8=', 'Admin', 'Pending', 'What is your Nickname?', 'mike', ''),
(43, 'asdas', 'asdasd', 'asda412@gmail.com', '09151391914', 'gwapo1', 'gwapo321', 'Admin', 'Approved', 'What is your favourite food?', 'orange', ''),
(44, 'Mike Gwapo', 'Aloguinsan', 'mayk2@gmail.com', '09332522486', 'mikemikes', 'DuZXgepBrfX4RST4n0OYayZ//wyEMl62BAAmaZc577s=', 'Admin', 'Approved', 'What is your favourite food?', 'apple', ''),
(45, 'Kapoya na ', 'Aloguinsan', 'kapoy@gmail.com', '09151391914', 'kapoyna123', 'eVmc4hEuCRoIXe9j1PApyw1jiCURD8vDEND5OSqY6y4=', 'Patient', 'Approved', 'What is your favourite food?', 'orange', ''),
(46, 'Tralalero ', 'Tralala', 'tralalalero@gmail.com', '09151391914', 'trara', '12+hIUd3t5gSwQx2P65XUSTHCW3GyKsR6bERknfCVK8=', 'Admin', 'Approved', 'What is your favourite food?', 'bayot', ''),
(47, 'Marlo Gwapo', 'Ahay', 'ahay@gmail.com', '09914410936', 'marlo123', 't2KI2ga5PH7olRxcEL4bEeO5LHCHaHB93KYqSOtl6CQ=', 'Patient', 'Approved', 'What is your favourite food?', 'manggo', ''),
(48, 'Bayot', 'Bayot', 'bayot@gmail.com', '09151391914', 'bayot123', 'N1ipZQOqKtUiS6OuTJ+pxzX7FN1EudnfRSm/6M6XbQ4=', 'Patient', 'Pending', 'What is your favourite food?', 'tambis', ''),
(49, 'hahahh', 'hahhah', 'haha@gmail.com', '09151391914', 'alcaya0', 'xRAm6cYqmwq/QRziumgwODEKu0tZjvV8QhM3xG0BWls=', 'Patient', 'Pending', 'What is your favourite movie?', 'avengers', ''),
(50, 'AHAHAHA', 'ajsbddbald', 'kasnda@gmail.com', '09151391914', 'clara', 'x7QY7OmZH45RguJX6OYJyNKnpJ09PxQGkqUqt1sZcDA=', 'Patient', 'Pending', 'What is your favourite food?', 'G0yRM9pzpxEyJAQxRAJ2WrDSP9NioWfW8MZbshURPZQ=', 'Null'),
(51, 'Andre Estrera', 'San Fernando ', 'andre@gmail.com', '09151391914', 'andre', 'iotT3QRqNjKLZhMV/9allMkimrRnL8YgfJ8/+ESVFvA=', 'Patient', 'Pending', 'What is your Nickname?', '2XmIVEekE6u21gal0PRcO3gJ5v3iyD8N80JvH8m/7Zc=', 'Null'),
(52, 'asdas', 'asdasd', 'asfasw@gmail.com', '09151391914', 'andre123', 'iotT3QRqNjKLZhMV/9allMkimrRnL8YgfJ8/+ESVFvA=', 'Patient', 'Approved', 'What is your Nickname?', '2XmIVEekE6u21gal0PRcO3gJ5v3iyD8N80JvH8m/7Zc=', 'Null'),
(53, 'asdasda', 'asdaj', 'asdassl@gmail.com', '09151391914', 'gwapo012', 'b+tCBQucdxB3v2y0wpLI6t+onMF2EfDNAQFCJ4TVaqk=', 'Patient', 'Approved', 'What is your Nickname?', 'M5O6PEvv1bBFjp9+3+3qlvrdSbBXkIzdgPSmc+nHrNg=', 'Null'),
(54, 'asdasa1wr`', 'asdasdadsadas', 'ajsdiashhfkba@gmail.com', '09151391914', 'marlogwapo', 'keyP+jB+A6NHJgR33gSrWWwS5tUaSyeN0ISkaLHJSZo=', 'Patient', 'Approved', 'What is your favourite movie?', 'iojDJA62T/fJJ/5x2FxJCU9vJ63KZ7F2stAqmwfot0U=', 'Null'),
(55, 'aknskbdblahj', 'jabsjasbdk', 'kjasdkjsa@gmail.com', '09332522486', 'hahaha123', 'gFUD5pPECuelDZ1XQ0X2jNi8oSuX+fIPdPJsFXVmLBQ=', 'Patient', 'Approved', 'What is your favourite food?', 'apple', 'NULL'),
(56, 'Marlo Alcaya', 'kasjahfas', 'jjajsslfhn@gmail.com', '09151391914', 'ataymar123', 'aibQOKNBvQiKPNYf+IyJpCzh/2kkXBDTO0VQDmGjbN8=', 'User Type : ', 'Pending', 'What is your favourite food?', 'apple', 'NULL'),
(57, 'Mike ', 'Mike', 'mike0@gmail.com', '09151391914', 'mikemikemike', 'pyEgDDdSiUeaAhJ3GPa8JbZMWl6Hpx7Jc18+GELTSIY=', 'User Type : ', 'Pending', 'What is your favourite food?', 'bingka', 'NULL'),
(58, 'asdasd', 'asfdasd', 'asdasfasfnsgd@gmail.com', '09151381814', 'kagwapo321', 'jOjaSZgoZ0RzELiTcl56uTm2TRFsFNfHYpF0GlhLUPY=', 'User Type : ', 'Pending', 'What is your Nickname?', 'kagwapo', 'NULL'),
(59, 'jashdiu', 'aksjdaishd', 'kasdkasnd@gmail.com', '09151391914', 'aldous12', '2+b55/fEpf/ToXUNhRmjU+lsn01q6sjF0NtXYq+ukvQ=', 'Patient', 'Approved', 'What is your favourite food?', 'miya', 'NULL'),
(60, 'Adolf', 'Aloguinsan', 'adolf12@gmail.com', '09151391914', 'adolf1234', '1BOxDuOgCIwUl31VksMtvoUG+C486Ji+D07VYSDZnGw=', 'Admin', 'Approved', 'What is your favourite movie?', 'avengers', 'NULL'),
(61, 'Marlo Alcaya', 'Aloguinsan', 'marlo32@gmail.com', '09151391914', 'marloalcaya0', 'H77GOYsTfDSOgfwiN/PJVq6o1LXgZPTaG+3hZNr5IMA=', 'Patient', 'Approved', 'What is your favourite food?', 'melon', ''),
(62, 'kansdhao', 'oiasjoiasj', 'ijsaod@gmail.com', '09151391914', 'maykel1', 't2KI2ga5PH7olRxcEL4bEeO5LHCHaHB93KYqSOtl6CQ=', 'Patient', 'Approved', 'What is your favourite food?', 'apple', ''),
(63, 'asfasfjak', 'asknfjasf', 'slfosnf@gmail.com', '09151391914', 'gwapohon', 'ma0nXRZUOp1eXi/wBIfE1QigEyut3xR7+Bs82IOUUWQ=', 'Patient', 'Pending', 'What is your favourite food?', 'apple', 'NULL'),
(64, 'asdasdasd', 'asdfasfs', 'asfasmfn@gmail.com', '09151391914', 'bibi1234', '1P9aeGbxWshgkN8WGPKyJNvn9M825DBq2B87n07ImOU=', 'Patient', 'Approved', 'What is your Nickname?', 'bibi', 'NULL');

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
  MODIFY `b_id` int(50) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tbl_log`
--
ALTER TABLE `tbl_log`
  MODIFY `log_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `p_id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=65;

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
