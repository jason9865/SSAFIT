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

INSERT INTO video (video_id, title, channel_title, description, part) 
VALUES 
("T-bVqdhqW2U", "🔥출렁이는 팔뚝살🔥빨리 빼려면 1달만 이 루틴하세요. (팔뚝살빼는운동/팔뚝살 빨리 빼는법/팔뚝살 완전 제거 운동)", "비타민신지니 VitaminJINY", "팔뚝살 #팔뚝살빼는운동 #팔뚝살빨리빼는법 팔뚝살빼는운동 2탄 https://www.youtube.com/watch?v=l8KA8dgbUd0&t=155s ...", "팔"),
("u47s1pxeEZU", "팔운동을 하면 벌어지는 일!?", "보통사람을 위한 운동채널", "크고 강한 팔근육 만드는 꿀팁이 가득한 [풀영상] : https://www.youtube.com/watch?v=AfHZGfbePsE ▷ '보통사람'이 바로 따라할 수 ...", "팔"),
("vNmvIF5j0no", "두꺼운 팔을 위한 삼두 운동 딱 &#39;네 가지&#39; (크리스 범스테드 팔 운동 루틴 #1 | 이런걸 알고 해야 두꺼워집니다)", "알고하는 헬스", "팔 두꺼워지는 운동 찾고 계시죠? 크리스 범스테드는 무슨 팔운동을 어떻게 해서 팔이 두꺼워졌을까요? 오늘은 크리스 범스테드의 팔 ...", "팔"),
("URVOrAxBDq0", "[ENG] 🏅요일별운동🏅 금요일 형님 조직으로 돌아오십쇼.. 어깨/팔 운동", "힙으뜸", "으뜸챌린지 #여자어깨운동 #상체근력운동.", "팔"),
("N1ZpbAWvRpw", "💪🏻정말 안빠지는 두툼한 팔뚝살 🔥1달만에 -7cm 없애는🔥 효과 보장 루틴 ( * 하루에 딱 8분만 투자하세요 )", "에이핏 afit", "이 영상은 유료광고가 없습니다 ❌ 오늘 운동은 난이도가 그렇게 많이 높지 않기 때문에 하루 중 아무때나 해주셔도 정말 괜찮은 운동 ...", "팔"),
("JsF2nzetLBU", "👑조회수 ‘8,000,000’ 찍은👑전설의 팔뚝살빼는운동 7분 리뉴얼 버전", "비타민신지니 VitaminJINY", "팔뚝살빼는운동 #팔뚝살완전제거운동 #tonedarmsworkout ...", "팔"),
("7FtObDQ1D-U", "팔이 안 커지는 5가지 이유 [feat. 팔 운동 루틴]", "세계적으로 유명한 운동 정보", "이두 #삼두 #팔운동루틴 이번 영상은 팔 운동에 진심인 제프형이 새롭게 설계한 팔 운동 루틴을 알아보겠습니다! [참고자료] -5 Steps ...", "팔"),
("KQwYFHWCHco", "짧고 굵게⚠️ 5분만에 탄탄한 팔근육 만드는 운동 (NO쉬는시간)", "에일린 mind yoga", "짧굵 시리즈 1탄, 팔운동편. NO BREAK! 5분동안 빠르게 달리는   여자 팔근육 만들기 루틴이에요. 출렁이는 팔뚝살은 모두 제거하는 ...", "팔"),
("KaWNR0Rzydg", "1번만 따라해도 ‘팔뚝살’이 금방 얇아지는 ‘역대급 팔뚝살 돌려깎기’ (급속버전🔥)", "비타민신지니 VitaminJINY", "팔뚝살빼는운동 #팔뚝살빼기 #팔뚝살 ✓이쁜 민소매를 입고 싶은데 팔뚝이 두꺼워서 라인이 안이쁘다거나! ✓아무리 운동해도 팔뚝 ...", "팔"),
("qkQdIMW1xlw", "Lv.2 꽉! 차는 머슬핏 만드는 팔 루틴 (이두,삼두) 덤벨필요", "권혁TV", "권혁이 직접 짜준 식단*권혁이 직접 짜준 식단 및 운동루틴* https://hulktv.imweb.me/. * 닭가슴살 최저가 미트리 ...", "팔")
;
commit;