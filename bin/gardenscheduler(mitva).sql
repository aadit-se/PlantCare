-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 21, 2021 at 11:30 AM
-- Server version: 5.1.53
-- PHP Version: 5.3.4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `gardenscheduler`
--

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Table structure for table `natureom_plantcare_action`
--

CREATE TABLE IF NOT EXISTS `natureom_plantcare_action` (
  `plantcare_action_id` int(11) NOT NULL,
  `plantcare_action_description` varchar(255) DEFAULT NULL,
  `plantcare_action_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`plantcare_action_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `natureom_plantcare_action`
--

INSERT INTO `natureom_plantcare_action` (`plantcare_action_id`, `plantcare_action_description`, `plantcare_action_name`) VALUES
(1, NULL, 'wattering'),
(2, NULL, 'trimming'),
(3, NULL, 'fertilizing'),
(4, NULL, 'pesticide'),
(5, NULL, 'soil cultivation');

-- --------------------------------------------------------

--
-- Table structure for table `natureom_role`
--

CREATE TABLE IF NOT EXISTS `natureom_role` (
  `role_id` bigint(20) NOT NULL,
  `role_description` varchar(255) DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `natureom_role`
--

INSERT INTO `natureom_role` (`role_id`, `role_description`, `role_name`) VALUES
(687, 'owner of garden, using facilities from plant master', 'customer'),
(712, 'takes care of plants (gardenre)', 'plant master'),
(731, 'supervises action of plant masters', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `natureom_user`
--

CREATE TABLE IF NOT EXISTS `natureom_user` (
  `user_id` bigint(20) NOT NULL,
  `user_city` varchar(70) DEFAULT NULL,
  `user_contact` bigint(20) DEFAULT NULL,
  `user_dob` date DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  `user_gender` varchar(255) DEFAULT NULL,
  `user_home` varchar(70) DEFAULT NULL,
  `user_landmark` varchar(70) DEFAULT NULL,
  `user_locality` varchar(70) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_pin` int(11) DEFAULT NULL,
  `user_state` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FKfp2rg054y17mmo6uh9lc894my` (`user_state`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `natureom_user`
--

INSERT INTO `natureom_user` (`user_id`, `user_city`, `user_contact`, `user_dob`, `user_email`, `user_gender`, `user_home`, `user_landmark`, `user_locality`, `user_name`, `user_pin`, `user_state`) VALUES
(782, 'ahmedabad', 70700700, '1982-10-01', 'ramu3@g.c', 'male', 'chil 1', 'astodia', 'maninagar', 'ramu 3', 380001, 7),
(855, 'ahmedabad', 70700700, '1982-10-03', 'ramu2@g.c', 'male', 'chil 1', 'astodia', 'maninagar', 'ramu 2', 380001, 7),
(885, 'ahmedabad', 70700700, '1982-10-04', 'ramu1@g.c', 'male', 'chil 1', 'astodia', 'maninagar', 'ramu 1', 380001, 7),
(914, 'ahmedabad', 5252552, '1990-10-10', 'avi@g.c', 'male', '111 GodrejCity', 'GodrejCity', 'sola', 'avinash', 380004, 7),
(939, 'ahmedabad', 1010100101, '1998-10-07', 'adi@g.c', 'male', '4-c skyline', 'AIS school', 'bodekdev', 'adit', 380015, 7),
(963, 'ahmedabad', 111111, '1998-10-18', 'rahul@g.c', 'male', 'shridaraliya', 'bhagban patyplot', 'sindhbhavan', 'rahul', 3800156, 7);

-- --------------------------------------------------------

--
-- Table structure for table `natureom_user_role`
--

CREATE TABLE IF NOT EXISTS `natureom_user_role` (
  `user_role_id` bigint(20) NOT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`user_role_id`),
  KEY `FKpmbmxnue4dqj4bbij5aji7m7n` (`role_id`),
  KEY `FKepcaa2ddk4jmbn9o57lm56l99` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `natureom_user_role`
--

INSERT INTO `natureom_user_role` (`user_role_id`, `role_name`, `user_name`, `role_id`, `user_id`) VALUES
(782, 'plant master', 'ramu 3', 712, 782),
(855, 'plant master', 'ramu 2', 712, 855),
(885, 'plant master', 'ramu 1', 712, 885),
(914, 'admin', 'avinash', 731, 914),
(939, 'customer', 'adit', 687, 939),
(963, 'customer', 'rahul', 687, 963);

-- --------------------------------------------------------

--
-- Table structure for table `plant_care_interval`
--

CREATE TABLE IF NOT EXISTS `plant_care_interval` (
  `plant_category_id` int(11) NOT NULL,
  `cleaning_interval` int(11) DEFAULT NULL,
  `fertilization_interval` int(11) DEFAULT NULL,
  `pesticide_spray_interval` int(11) DEFAULT NULL,
  `plant_category_name` varchar(255) DEFAULT NULL,
  `soil_cultivation_interval` int(11) DEFAULT NULL,
  `trimming_interval` int(11) DEFAULT NULL,
  `watering_interval` int(11) DEFAULT NULL,
  PRIMARY KEY (`plant_category_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `plant_care_interval`
--

INSERT INTO `plant_care_interval` (`plant_category_id`, `cleaning_interval`, `fertilization_interval`, `pesticide_spray_interval`, `plant_category_name`, `soil_cultivation_interval`, `trimming_interval`, `watering_interval`) VALUES
(1, 35, 25, 15, 'tulsi', 18, 10, 2),
(2, 30, 30, 20, 'aloevera', 35, 40, 7),
(3, 20, 20, 13, 'rose', 16, 9, 2),
(4, 45, 22, 17, 'tulip', 25, 9, 2);

-- --------------------------------------------------------

--
-- Table structure for table `plant_space`
--

CREATE TABLE IF NOT EXISTS `plant_space` (
  `plant_space_id` bigint(20) NOT NULL,
  `plant_space_address` varchar(255) DEFAULT NULL,
  `plant_space_customer_name` varchar(255) DEFAULT NULL,
  `plant_space_desc` varchar(255) DEFAULT NULL,
  `plant_space_landmark` varchar(255) DEFAULT NULL,
  `plant_space_name` varchar(255) DEFAULT NULL,
  `plant_space_plantmaster_name` varchar(255) DEFAULT NULL,
  `plant_space_status` varchar(255) DEFAULT NULL,
  `plant_space_customer_id` bigint(20) DEFAULT NULL,
  `plant_space_plantmaster_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`plant_space_id`),
  KEY `FKjxed2bxet7sg8qjbtqd60d5g3` (`plant_space_customer_id`),
  KEY `FK959tyndid7c2cqrbn4rnn14u1` (`plant_space_plantmaster_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `plant_space`
--

INSERT INTO `plant_space` (`plant_space_id`, `plant_space_address`, `plant_space_customer_name`, `plant_space_desc`, `plant_space_landmark`, `plant_space_name`, `plant_space_plantmaster_name`, `plant_space_status`, `plant_space_customer_id`, `plant_space_plantmaster_id`) VALUES
(5, 'ahmedabad', 'rahul', 'rahul garden', 'rahul house', 'rahul house', NULL, NULL, 963, 885),
(30, 'ahmedabad', 'adit', 'adit garden', 'bodakdev', 'adit house', NULL, NULL, 939, 855),
(49, 'ahmedabad', 'adit', 'adit office garden', 'sindhu bhavan', 'adit office', NULL, NULL, 939, 855);

-- --------------------------------------------------------

--
-- Table structure for table `plant_space_spot`
--

CREATE TABLE IF NOT EXISTS `plant_space_spot` (
  `plant_space_spot_id` bigint(20) NOT NULL,
  `plant_category_name` varchar(255) DEFAULT NULL,
  `plant_space_name` varchar(255) DEFAULT NULL,
  `plant_space_spot_desc` varchar(255) DEFAULT NULL,
  `plant_space_spot_name` varchar(255) DEFAULT NULL,
  `plan_spot_status` varchar(255) DEFAULT NULL,
  `plant_category_id` int(11) DEFAULT NULL,
  `plant_space_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`plant_space_spot_id`),
  KEY `FKj2ed51nfrfffn4o2wfr8f0sbd` (`plant_category_id`),
  KEY `FKgux2cna9u74ei5ja0hj0s580k` (`plant_space_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `plant_space_spot`
--

INSERT INTO `plant_space_spot` (`plant_space_spot_id`, `plant_category_name`, `plant_space_name`, `plant_space_spot_desc`, `plant_space_spot_name`, `plan_spot_status`, `plant_category_id`, `plant_space_id`) VALUES
(171, 'aloevera', 'rahul house', 'second of gaden', 'garden-1 spot-2', NULL, 2, 5),
(330, 'tulip', 'rahul house', 'fourth of gaden', 'garden-1 spot-4', NULL, 4, 5),
(419, 'tulip', 'adit house', 'first of gaden', 'garden-2 spot-1', NULL, 4, 30),
(512, 'rose', 'adit house', 'third of gaden', 'garden-2 spot-3', NULL, 3, 30),
(598, 'tulip', 'adit house', 'fourth of gaden', 'garden-2 spot-4', NULL, 4, 30),
(691, 'tulsi', 'adit office', 'first of gaden', 'garden-3 spot-1', NULL, 1, 49),
(824, 'aloevera', 'adit office', 'second of gaden', 'garden-3 spot-2', NULL, 2, 49),
(919, 'tulip', 'adit office', 'fourth of gaden', 'garden-3 spot-4', NULL, 4, 49);

-- --------------------------------------------------------

--
-- Table structure for table `plant_spot_log`
--

CREATE TABLE IF NOT EXISTS `plant_spot_log` (
  `log_id` bigint(20) NOT NULL,
  `action_done` varchar(255) DEFAULT NULL,
  `action_timing` datetime DEFAULT NULL,
  `spot_name` varchar(255) DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `plant_master_id` bigint(20) DEFAULT NULL,
  `spot_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`log_id`),
  KEY `FKl4eennilpttcfy2skkknmmjai` (`customer_id`),
  KEY `FK7bubm1mytod7npwdc1e40c3ju` (`plant_master_id`),
  KEY `FKf4bo7w108la2poqky6lkx8adm` (`spot_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `plant_spot_log`
--


-- --------------------------------------------------------

--
-- Table structure for table `space_spot_details`
--

CREATE TABLE IF NOT EXISTS `space_spot_details` (
  `detail_id` bigint(20) NOT NULL,
  `last_fertilized` date DEFAULT NULL,
  `last_pesticide` date DEFAULT NULL,
  `last_soil_cultivated` date DEFAULT NULL,
  `last_trim` date DEFAULT NULL,
  `last_watered` date DEFAULT NULL,
  `last_cleaned` date DEFAULT NULL,
  `next_clean` date DEFAULT NULL,
  `next_fertilize` date DEFAULT NULL,
  `next_pesticide` date DEFAULT NULL,
  `next_soil_cultivated` date DEFAULT NULL,
  `next_trim` date DEFAULT NULL,
  `next_water` date DEFAULT NULL,
  `details_spot_name` varchar(255) DEFAULT NULL,
  `details_category_id` int(11) DEFAULT NULL,
  `details_space_id` bigint(20) DEFAULT NULL,
  `details_spot_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`detail_id`),
  KEY `FK8uuwooemx8gpktwydbgqwkeql` (`details_category_id`),
  KEY `FK9gfoi6xwfya76q4gji7x1r1a0` (`details_space_id`),
  KEY `FKlgdia02dqvjfvc11ypnrty1m6` (`details_spot_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `space_spot_details`
--

INSERT INTO `space_spot_details` (`detail_id`, `last_fertilized`, `last_pesticide`, `last_soil_cultivated`, `last_trim`, `last_watered`, `last_cleaned`, `next_clean`, `next_fertilize`, `next_pesticide`, `next_soil_cultivated`, `next_trim`, `next_water`, `details_spot_name`, `details_category_id`, `details_space_id`, `details_spot_id`) VALUES
(234, NULL, NULL, NULL, NULL, NULL, NULL, '2021-06-10', '2021-06-10', '2021-05-31', '2021-06-15', '2021-06-20', '2021-05-15', 'garden-1 spot-2', 2, 5, 171),
(401, NULL, NULL, NULL, NULL, NULL, NULL, '2021-06-25', '2021-06-02', '2021-05-28', '2021-06-05', '2021-05-20', '2021-05-14', 'garden-1 spot-4', 4, 5, 330),
(495, NULL, NULL, NULL, NULL, NULL, NULL, '2021-06-25', '2021-06-02', '2021-05-28', '2021-06-05', '2021-05-20', '2021-05-13', 'garden-2 spot-1', 4, 30, 419),
(577, NULL, NULL, NULL, NULL, NULL, NULL, '2021-05-31', '2021-05-31', '2021-05-24', '2021-05-27', '2021-05-20', '2021-05-13', 'garden-2 spot-3', 3, 30, 512),
(666, NULL, NULL, NULL, NULL, NULL, NULL, '2021-06-25', '2021-06-02', '2021-05-28', '2021-06-05', '2021-05-20', '2021-05-13', 'garden-2 spot-4', 4, 30, 598),
(772, NULL, NULL, NULL, NULL, NULL, NULL, '2021-06-15', '2021-06-05', '2021-05-26', '2021-05-29', '2021-05-21', '2021-05-09', 'garden-3 spot-1', 1, 49, 691),
(885, NULL, NULL, NULL, NULL, NULL, NULL, '2021-06-10', '2021-06-10', '2021-05-31', '2021-06-15', '2021-06-20', '2021-05-09', 'garden-3 spot-2', 2, 49, 824),
(984, NULL, NULL, NULL, NULL, NULL, NULL, '2021-06-25', '2021-06-02', '2021-05-28', '2021-06-05', '2021-05-20', '2021-05-09', 'garden-3 spot-4', 4, 49, 919);

-- --------------------------------------------------------

--
-- Table structure for table `state_table`
--

CREATE TABLE IF NOT EXISTS `state_table` (
  `stateid` int(11) NOT NULL,
  `state_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`stateid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `state_table`
--

INSERT INTO `state_table` (`stateid`, `state_name`) VALUES
(1, 'Andhra Pradesh'),
(2, 'Arunachal Pradesh'),
(3, 'Assam'),
(4, 'Bihar'),
(5, 'Chhattisgarh'),
(6, 'Goa'),
(7, 'Gujarat'),
(8, 'Haryana'),
(9, 'Himachal Pradesh'),
(10, 'Mizoram'),
(11, 'Jharkhand'),
(12, 'Karnataka'),
(13, 'Kerala'),
(14, 'Madhya Pradesh'),
(15, 'Maharastra'),
(16, 'Manipur'),
(17, 'Meghalaya'),
(18, 'Mizoram'),
(19, 'Nagaland'),
(20, 'Odisha'),
(21, 'Punjab'),
(22, 'Rajasthan'),
(23, 'Sikkim'),
(24, 'Tamil Nadu'),
(25, 'Telangana'),
(26, 'Tripura'),
(27, 'Uttar Pradesh'),
(28, 'Uttarakhand'),
(29, 'West Bengal');
