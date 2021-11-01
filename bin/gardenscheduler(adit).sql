/*
SQLyog Community v13.1.7 (64 bit)
MySQL - 8.0.24 : Database - gardenscheduler
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`gardenscheduler` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `gardenscheduler`;

/*Table structure for table `hibernate_sequence` */

DROP TABLE IF EXISTS `hibernate_sequence`;

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `hibernate_sequence` */

insert  into `hibernate_sequence`(`next_val`) values 
(30);

/*Table structure for table `natureom_plantcare_action` */

DROP TABLE IF EXISTS `natureom_plantcare_action`;

CREATE TABLE `natureom_plantcare_action` (
  `plantcare_action_id` int NOT NULL,
  `plantcare_action_description` varchar(255) DEFAULT NULL,
  `plantcare_action_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`plantcare_action_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `natureom_plantcare_action` */

insert  into `natureom_plantcare_action`(`plantcare_action_id`,`plantcare_action_description`,`plantcare_action_name`) values 
(1,NULL,'wattering'),
(2,NULL,'trimming'),
(3,NULL,'fertilizing'),
(4,NULL,'pesticide'),
(5,NULL,'soil cultivation');

/*Table structure for table `natureom_role` */

DROP TABLE IF EXISTS `natureom_role`;

CREATE TABLE `natureom_role` (
  `role_id` bigint NOT NULL,
  `role_description` varchar(255) DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `natureom_role` */

insert  into `natureom_role`(`role_id`,`role_description`,`role_name`) values 
(687,'owner of garden, using facilities from plant master','customer'),
(712,'takes care of plants (gardenre)','plant master'),
(731,'supervises action of plant masters','admin');

/*Table structure for table `natureom_user` */

DROP TABLE IF EXISTS `natureom_user`;

CREATE TABLE `natureom_user` (
  `user_id` bigint NOT NULL,
  `user_city` varchar(70) DEFAULT NULL,
  `user_contact` bigint DEFAULT NULL,
  `user_dob` date DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  `user_gender` varchar(255) DEFAULT NULL,
  `user_home` varchar(70) DEFAULT NULL,
  `user_landmark` varchar(70) DEFAULT NULL,
  `user_locality` varchar(70) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_pin` int DEFAULT NULL,
  `user_state` varchar(70) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `natureom_user` */

insert  into `natureom_user`(`user_id`,`user_city`,`user_contact`,`user_dob`,`user_email`,`user_gender`,`user_home`,`user_landmark`,`user_locality`,`user_name`,`user_pin`,`user_state`) values 
(782,'ahmedabad',70700700,'1982-10-01','ramu3@g.c','male','chil 1','astodia','maninagar','ramu 3',380001,'7'),
(855,'ahmedabad',70700700,'1982-10-03','ramu2@g.c','male','chil 1','astodia','maninagar','ramu 2',380001,'7'),
(885,'ahmedabad',70700700,'1982-10-04','ramu1@g.c','male','chil 1','astodia','maninagar','ramu 1',380001,'7'),
(914,'ahmedabad',5252552,'1990-10-10','avi@g.c','male','111 GodrejCity','GodrejCity','sola','avinash',380004,'7'),
(939,'ahmedabad',1010100101,'1998-10-07','adi@g.c','male','4-c skyline','AIS school','bodekdev','adit',380015,'7'),
(963,'ahmedabad',111111,'1998-10-18','rahul@g.c','male','shridaraliya','bhagban patyplot','sindhbhavan','rahul',3800156,'7');

/*Table structure for table `natureom_user_role` */

DROP TABLE IF EXISTS `natureom_user_role`;

CREATE TABLE `natureom_user_role` (
  `user_role_id` bigint NOT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `role_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`user_role_id`),
  KEY `FKpmbmxnue4dqj4bbij5aji7m7n` (`role_id`),
  KEY `FKepcaa2ddk4jmbn9o57lm56l99` (`user_id`),
  CONSTRAINT `FKepcaa2ddk4jmbn9o57lm56l99` FOREIGN KEY (`user_id`) REFERENCES `natureom_user` (`user_id`),
  CONSTRAINT `FKpmbmxnue4dqj4bbij5aji7m7n` FOREIGN KEY (`role_id`) REFERENCES `natureom_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `natureom_user_role` */

insert  into `natureom_user_role`(`user_role_id`,`role_name`,`user_name`,`role_id`,`user_id`) values 
(782,'plant master','ramu 3',712,782),
(855,'plant master','ramu 2',712,855),
(885,'plant master','ramu 1',712,885),
(914,'admin','avinash',731,914),
(939,'customer','adit',687,939),
(963,'customer','rahul',687,963);

/*Table structure for table `plant_care_interval` */

DROP TABLE IF EXISTS `plant_care_interval`;

CREATE TABLE `plant_care_interval` (
  `plant_category_id` int NOT NULL,
  `cleaning_interval` int DEFAULT NULL,
  `fertilization_interval` int DEFAULT NULL,
  `pesticide_spray_interval` int DEFAULT NULL,
  `plant_category_name` varchar(255) DEFAULT NULL,
  `soil_cultivation_interval` int DEFAULT NULL,
  `trimming_interval` int DEFAULT NULL,
  `watering_interval` int DEFAULT NULL,
  PRIMARY KEY (`plant_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `plant_care_interval` */

insert  into `plant_care_interval`(`plant_category_id`,`cleaning_interval`,`fertilization_interval`,`pesticide_spray_interval`,`plant_category_name`,`soil_cultivation_interval`,`trimming_interval`,`watering_interval`) values 
(1,35,25,15,'tulsi',18,10,2),
(2,30,30,20,'aloevera',35,40,7),
(3,20,20,13,'rose',16,9,2),
(4,45,22,17,'tulip',25,9,2);

/*Table structure for table `plant_space` */

DROP TABLE IF EXISTS `plant_space`;

CREATE TABLE `plant_space` (
  `plant_space_id` bigint NOT NULL,
  `plant_space_address` varchar(255) DEFAULT NULL,
  `plant_space_customer_name` varchar(255) DEFAULT NULL,
  `plant_space_desc` varchar(255) DEFAULT NULL,
  `plant_space_landmark` varchar(255) DEFAULT NULL,
  `plant_space_name` varchar(255) DEFAULT NULL,
  `plant_space_plantmaster_name` varchar(255) DEFAULT NULL,
  `plant_space_status` varchar(255) DEFAULT NULL,
  `plant_space_customer_id` bigint DEFAULT NULL,
  `plant_space_plantmaster_id` bigint DEFAULT NULL,
  PRIMARY KEY (`plant_space_id`),
  KEY `FKjxed2bxet7sg8qjbtqd60d5g3` (`plant_space_customer_id`),
  KEY `FK959tyndid7c2cqrbn4rnn14u1` (`plant_space_plantmaster_id`),
  CONSTRAINT `FK959tyndid7c2cqrbn4rnn14u1` FOREIGN KEY (`plant_space_plantmaster_id`) REFERENCES `natureom_user_role` (`user_role_id`),
  CONSTRAINT `FKjxed2bxet7sg8qjbtqd60d5g3` FOREIGN KEY (`plant_space_customer_id`) REFERENCES `natureom_user_role` (`user_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `plant_space` */

insert  into `plant_space`(`plant_space_id`,`plant_space_address`,`plant_space_customer_name`,`plant_space_desc`,`plant_space_landmark`,`plant_space_name`,`plant_space_plantmaster_name`,`plant_space_status`,`plant_space_customer_id`,`plant_space_plantmaster_id`) values 
(5,'ahmedabad','rahul','rahul garden','rahul house','rahul house',NULL,NULL,963,885),
(30,'ahmedabad','adit','adit garden','bodakdev','adit house',NULL,NULL,939,855),
(49,'ahmedabad','adit','adit office garden','sindhu bhavan','adit office',NULL,NULL,939,855);

/*Table structure for table `plant_space_spot` */

DROP TABLE IF EXISTS `plant_space_spot`;

CREATE TABLE `plant_space_spot` (
  `plant_space_spot_id` bigint NOT NULL,
  `plant_category_name` varchar(255) DEFAULT NULL,
  `plant_space_name` varchar(255) DEFAULT NULL,
  `plant_space_spot_desc` varchar(255) DEFAULT NULL,
  `plant_space_spot_name` varchar(255) DEFAULT NULL,
  `plant_category_id` int DEFAULT NULL,
  `plant_space_id` bigint DEFAULT NULL,
  `plan_spot_status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`plant_space_spot_id`),
  KEY `FKj2ed51nfrfffn4o2wfr8f0sbd` (`plant_category_id`),
  KEY `FKgux2cna9u74ei5ja0hj0s580k` (`plant_space_id`),
  CONSTRAINT `FKgux2cna9u74ei5ja0hj0s580k` FOREIGN KEY (`plant_space_id`) REFERENCES `plant_space` (`plant_space_id`),
  CONSTRAINT `FKj2ed51nfrfffn4o2wfr8f0sbd` FOREIGN KEY (`plant_category_id`) REFERENCES `plant_care_interval` (`plant_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `plant_space_spot` */

insert  into `plant_space_spot`(`plant_space_spot_id`,`plant_category_name`,`plant_space_name`,`plant_space_spot_desc`,`plant_space_spot_name`,`plant_category_id`,`plant_space_id`,`plan_spot_status`) values 
(171,'aloevera','rahul house','second of gaden','garden-1 spot-2',2,5,NULL),
(330,'tulip','rahul house','fourth of gaden','garden-1 spot-4',4,5,NULL),
(419,'tulip','adit house','first of gaden','garden-2 spot-1',4,30,NULL),
(512,'rose','adit house','third of gaden','garden-2 spot-3',3,30,NULL),
(598,'tulip','adit house','fourth of gaden','garden-2 spot-4',4,30,NULL),
(691,'tulsi','adit office','first of gaden','garden-3 spot-1',1,49,NULL),
(824,'aloevera','adit office','second of gaden','garden-3 spot-2',2,49,NULL),
(919,'tulip','adit office','fourth of gaden','garden-3 spot-4',4,49,NULL);

/*Table structure for table `space_spot_details` */

DROP TABLE IF EXISTS `space_spot_details`;

CREATE TABLE `space_spot_details` (
  `detail_id` bigint NOT NULL,
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
  `details_category_id` int DEFAULT NULL,
  `details_space_id` bigint DEFAULT NULL,
  `details_spot_id` bigint DEFAULT NULL,
  PRIMARY KEY (`detail_id`),
  KEY `FK8uuwooemx8gpktwydbgqwkeql` (`details_category_id`),
  KEY `FK9gfoi6xwfya76q4gji7x1r1a0` (`details_space_id`),
  KEY `FKlgdia02dqvjfvc11ypnrty1m6` (`details_spot_id`),
  CONSTRAINT `FK8uuwooemx8gpktwydbgqwkeql` FOREIGN KEY (`details_category_id`) REFERENCES `plant_care_interval` (`plant_category_id`),
  CONSTRAINT `FK9gfoi6xwfya76q4gji7x1r1a0` FOREIGN KEY (`details_space_id`) REFERENCES `plant_space` (`plant_space_id`),
  CONSTRAINT `FKlgdia02dqvjfvc11ypnrty1m6` FOREIGN KEY (`details_spot_id`) REFERENCES `plant_space_spot` (`plant_space_spot_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `space_spot_details` */

insert  into `space_spot_details`(`detail_id`,`last_fertilized`,`last_pesticide`,`last_soil_cultivated`,`last_trim`,`last_watered`,`last_cleaned`,`next_clean`,`next_fertilize`,`next_pesticide`,`next_soil_cultivated`,`next_trim`,`next_water`,`details_spot_name`,`details_category_id`,`details_space_id`,`details_spot_id`) values 
(234,NULL,NULL,NULL,NULL,NULL,NULL,'2021-06-10','2021-06-10','2021-05-31','2021-06-15','2021-06-20','2021-05-15','garden-1 spot-2',2,5,171),
(401,NULL,NULL,NULL,NULL,NULL,NULL,'2021-06-25','2021-06-02','2021-05-28','2021-06-05','2021-05-20','2021-05-14','garden-1 spot-4',4,5,330),
(495,NULL,NULL,NULL,NULL,NULL,NULL,'2021-06-25','2021-06-02','2021-05-28','2021-06-05','2021-05-20','2021-05-13','garden-2 spot-1',4,30,419),
(577,NULL,NULL,NULL,NULL,NULL,NULL,'2021-05-31','2021-05-31','2021-05-24','2021-05-27','2021-05-20','2021-05-13','garden-2 spot-3',3,30,512),
(666,NULL,NULL,NULL,NULL,NULL,NULL,'2021-06-25','2021-06-02','2021-05-28','2021-06-05','2021-05-20','2021-05-13','garden-2 spot-4',4,30,598),
(772,NULL,NULL,NULL,NULL,NULL,NULL,'2021-06-15','2021-06-05','2021-05-26','2021-05-29','2021-05-21','2021-05-09','garden-3 spot-1',1,49,691),
(885,NULL,NULL,NULL,NULL,NULL,NULL,'2021-06-10','2021-06-10','2021-05-31','2021-06-15','2021-06-20','2021-05-09','garden-3 spot-2',2,49,824),
(984,NULL,NULL,NULL,NULL,NULL,NULL,'2021-06-25','2021-06-02','2021-05-28','2021-06-05','2021-05-20','2021-05-09','garden-3 spot-4',4,49,919);

/*Table structure for table `state_table` */

DROP TABLE IF EXISTS `state_table`;

CREATE TABLE `state_table` (
  `stateid` int NOT NULL,
  `state_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`stateid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `state_table` */

insert  into `state_table`(`stateid`,`state_name`) values 
(1,'Andhra Pradesh'),
(2,'Arunachal Pradesh'),
(3,'Assam'),
(4,'Bihar'),
(5,'Chhattisgarh'),
(6,'Goa'),
(7,'Gujarat'),
(8,'Haryana'),
(9,'Himachal Pradesh'),
(10,'Mizoram'),
(11,'Jharkhand'),
(12,'Karnataka'),
(13,'Kerala'),
(14,'Madhya Pradesh'),
(15,'Maharastra'),
(16,'Manipur'),
(17,'Meghalaya'),
(18,'Mizoram'),
(19,'Nagaland'),
(20,'Odisha'),
(21,'Punjab'),
(22,'Rajasthan'),
(23,'Sikkim'),
(24,'Tamil Nadu'),
(25,'Telangana'),
(26,'Tripura'),
(27,'Uttar Pradesh'),
(28,'Uttarakhand'),
(29,'West Bengal');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
