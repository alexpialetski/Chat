SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `chat`;
CREATE SCHEMA IF NOT EXISTS `chat` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `chat`;

-- -----------------------------------------------------
-- Table `chat`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `chat`.`role`;

CREATE TABLE `chat`.`role`
(
  `id`        TINYINT     NOT NULL,
  `role_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `chat`.`status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `chat`.`status`;

CREATE TABLE `chat`.`status`
(
  `id`        TINYINT     NOT NULL,
  `statusRu`  VARCHAR(45) NOT NULL,
  `statusEng` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `chat`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `chat`.`User`;

CREATE TABLE IF NOT EXISTS `chat`.`user`
(
  `id`        INT(11)      NOT NULL AUTO_INCREMENT,
  `email`     VARCHAR(255) NOT NULL,
  `password`  VARCHAR(32)  NOT NULL,
  `status_id` TINYINT(4)   NOT NULL DEFAULT 1,
  `role_id`   TINYINT(4)   NOT NULL,
  PRIMARY KEY (`id`, `role_id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  INDEX `fk_user_role1_idx` (`role_id` ASC) VISIBLE,
  INDEX `fk_user_status_idx` (status_id ASC) VISIBLE,
  CONSTRAINT `fk_user_role1`
    FOREIGN KEY (`role_id`)
      REFERENCES `chat`.`role` (`id`)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_status`
    FOREIGN KEY (`status_id`)
      REFERENCES `chat`.`status` (`id`)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `chat`.`Client`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `chat`.`Client`;

CREATE TABLE IF NOT EXISTS `chat`.`Client`
(
  `id`          INT(11)      NOT NULL AUTO_INCREMENT,
  `first_name`  VARCHAR(45)  NOT NULL,
  `last_name`   VARCHAR(45)  NOT NULL,
  `about`       VARCHAR(100) NOT NULL DEFAULT '-',
  `User_idUser` INT(11)      NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `User_idUser_idx` (`User_idUser` ASC) VISIBLE,
  CONSTRAINT `fk_Client_User`
    FOREIGN KEY (`User_idUser`)
      REFERENCES `chat`.`user` (`id`)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `chat`.`conversation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `chat`.`conversation`;

CREATE TABLE IF NOT EXISTS `chat`.`conversation`
(
  `id`            INT(11)      NOT NULL AUTO_INCREMENT,
  `name`          VARCHAR(100) NOT NULL DEFAULT 'NAMELESS',
  `date_creation` DATE         NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `chat`.`conversation_group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `chat`.`conversation_group`;

CREATE TABLE IF NOT EXISTS `chat`.`conversation_group`
(
  `id`                          INT(11) NOT NULL AUTO_INCREMENT,
  `Conversation_idConversation` INT(11) NOT NULL,
  `User_idUser`                 INT(11) NOT NULL,
  PRIMARY KEY (`id`, `Conversation_idConversation`),
  INDEX `fk_conversation_group_conversation_idx` (`Conversation_idConversation` ASC) VISIBLE,
  INDEX `fk_conversation_group_user1_idx` (`User_idUser` ASC) VISIBLE,
  CONSTRAINT `fk_conversation_group_conversation`
    FOREIGN KEY (`Conversation_idConversation`)
      REFERENCES `chat`.`conversation` (`id`)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  CONSTRAINT `fk_conversation_group_user1`
    FOREIGN KEY (`User_idUser`)
      REFERENCES `chat`.`user` (`id`)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `chat`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `chat`.`friend`;

CREATE TABLE IF NOT EXISTS `chat`.`friend`
(
  `id`                 INT(11) NOT NULL AUTO_INCREMENT,
  `User_idUser`        INT(11) NOT NULL,
  `User_idUser_friend` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_friend_user_idx` (`User_idUser` ASC) VISIBLE,
  INDEX `fk_friend_user_friend_idx` (`User_idUser_friend` ASC) VISIBLE,
  CONSTRAINT `fk_friend_user1`
    FOREIGN KEY (`User_idUser`)
      REFERENCES `chat`.`user` (`id`)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  CONSTRAINT `fk_friend_user1_friend`
    FOREIGN KEY (`User_idUser_friend`)
      REFERENCES `chat`.`user` (`id`)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `chat`.`message`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chat`.`message`
(
  `id`                          INT(11)      NOT NULL AUTO_INCREMENT,
  `text`                        VARCHAR(250) NOT NULL,
  `Conversation_idConversation` INT(11)      NOT NULL,
  `User_idUser`                 INT(11)      NOT NULL,
  `creation_date_time`          DATETIME     NOT NULL,
  `important`                   TINYINT DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `fk_message_user1_idx` (`User_idUser` ASC) VISIBLE,
  INDEX `fk_message_conversation1_idx` (`Conversation_idConversation` ASC) VISIBLE,
  CONSTRAINT `fk_message_conversation1`
    FOREIGN KEY (`Conversation_idConversation`)
      REFERENCES `chat`.`conversation` (`id`),
  CONSTRAINT `fk_message_user1`
    FOREIGN KEY (`User_idUser`)
      REFERENCES `chat`.`user` (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARACTER SET
    = utf8;

-- -----------------------------------------------------
-- Table `chat`.`new_message`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chat`.`new_message`
(
  `id`                          INT(11) NOT NULL AUTO_INCREMENT,
  `Conversation_idConversation` INT(11) NOT NULL,
  `Message_idMessage`           INT(11) NOT NULL,
  `User_idUser`                 INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_message_user_idx` (`User_idUser` ASC) VISIBLE,
  INDEX `fk_message_conversation1_idx` (`Conversation_idConversation` ASC) VISIBLE,
  INDEX `fk_message_message_idx` (`Message_idMessage` ASC) VISIBLE,
  CONSTRAINT `fk_new_message_conversation1`
    FOREIGN KEY (`Conversation_idConversation`)
      REFERENCES `chat`.`conversation` (`id`),
  CONSTRAINT `fk_new_message_user1`
    FOREIGN KEY (`User_idUser`)
      REFERENCES `chat`.`user` (`id`),
  CONSTRAINT `fk_new_message_message`
    FOREIGN KEY (`Message_idMessage`)
      REFERENCES `chat`.`message` (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARACTER SET = utf8;

# -- -----------------------------------------------------
# -- Table `chat`.`important`
# -- -----------------------------------------------------
# CREATE TABLE IF NOT EXISTS `chat`.`important`
# (
#   `id`                INT(11) NOT NULL AUTO_INCREMENT,
#   `User_idUser`       INT(11) NOT NULL,
#   `Message_idMessage` INT(11) NOT NULL,
#   PRIMARY KEY (`id`, `Message_idMessage`),
#   INDEX `fk_conversation_group_conversation_idx` (`Message_idMessage` ASC) VISIBLE,
#   INDEX `fk_conversation_group_user1_idx` (`User_idUser` ASC) VISIBLE,
#   CONSTRAINT `fk_important_conversation_conversation`
#     FOREIGN KEY (`Message_idMessage`)
#       REFERENCES `chat`.`message` (`id`)
#       ON DELETE NO ACTION
#       ON UPDATE NO ACTION,
#   CONSTRAINT `fk_important_message_user1`
#     FOREIGN KEY (`User_idUser`)
#       REFERENCES `chat`.`user` (`id`)
#       ON DELETE NO ACTION
#       ON UPDATE NO ACTION
# )
#   ENGINE = InnoDB
#   AUTO_INCREMENT = 118
#   DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `chat`.`mail`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `chat`.`mail`;

CREATE TABLE `chat`.`mail`
(
  `id`     INT         NOT NULL AUTO_INCREMENT,
  `mailId` VARCHAR(45) NOT NULL,
  `key`    VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `mailId_UNIQUE` (`mailId` ASC) VISIBLE
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COLLATE = utf8_bin;