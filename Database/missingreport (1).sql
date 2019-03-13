-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 11, 2019 at 07:45 PM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 5.5.37

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `missingreport`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_notification`
--

CREATE TABLE `tbl_notification` (
  `id` int(11) NOT NULL,
  `notification_by` varchar(255) NOT NULL,
  `notification_to` varchar(255) NOT NULL,
  `post_id` int(11) NOT NULL,
  `category` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_posts_bike`
--

CREATE TABLE `tbl_posts_bike` (
  `id` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `bbrandname` varchar(255) NOT NULL,
  `bmodel` varchar(255) NOT NULL,
  `bcolor` varchar(255) NOT NULL,
  `bbikenumber` varchar(255) NOT NULL,
  `location` varchar(255) NOT NULL,
  `option` varchar(255) NOT NULL,
  `pname` varchar(255) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_posts_mobile`
--

CREATE TABLE `tbl_posts_mobile` (
  `id` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `color` varchar(255) NOT NULL,
  `model_no` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `model_name` varchar(255) NOT NULL,
  `size` varchar(255) NOT NULL,
  `location` varchar(255) NOT NULL,
  `option` varchar(255) NOT NULL,
  `pname` varchar(255) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_posts_nid`
--

CREATE TABLE `tbl_posts_nid` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `age` varchar(255) NOT NULL,
  `nid` varchar(255) NOT NULL,
  `gender` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `location` varchar(255) NOT NULL,
  `option` varchar(255) NOT NULL,
  `pname` varchar(255) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_posts_others`
--

CREATE TABLE `tbl_posts_others` (
  `id` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `color` varchar(255) NOT NULL,
  `location` varchar(255) NOT NULL,
  `option` varchar(255) NOT NULL,
  `pname` varchar(255) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_posts_passport`
--

CREATE TABLE `tbl_posts_passport` (
  `id` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `age` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `passport_id` varchar(255) NOT NULL,
  `gender` varchar(255) NOT NULL,
  `location` varchar(255) NOT NULL,
  `option` varchar(255) NOT NULL,
  `pname` varchar(255) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_posts_person`
--

CREATE TABLE `tbl_posts_person` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `age` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `height` varchar(255) NOT NULL,
  `gender` varchar(255) NOT NULL,
  `skin` varchar(255) NOT NULL,
  `location` varchar(255) NOT NULL,
  `pname` varchar(255) NOT NULL,
  `option` varchar(255) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_posts_wallet`
--

CREATE TABLE `tbl_posts_wallet` (
  `id` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `amount` varchar(255) NOT NULL,
  `color` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `location` varchar(255) NOT NULL,
  `option` varchar(255) NOT NULL,
  `pname` varchar(255) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_signup`
--

CREATE TABLE `tbl_signup` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `email` text NOT NULL,
  `password` text NOT NULL,
  `contact` text NOT NULL,
  `image` text NOT NULL,
  `latitude` text NOT NULL,
  `longitude` text NOT NULL,
  `city` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_signup`
--

INSERT INTO `tbl_signup` (`id`, `name`, `email`, `password`, `contact`, `image`, `latitude`, `longitude`, `city`) VALUES
(1, 'ggtgt', 'gmail.com', '202cb962ac59075b964b07152d234b70', '555555', 'photos/5bb9f5a162b2a.jpg', '31.24916', '121.4878983', 'fggyy'),
(2, 'tgfff', 'email', '024d7f84fff11dd7e8d9c510137a2381', '5555', 'photos/5bb9f5ed9e52a.jpg', '', '', 'ftgyt'),
(3, 'yeyeyw', 'sggssg', '77eda4f5471754d7868c65f7db1ddc34', '', 'photos/5bbd929fb1587.jpg', '', '', 'gehwyw'),
(4, 'Bappi', 'faymbappi@gmail.com', 'ec795f5aa8d6b395c7bf5c8a4a46b2b2', '', 'photos/5bbd97b341a20.jpg', '', '', 'Dhaka'),
(5, 'test', 'test', '098f6bcd4621d373cade4e832627b4f6', '', 'photos/5bc4f77319e3c.jpg', '', '', 'brazil'),
(6, 'Faym', 'faymbappi', 'ec795f5aa8d6b395c7bf5c8a4a46b2b2', '', 'photos/5bc67ae3bd4ef.jpg', '', '', 'Dhaka');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_notification`
--
ALTER TABLE `tbl_notification`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_posts_bike`
--
ALTER TABLE `tbl_posts_bike`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_posts_mobile`
--
ALTER TABLE `tbl_posts_mobile`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_posts_nid`
--
ALTER TABLE `tbl_posts_nid`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_posts_others`
--
ALTER TABLE `tbl_posts_others`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_posts_passport`
--
ALTER TABLE `tbl_posts_passport`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_posts_person`
--
ALTER TABLE `tbl_posts_person`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_posts_wallet`
--
ALTER TABLE `tbl_posts_wallet`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_signup`
--
ALTER TABLE `tbl_signup`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_notification`
--
ALTER TABLE `tbl_notification`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `tbl_posts_bike`
--
ALTER TABLE `tbl_posts_bike`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `tbl_posts_mobile`
--
ALTER TABLE `tbl_posts_mobile`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `tbl_posts_nid`
--
ALTER TABLE `tbl_posts_nid`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `tbl_posts_others`
--
ALTER TABLE `tbl_posts_others`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `tbl_posts_passport`
--
ALTER TABLE `tbl_posts_passport`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `tbl_posts_person`
--
ALTER TABLE `tbl_posts_person`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `tbl_posts_wallet`
--
ALTER TABLE `tbl_posts_wallet`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `tbl_signup`
--
ALTER TABLE `tbl_signup`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
