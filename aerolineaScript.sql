-- MySQL Script generated by MySQL Workbench
-- 04/29/16 16:53:35
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema Aerolinea
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Aerolinea
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Aerolinea` DEFAULT CHARACTER SET utf8 ;
USE `Aerolinea` ;

-- -----------------------------------------------------
-- Table `Aerolinea`.`PAIS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Aerolinea`.`PAIS` ;

CREATE TABLE IF NOT EXISTS `Aerolinea`.`PAIS` (
  `id_pais` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_pais`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Aerolinea`.`CIUDAD`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Aerolinea`.`CIUDAD` ;

CREATE TABLE IF NOT EXISTS `Aerolinea`.`CIUDAD` (
  `id_ciudad` INT NOT NULL AUTO_INCREMENT,
  `id_pais` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_ciudad`),
  INDEX `pais_idx` (`id_pais` ASC),
  CONSTRAINT `fk_CIUDAD_PAIS1`
    FOREIGN KEY (`id_pais`)
    REFERENCES `Aerolinea`.`PAIS` (`id_pais`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Aerolinea`.`AEROPUERTO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Aerolinea`.`AEROPUERTO` ;

CREATE TABLE IF NOT EXISTS `Aerolinea`.`AEROPUERTO` (
  `id_aeropuerto` INT NOT NULL AUTO_INCREMENT,
  `id_ciudad` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(45) NULL,
  `telefono` VARCHAR(45) NULL,
  `iata` VARCHAR(45) NULL,
  `oficina` VARCHAR(45) NULL,
  PRIMARY KEY (`id_aeropuerto`),
  INDEX `ciudad_idx` (`id_ciudad` ASC),
  CONSTRAINT `fk_AEROPUERTO_CIUDAD1`
    FOREIGN KEY (`id_ciudad`)
    REFERENCES `Aerolinea`.`CIUDAD` (`id_ciudad`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '			';


-- -----------------------------------------------------
-- Table `Aerolinea`.`TIPODOCUMENTO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Aerolinea`.`TIPODOCUMENTO` ;

CREATE TABLE IF NOT EXISTS `Aerolinea`.`TIPODOCUMENTO` (
  `id_tipodocumento` INT NOT NULL AUTO_INCREMENT,
  `id_pais` INT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_tipodocumento`),
  INDEX `fk_TIPODOCUMENTO_PAIS1_idx` (`id_pais` ASC),
  CONSTRAINT `fk_TIPODOCUMENTO_PAIS1`
    FOREIGN KEY (`id_pais`)
    REFERENCES `Aerolinea`.`PAIS` (`id_pais`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Aerolinea`.`CLIENTE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Aerolinea`.`CLIENTE` ;

CREATE TABLE IF NOT EXISTS `Aerolinea`.`CLIENTE` (
  `id_cliente` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `documento` VARCHAR(45) NOT NULL,
  `tipodocumento` INT NOT NULL,
  `correo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_cliente`),
  INDEX `tipodocumento_idx` (`tipodocumento` ASC),
  UNIQUE INDEX `id_cliente_UNIQUE` (`id_cliente` ASC),
  UNIQUE INDEX `correo_UNIQUE` (`correo` ASC),
  CONSTRAINT `fk_CLIENTE_TIPODOCUMENTO1`
    FOREIGN KEY (`tipodocumento`)
    REFERENCES `Aerolinea`.`TIPODOCUMENTO` (`id_tipodocumento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Aerolinea`.`ITINERARIO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Aerolinea`.`ITINERARIO` ;

CREATE TABLE IF NOT EXISTS `Aerolinea`.`ITINERARIO` (
  `id_itinerario` INT NOT NULL AUTO_INCREMENT,
  `preciocompleto` DOUBLE NOT NULL,
  PRIMARY KEY (`id_itinerario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Aerolinea`.`AVION`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Aerolinea`.`AVION` ;

CREATE TABLE IF NOT EXISTS `Aerolinea`.`AVION` (
  `id_avion` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `capacidad` INT NOT NULL,
  `filas` INT NULL,
  `columnas` INT NULL,
  PRIMARY KEY (`id_avion`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Aerolinea`.`VUELO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Aerolinea`.`VUELO` ;

CREATE TABLE IF NOT EXISTS `Aerolinea`.`VUELO` (
  `id_vuelo` INT NOT NULL AUTO_INCREMENT,
  `id_avion` INT NOT NULL,
  `id_itinerario` INT NOT NULL,
  `precio` DOUBLE NOT NULL,
  `desde` INT NOT NULL,
  `hasta` INT NOT NULL,
  `millas` DOUBLE NOT NULL,
  PRIMARY KEY (`id_vuelo`),
  INDEX `itinerario_idx` (`id_itinerario` ASC),
  INDEX `fk_VUELO_AVION1_idx` (`id_avion` ASC),
  INDEX `fk_VUELO_AEROPUERTO1_idx` (`desde` ASC),
  INDEX `fk_VUELO_AEROPUERTO2_idx` (`hasta` ASC),
  CONSTRAINT `fk_VUELO_ITINERARIO1`
    FOREIGN KEY (`id_itinerario`)
    REFERENCES `Aerolinea`.`ITINERARIO` (`id_itinerario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_VUELO_AVION1`
    FOREIGN KEY (`id_avion`)
    REFERENCES `Aerolinea`.`AVION` (`id_avion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_VUELO_AEROPUERTO1`
    FOREIGN KEY (`desde`)
    REFERENCES `Aerolinea`.`AEROPUERTO` (`id_aeropuerto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_VUELO_AEROPUERTO2`
    FOREIGN KEY (`hasta`)
    REFERENCES `Aerolinea`.`AEROPUERTO` (`id_aeropuerto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Aerolinea`.`PASAJE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Aerolinea`.`PASAJE` ;

CREATE TABLE IF NOT EXISTS `Aerolinea`.`PASAJE` (
  `id_pasaje` INT NOT NULL AUTO_INCREMENT,
  `id_itinerario` INT NOT NULL,
  `id_cliente` INT NOT NULL,
  `pagado` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id_pasaje`),
  INDEX `fk_PASAJE_ITINERARIO1_idx` (`id_itinerario` ASC),
  INDEX `fk_PASAJE_CLIENTE1_idx` (`id_cliente` ASC),
  CONSTRAINT `fk_PASAJE_ITINERARIO1`
    FOREIGN KEY (`id_itinerario`)
    REFERENCES `Aerolinea`.`ITINERARIO` (`id_itinerario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PASAJE_CLIENTE1`
    FOREIGN KEY (`id_cliente`)
    REFERENCES `Aerolinea`.`CLIENTE` (`id_cliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Aerolinea`.`SOCIO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Aerolinea`.`SOCIO` ;

CREATE TABLE IF NOT EXISTS `Aerolinea`.`SOCIO` (
  `id_socio` INT NOT NULL AUTO_INCREMENT,
  `id_cliente` INT NOT NULL,
  `usuario` VARCHAR(45) NOT NULL,
  `contrasena` VARCHAR(45) NOT NULL,
  `millas` DOUBLE ZEROFILL NULL,
  PRIMARY KEY (`id_socio`),
  UNIQUE INDEX `usuario_UNIQUE` (`usuario` ASC),
  UNIQUE INDEX `id_socio_UNIQUE` (`id_socio` ASC),
  UNIQUE INDEX `id_cliente_UNIQUE` (`id_cliente` ASC),
  CONSTRAINT `fk_SOCIO_CLIENTE1`
    FOREIGN KEY (`id_cliente`)
    REFERENCES `Aerolinea`.`CLIENTE` (`id_cliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Aerolinea`.`TIPOCLASE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Aerolinea`.`TIPOCLASE` ;

CREATE TABLE IF NOT EXISTS `Aerolinea`.`TIPOCLASE` (
  `id_tipoclase` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `mutiplicadorprecio` DOUBLE NOT NULL,
  PRIMARY KEY (`id_tipoclase`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Aerolinea`.`SILLA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Aerolinea`.`SILLA` ;

CREATE TABLE IF NOT EXISTS `Aerolinea`.`SILLA` (
  `id_silla` INT NOT NULL AUTO_INCREMENT,
  `id_vuelo` INT NOT NULL,
  `id_pasaje` INT NULL,
  `id_tipoclase` INT NOT NULL,
  `fila` INT NULL,
  `columna` INT NULL,
  PRIMARY KEY (`id_silla`),
  INDEX `fk_SILLA_VUELO1_idx` (`id_vuelo` ASC),
  INDEX `fk_SILLA_CLIENTE1_idx` (`id_pasaje` ASC),
  INDEX `fk_SILLA_TIPOCLASE_idx` (`id_tipoclase` ASC),
  CONSTRAINT `fk_SILLA_VUELO1`
    FOREIGN KEY (`id_vuelo`)
    REFERENCES `Aerolinea`.`VUELO` (`id_vuelo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SILLA_PASAJE1`
    FOREIGN KEY (`id_pasaje`)
    REFERENCES `Aerolinea`.`PASAJE` (`id_pasaje`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SILLA_TIPOCLASE`
    FOREIGN KEY (`id_tipoclase`)
    REFERENCES `Aerolinea`.`TIPOCLASE` (`id_tipoclase`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `Aerolinea`.`PAIS`
-- -----------------------------------------------------
START TRANSACTION;
USE `Aerolinea`;
INSERT INTO `Aerolinea`.`PAIS` (`id_pais`, `nombre`) VALUES (1, 'Colombia');
INSERT INTO `Aerolinea`.`PAIS` (`id_pais`, `nombre`) VALUES (2, 'United States');

COMMIT;


-- -----------------------------------------------------
-- Data for table `Aerolinea`.`CIUDAD`
-- -----------------------------------------------------
START TRANSACTION;
USE `Aerolinea`;
INSERT INTO `Aerolinea`.`CIUDAD` (`id_ciudad`, `id_pais`, `nombre`) VALUES (1, 1, 'Bogota');
INSERT INTO `Aerolinea`.`CIUDAD` (`id_ciudad`, `id_pais`, `nombre`) VALUES (2, 1, 'Medellin');
INSERT INTO `Aerolinea`.`CIUDAD` (`id_ciudad`, `id_pais`, `nombre`) VALUES (3, 2, 'New York');

COMMIT;


-- -----------------------------------------------------
-- Data for table `Aerolinea`.`AEROPUERTO`
-- -----------------------------------------------------
START TRANSACTION;
USE `Aerolinea`;
INSERT INTO `Aerolinea`.`AEROPUERTO` (`id_aeropuerto`, `id_ciudad`, `nombre`, `direccion`, `telefono`, `iata`, `oficina`) VALUES (1, 1, 'El Dorado', 'cr 4545454', '1212455165', 'BOG', '101');
INSERT INTO `Aerolinea`.`AEROPUERTO` (`id_aeropuerto`, `id_ciudad`, `nombre`, `direccion`, `telefono`, `iata`, `oficina`) VALUES (2, 2, 'Olaya Herrera', 'cll 5656 8989', '15465898999', 'OLH', '500');
INSERT INTO `Aerolinea`.`AEROPUERTO` (`id_aeropuerto`, `id_ciudad`, `nombre`, `direccion`, `telefono`, `iata`, `oficina`) VALUES (3, 3, 'Jhon F. Kennedy', '458 Street', '98989898989', 'JFK', '520');

COMMIT;


-- -----------------------------------------------------
-- Data for table `Aerolinea`.`TIPODOCUMENTO`
-- -----------------------------------------------------
START TRANSACTION;
USE `Aerolinea`;
INSERT INTO `Aerolinea`.`TIPODOCUMENTO` (`id_tipodocumento`, `id_pais`, `nombre`) VALUES (1, 1, 'Cedula');
INSERT INTO `Aerolinea`.`TIPODOCUMENTO` (`id_tipodocumento`, `id_pais`, `nombre`) VALUES (2, 2, 'DNI');

COMMIT;


-- -----------------------------------------------------
-- Data for table `Aerolinea`.`CLIENTE`
-- -----------------------------------------------------
START TRANSACTION;
USE `Aerolinea`;
INSERT INTO `Aerolinea`.`CLIENTE` (`id_cliente`, `nombre`, `documento`, `tipodocumento`, `correo`) VALUES (1, 'Ente Del Socorro', '1025688556', 1, 'correo@correo.com');
INSERT INTO `Aerolinea`.`CLIENTE` (`id_cliente`, `nombre`, `documento`, `tipodocumento`, `correo`) VALUES (2, 'Tiago', '1665699988', 1, 'correo2@algo.com');
INSERT INTO `Aerolinea`.`CLIENTE` (`id_cliente`, `nombre`, `documento`, `tipodocumento`, `correo`) VALUES (3, 'Mr. McBuildCraft', '9998755510', 2, 'mrmcbc@op.com');

COMMIT;


-- -----------------------------------------------------
-- Data for table `Aerolinea`.`ITINERARIO`
-- -----------------------------------------------------
START TRANSACTION;
USE `Aerolinea`;
INSERT INTO `Aerolinea`.`ITINERARIO` (`id_itinerario`, `preciocompleto`) VALUES (1, 5000000);

COMMIT;


-- -----------------------------------------------------
-- Data for table `Aerolinea`.`AVION`
-- -----------------------------------------------------
START TRANSACTION;
USE `Aerolinea`;
INSERT INTO `Aerolinea`.`AVION` (`id_avion`, `nombre`, `capacidad`, `filas`, `columnas`) VALUES (1, 'HTC-5220', 300, 30, 10);
INSERT INTO `Aerolinea`.`AVION` (`id_avion`, `nombre`, `capacidad`, `filas`, `columnas`) VALUES (2, 'JKH-201', 10, 5, 2);
INSERT INTO `Aerolinea`.`AVION` (`id_avion`, `nombre`, `capacidad`, `filas`, `columnas`) VALUES (3, 'Premium PTC-9875', 500, 50, 10);

COMMIT;


-- -----------------------------------------------------
-- Data for table `Aerolinea`.`VUELO`
-- -----------------------------------------------------
START TRANSACTION;
USE `Aerolinea`;
INSERT INTO `Aerolinea`.`VUELO` (`id_vuelo`, `id_avion`, `id_itinerario`, `precio`, `desde`, `hasta`, `millas`) VALUES (1, 1, 1, 3000000, 1, 2, 50);
INSERT INTO `Aerolinea`.`VUELO` (`id_vuelo`, `id_avion`, `id_itinerario`, `precio`, `desde`, `hasta`, `millas`) VALUES (2, 2, 1, 2000000, 2, 3, 180);

COMMIT;


-- -----------------------------------------------------
-- Data for table `Aerolinea`.`PASAJE`
-- -----------------------------------------------------
START TRANSACTION;
USE `Aerolinea`;
INSERT INTO `Aerolinea`.`PASAJE` (`id_pasaje`, `id_itinerario`, `id_cliente`, `pagado`) VALUES (1, 1, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `Aerolinea`.`SOCIO`
-- -----------------------------------------------------
START TRANSACTION;
USE `Aerolinea`;
INSERT INTO `Aerolinea`.`SOCIO` (`id_socio`, `id_cliente`, `usuario`, `contrasena`, `millas`) VALUES (1, 3, 'mc', 'asd456asd', 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `Aerolinea`.`TIPOCLASE`
-- -----------------------------------------------------
START TRANSACTION;
USE `Aerolinea`;
INSERT INTO `Aerolinea`.`TIPOCLASE` (`id_tipoclase`, `nombre`, `mutiplicadorprecio`) VALUES (1, 'Turista', 1);
INSERT INTO `Aerolinea`.`TIPOCLASE` (`id_tipoclase`, `nombre`, `mutiplicadorprecio`) VALUES (2, 'Ejecutiva', 1.5);
INSERT INTO `Aerolinea`.`TIPOCLASE` (`id_tipoclase`, `nombre`, `mutiplicadorprecio`) VALUES (3, 'Especial', 0.78);

COMMIT;


-- -----------------------------------------------------
-- Data for table `Aerolinea`.`SILLA`
-- -----------------------------------------------------
START TRANSACTION;
USE `Aerolinea`;
INSERT INTO `Aerolinea`.`SILLA` (`id_silla`, `id_vuelo`, `id_pasaje`, `id_tipoclase`, `fila`, `columna`) VALUES (1, 1, 1, 1, 1, 1);
INSERT INTO `Aerolinea`.`SILLA` (`id_silla`, `id_vuelo`, `id_pasaje`, `id_tipoclase`, `fila`, `columna`) VALUES (2, 1, NULL, 1, 1, 2);
INSERT INTO `Aerolinea`.`SILLA` (`id_silla`, `id_vuelo`, `id_pasaje`, `id_tipoclase`, `fila`, `columna`) VALUES (3, 1, NULL, 1, 1, 3);
INSERT INTO `Aerolinea`.`SILLA` (`id_silla`, `id_vuelo`, `id_pasaje`, `id_tipoclase`, `fila`, `columna`) VALUES (4, 1, NULL, 2, 2, 1);
INSERT INTO `Aerolinea`.`SILLA` (`id_silla`, `id_vuelo`, `id_pasaje`, `id_tipoclase`, `fila`, `columna`) VALUES (5, 2, NULL, 3, 1, 1);
INSERT INTO `Aerolinea`.`SILLA` (`id_silla`, `id_vuelo`, `id_pasaje`, `id_tipoclase`, `fila`, `columna`) VALUES (6, 2, NULL, 3, 1, 2);

COMMIT;
