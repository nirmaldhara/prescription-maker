/* table script for patient details */
CREATE TABLE `patient` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `dob` datetime DEFAULT NULL,
  `age_in_years` int DEFAULT '0',
  `sex` varchar(255) DEFAULT NULL,
  `mobile_no` varchar(255) DEFAULT NULL,
  `patient_id` int DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
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
   CREATE TABLE `prescription` (
     `id` int NOT NULL AUTO_INCREMENT,
     `visit_id` int NOT NULL DEFAULT '0',
     `patient_id` int NOT NULL DEFAULT '0',
     `medicine_id` int NOT NULL,
     `when_bf_af` varchar(10) DEFAULT NULL,
     `no_of_days` int DEFAULT NULL,
     `dose1` varchar(10) DEFAULT NULL,
     `dose2` varchar(10) DEFAULT NULL,
     `dose3` varchar(10) DEFAULT NULL,
     `dose4` varchar(10) DEFAULT NULL,
     `dose5` varchar(10) DEFAULT NULL,
     `dose6` varchar(10) DEFAULT NULL,
     `note` varchar(500) DEFAULT NULL,
     `date` datetime DEFAULT CURRENT_TIMESTAMP,
     PRIMARY KEY (`id`)
   ) ;


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
      /* table script for p_findings */
      CREATE TABLE `prescription`.`p_findings` (
        `id` INT NOT NULL AUTO_INCREMENT,
        `findings_id` INT NOT NULL,
        `visit_id` INT NOT NULL,
        PRIMARY KEY (`id`));

    /* table script for p_suggestions */
    CREATE TABLE `prescription`.`p_suggestions` (
      `id` INT NOT NULL AUTO_INCREMENT,
      `suggestions_id` INT NOT NULL,
      `visit_id` INT NOT NULL,
      PRIMARY KEY (`id`));

    /* table script for complain */
      CREATE TABLE `prescription`.`complain` (
        `id` INT NOT NULL AUTO_INCREMENT,
        `complain` VARCHAR(255) NULL,
        PRIMARY KEY (`id`),
        UNIQUE INDEX `complain_UNIQUE` (`complain` ASC) VISIBLE);

    /* table script for p_complain_of */
        CREATE TABLE `prescription`.`p_complain_of` (
                `id` INT NOT NULL AUTO_INCREMENT,
                `complain_id` INT NOT NULL,
                `visit_id` INT NOT NULL,
                PRIMARY KEY (`id`))

    /* table script for p_complain_of */
      CREATE TABLE `visit_history` (
        `id` int NOT NULL AUTO_INCREMENT,
        `patient_id` int NOT NULL,
        `visit_id` int NOT NULL,
        `visit_date` datetime NOT NULL,
        `next_visit` datetime NOT NULL,
        `weight` float DEFAULT '0',
        `height` float DEFAULT '0',
        `bp` varchar(45) DEFAULT NULL,
        `pulse` varchar(45) DEFAULT NULL,
        PRIMARY KEY (`id`),
        UNIQUE KEY `visit_id_UNIQUE` (`visit_id`)
      )

