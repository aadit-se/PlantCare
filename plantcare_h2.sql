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

USE `plantcare`;

/*Table structure for table `natureom_plantcare_action` */

/*Data for the table `natureom_plantcare_action` */

insert  into `natureom_plantcare_action`(`plantcare_action_id`,`plantcare_action_description`,`plantcare_action_name`) values 
(1,NULL,'wattering'),
(2,NULL,'trimming'),
(3,NULL,'fertilizing'),
(4,NULL,'pesticide'),
(5,NULL,'soil cultivation');

/*Table structure for table `natureom_role` */


/*Data for the table `natureom_role` */

insert  into `natureom_role`(`role_id`,`role_description`,`role_name`) values 
(687,'owner of garden, using facilities from plant master','customer'),
(712,'takes care of plants (gardenre)','plant master'),
(731,'supervises action of plant masters','admin');

/*Table structure for table `natureom_user` */


/*Data for the table `natureom_user` */

insert  into `natureom_user`(`user_id`,`user_city`,`user_contact`,`user_dob`,`user_email`,`user_gender`,`user_home`,`user_landmark`,`user_locality`,`user_name`,`user_pin`,`user_state`) values 
(782,'ahmedabad',70700700,'1982-10-01','ramu3@g.c','male','chil 1','astodia','maninagar','ramu 3',380001,'7'),
(855,'ahmedabad',70700700,'1982-10-03','ramu2@g.c','male','chil 1','astodia','maninagar','ramu 2',380001,'7'),
(885,'ahmedabad',70700700,'1982-10-04','ramu1@g.c','male','chil 1','astodia','maninagar','ramu 1',380001,'7'),
(914,'ahmedabad',5252552,'1990-10-10','avi@g.c','male','111 GodrejCity','GodrejCity','sola','avinash',380004,'7'),
(939,'ahmedabad',1010100101,'1998-10-07','adi@g.c','male','4-c skyline','AIS school','bodekdev','adit',380015,'7'),
(963,'ahmedabad',111111,'1998-10-18','rahul@g.c','male','shridaraliya','bhagban patyplot','sindhbhavan','rahul',3800156,'7');

/*Table structure for table `natureom_user_role` */

/*Data for the table `natureom_user_role` */

insert  into `natureom_user_role`(`user_role_id`,`role_name`,`user_name`,`role_id`,`user_id`) values 
(782,'plant master','ramu 3',712,782),
(855,'plant master','ramu 2',712,855),
(885,'plant master','ramu 1',712,885),
(914,'admin','avinash',731,914),
(939,'customer','adit',687,939),
(963,'customer','rahul',687,963);

/*Table structure for table `plant_care_interval` */

/*Data for the table `plant_care_interval` */

insert  into `plant_care_interval`(`plant_category_id`,`cleaning_interval`,`fertilization_interval`,`pesticide_spray_interval`,`plant_category_name`,`soil_cultivation_interval`,`trimming_interval`,`watering_interval`) values 
(1,35,25,15,'tulsi',18,10,2),
(2,30,30,20,'aloevera',35,40,7),
(3,20,20,13,'rose',16,9,2),
(4,45,22,17,'tulip',25,9,2);

/*Table structure for table `plant_space` */

/*Data for the table `plant_space` */

insert  into `plant_space`(`plant_space_id`,`plant_space_address`,`plant_space_customer_name`,`plant_space_desc`,`plant_space_landmark`,`plant_space_name`,`plant_space_plantmaster_name`,`plant_space_status`,`plant_space_customer_id`,`plant_space_plantmaster_id`) values 
(5,'ahmedabad','rahul','rahul garden','rahul house','rahul house',NULL,NULL,963,885),
(30,'ahmedabad','adit','adit garden','bodakdev','adit house',NULL,NULL,939,855),
(49,'ahmedabad','adit','adit office garden','sindhu bhavan','adit office',NULL,NULL,939,855);

/*Table structure for table `plant_space_spot` */

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
