USE ssafit_db;
-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `user` (
  `user_seq` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(16) NOT NULL,
  `user_pwd` VARCHAR(40) NOT NULL,
  `user_name` VARCHAR(40) NULL,
  `nickname` VARCHAR(40) NULL,
  `email` VARCHAR(50) NULL,
  `user_rank` INT NULL default 1,
  `joined_at` DATETIME NULL DEFAULT now(),
  PRIMARY KEY (`user_seq`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `board`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `board` (
  `board_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(250) NULL,
  `description` TEXT NULL,
  `created_at` DATETIME NULL default now(),
  PRIMARY KEY (`board_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `article`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `article` (
  `article_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(200) NULL,
  `content` TEXT NULL,
  `board_id` INT NOT NULL,
  `user_seq` INT NOT NULL,
  `view_cnt` INT NOT NULL DEFAULT 0,
  `created_at` DATETIME NULL DEFAULT NOW(),
  `modified_at` DATETIME NULL DEFAULT NOW(),
  PRIMARY KEY (`article_id`),
  CONSTRAINT `fk_article_board`
    FOREIGN KEY (`board_id`)
    REFERENCES `board` (`board_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_article_user`
    FOREIGN KEY (`user_seq`)
    REFERENCES `user` (`user_seq`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comment`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `comment` (
  `comment_id` INT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(400) NULL,
  `user_seq` INT NOT NULL,
  `article_id` INT NOT NULL,
  `board_id` INT NOT NULL,
  `created_at` DATETIME NULL DEFAULT NOW() ,
  PRIMARY KEY (`comment_id`),
  CONSTRAINT `fk_comment_article`
    FOREIGN KEY (`article_id`)
    REFERENCES `article` (`article_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_board`
    FOREIGN KEY (`board_id`)
    REFERENCES `board` (`board_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_user`
    FOREIGN KEY (`user_seq`)
    REFERENCES `user` (`user_seq`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `file`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `file` ;

CREATE TABLE IF NOT EXISTS `file` (
  `file_id` INT NOT NULL AUTO_INCREMENT,
  `originfile` VARCHAR(300) NULL,
  `savefolder` VARCHAR(300) NULL,
  `savefile` VARCHAR(300) NULL,
  `article_id` INT NOT NULL,
  PRIMARY KEY (`file_id`),
  CONSTRAINT `fk_file_article`
    FOREIGN KEY (`article_id`)
    REFERENCES `article` (`article_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `article_like`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `article_like` (
  `article_like_id` INT NOT NULL AUTO_INCREMENT,
  `user_seq` INT NOT NULL,
  `article_id` INT NOT NULL,
  PRIMARY KEY (`article_like_id`),
  CONSTRAINT `fk_article_like_user`
    FOREIGN KEY (`user_seq`)
    REFERENCES `user` (`user_seq`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_article_like_article`
    FOREIGN KEY (`article_id`)
    REFERENCES `article` (`article_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comment_like`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `comment_like` (
  `comment_like_id` INT NOT NULL AUTO_INCREMENT,
  `user_seq` INT NOT NULL,
  `comment_id` INT NOT NULL,
  PRIMARY KEY (`comment_like_id`),
  CONSTRAINT `fk_comment_like_comment`
    FOREIGN KEY (`comment_id`)
    REFERENCES `comment` (`comment_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_like_user`
    FOREIGN KEY (`user_seq`)
    REFERENCES `user` (`user_seq`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comment_dislike`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `comment_dislike` (
  `comment_dislike_id` INT NOT NULL AUTO_INCREMENT,
  `user_seq` INT NOT NULL,
  `comment_id` INT NOT NULL,
  PRIMARY KEY (`comment_dislike_id`),
  CONSTRAINT `fk_comment_dislike_user`
    FOREIGN KEY (`user_seq`)
    REFERENCES `user` (`user_seq`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_dislike_comment`
    FOREIGN KEY (`comment_id`)
    REFERENCES `comment` (`comment_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `article_dislike`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `article_dislike` (
  `article_dislike_id` INT NOT NULL AUTO_INCREMENT,
  `user_seq` INT NOT NULL,
  `article_id` INT NOT NULL,
  PRIMARY KEY (`article_dislike_id`),
  CONSTRAINT `fk_article_dislike_user`
    FOREIGN KEY (`user_seq`)
    REFERENCES `user` (`user_seq`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_article_dislike_article`
    FOREIGN KEY (`article_id`)
    REFERENCES `article` (`article_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table `reply`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `reply` (
	`reply_id` INT NOT NULL AUTO_INCREMENT,
    `content` VARCHAR(400) NULL,
    `user_seq` INT NOT NULL,
    `comment_id` INT NOT NULL,
    `created_at` DATETIME NULL DEFAULT NOW(),
    PRIMARY KEY (`reply_id`),
    CONSTRAINT `fk_reply_user`
	  FOREIGN KEY(`user_seq`)
      REFERENCES `user`(`user_seq`)
      ON DELETE CASCADE
      ON UPDATE NO ACTION,
	CONSTRAINT `fk_reply_comment`
      FOREIGN KEY(`comment_id`)
      REFERENCES `comment` (`comment_id`)
      ON DELETE CASCADE
      ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `video`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `video` (
	`video_id` VARCHAR(100) NOT NULL,
    `title` VARCHAR(200) NULL,
    `channel_title` VARCHAR(50) NULL,
    `description` VARCHAR(200) NULL,
    `part` VARCHAR(20) NULL,
    `view_cnt` INT NULL DEFAULT 0,
    PRIMARY KEY (`video_id`)
)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `video`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `video_like` (
	`video_like_id` INT NOT NULL AUTO_INCREMENT,
    `video_id` VARCHAR(100) NOT NULL,
    `user_seq` INT NOT NULL,
    PRIMARY KEY (`video_like_id`),
    CONSTRAINT `fk_video_id`
		FOREIGN KEY (`video_id`)
        REFERENCES `video` (`video_id`)
        ON DELETE CASCADE
        ON UPDATE NO ACTION,
	CONSTRAINT `fk_video_like_user_seq`
		FOREIGN KEY (`user_seq`)
        REFERENCES `user`(`user_seq`)
        ON DELETE CASCADE
        ON UPDATE NO ACTION)
ENGINE=InnoDB;
	
-- -----------------------------------------------------
-- Table `follow`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS 	`follow` (
	`follow_id` INT NOT NULL AUTO_INCREMENT,
    `from_user_seq` INT NOT NULL,
    `to_user_seq` INT NOT NULL,
    PRIMARY KEY (`follow_id`),
    CONSTRAINT `fk_from_user_seq`
		FOREIGN KEY (`from_user_seq`)
        REFERENCES `user`(`user_seq`)
        ON DELETE CASCADE
        ON UPDATE NO ACTION,
	CONSTRAINT `fk_to_user_seq`
		FOREIGN KEY (`to_user_seq`)
        REFERENCES `user`(`user_seq`)
		ON DELETE CASCADE
        ON UPDATE NO ACTION
)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `video_comment`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `video_comment` (
	video_comment_id INT NOT NULL AUTO_INCREMENT,
    content VARCHAR(400) NOT NULL,
    user_seq INT NOT NULL,
    video_id VARCHAR(100) NOT NULL,
    created_at DATETIME NULL DEFAULT NOW(),
    PRIMARY KEY (`video_comment_id`),
    CONSTRAINT `fk_video_comment_user_seq`
		FOREIGN KEY (`user_seq`)
        REFERENCES `user`(`user_seq`)
        ON DELETE CASCADE
        ON UPDATE NO ACTION,
	CONSTRAINT `fk_video_comment_video_id`
		FOREIGN KEY (`video_id`)
        REFERENCES `video`(`video_id`)
        ON DELETE CASCADE
        ON UPDATE NO ACTION
)
ENGINE=InnoDB;