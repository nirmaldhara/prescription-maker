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

    /* table script for prescription details */
    CREATE TABLE `prescription`(
      `id` INT NOT NULL AUTO_INCREMENT,
      `patientid` INT NULL,
      `medicine_name` VARCHAR(45) NULL,
      `when_bf_af` VARCHAR(45) NULL,
      `no_of_days` VARCHAR(45) NULL,
      `dose1` VARCHAR(45) NULL,
      `dose2` VARCHAR(45) NULL,
      `dose3` VARCHAR(45) NULL,
      `dose4` VARCHAR(45) NULL,
      `dose5` VARCHAR(45) NULL,
      `dose6` VARCHAR(45) NULL,
      `note` VARCHAR(45) NULL,
      PRIMARY KEY (`id`));

     /* table script for lab_test */
      CREATE TABLE `lab_test` (
            `id` int NOT NULL AUTO_INCREMENT,
      	  `test_name` varchar(255) DEFAULT NULL,
      	  `test_value` varchar(255) DEFAULT NULL,
            `date` DATETIME DEFAULT CURRENT_TIMESTAMP,
            PRIMARY KEY (`id`)
          )

      /* table script for previous_history */
      CREATE TABLE `prescription`.`previous_history` (
        `id` INT NOT NULL AUTO_INCREMENT,
        `previous_history` VARCHAR(255) NULL,
        PRIMARY KEY (`id`),
        UNIQUE INDEX `previous_history_UNIQUE` (`previous_history` ASC) VISIBLE);


      /* table script for findings */
      CREATE TABLE `prescription`.`findings` (
        `id` INT NOT NULL AUTO_INCREMENT,
        `findings` VARCHAR(255) NULL,
        PRIMARY KEY (`id`),
        UNIQUE INDEX `findings_UNIQUE` (`findings` ASC) VISIBLE);


      /* table script for suggestions */
      CREATE TABLE `prescription`.`suggestions` (
        `id` INT NOT NULL AUTO_INCREMENT,
        `suggestions` VARCHAR(255) NULL,
        PRIMARY KEY (`id`),
        UNIQUE INDEX `suggestions_UNIQUE` (`suggestions` ASC) VISIBLE);


      /* table script for p_previous_history */
      CREATE TABLE `prescription`.`p_previous_history` (
        `id` INT NOT NULL AUTO_INCREMENT,
        `previous_history_id` INT NOT NULL,
        `visit_id` INT NOT NULL,
        PRIMARY KEY (`id`));

