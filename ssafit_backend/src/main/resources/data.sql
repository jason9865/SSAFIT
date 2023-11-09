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

INSERT INTO article (article_id,title,content,board_id,user_seq,created_at)
VALUES 
(1,"안녕하세요","반갑습니다",1,1,now()),
(2,"안녕하세요2","반갑습니다2",1,1,now()),
(3,"안녕하세요3","반갑습니다3",1,1,now()),
(4,"공지사항","공지사항",2,1,now());
commit;

INSERT INTO comment (comment_id,content,user_seq,article_id,board_id,created_at)
VALUES
(1,"댓글1",2,1,1,now()),
(2,"댓글2",2,1,1,now()),
(3,"댓글3",2,1,1,now()),
(4,"댓글4",2,4,2,now());
commit;

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
commit;