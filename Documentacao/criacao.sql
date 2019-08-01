-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema tads17_gabriel
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema tads17_gabriel
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `tads17_gabriel` DEFAULT CHARACTER SET utf8 ;
USE `tads17_gabriel` ;

-- -----------------------------------------------------
-- Table `tads17_gabriel`.`pi_curso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tads17_gabriel`.`pi_curso` (
  `id` INT NOT NULL,
  `nome` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tads17_gabriel`.`pi_aluno`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tads17_gabriel`.`pi_aluno` (
  `matricula` BIGINT NOT NULL,
  `curso_id` INT NOT NULL,
  `nome` VARCHAR(255) NOT NULL,
  `turma` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`matricula`, `curso_id`),
  INDEX `fk_pi_aluno_pi_curso_idx` (`curso_id` ASC),
  CONSTRAINT `fk_pi_aluno_pi_curso`
    FOREIGN KEY (`curso_id`)
    REFERENCES `tads17_gabriel`.`pi_curso` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tads17_gabriel`.`pi_carteirinha`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tads17_gabriel`.`pi_carteirinha` (
  `aluno_matricula` BIGINT NOT NULL,
  `validade` DATE NOT NULL,
  `data_de_emissao` DATE NOT NULL,
  `via` INT NOT NULL,
  PRIMARY KEY (`aluno_matricula`),
  CONSTRAINT `fk_pi_carteirinha_pi_aluno1`
    FOREIGN KEY (`aluno_matricula`)
    REFERENCES `tads17_gabriel`.`pi_aluno` (`matricula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
