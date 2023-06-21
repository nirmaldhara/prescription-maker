/* table script for patient details */
CREATE TABLE `patient` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `mobile_no` varchar(255) DEFAULT NULL,
  `patient_id` int DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `search` (`mobile_no`,`patient_id`)
)
/////
CREATE TABLE `medicine` (
  `id` int NOT NULL AUTO_INCREMENT,
  `medicine_name` varchar(255) DEFAULT NULL,
  `when_BF_AF` varchar(10) DEFAULT NULL,
  `no_of_days` int DEFAULT NULL,
  `dose1` varchar(10) DEFAULT NULL,
  `dose2` varchar(10) DEFAULT NULL,
  `dose3` varchar(10) DEFAULT NULL,
  `dose4` varchar(10) DEFAULT NULL,
  `dose5` varchar(10) DEFAULT NULL,
  `dose6` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `search` (`medicine_name`)
)