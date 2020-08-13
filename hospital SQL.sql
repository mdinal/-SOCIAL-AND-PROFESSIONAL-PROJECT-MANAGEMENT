-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 13, 2020 at 06:12 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.2.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hospital`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `UserName` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `ID` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`UserName`, `Password`, `ID`) VALUES
('admin', '07WfylTarjY=', 1);

-- --------------------------------------------------------

--
-- Table structure for table `doctor`
--

CREATE TABLE `doctor` (
  `Name` varchar(50) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Phone` int(10) NOT NULL,
  `Address` varchar(100) NOT NULL,
  `Specialist` varchar(50) NOT NULL,
  `ID` int(11) NOT NULL,
  `Password` varchar(50) NOT NULL DEFAULT '8mzCNJUNtvSJJSpQl9fUJg=='
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `doctor`
--

INSERT INTO `doctor` (`Name`, `Email`, `Phone`, `Address`, `Specialist`, `ID`, `Password`) VALUES
('kumara saparamadu', 'gamage@gamil.com', 112224586, '21/b,salmal uyanana,nugegoda', 'Surgeon', 1, '8mzCNJUNtvSJJSpQl9fUJg=='),
('k', 'A@gmail.com', 112224752, '21/3,GAMAGE', 'dental surgeon', 2, '8mzCNJUNtvSJJSpQl9fUJg=='),
('batman', 'rupa@gmail.com', 112296734, '31/1,galahitiyawa,negombo.', 'child specialist doctor', 4, '8mzCNJUNtvSJJSpQl9fUJg==');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `ID` int(11) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Date of Birth` varchar(50) NOT NULL,
  `Address` varchar(100) NOT NULL,
  `Phone` int(10) NOT NULL,
  `NIC` varchar(11) NOT NULL,
  `Position` varchar(20) NOT NULL,
  `Password` varchar(50) NOT NULL DEFAULT '8mzCNJUNtvSJJSpQl9fUJg=='
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`ID`, `Name`, `Date of Birth`, `Address`, `Phone`, `NIC`, `Position`, `Password`) VALUES
(2, 'kamal gamage', '2020-07-01', '31/2,rajakaruna mawatha,gampaha', 0, '9611224499V', 'receptionist', '8mzCNJUNtvSJJSpQl9fUJg=='),
(3, 'kamal gamage', '2020-07-01', '31/2,rajakaruna mawatha,gampaha', 112224586, '9611224499V', 'receptionist', '8mzCNJUNtvSJJSpQl9fUJg=='),
(6, 'dinal', '1996-02-22', '360/b,jayamawatha,andiamabalama', 1122515751, '96011111V', 'receptionist', '8mzCNJUNtvSJJSpQl9fUJg=='),
(8, 'isuru', '1999-08-27', 'game', 1122575241, '96011111V', 'receptionist', '8mzCNJUNtvSJJSpQl9fUJg==');

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE `patient` (
  `ID` int(11) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Age` int(5) NOT NULL,
  `Phone` int(10) NOT NULL,
  `Details` varchar(200) NOT NULL DEFAULT 'New Patient'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`ID`, `Name`, `Age`, `Phone`, `Details`) VALUES
(1, 'Dinal Maduranga', 20, 112224586, 'ledak'),
(2, 'saman', 19, 11227428, 'New Patient'),
(3, 'kumara', 12, 11224567, 'kaluwa'),
(4, 'gamunu', 12, 112276523, 'New Patient'),
(5, 'juna', 111, 112242311, 'd Patient');

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

CREATE TABLE `reservation` (
  `PID` int(11) NOT NULL,
  `DID` int(11) NOT NULL,
  `ID` int(11) NOT NULL,
  `Confirmed` tinyint(1) NOT NULL DEFAULT 0,
  `Number` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE `room` (
  `ID` int(11) NOT NULL,
  `Number` int(11) NOT NULL,
  `Floor` int(11) NOT NULL,
  `Availability` varchar(10) NOT NULL DEFAULT 'available'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `room`
--

INSERT INTO `room` (`ID`, `Number`, `Floor`, `Availability`) VALUES
(1, 1, 1, '1'),
(2, 2, 2, '0'),
(3, 2, 1, '0'),
(4, 21, 3, '1');

-- --------------------------------------------------------

--
-- Table structure for table `vehicle`
--

CREATE TABLE `vehicle` (
  `ID` int(11) NOT NULL,
  `Number` varchar(10) NOT NULL,
  `Availability` varchar(12) NOT NULL DEFAULT 'available',
  `Model` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `vehicle`
--

INSERT INTO `vehicle` (`ID`, `Number`, `Availability`, `Model`) VALUES
(1, 'CAQ-2022', 'Available', 'Nisan'),
(2, 'KK-8810', 'Available', 'Toyota');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `PID` (`PID`,`DID`),
  ADD KEY `DID` (`DID`);

--
-- Indexes for table `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `vehicle`
--
ALTER TABLE `vehicle`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `doctor`
--
ALTER TABLE `doctor`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `patient`
--
ALTER TABLE `patient`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `room`
--
ALTER TABLE `room`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `vehicle`
--
ALTER TABLE `vehicle`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`DID`) REFERENCES `doctor` (`ID`),
  ADD CONSTRAINT `reservation_ibfk_2` FOREIGN KEY (`PID`) REFERENCES `patient` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
