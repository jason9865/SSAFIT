
-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `user` (
  `user_seq` INT NOT NULL AUTO_INCREMENT,
  `id` VARCHAR(16) NOT NULL,
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
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(250) NULL,
  `description` TEXT NULL,
  `created_at` DATETIME NULL default now(),
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `article`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `article` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(200) NULL,
  `content` TEXT NULL,
  `board_id` INT NOT NULL,
  `user_seq` INT NOT NULL,
  `view_cnt` INT NULL,
  `lft` INT NULL,
  `rgt` INT NULL,
  `depth` INT NULL,
  `created_at` DATETIME NULL DEFAULT NOW(),
  `modified_at` DATETIME NULL DEFAULT NOW(),
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_article_board`
    FOREIGN KEY (`board_id`)
    REFERENCES `board` (`id`)
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
  `id` INT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(400) NULL,
  `user_seq` INT NOT NULL,
  `article_id` INT NOT NULL,
  `board_id` INT NOT NULL,
  `lft` INT NULL,
  `rgt` INT NULL,
  `depth` INT NULL,
  `created_at` DATETIME NULL DEFAULT NOW(),
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_comment_article`
    FOREIGN KEY (`article_id`)
    REFERENCES `article` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_board`
    FOREIGN KEY (`board_id`)
    REFERENCES `board` (`id`)
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
  `id` INT NOT NULL AUTO_INCREMENT,
  `originfile` VARCHAR(300) NULL,
  `savefolder` VARCHAR(300) NULL,
  `savefile` VARCHAR(300) NULL,
  `article_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_file_article`
    FOREIGN KEY (`article_id`)
    REFERENCES `article` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `article_like`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `article_like` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_seq` INT NOT NULL,
  `article_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_article_like_user`
    FOREIGN KEY (`user_seq`)
    REFERENCES `user` (`user_seq`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_article_like_article`
    FOREIGN KEY (`article_id`)
    REFERENCES `article` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comment_like`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `comment_like` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_seq` INT NOT NULL,
  `comment_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_comment_like_comment`
    FOREIGN KEY (`comment_id`)
    REFERENCES `comment` (`id`)
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
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_seq` INT NOT NULL,
  `comment_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_comment_dislike_user`
    FOREIGN KEY (`user_seq`)
    REFERENCES `user` (`user_seq`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_dislike_comment`
    FOREIGN KEY (`comment_id`)
    REFERENCES `comment` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `article_dislike`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `article_dislike` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_seq` INT NOT NULL,
  `article_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_article_dislike_user`
    FOREIGN KEY (`user_seq`)
    REFERENCES `user` (`user_seq`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_article_dislike_article`
    FOREIGN KEY (`article_id`)
    REFERENCES `article` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO user VALUES 
(1,'kimssafy','123','김싸피','김닉','kimssafy@email.com',1,now()),
(2,'leessafy','123','이싸피','이닉','leessafy@email.com',1,now()),
(3,'parkssafy','123','박싸피','박닉','parkssafy@email.com',1,now()),
(4,'choissafy','123','최싸피','최닉','choissafy@email.com',1,now()),
(5,'jeongssafy','123','정싸피','정닉','jeongssafy@email.com',1,now()),
(6,'kangssafy','123','강싸피','강닉','kangssafy@email.com',1,now()),
(7,'jossafy','123','조싸피','조닉','jossafy@email.com',1,now()),
(8,'yoonssafy','123','윤싸피','윤닉','yoonssafy@email.com',1,now()),
(9,'jangssafy','123','장싸피','장닉','jangssafy@email.com',1,now()),
(10,'limssafy','123','임싸피','임닉','limssafy@email.com',1,now()),
(11,'ssafy','1234','관리자','관리자','ssafy@ssafy.com',2,now());
commit;

INSERT INTO board VALUES
(1,"자유게시판","자유게시판",now()),
(2,"공지사항","공지사항",now());
commit;

INSERT INTO article (id,title,content,board_id,user_seq,created_at)
VALUES 
(1,"안녕하세요","반갑습니다",1,1,now()),
(2,"안녕하세요2","반갑습니다2",1,1,now()),
(3,"안녕하세요3","반갑습니다3",1,1,now()),
(4,"공지사항","공지사항",2,1,now());
commit;

INSERT INTO comment (id,content,user_seq,article_id,board_id,created_at)
VALUES
(1,"댓글1",2,1,1,now()),
(2,"댓글2",2,1,1,now()),
(3,"댓글3",2,1,1,now()),
(4,"댓글4",2,4,2,now());
commit;