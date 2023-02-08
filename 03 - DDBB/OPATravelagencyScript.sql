-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema opatravelagency
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema opatravelagency
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `opatravelagency` DEFAULT CHARACTER SET utf8mb3 ;
USE `opatravelagency` ;

-- -----------------------------------------------------
-- Table `opatravelagency`.`airtrip`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opatravelagency`.`airtrip` (
  `Id_airTrip` SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name_airTrip` VARCHAR(100) NOT NULL,
  `days_airTrip` SMALLINT UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`Id_airTrip`),
  UNIQUE INDEX `Id_airTrip_UNIQUE` (`Id_airTrip` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `opatravelagency`.`cities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opatravelagency`.`cities` (
  `Id_cities` SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name_cities` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`Id_cities`),
  UNIQUE INDEX `Id_cities_UNIQUE` (`Id_cities` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `opatravelagency`.`citiesairtrip`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opatravelagency`.`citiesairtrip` (
  `id_cities_citiesairtrip` SMALLINT UNSIGNED NOT NULL,
  `id_airTrip_citiesairtrip` SMALLINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id_cities_citiesairtrip`, `id_airTrip_citiesairtrip`),
  INDEX `fk_citiesAirTrip_airTrip1_idx` (`id_airTrip_citiesairtrip` ASC) VISIBLE,
  CONSTRAINT `fk_citiesAirTrip_airTrip1`
    FOREIGN KEY (`id_airTrip_citiesairtrip`)
    REFERENCES `opatravelagency`.`airtrip` (`Id_airTrip`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_citiesTrip_cities1`
    FOREIGN KEY (`id_cities_citiesairtrip`)
    REFERENCES `opatravelagency`.`cities` (`Id_cities`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `opatravelagency`.`landtrip`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opatravelagency`.`landtrip` (
  `Id_landTrip` SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name_landTrip` VARCHAR(100) NOT NULL,
  `days_landTrip` SMALLINT NOT NULL,
  PRIMARY KEY (`Id_landTrip`),
  UNIQUE INDEX `Id_landTrip_UNIQUE` (`Id_landTrip` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `opatravelagency`.`citieslandtrip`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opatravelagency`.`citieslandtrip` (
  `id_landTrip_citieslandtrip` SMALLINT UNSIGNED NOT NULL,
  `id_cities_citieslandtrip` SMALLINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id_landTrip_citieslandtrip`, `id_cities_citieslandtrip`),
  INDEX `fk_citiesLandTrip_cities1_idx` (`id_cities_citieslandtrip` ASC) VISIBLE,
  CONSTRAINT `fk_citiesLandTrip_cities1`
    FOREIGN KEY (`id_cities_citieslandtrip`)
    REFERENCES `opatravelagency`.`cities` (`Id_cities`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_citiesLandTrip_landTrip1`
    FOREIGN KEY (`id_landTrip_citieslandtrip`)
    REFERENCES `opatravelagency`.`landtrip` (`Id_landTrip`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `opatravelagency`.`flights`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opatravelagency`.`flights` (
  `Id_flights` SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `Id_cities_flights` SMALLINT UNSIGNED NOT NULL,
  `departureTime_flights` TIME NOT NULL,
  PRIMARY KEY (`Id_flights`),
  UNIQUE INDEX `Id_flights_UNIQUE` (`Id_flights` ASC) VISIBLE,
  INDEX `fk_flights_cities1_idx` (`Id_cities_flights` ASC) VISIBLE,
  CONSTRAINT `fk_flights_cities1`
    FOREIGN KEY (`Id_cities_flights`)
    REFERENCES `opatravelagency`.`cities` (`Id_cities`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `opatravelagency`.`flightsairtrip`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opatravelagency`.`flightsairtrip` (
  `id_airTrip_flightsairtrip` SMALLINT UNSIGNED NOT NULL,
  `id_flights_flightsairtrip` SMALLINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id_airTrip_flightsairtrip`, `id_flights_flightsairtrip`),
  INDEX `fk_flightsAirTrip_flights1_idx` (`id_flights_flightsairtrip` ASC) VISIBLE,
  CONSTRAINT `fk_flightsAirTrip_airTrip1`
    FOREIGN KEY (`id_airTrip_flightsairtrip`)
    REFERENCES `opatravelagency`.`airtrip` (`Id_airTrip`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_flightsAirTrip_flights1`
    FOREIGN KEY (`id_flights_flightsairtrip`)
    REFERENCES `opatravelagency`.`flights` (`Id_flights`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `opatravelagency`.`hotels`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opatravelagency`.`hotels` (
  `Id_hotels` SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `Id_cities_hotels` SMALLINT UNSIGNED NOT NULL,
  `name_hotels` VARCHAR(76) NOT NULL,
  `idCategory_hotels` SMALLINT NOT NULL,
  PRIMARY KEY (`Id_hotels`),
  UNIQUE INDEX `Id_hotels_UNIQUE` (`Id_hotels` ASC) VISIBLE,
  INDEX `fk_hotels_cities_idx` (`Id_cities_hotels` ASC) VISIBLE,
  CONSTRAINT `fk_hotels_cities`
    FOREIGN KEY (`Id_cities_hotels`)
    REFERENCES `opatravelagency`.`cities` (`Id_cities`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `opatravelagency`.`hotelslandtrip`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opatravelagency`.`hotelslandtrip` (
  `id_hotels_hotelslandtrip` SMALLINT UNSIGNED NOT NULL,
  `id_landTrip_hotelslandtrip` SMALLINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id_hotels_hotelslandtrip`, `id_landTrip_hotelslandtrip`),
  INDEX `fk_hotelsLandtrip_landTrip1_idx` (`id_landTrip_hotelslandtrip` ASC) VISIBLE,
  CONSTRAINT `fk_hotelsLandtrip_hotels1`
    FOREIGN KEY (`id_hotels_hotelslandtrip`)
    REFERENCES `opatravelagency`.`hotels` (`Id_hotels`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_hotelsLandtrip_landTrip1`
    FOREIGN KEY (`id_landTrip_hotelslandtrip`)
    REFERENCES `opatravelagency`.`landtrip` (`Id_landTrip`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

USE `opatravelagency` ;

-- -----------------------------------------------------
-- Placeholder table for view `opatravelagency`.`air_trip_cities_vw`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opatravelagency`.`air_trip_cities_vw` (`id_airTrip_citiesairtrip` INT, `Id_cities` INT, `name_cities` INT);

-- -----------------------------------------------------
-- Placeholder table for view `opatravelagency`.`air_trip_flights_vw`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opatravelagency`.`air_trip_flights_vw` (`id_airTrip_flightsairtrip` INT, `Id_flights` INT, `departureTime_flights` INT, `Id_cities` INT, `name_cities` INT);

-- -----------------------------------------------------
-- Placeholder table for view `opatravelagency`.`flight_complet_vw`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opatravelagency`.`flight_complet_vw` (`Id_flights` INT, `departureTime_flights` INT, `Id_cities` INT, `name_cities` INT);

-- -----------------------------------------------------
-- Placeholder table for view `opatravelagency`.`hotel_complet_vw`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opatravelagency`.`hotel_complet_vw` (`Id_hotels` INT, `name_hotels` INT, `idCategory_hotels` INT, `Id_cities` INT, `name_cities` INT);

-- -----------------------------------------------------
-- Placeholder table for view `opatravelagency`.`land_trip_cities_vw`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opatravelagency`.`land_trip_cities_vw` (`id_landTrip_citieslandtrip` INT, `Id_cities` INT, `name_cities` INT);

-- -----------------------------------------------------
-- Placeholder table for view `opatravelagency`.`land_trip_hotels_vw`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opatravelagency`.`land_trip_hotels_vw` (`id_landTrip_hotelslandtrip` INT, `Id_hotels` INT, `name_hotels` INT, `idCategory_hotels` INT, `Id_cities` INT, `name_cities` INT);

-- -----------------------------------------------------
-- View `opatravelagency`.`air_trip_cities_vw`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `opatravelagency`.`air_trip_cities_vw`;
USE `opatravelagency`;
CREATE 
	 OR REPLACE ALGORITHM=UNDEFINED 
    DEFINER=`root`@`localhost` 
    SQL SECURITY DEFINER 
VIEW `opatravelagency`.`air_trip_cities_vw` AS 
	select 
		`opatravelagency`.`citiesairtrip`.`id_airTrip_citiesairtrip` AS `id_airTrip_citiesairtrip`,
        `cty`.`Id_cities` AS `Id_cities`,
        `cty`.`name_cities` AS `name_cities` 
	from 
		(`opatravelagency`.`citiesairtrip` 
        left join `opatravelagency`.`cities` `cty` on((`cty`.`Id_cities` = `opatravelagency`.`citiesairtrip`.`id_cities_citiesairtrip`)));

-- -----------------------------------------------------
-- View `opatravelagency`.`air_trip_flights_vw`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `opatravelagency`.`air_trip_flights_vw`;
USE `opatravelagency`;
CREATE 
	 OR REPLACE ALGORITHM=UNDEFINED 
    DEFINER=`root`@`localhost` 
    SQL SECURITY DEFINER 
VIEW `opatravelagency`.`air_trip_flights_vw` AS 
	select 
		`fat`.`id_airTrip_flightsairtrip` AS `id_airTrip_flightsairtrip`,
		`flg`.`Id_flights` AS `Id_flights`,
		`flg`.`departureTime_flights` AS `departureTime_flights`,
		`cty`.`Id_cities` AS `Id_cities`,
		`cty`.`name_cities` AS `name_cities` 
    from 
		((`opatravelagency`.`flightsairtrip` `fat` 
        left join `opatravelagency`.`flights` `flg` on((`flg`.`Id_flights` = `fat`.`id_flights_flightsairtrip`))) 
        left join `opatravelagency`.`cities` `cty` on((`cty`.`Id_cities` = `flg`.`Id_cities_flights`)));

-- -----------------------------------------------------
-- View `opatravelagency`.`flight_complet_vw`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `opatravelagency`.`flight_complet_vw`;
USE `opatravelagency`;
CREATE 
	 OR REPLACE ALGORITHM=UNDEFINED 
	DEFINER=`root`@`localhost` 
    SQL SECURITY DEFINER 
VIEW `opatravelagency`.`flight_complet_vw` AS 
	select 
		`flg`.`Id_flights` AS `Id_flights`,
        `flg`.`departureTime_flights` AS `departureTime_flights`,
        `cty`.`Id_cities` AS `Id_cities`,
        `cty`.`name_cities` AS `name_cities` 
	from 
		(`opatravelagency`.`flights` `flg` 
        left join `opatravelagency`.`cities` `cty` on((`cty`.`Id_cities` = `flg`.`Id_cities_flights`))) 
	group by `flg`.`Id_flights`;

-- -----------------------------------------------------
-- View `opatravelagency`.`hotel_complet_vw`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `opatravelagency`.`hotel_complet_vw`;
USE `opatravelagency`;
CREATE 
	 OR REPLACE ALGORITHM=UNDEFINED 
    DEFINER=`root`@`localhost` 
    SQL SECURITY DEFINER 
VIEW `opatravelagency`.`hotel_complet_vw` AS 
	select 
		`ho`.`Id_hotels` AS `Id_hotels`,
        `ho`.`name_hotels` AS `name_hotels`,
        `ho`.`idCategory_hotels` AS `idCategory_hotels`,
        `cts`.`Id_cities` AS `Id_cities`,
        `cts`.`name_cities` AS `name_cities` 
	from 
		(`opatravelagency`.`hotels` `ho` 
        left join `opatravelagency`.`cities` `cts` on((`cts`.`Id_cities` = `ho`.`Id_cities_hotels`))) 
	group by `ho`.`Id_hotels`;

-- -----------------------------------------------------
-- View `opatravelagency`.`land_trip_cities_vw`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `opatravelagency`.`land_trip_cities_vw`;
USE `opatravelagency`;
CREATE 
	 OR REPLACE ALGORITHM=UNDEFINED 
	DEFINER=`root`@`localhost` 
    SQL SECURITY DEFINER 
VIEW `opatravelagency`.`land_trip_cities_vw` AS
    SELECT 
        `clt`.`id_landTrip_citieslandtrip` AS `id_landTrip_citieslandtrip`,
        `cty`.`Id_cities` AS `Id_cities`,
        `cty`.`name_cities` AS `name_cities`
    FROM
        (`opatravelagency`.`citieslandtrip` `clt`
        LEFT JOIN `opatravelagency`.`cities` `cty` ON ((`cty`.`Id_cities` = `clt`.`id_cities_citieslandtrip`)));

-- -----------------------------------------------------
-- View `opatravelagency`.`land_trip_hotels_vw`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `opatravelagency`.`land_trip_hotels_vw`;
USE `opatravelagency`;
CREATE 
     OR REPLACE ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `opatravelagency`.`land_trip_hotels_vw` AS
    SELECT 
        `hlt`.`id_landTrip_hotelslandtrip` AS `id_landTrip_hotelslandtrip`,
        `htl`.`Id_hotels` AS `Id_hotels`,
        `htl`.`name_hotels` AS `name_hotels`,
        `htl`.`idCategory_hotels` AS `idCategory_hotels`,
        `cty`.`Id_cities` AS `Id_cities`,
        `cty`.`name_cities` AS `name_cities`
    FROM
        ((`opatravelagency`.`hotelslandtrip` `hlt`
        LEFT JOIN `opatravelagency`.`hotels` `htl` ON ((`htl`.`Id_hotels` = `hlt`.`id_hotels_hotelslandtrip`)))
        LEFT JOIN `opatravelagency`.`cities` `cty` ON ((`cty`.`Id_cities` = `htl`.`Id_cities_hotels`)));
CREATE USER 'opabasic' IDENTIFIED BY 'OPABaUnoDosTres';

GRANT SELECT ON TABLE `opatravelagency`.* TO 'opabasic';
CREATE USER 'opaadministrador' IDENTIFIED BY 'OPAAdCincoSeis';

GRANT ALL ON `opatravelagency`.* TO 'opaadministrador';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `opatravelagency`.`airtrip`
-- -----------------------------------------------------
START TRANSACTION;
USE `opatravelagency`;
INSERT INTO `opatravelagency`.`airtrip` (`Id_airTrip`, `name_airTrip`, `days_airTrip`) VALUES (1, 'Air Trip 1', 7);
INSERT INTO `opatravelagency`.`airtrip` (`Id_airTrip`, `name_airTrip`, `days_airTrip`) VALUES (2, 'Air Trip 2', 9);
INSERT INTO `opatravelagency`.`airtrip` (`Id_airTrip`, `name_airTrip`, `days_airTrip`) VALUES (3, 'Air Trip 3', 10);
INSERT INTO `opatravelagency`.`airtrip` (`Id_airTrip`, `name_airTrip`, `days_airTrip`) VALUES (4, 'Air Trip 4', 8);

COMMIT;


-- -----------------------------------------------------
-- Data for table `opatravelagency`.`cities`
-- -----------------------------------------------------
START TRANSACTION;
USE `opatravelagency`;
INSERT INTO `opatravelagency`.`cities` (`Id_cities`, `name_cities`) VALUES (1, 'City One');
INSERT INTO `opatravelagency`.`cities` (`Id_cities`, `name_cities`) VALUES (2, 'City Two');
INSERT INTO `opatravelagency`.`cities` (`Id_cities`, `name_cities`) VALUES (3, 'City Three');
INSERT INTO `opatravelagency`.`cities` (`Id_cities`, `name_cities`) VALUES (4, 'City Fort');
INSERT INTO `opatravelagency`.`cities` (`Id_cities`, `name_cities`) VALUES (5, 'City Five');
INSERT INTO `opatravelagency`.`cities` (`Id_cities`, `name_cities`) VALUES (6, 'City Six');
INSERT INTO `opatravelagency`.`cities` (`Id_cities`, `name_cities`) VALUES (7, 'City Seven');
INSERT INTO `opatravelagency`.`cities` (`Id_cities`, `name_cities`) VALUES (8, 'City Eight');
INSERT INTO `opatravelagency`.`cities` (`Id_cities`, `name_cities`) VALUES (9, 'City Nine');
INSERT INTO `opatravelagency`.`cities` (`Id_cities`, `name_cities`) VALUES (10, 'City Ten');

COMMIT;


-- -----------------------------------------------------
-- Data for table `opatravelagency`.`citiesairtrip`
-- -----------------------------------------------------
START TRANSACTION;
USE `opatravelagency`;
INSERT INTO `opatravelagency`.`citiesairtrip` (`id_cities_citiesairtrip`, `id_airTrip_citiesairtrip`) VALUES (2, 1);
INSERT INTO `opatravelagency`.`citiesairtrip` (`id_cities_citiesairtrip`, `id_airTrip_citiesairtrip`) VALUES (3, 1);
INSERT INTO `opatravelagency`.`citiesairtrip` (`id_cities_citiesairtrip`, `id_airTrip_citiesairtrip`) VALUES (4, 2);
INSERT INTO `opatravelagency`.`citiesairtrip` (`id_cities_citiesairtrip`, `id_airTrip_citiesairtrip`) VALUES (5, 2);
INSERT INTO `opatravelagency`.`citiesairtrip` (`id_cities_citiesairtrip`, `id_airTrip_citiesairtrip`) VALUES (6, 3);
INSERT INTO `opatravelagency`.`citiesairtrip` (`id_cities_citiesairtrip`, `id_airTrip_citiesairtrip`) VALUES (7, 3);
INSERT INTO `opatravelagency`.`citiesairtrip` (`id_cities_citiesairtrip`, `id_airTrip_citiesairtrip`) VALUES (8, 4);
INSERT INTO `opatravelagency`.`citiesairtrip` (`id_cities_citiesairtrip`, `id_airTrip_citiesairtrip`) VALUES (9, 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table `opatravelagency`.`landtrip`
-- -----------------------------------------------------
START TRANSACTION;
USE `opatravelagency`;
INSERT INTO `opatravelagency`.`landtrip` (`Id_landTrip`, `name_landTrip`, `days_landTrip`) VALUES (1, 'Land Trip 1', 4);
INSERT INTO `opatravelagency`.`landtrip` (`Id_landTrip`, `name_landTrip`, `days_landTrip`) VALUES (2, 'Land Trip 2', 5);
INSERT INTO `opatravelagency`.`landtrip` (`Id_landTrip`, `name_landTrip`, `days_landTrip`) VALUES (3, 'Land Trip 3', 6);
INSERT INTO `opatravelagency`.`landtrip` (`Id_landTrip`, `name_landTrip`, `days_landTrip`) VALUES (4, 'Land Trip 4', 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table `opatravelagency`.`citieslandtrip`
-- -----------------------------------------------------
START TRANSACTION;
USE `opatravelagency`;
INSERT INTO `opatravelagency`.`citieslandtrip` (`id_landTrip_citieslandtrip`, `id_cities_citieslandtrip`) VALUES (1, 9);
INSERT INTO `opatravelagency`.`citieslandtrip` (`id_landTrip_citieslandtrip`, `id_cities_citieslandtrip`) VALUES (1, 4);
INSERT INTO `opatravelagency`.`citieslandtrip` (`id_landTrip_citieslandtrip`, `id_cities_citieslandtrip`) VALUES (2, 3);
INSERT INTO `opatravelagency`.`citieslandtrip` (`id_landTrip_citieslandtrip`, `id_cities_citieslandtrip`) VALUES (2, 6);
INSERT INTO `opatravelagency`.`citieslandtrip` (`id_landTrip_citieslandtrip`, `id_cities_citieslandtrip`) VALUES (3, 5);
INSERT INTO `opatravelagency`.`citieslandtrip` (`id_landTrip_citieslandtrip`, `id_cities_citieslandtrip`) VALUES (3, 8);
INSERT INTO `opatravelagency`.`citieslandtrip` (`id_landTrip_citieslandtrip`, `id_cities_citieslandtrip`) VALUES (4, 7);
INSERT INTO `opatravelagency`.`citieslandtrip` (`id_landTrip_citieslandtrip`, `id_cities_citieslandtrip`) VALUES (4, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `opatravelagency`.`flights`
-- -----------------------------------------------------
START TRANSACTION;
USE `opatravelagency`;
INSERT INTO `opatravelagency`.`flights` (`Id_flights`, `Id_cities_flights`, `departureTime_flights`) VALUES (1, 1, '9:10:00');
INSERT INTO `opatravelagency`.`flights` (`Id_flights`, `Id_cities_flights`, `departureTime_flights`) VALUES (2, 2, '10:01:00');
INSERT INTO `opatravelagency`.`flights` (`Id_flights`, `Id_cities_flights`, `departureTime_flights`) VALUES (3, 3, '13:16:00');
INSERT INTO `opatravelagency`.`flights` (`Id_flights`, `Id_cities_flights`, `departureTime_flights`) VALUES (4, 4, '16:16:00');
INSERT INTO `opatravelagency`.`flights` (`Id_flights`, `Id_cities_flights`, `departureTime_flights`) VALUES (5, 5, '8:37:00');
INSERT INTO `opatravelagency`.`flights` (`Id_flights`, `Id_cities_flights`, `departureTime_flights`) VALUES (6, 6, '7:15:00');
INSERT INTO `opatravelagency`.`flights` (`Id_flights`, `Id_cities_flights`, `departureTime_flights`) VALUES (7, 7, '20:45:00');
INSERT INTO `opatravelagency`.`flights` (`Id_flights`, `Id_cities_flights`, `departureTime_flights`) VALUES (8, 8, '17:9:00');
INSERT INTO `opatravelagency`.`flights` (`Id_flights`, `Id_cities_flights`, `departureTime_flights`) VALUES (9, 9, '18:56:00');
INSERT INTO `opatravelagency`.`flights` (`Id_flights`, `Id_cities_flights`, `departureTime_flights`) VALUES (10, 10, '23:22:00');

COMMIT;


-- -----------------------------------------------------
-- Data for table `opatravelagency`.`flightsairtrip`
-- -----------------------------------------------------
START TRANSACTION;
USE `opatravelagency`;
INSERT INTO `opatravelagency`.`flightsairtrip` (`id_airTrip_flightsairtrip`, `id_flights_flightsairtrip`) VALUES (1, 1);
INSERT INTO `opatravelagency`.`flightsairtrip` (`id_airTrip_flightsairtrip`, `id_flights_flightsairtrip`) VALUES (1, 9);
INSERT INTO `opatravelagency`.`flightsairtrip` (`id_airTrip_flightsairtrip`, `id_flights_flightsairtrip`) VALUES (2, 10);
INSERT INTO `opatravelagency`.`flightsairtrip` (`id_airTrip_flightsairtrip`, `id_flights_flightsairtrip`) VALUES (2, 8);
INSERT INTO `opatravelagency`.`flightsairtrip` (`id_airTrip_flightsairtrip`, `id_flights_flightsairtrip`) VALUES (3, 2);
INSERT INTO `opatravelagency`.`flightsairtrip` (`id_airTrip_flightsairtrip`, `id_flights_flightsairtrip`) VALUES (3, 7);
INSERT INTO `opatravelagency`.`flightsairtrip` (`id_airTrip_flightsairtrip`, `id_flights_flightsairtrip`) VALUES (4, 3);
INSERT INTO `opatravelagency`.`flightsairtrip` (`id_airTrip_flightsairtrip`, `id_flights_flightsairtrip`) VALUES (4, 6);

COMMIT;


-- -----------------------------------------------------
-- Data for table `opatravelagency`.`hotels`
-- -----------------------------------------------------
START TRANSACTION;
USE `opatravelagency`;
INSERT INTO `opatravelagency`.`hotels` (`Id_hotels`, `Id_cities_hotels`, `name_hotels`, `idCategory_hotels`) VALUES (1, 1, 'Hotel One', 3);
INSERT INTO `opatravelagency`.`hotels` (`Id_hotels`, `Id_cities_hotels`, `name_hotels`, `idCategory_hotels`) VALUES (2, 2, 'Hotel Two', 2);
INSERT INTO `opatravelagency`.`hotels` (`Id_hotels`, `Id_cities_hotels`, `name_hotels`, `idCategory_hotels`) VALUES (3, 3, 'Hotel Three', 4);
INSERT INTO `opatravelagency`.`hotels` (`Id_hotels`, `Id_cities_hotels`, `name_hotels`, `idCategory_hotels`) VALUES (4, 4, 'Hotel Fort', 5);
INSERT INTO `opatravelagency`.`hotels` (`Id_hotels`, `Id_cities_hotels`, `name_hotels`, `idCategory_hotels`) VALUES (5, 5, 'Hotel Five', 6);
INSERT INTO `opatravelagency`.`hotels` (`Id_hotels`, `Id_cities_hotels`, `name_hotels`, `idCategory_hotels`) VALUES (6, 6, 'Hotel Six', 4);
INSERT INTO `opatravelagency`.`hotels` (`Id_hotels`, `Id_cities_hotels`, `name_hotels`, `idCategory_hotels`) VALUES (7, 7, 'Hotel Seven', 3);
INSERT INTO `opatravelagency`.`hotels` (`Id_hotels`, `Id_cities_hotels`, `name_hotels`, `idCategory_hotels`) VALUES (8, 8, 'Hotel Eight', 2);
INSERT INTO `opatravelagency`.`hotels` (`Id_hotels`, `Id_cities_hotels`, `name_hotels`, `idCategory_hotels`) VALUES (9, 9, 'Hotel Nine', 1);
INSERT INTO `opatravelagency`.`hotels` (`Id_hotels`, `Id_cities_hotels`, `name_hotels`, `idCategory_hotels`) VALUES (10, 10, 'Hotel Ten', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `opatravelagency`.`hotelslandtrip`
-- -----------------------------------------------------
START TRANSACTION;
USE `opatravelagency`;
INSERT INTO `opatravelagency`.`hotelslandtrip` (`id_hotels_hotelslandtrip`, `id_landTrip_hotelslandtrip`) VALUES (9, 1);
INSERT INTO `opatravelagency`.`hotelslandtrip` (`id_hotels_hotelslandtrip`, `id_landTrip_hotelslandtrip`) VALUES (4, 1);
INSERT INTO `opatravelagency`.`hotelslandtrip` (`id_hotels_hotelslandtrip`, `id_landTrip_hotelslandtrip`) VALUES (3, 2);
INSERT INTO `opatravelagency`.`hotelslandtrip` (`id_hotels_hotelslandtrip`, `id_landTrip_hotelslandtrip`) VALUES (6, 2);
INSERT INTO `opatravelagency`.`hotelslandtrip` (`id_hotels_hotelslandtrip`, `id_landTrip_hotelslandtrip`) VALUES (5, 3);
INSERT INTO `opatravelagency`.`hotelslandtrip` (`id_hotels_hotelslandtrip`, `id_landTrip_hotelslandtrip`) VALUES (8, 3);
INSERT INTO `opatravelagency`.`hotelslandtrip` (`id_hotels_hotelslandtrip`, `id_landTrip_hotelslandtrip`) VALUES (7, 4);
INSERT INTO `opatravelagency`.`hotelslandtrip` (`id_hotels_hotelslandtrip`, `id_landTrip_hotelslandtrip`) VALUES (2, 4);

COMMIT;

