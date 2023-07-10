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
      `when_bf_af` varchar(10) DEFAULT NULL,
      `no_of_days` int DEFAULT NULL,
      `dose1` varchar(10) DEFAULT NULL,
      `dose2` varchar(10) DEFAULT NULL,
      `dose3` varchar(10) DEFAULT NULL,
      `dose4` varchar(10) DEFAULT NULL,
      `dose5` varchar(10) DEFAULT NULL,
      `dose6` varchar(10) DEFAULT NULL,
      `note` varchar(500) DEFAULT NULL,
      PRIMARY KEY (`id`),
      KEY `search` (`medicine_name`)
    )

    /////

    CREATE TABLE `prescription` (
      `id` int NOT NULL AUTO_INCREMENT,
      `visit_id` int NOT NULL DEFAULT 0,
     `patient_id` int NOT NULL DEFAULT 0,
      `medicine_name` varchar(255) DEFAULT NULL,
      `when_bf_af` varchar(10) DEFAULT NULL,
      `no_of_days` int DEFAULT NULL,
      `dose1` varchar(10) DEFAULT NULL,
      `dose2` varchar(10) DEFAULT NULL,
      `dose3` varchar(10) DEFAULT NULL,
      `dose4` varchar(10) DEFAULT NULL,
      `dose5` varchar(10) DEFAULT NULL,
      `dose6` varchar(10) DEFAULT NULL,
      `note` varchar(500) DEFAULT NULL,
      `date` DATETIME DEFAULT CURRENT_TIMESTAMP,
      PRIMARY KEY (`id`)
    )
