CREATE TABLE IF NOT EXISTS `video` (
	`id` INT NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(200) NULL,
    `part` VARCHAR(20) NULL,
    `channel_name` VARCHAR(50) NULL,
    `url` VARCHAR(2048) NULL,
    `view_cnt` INT NULL DEFAULT 0,
    `created_at` DATETIME NULL DEFAULT NOW(),
    PRIMARY KEY (`id`)
);

INSERT INTO video (title,part,channel_name,url) 
VALUES 
("전신 다이어트 최고의 운동 [칼소폭 찐 핵핵매운맛]","전신","ThankyouBUBU","https://www.youtube.com/embed/gMaB-fG4u4g"),
("하루 15분! 전신 칼로리 불태우는 다이어트 운동","전신","ThankyouBUBU", "https://www.youtube.com/embed/swRNeYw1JkY"),
("상체 다이어트 최고의 운동 BEST [팔뚝살/겨드랑이살/등살/가슴어깨라인]","상체","ThankyouBUBU", "https://www.youtube.com/embed/54tTYO-vU2E"),
("상체비만 다이어트 최고의 운동 [상체 핵매운맛]","상체","ThankyouBUBU", "https://www.youtube.com/embed/QqqZH3j_vH0"),
("하체운동이 중요한 이유? 이것만 보고 따라하자 ! [하체운동 교과서]","하체","김강민", "https://www.youtube.com/embed/tzN6ypk6Sps"),
("저는 하체 식주의자 입니다","하체","GYM종국", "https://www.youtube.com/embed/u5OgcZdNbMo"),
("11자복근 복부 최고의 운동 [복근 핵매운맛]","복부","ThankyouBUBU", "https://www.youtube.com/embed/PjGcOP-TQPE"),
("(Sub)누워서하는 5분 복부운동!! 효과보장! (매일 2주만 해보세요!)","복부","SomiFit", "https://www.youtube.com/embed/7TLk7pscICk")
;

